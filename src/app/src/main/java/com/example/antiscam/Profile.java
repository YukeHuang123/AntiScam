package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AuthUtils;

public class Profile extends AppCompatActivity {
    private Button signOutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Get user information
        TextView userNameView = findViewById(R.id.userNamePro);
        ImageView imageView = findViewById(R.id.avatarImgViewPro);
        UserInfoManager.getUserInfo(this, userNameView, imageView);

        signOutButton = (Button) findViewById(R.id.logoutBtn);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUtils.logout(Profile.this);
            }
        });
    }
}