package com.example.antiscam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.antiscam.act.CaseDetail;
import com.example.antiscam.act.SearchResultActivity;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.tool.CacheToFile;
import com.example.antiscam.tool.DoublyLinkedListExclusionStrategy;
import com.example.antiscam.tool.LRUCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScamCaseCardAdapter cardAdapter;
    private LRUCache<String, ScamCaseWithUser> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initHistory();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initHistory() {
        cache = CacheToFile.loadCacheFromInternalStorage(this);
//
        if (cache == null) {
            Log.d("cache", "no cache find");
            cache = new LRUCache<>(100);
        }
        Log.d("cache", cache.toString());

        List<ScamCaseWithUser> dataList = new ArrayList<>(cache.getAll());

        cardAdapter = new ScamCaseCardAdapter(dataList, R.layout.mainmenu_cardlist);

        cardAdapter.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCaseWithUser) {
                Intent intent = new Intent(History.this, CaseDetail.class);
                intent.putExtra("scamCaseWithUser", scamCaseWithUser);

                cache.put(String.valueOf(scamCaseWithUser.getScamCase().getScam_id()), scamCaseWithUser);
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new DoublyLinkedListExclusionStrategy())
                        .create();
                String cacheString = gson.toJson(cache);
                LRUCache<String, ScamCaseWithUser> cache = gson.fromJson(cacheString,
                        new TypeToken<LRUCache<String, ScamCaseWithUser>>() {
                        }.getType());
                Log.d("cacheToStr", JSON.toJSONString(cache));
                CacheToFile.saveCacheToInternalStorage(History.this, cache);

                startActivity(intent);
            }
        });

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        findViewById(R.id.btn_back).setOnClickListener(v -> onBackPressed());

    }
}