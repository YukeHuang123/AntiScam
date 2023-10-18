package com.example.antiscam.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.bean.User;
import com.example.antiscam.databinding.ActivityCaseDetailBinding;
import com.example.antiscam.dataclass.UserInfoManager;
import com.google.firebase.storage.StorageReference;

public class CaseDetail extends AppCompatActivity {
    private ActivityCaseDetailBinding binding;
    private Boolean showProfile = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        // Initialize the binding object
        binding = ActivityCaseDetailBinding.inflate(getLayoutInflater());
        // Set the root view of the activity
        setContentView(binding.getRoot());
        binding.close.setOnClickListener(view -> onBackPressed());
        ScamCaseWithUser originalCase=null;


        //receive the ScamCaseWithUser pass from main menu
        Intent intent = getIntent();
        if(intent.hasExtra("scamCaseWithUser")){
            originalCase= intent.getParcelableExtra("scamCaseWithUser");
        }
        if (intent.hasExtra("fromPage")) {
            if (intent.getStringExtra("fromPage").equals("userProfile")) {
                showProfile = false;
            }
        }
        if(originalCase!=null){
            //get scamCase and user
            ScamCase scamCase = originalCase.getScamCase();
            User user = originalCase.getUser();
            String name=user.getUsername();
            // bind data on view
            binding.caseView.setText(scamCase.getDescription());
            binding.userName.setText(name);
            binding.caseTitle.setText(scamCase.getTitle());
            binding.fixTitle.setText(scamCase.getTitle());
            binding.caseType.setText("Scam Type: "+scamCase.getScam_type());
            binding.contact.setText("Contact Method: "+scamCase.getContactMethod());
            binding.payment.setText("Payment Method: "+scamCase.getPaymentMethod());
            binding.age.setText("Age: "+String.valueOf(scamCase.getVictim_age()));
            binding.location.setText("Location: "+scamCase.getVictim_city());
            binding.amount.setText(String.valueOf("Lost: "+scamCase.getAmount()));
            //binding.userPicture.setImageDrawable();

            String avatarPath = user.getAvatar();
            String email=user.getEmail();

            //find image
            ImageView userAvatarView = findViewById(R.id.userPicture);
            try {
                StorageReference useravatar = UserInfoManager.getUserAvatar(avatarPath);
                UserInfoManager.loadUserAvatar(useravatar, userAvatarView);
            } catch (Exception e) {
                e.printStackTrace();
            }

            userAvatarView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showProfile) {
                        Intent intent=new Intent(CaseDetail.this, Profile.class);
                        intent.putExtra("username",name);
                        intent.putExtra("email",email);
                        intent.putExtra("avatarPath",avatarPath);
                        startActivity(intent);
                    } else {
                        onBackPressed();
                    }
                }
            });
        }

    }
}