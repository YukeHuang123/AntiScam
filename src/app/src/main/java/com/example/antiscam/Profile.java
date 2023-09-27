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
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AuthUtils;

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
        ImageView imageView = findViewById(R.id.avatarImgViewPro);
        UserInfoManager.getUserInfo(this, userNameView, imageView);

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
        cardAdapterProfile = new ScamCaseCardAdapter(scamCaseWithUserList, R.layout.mainmenu_cardlist);

        // Initialize recyclerView
        recyclerViewProfile = findViewById(R.id.recyclerViewProfile);
        recyclerViewProfile.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewProfile.setLayoutManager(layoutManager);
        recyclerViewProfile.setAdapter(cardAdapterProfile);
    }
}