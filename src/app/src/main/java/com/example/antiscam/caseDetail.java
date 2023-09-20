package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class caseDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String userName = intent.getStringExtra("userName");
            String description = intent.getStringExtra("description");
            String type = intent.getStringExtra("type");

            TextView caseTitleTextView = findViewById(R.id.caseTitle);
            TextView userNameTextView = findViewById(R.id.userName);
            TextView caseViewTextView = findViewById(R.id.caseView);
            TextView typeTextView = findViewById(R.id.tag);

            caseTitleTextView.setText(title);
            userNameTextView.setText(userName);
            caseViewTextView.setText(description);
            typeTextView.setText(type);
        }

        //TextView caseDescription = (TextView) findViewById(R.id.caseView);
        //caseDescription.setText();
    }
    public void leave(View v){
        Intent intent=new Intent(caseDetail.this, MainMenu.class);
        startActivity(intent);
    }
    public void showProfile(View v){
        Intent intent=new Intent(caseDetail.this, Profile.class);
        startActivity(intent);
    }




}