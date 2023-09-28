package com.example.antiscam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.adapter.ScamCaseCardAdapter;
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
    private ScamCaseCardAdapter cardAdapterProfile;
    private Button signOutButton;
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
            String username = intent.getStringExtra("username");
            String email = intent.getStringExtra("email");
            String userAvatarPath = intent.getStringExtra("avatarPath");

            // Set user name
            userNameView.setText(username);
            // Get image reference and load to ImageView
            try {
                StorageReference useravatar = UserInfoManager.getUserAvatar(userAvatarPath);
                UserInfoManager.loadUserAvatar(useravatar, userAvatarView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Sign out button
        signOutButton = (Button) findViewById(R.id.logoutBtn);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUtils.logout(Profile.this);
            }
        });

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
        cardAdapterProfile = new ScamCaseCardAdapter(scamCaseWithUserList, R.layout.profile_cardlist);

        ScamCaseUserCombine.loadScamCases(new DataLoadCallback() {
            @Override
            public void onDataLoaded(List<ScamCaseWithUser> scamCaseWithUserList) {

                cardAdapterProfile.setData(scamCaseWithUserList);
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
}