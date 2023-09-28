package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.bean.User;
import com.example.antiscam.databinding.ActivityCaseDetailBinding;

public class CaseDetail extends AppCompatActivity {
    ActivityCaseDetailBinding binding;
    //private List<ScamCaseWithUser> scamCaseWithUsers;


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
            // bind data on view
            binding.caseView.setText(scamCase.getDescription());
            binding.userName.setText(user.getUsername());
            binding.caseTitle.setText(scamCase.getTitle());
            binding.fixTitle.setText(scamCase.getTitle());
            binding.caseType.setText(scamCase.getScam_type());
            binding.contact.setText(scamCase.getContactMethod());
            binding.contact.setText(scamCase.getContactMethod());
            binding.payment.setText(scamCase.getPaymentMethod());
            binding.age.setText(scamCase.getVictim_age());
            binding.location.setText(scamCase.getVictim_city());
            binding.amount.setText(String.valueOf(scamCase.getAmount()));
            //binding.userPicture.setText(user.getAvatar());
        }

    }
//    public void leave(View v){
//        Intent intent=new Intent(CaseDetail.this, MainMenu.class);
//        startActivity(intent);
//    }
    public void showProfile(View v){
        Intent intent=new Intent(CaseDetail.this, Profile.class);
        startActivity(intent);
    }




}