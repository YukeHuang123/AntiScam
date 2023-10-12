package com.example.antiscam.act;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.History;
import com.example.antiscam.R;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.adapter.ScamCaseCardProfileAdapter;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.ScamCaseDao;
import com.example.antiscam.dataclass.ScamCaseDaoImpl;
import com.example.antiscam.dataclass.ScamCaseUserCombine;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AndroidUtil;
import com.example.antiscam.tool.AuthUtils;
import com.example.antiscam.tool.CacheToFile;
import com.example.antiscam.tool.DataLoadCallback;
import com.example.antiscam.tool.LRUCache;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private RecyclerView recyclerViewProfile;
    private ScamCaseCardProfileAdapter cardAdapterProfile;
    private Button signOutButton;
    private String username;
    private String email;
    private String userAvatarPath;
    private String authUserEmail;
//    String documentId;
    ProgressBar progressBar;
    private LRUCache<String, ScamCaseWithUser> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getIntentForProfile();
        initPersonalInfo();
        initPosts();
        initBtn();
    }

    protected void initPosts() {
        // scam case list
        List<ScamCaseWithUser> scamCaseWithUserList = new ArrayList<>();
        cardAdapterProfile = new ScamCaseCardProfileAdapter(scamCaseWithUserList, R.layout.profile_cardlist, email);

        ScamCaseUserCombine.loadScamCases(new DataLoadCallback() {
            @Override
            public void onDataLoaded(List<ScamCaseWithUser> scamCaseWithUserList) {
                List<ScamCaseWithUser> authUserScamCases = new ArrayList<>();
                for (ScamCaseWithUser scamCaseWithUser : scamCaseWithUserList){
                    if (email.equals(scamCaseWithUser.getUser().getEmail())){
                        authUserScamCases.add(scamCaseWithUser);
                    }
                }
                cardAdapterProfile.setData(authUserScamCases);
            }
        });

        // Scam case card click listener
        cardAdapterProfile.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCaseWithUser) {
                Intent intentCaseDetail = new Intent(Profile.this, CaseDetail.class);
                intentCaseDetail.putExtra("scamCaseWithUser", scamCaseWithUser);
                intentCaseDetail.putExtra("fromPage","userProfile");
                startActivity(intentCaseDetail);
            }
        });

        cardAdapterProfile.setOnDelBtnClickListener(new ScamCaseCardProfileAdapter.OnDelBtnClickListener() {
            @Override
            public void onDelBtnClick(int position, ScamCaseWithUser scamCaseWithUser) {
//                setInProgress(true);
//                AndroidUtil.showToast(getApplicationContext(), "delete button clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
                builder.setMessage("Are you sure to delete post?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // According to scam_id, search for document id and then delete the document
                        int scamCaseId = scamCaseWithUser.getScamCase().getScam_id();

                        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
                        scamCaseDaoImpl.getDocumentId(scamCaseId, new ScamCaseDao.OnDocumentIdCallback() {
                            @Override
                            public void onDocumentIdReceived(String documentId) {
                                deletePost(documentId);
                            }
                            @Override
                            public void onDocumentIdNotFound() {
                                AndroidUtil.showToast(getApplicationContext(), "No such post");
                            }
                        });
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });
                AlertDialog mDialog = builder.create();
                mDialog.show();
            }
        });

        // Initialize recyclerView
        recyclerViewProfile = findViewById(R.id.recyclerViewProfile);
        recyclerViewProfile.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewProfile.setLayoutManager(layoutManager);
        recyclerViewProfile.setAdapter(cardAdapterProfile);
    }

    void getIntentForProfile(){
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
            email = intent.getStringExtra("email");
            userAvatarPath = intent.getStringExtra("avatarPath");
        }
    }

    void initPersonalInfo(){
        // Get user information
        TextView userNameView = findViewById(R.id.userNamePro);
        ImageView userAvatarView = findViewById(R.id.avatarImgViewPro);
        authUserEmail = UserInfoManager.getAuthUserEmail();

        // Set user name
        userNameView.setText(username);

        // Get image reference and load to ImageView
        try {
            StorageReference useravatar = UserInfoManager.getUserAvatar(userAvatarPath);
            UserInfoManager.loadUserAvatar(useravatar, userAvatarView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Click Avatar, update profile
        userAvatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToUpdateProfile = new Intent(Profile.this, UpdateProfile.class);
                intentToUpdateProfile.putExtra("username", username);
                intentToUpdateProfile.putExtra("email", email);
                intentToUpdateProfile.putExtra("avatarPath", userAvatarPath);
                startActivity(intentToUpdateProfile);
            }
        });

        // Click username, update profile
        userNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToUpdateProfile = new Intent(Profile.this, UpdateProfile.class);
                intentToUpdateProfile.putExtra("username", username);
                intentToUpdateProfile.putExtra("email", email);
                intentToUpdateProfile.putExtra("avatarPath", userAvatarPath);
                startActivity(intentToUpdateProfile);
            }
        });
    }

    void initBtn(){
        // Sign out button
        signOutButton = (Button) findViewById(R.id.logoutBtn);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUtils.logout(Profile.this);
            }
        });

        // Message icon visible when browsing other user's profile
        // Delete button invisible when browsing other user's profile
        ImageView messageView = findViewById(R.id.message);
        if (authUserEmail != null && authUserEmail.equals(email)) {
            messageView.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            messageView.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, ChatActivity.class)
                        .putExtra("img", userAvatarPath)
                        .putExtra("nick", username)
                        .putExtra("email", email);
                startActivity(intent);
            }
        });

        // close profile button
        ImageView closeProfileButton = findViewById(R.id.closeProfile);
        closeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainMenu.class);
                startActivity(intent);
                // close current Activity
                finish();
            }
        });
    }

    void deletePost(String documentId){
        cache = CacheToFile.loadCacheFromInternalStorage(this);
        FirebaseFirestore.getInstance().collection("scam_cases").document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        AndroidUtil.showToast(getApplicationContext(), "Successfully deleted post");
                        cache.remove(documentId, new ScamCaseWithUser());
                        reloadScamCase();
//                        setInProgress(false);

//                        AndroidUtil.showToast(getApplicationContext(), "Successfully reloaded posts");
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                        AndroidUtil.showToast(getApplicationContext(), "post deletion failed");
                    }
                });
    }

    void reloadScamCase() {
        ScamCaseUserCombine.loadScamCases(new DataLoadCallback() {
            @Override
            public void onDataLoaded(List<ScamCaseWithUser> scamCaseWithUserList) {
                List<ScamCaseWithUser> authUserScamCases = new ArrayList<>();
                for (ScamCaseWithUser scamCaseWithUser : scamCaseWithUserList) {
                    if (email.equals(scamCaseWithUser.getUser().getEmail())) {
                        authUserScamCases.add(scamCaseWithUser);
                    }
                }
                cardAdapterProfile.setData(authUserScamCases);
                cardAdapterProfile.notifyDataSetChanged();
            }
        });

        // Set adapter for recyclerView to display scam list cards
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerViewProfile.setLayoutManager(layoutManager);
//        recyclerViewProfile.setAdapter(cardAdapterProfile);
    }

    void setInProgress(boolean inProgress) {
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void goToHistory(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}