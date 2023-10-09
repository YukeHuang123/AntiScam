package com.example.antiscam.act;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.adapter.ChatAdapter;
import com.example.antiscam.bean.ChatModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private LinkedList<ChatModel> chatModels = new LinkedList<>();
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    TextView titleView;
    Toolbar toolbar;
    EditText et;
    Button bt;
    CollectionReference ref;
    FirebaseUser user;
    String email;
    String nick;
    String img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        initConfig();
    }

    private void initConfig() {
        email = getIntent().getStringExtra("email");
        nick = getIntent().getStringExtra("nick");
        img = getIntent().getStringExtra("img");
        user = FirebaseAuth.getInstance().getCurrentUser();
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
                            chatModels.add(chatModel);
                        } else if (Objects.equals(chatModel.getSendUserEmail(), user.getEmail())) {
                            // send
                            chatModel.setType(ChatModel.SEND);
                            chatModels.add(chatModel);
                        }
                    }
                }
                refreshRecycleView();
            }
        });
//        ref.orderBy("timestamp", Query.Direction.ASCENDING)
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            QuerySnapshot snapshots = task.getResult();
//                            Log.i(TAG, "onComplete: ");
//                            for (QueryDocumentSnapshot snapshot : snapshots) {
//                                ChatModel chatModel = snapshot.toObject(ChatModel.class);
//                                if (Objects.equals(chatModel.getSendUserEmail(), email)) {
//                                    // receive
//                                    chatModel.setType(ChatModel.RECEIVE);
//                                    chatModels.add(chatModel);
//                                } else if (Objects.equals(chatModel.getSendUserEmail(), user.getEmail())) {
//                                    // send
//                                    chatModel.setType(ChatModel.SEND);
//                                    chatModels.add(chatModel);
//                                }
//                            }
//                            refreshRecycleView();
//                        }
//                    }
//                });

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
                if (!msg.isEmpty()) {
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

    private void receiveMsg(ChatModel chatModel) {
        if (!Objects.equals(chatModel.getSendUserEmail(), email)) return;
        chatModel.setType(ChatModel.RECEIVE);

    }

    private void refreshRecycleView() {
        if (chatModels.size() < 1) return;
        chatAdapter.notifyItemInserted(chatModels.size() - 1);
        recyclerView.scrollToPosition(chatModels.size() - 1);
    }
}
