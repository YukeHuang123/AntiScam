package com.example.antiscam.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.adapter.ScamCaseCardProfileAdapter;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.ScamCaseUserCombine;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AuthUtils;
import com.example.antiscam.tool.DataLoadCallback;
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
    // 自定义的图像选择请求码
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initProfile();
    }

    protected void initProfile() {
        // Get user information
        TextView userNameView = findViewById(R.id.userNamePro);
        ImageView userAvatarView = findViewById(R.id.avatarImgViewPro);

//        UserInfoManager.getUserInfo(this, userNameView, imageView);
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
            email = intent.getStringExtra("email");
            userAvatarPath = intent.getStringExtra("avatarPath");

            // Set user name
            userNameView.setText(username);

            // Get image reference and load to ImageView
            try {
                StorageReference useravatar = UserInfoManager.getUserAvatar(userAvatarPath);
                UserInfoManager.loadUserAvatar(useravatar, userAvatarView);
            } catch (Exception e) {
                e.printStackTrace();
            }

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
            authUserEmail = UserInfoManager.getAuthUserEmail();
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
        }


        // close profile
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

        // scam case list
//        List<ScamCaseWithUser> scamCaseWithUserList = ScamCaseUserCombine.loadScamCases();
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

        // Initialize recyclerView
        recyclerViewProfile = findViewById(R.id.recyclerViewProfile);
        recyclerViewProfile.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewProfile.setLayoutManager(layoutManager);
        recyclerViewProfile.setAdapter(cardAdapterProfile);

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

        // Click Avatar, update profile
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
}