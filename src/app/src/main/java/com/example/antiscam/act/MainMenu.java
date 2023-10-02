package com.example.antiscam.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.antiscam.CaseDetail;
import com.example.antiscam.R;
import com.example.antiscam.SearchTool;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.ScamCaseUserCombine;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.repository.DataRepository;
import com.example.antiscam.tool.CacheToFile;
import com.example.antiscam.tool.DataLoadCallback;
import com.example.antiscam.tool.LRUCache;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    //    private mainMenuCardAdapter cardAdapter;
    private ScamCaseCardAdapter cardAdapter;
    private Button signOutButton;
    private SearchView searchView;

    private LRUCache<Integer, ScamCaseWithUser> cache;

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
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        cache = CacheToFile.loadCacheFromInternalStorage(this);

        if (cache == null) {
            Log.d("cache", "no cache find");
            cache = new LRUCache<>(100);
        }
        Log.d("cache", cache.toString());


        cardAdapter = new ScamCaseCardAdapter(dataList, R.layout.mainmenu_cardlist);
        /*connect card to case detail*/

        ScamCaseUserCombine.loadScamCases(new DataLoadCallback() {

            @Override
            public void onDataLoaded(List<ScamCaseWithUser> dataList) {
                DataRepository.getInstance().addAllScamCaseWithUsers(dataList);
                cardAdapter.setData(dataList);
            }
        });
//        cardAdapter = new mainMenuCardAdapter(dataList);
        cardAdapter.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCaseWithUser) {
                Intent intent = new Intent(MainMenu.this, CaseDetail.class);
                intent.putExtra("scamCaseWithUser", scamCaseWithUser);

                cache.put(scamCaseWithUser.getScamCase().getScam_id(), scamCaseWithUser);
                Log.d("cacheToStr", JSON.toJSONString(cache));
//                CacheToFile.saveCacheToInternalStorage(MainMenu.this, cache);

                startActivity(intent);
            }
        });


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

        String authUserEmail = UserInfoManager.getAuthUserEmail();
        String authUserName = UserInfoManager.getAuthUserName();
        String authUserAvatarPath = UserInfoManager.getAuthUserAvatarPath();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When click avatar, to profile page
                Intent intentToProfile = new Intent(MainMenu.this, Profile.class);
                intentToProfile.putExtra("username", authUserName);
                intentToProfile.putExtra("email", authUserEmail);
                intentToProfile.putExtra("avatarPath", authUserAvatarPath);
                startActivity(intentToProfile);
            }
        });

        findViewById(R.id.btn_search).setOnClickListener(v -> search());
        searchView = findViewById(R.id.searchView);


//        ConstraintLayout cardLayout = findViewById(R.id.cardLayout);
//        cardLayout.setClickable(true);
//        cardLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent=new Intent(MainMenu.this, CaseDetail.class);
//                startActivity(intent);
//            }
//        });

    }

    void search() {
        String query = searchView.getQuery().toString();
        if (query.length() == 0) {
            return;
        }
        List<ScamCaseWithUser> result = SearchTool.search(query, DataRepository.getInstance().getScamCaseWithUsers());
        if (result.isEmpty()) {
            Toast.makeText(this, "Result is Empty,Please retry", Toast.LENGTH_LONG).show();
            return;
        }
        startActivity(new Intent(this, SearchResultActivity.class).putExtra("search_content", query));
    }


}

