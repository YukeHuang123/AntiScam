package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.antiscam.act.MainMenu;

public class AntiScam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antiscam);

        // Capture ADD URL button from layout
        Button gotoMainMenuBtn = (Button) findViewById(R.id.testMainMenu);
        // Register the onClick listener
        gotoMainMenuBtn.setOnClickListener(gotoMainMenuListener);
    }

    private View.OnClickListener gotoMainMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent toMainMenuInt = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(toMainMenuInt);
        }
    };
}