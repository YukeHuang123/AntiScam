package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class caseDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);
    }
    public void leave(View v){
        Intent intent=new Intent(caseDetail.this, MainMenu.class);
        startActivity(intent);
    }

}