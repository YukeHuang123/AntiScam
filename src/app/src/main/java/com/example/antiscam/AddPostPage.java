package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.antiscam.databinding.ActivityAddPostPageBinding;
import com.example.antiscam.databinding.ActivityCaseDetailBinding;

public class AddPostPage extends AppCompatActivity {
    ActivityCaseDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_page);
        ImageView close=findViewById(R.id.close2);
        close.setOnClickListener(view -> onBackPressed());

        Button submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText age=findViewById(R.id.editAge);
                EditText amount=findViewById(R.id.editAmount);
                EditText description=findViewById(R.id.editDescription);
                EditText day=findViewById(R.id.DD);
                EditText month=findViewById(R.id.MM);
                EditText year=findViewById(R.id.YYYY);

                String age1 = age.getText().toString().trim();
                String amount1 = amount.getText().toString().trim();
                String description1 = description.getText().toString().trim();
                String day1 = day.getText().toString().trim();
                String month1 = month.getText().toString().trim();
                String year1 = year.getText().toString().trim();

                if (age1.isEmpty() || amount1.isEmpty()||description1.isEmpty()||day1.isEmpty()||month1.isEmpty()||year1.isEmpty()) {
                    Toast.makeText(AddPostPage.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else if(!checkAge(age1)||!checkDay(day1)||!checkMonth(month1)||!checkYear(year1)){
                    Toast.makeText(AddPostPage.this, "Date or age is not valid!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent=new Intent(AddPostPage.this, SubmitSuccessPage.class);
                    startActivity(intent);
                }
            }
        });
    }


    public boolean checkDay(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 31;
        }
        return false;
    }
    public boolean checkMonth(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 12;
        }
        return false;
    }
    public boolean checkYear(String s){
        if(s.matches("^[0-9]{4}$")){
            return Integer.parseInt(s) >= 1970 && Integer.parseInt(s) <= 2023;
        }
        return false;
    }
    public boolean checkAge(String s){
        if(s.matches("^[0-9]{1}$")||s.matches("^[0-9]{2}$")){
            int age=Integer.parseInt(s);
            return age<100;
        }
        return false;

    }

}