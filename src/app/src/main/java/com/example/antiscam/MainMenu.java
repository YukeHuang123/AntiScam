package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.antiscam.adapter.mainMenuCardAdapter;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.dataclass.scamCaseList;
import com.example.antiscam.dataclass.scamCaseListAccess;
import com.example.antiscam.tool.AuthUtils;

import java.util.List;

public class MainMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private mainMenuCardAdapter cardAdapter;
    private Button signOutButton;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Data List for scam case card and set scam case data to adapter
        List<scamCaseList> dataList = scamCaseListAccess.loadJsonData(this, "scamCase.json");
        cardAdapter = new mainMenuCardAdapter(dataList);

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        signOutButton = (Button) findViewById(R.id.logout_Button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUtils.logout(MainMenu.this);
            }
        });

        TextView userNameView = findViewById(R.id.userName);
        ImageView imageView = findViewById(R.id.avatarImgView);
        UserInfoManager.getUserInfo(this, userNameView, imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When click avatar, to profile page
                Intent intent = new Intent(MainMenu.this, Profile.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_search).setOnClickListener(v -> search());
        searchView = findViewById(R.id.searchView);
    }

    void search() {
        String query = searchView.getQuery().toString();
        if (query.length() == 0) {
            return;
        }
        startActivity(new Intent(this, SearchResultActivity.class).putExtra("search_content", query));
    }
}