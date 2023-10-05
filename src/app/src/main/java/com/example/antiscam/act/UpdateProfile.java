package com.example.antiscam.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.antiscam.R;
import com.example.antiscam.dataclass.UserInfoManager;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // close profile
        ImageView closeProfileButton = findViewById(R.id.closeUpdateProfile);
        String authUserEmail = UserInfoManager.getAuthUserEmail();
        String authUserName = UserInfoManager.getAuthUserName();
        String authUserAvatarPath = UserInfoManager.getAuthUserAvatarPath();
        closeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToProfile = new Intent(UpdateProfile.this, Profile.class);
                intentToProfile.putExtra("username", authUserName);
                intentToProfile.putExtra("email", authUserEmail);
                intentToProfile.putExtra("avatarPath", authUserAvatarPath);
                startActivity(intentToProfile);
                // close current Activity
                finish();
            }
        });

    }
}