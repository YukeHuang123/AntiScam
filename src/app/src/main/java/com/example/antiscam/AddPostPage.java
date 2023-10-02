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
                    Toast.makeText(AddPostPage.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if(!checkInt(age1)||!checkInt(day1)||!checkInt(month1)||!checkInt(year1)){
                    Toast.makeText(AddPostPage.this, "Date and age can only be filled with integer", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent=new Intent(AddPostPage.this, SubmitSuccessPage.class);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean checkInt(String text){
        if(text.matches("\\d+")){
            return true;
        }
        return false;
    }
}