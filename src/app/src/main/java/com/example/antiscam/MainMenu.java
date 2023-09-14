package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        WebView mainMenu = (WebView) findViewById(R.id.mainMenuWV);
//        Intent mainMenuInt = getIntent();
//        startActivity(mainMenuInt);
        mainMenu.loadUrl("https://google.com");
    }
}