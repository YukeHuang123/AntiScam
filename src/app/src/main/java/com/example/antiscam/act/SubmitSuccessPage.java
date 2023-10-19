package com.example.antiscam.act;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.antiscam.R;
/**
 * @author Yuke Huang, u7618794
 */
public class SubmitSuccessPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_success_page);
        Button back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SubmitSuccessPage.this,MainMenu.class);
                startActivity(intent);
            }
        });

        Button addNew=findViewById(R.id.addMore);
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SubmitSuccessPage.this, AddPostPage.class);
                startActivity(intent);
            }
        });
    }
}