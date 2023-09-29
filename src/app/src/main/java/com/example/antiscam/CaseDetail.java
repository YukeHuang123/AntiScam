package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.bean.User;
import com.example.antiscam.databinding.ActivityCaseDetailBinding;
import com.example.antiscam.dataclass.UserInfoManager;
import com.google.firebase.storage.StorageReference;

public class CaseDetail extends AppCompatActivity {
    ActivityCaseDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        // Initialize the binding object
        binding = ActivityCaseDetailBinding.inflate(getLayoutInflater());
        // Set the root view of the activity
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);//
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());

        //step: find in cache first, if nor find then find in firebase

        //2. find in firebase
        //scamCaseWithUsers=new ArrayList<>();
        //scamCaseWithUsers=ScamCaseUserCombine.loadScamCases();
        ScamCaseWithUser originalCase=null;


        //receive the ScamCaseWithUser pass from main menu
        Intent intent = getIntent();
        if(intent.hasExtra("scamCaseWithUser")){
            originalCase= intent.getParcelableExtra("scamCaseWithUser");
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
            binding.caseType.setText(scamCase.getScam_type());
            binding.contact.setText(scamCase.getContactMethod());
            binding.contact.setText(scamCase.getContactMethod());
            binding.payment.setText(scamCase.getPaymentMethod());
            binding.age.setText(String.valueOf(scamCase.getVictim_age()));
            binding.location.setText(scamCase.getVictim_city());
            binding.amount.setText(String.valueOf(scamCase.getAmount()));
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
                    Intent intent=new Intent(CaseDetail.this, Profile.class);
                    intent.putExtra("name",name);
                    intent.putExtra("email",email);
                    intent.putExtra("avatarPath",avatarPath);
                    startActivity(intent);

                }
            });



        }

    }
//    public void showProfile(View v){
//        TextView userNameView = findViewById(R.id.userName);
//        ImageView imageView = findViewById(R.id.avatarImgView);
//        UserInfoManager.getUserInfo(this, userNameView, imageView);
//
//        //String authUserEmail = UserInfoManager.getAuthUserEmail();
//        String authUserName = UserInfoManager.getAuthUserName();
//
//
//        Intent intentToProfile = new Intent(CaseDetail.this, Profile.class);
//        intentToProfile.putExtra("username", authUserName);
//        //intentToProfile.putExtra("email", authUserEmail);
//        startActivity(intentToProfile);
//    }




}