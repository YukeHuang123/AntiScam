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

import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.dataclass.ScamCaseUserCombine;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.UserInfoManager;

import java.util.List;

public class MainMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    //    private mainMenuCardAdapter cardAdapter;
    private ScamCaseCardAdapter cardAdapter;
    private Button signOutButton;
    private SearchView searchView;
    //    private int visibleThreshold = 10;
//    private boolean isLoading = false;
//    private boolean isLastPage = false;
//    private int currentPage = 1; // Current page count
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initMainMenu();
    }

    private void initMainMenu() {
        // Data List for scam case card and set scam case data to adapter
//        List<scamCaseList> dataList = scamCaseListAccess.loadJsonData(this, "scamCase.json");
        List<ScamCaseWithUser> dataList = ScamCaseUserCombine.loadScamCases();
//        cardAdapter = new mainMenuCardAdapter(dataList);
        cardAdapter = new ScamCaseCardAdapter(dataList, R.layout.mainmenu_cardlist);

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cardAdapter);


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

        //connect card to case detail
        cardAdapter.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCase) {
                Intent intent=new Intent(MainMenu.this, CaseDetail.class);
                intent.putExtra("scamCaseWithUser", scamCase);
                startActivity(intent);
            }
        });

    }

    void search() {
        String query = searchView.getQuery().toString();
        if (query.length() == 0) {
            return;
        }
        startActivity(new Intent(this, SearchResultActivity.class).putExtra("search_content", query));
    }
}