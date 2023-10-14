package com.example.antiscam.act;


import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.adapter.ChatAdapter;
import com.example.antiscam.bean.BlockModel;
import com.example.antiscam.bean.ChatModel;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.BlockManager;
import com.example.antiscam.dataclass.ScamCaseDao;
import com.example.antiscam.dataclass.ScamCaseDaoImpl;
import com.example.antiscam.singleton.FirebaseAuthManager;
import com.example.antiscam.tool.AndroidUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    public static final String TAG = "ChatActivity";
    private LinkedList<ChatModel> chatModels = new LinkedList<>();
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    TextView titleView;
    Toolbar toolbar;
    EditText et;
    Button bt;
    CollectionReference ref;
    FirebaseAuthManager firebaseAuthManager = FirebaseAuthManager.getInstance();
    FirebaseUser user;
    String email;
    String nick;
    String img;
    String documentId;
    List<String> documentIds;
    Button blockBtnView;
    Button unblockBtnView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        initConfig();
        initBlock();
    }

    private void initConfig() {
        email = getIntent().getStringExtra("email");
        nick = getIntent().getStringExtra("nick");
        img = getIntent().getStringExtra("img");
        user = firebaseAuthManager.getUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ref = db.collection("chats");

        titleView.setText(email);
        // receiver msg
        ref.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) return;
                Log.i(TAG, "addSnapshotListener: ");
                for (DocumentChange dc : value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        ChatModel chatModel = dc.getDocument().toObject(ChatModel.class);
                        Log.i(TAG, "onEvent: " + chatModel);
                        if (Objects.equals(chatModel.getSendUserEmail(), email)) {
                            // receive
                            chatModel.setType(ChatModel.RECEIVE);
                            chatModel.setReceiveUserName(nick);
                            chatModels.add(chatModel);
                            sortChat(chatModels);
                        } else if (Objects.equals(chatModel.getSendUserEmail(), user.getEmail())) {
                            // send
                            chatModel.setType(ChatModel.SEND);
                            chatModels.add(chatModel);
                            sortChat(chatModels);
                        }
                    }
                }

                documentIds = BlockManager.getDocumentId("blocked", email);

                refreshRecycleView();
            }
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.chat_recycler);
        et = findViewById(R.id.chat_et);
        bt = findViewById(R.id.chat_bt);
        toolbar = findViewById(R.id.chat_toolbar);
        titleView = findViewById(R.id.chat_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayout);
        chatAdapter = new ChatAdapter(chatModels);
        recyclerView.setAdapter(chatAdapter);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = et.getText().toString();
                boolean isBlocked = getIntent().getBooleanExtra("isBlockedByAuthUser", false);
                if (isBlocked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);
                    builder.setMessage("You have been blocked by the other party and cannot send messages.").setPositiveButton("Understood", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // According to scam_id, search for document id and then delete the document
                        }
                    });
                    AlertDialog mDialog = builder.create();
                    mDialog.show();
                } else if (!msg.isEmpty()) {
                    sendMsg(msg);
                    et.getText().clear();
                } else {
                    Toast.makeText(ChatActivity.this, "Message is empty!", Toast.LENGTH_LONG).show();
                }
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sortChat(List<ChatModel> chatModels) {
        Collections.sort(chatModels, new Comparator<ChatModel>() {
            @Override
            public int compare(ChatModel o1, ChatModel o2) {
                return Long.compare(o1.getSendTime(), o2.getSendTime());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendMsg(String msg) {
        ChatModel chatModel = new ChatModel(user.getEmail(), String.valueOf(user.getPhotoUrl()), user.getDisplayName(),
                email, img, nick, msg, ChatModel.SEND);
        ref.add(chatModel);
    }

    private void refreshRecycleView() {
        if (chatModels.size() < 1) return;
        chatAdapter.notifyItemInserted(chatModels.size() - 1);
        recyclerView.scrollToPosition(chatModels.size() - 1);
    }

    private void initBlock() {
        // Find block and unblock button
        blockBtnView = findViewById(R.id.blockBtn);
        unblockBtnView = findViewById(R.id.unblockBtn);
        boolean isBlockedByAuthUser = getIntent().getBooleanExtra("isBlockedByAuthUser", false);
        if (isBlockedByAuthUser) {
            //
            blockBtnView.setVisibility(View.GONE);
            unblockBtnView.setVisibility(View.VISIBLE);
        } else {
            blockBtnView.setVisibility(View.VISIBLE);
            unblockBtnView.setVisibility(View.GONE);
        }
        unblockBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                if (!documentIds.isEmpty()) {
                    documentId = documentIds.get(0);
                }
                FirebaseFirestore.getInstance().collection("block").document(documentId)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                unblockBtnView.setVisibility(View.GONE);
                                blockBtnView.setVisibility(View.VISIBLE);
                                AndroidUtil.showToast(getApplicationContext(), "Successfully unblocked");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener(){
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error deleting document", e);
                                AndroidUtil.showToast(getApplicationContext(), "Unblock failed");
                            }
                        });
            }
        });
        blockBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockUser();
            }
        });
    }

    void blockUser() {
//        BlockModel blockModel = new BlockModel(user.getEmail(), email);
        Map<String, Object> blockRecords = new HashMap<>();
        blockRecords.put("blocker", user.getEmail());
        blockRecords.put("blocked", email);
        FirebaseFirestore.getInstance().collection("block")
                .add(blockRecords)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AndroidUtil.showToast(getApplicationContext(), "Successfully blocked");
                        blockBtnView.setVisibility(View.GONE);
                        unblockBtnView.setVisibility(View.VISIBLE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        AndroidUtil.showToast(getApplicationContext(), "Block failed");
                    }
                });
    }
}
