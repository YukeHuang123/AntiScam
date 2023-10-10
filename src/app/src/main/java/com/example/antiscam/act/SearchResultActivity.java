package com.example.antiscam.act;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.antiscam.R;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.repository.DataRepository;
import com.example.antiscam.tool.CacheToFile;
import com.example.antiscam.tool.DoublyLinkedListExclusionStrategy;
import com.example.antiscam.tool.LRUCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScamCaseCardAdapter cardAdapter;
    private String searchContent;
    private LRUCache<String, ScamCaseWithUser> cache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchContent = getIntent().getStringExtra("search_content");

        cache = CacheToFile.loadCacheFromInternalStorage(this);
//
        if (cache == null) {
            Log.d("cache", "no cache find");
            cache = new LRUCache<>(100);
        }

        cardAdapter = new ScamCaseCardAdapter(DataRepository.getInstance().getScamCaseWithUsers(), R.layout.mainmenu_cardlist);

        cardAdapter.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCaseWithUser) {
                Intent intent = new Intent(SearchResultActivity.this, CaseDetail.class);
                intent.putExtra("scamCaseWithUser", scamCaseWithUser);

                cache.put(String.valueOf(scamCaseWithUser.getScamCase().getScam_id()), scamCaseWithUser);
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new DoublyLinkedListExclusionStrategy())
                        .create();
                String cacheString = gson.toJson(cache);
                LRUCache<String , ScamCaseWithUser> cache = gson.fromJson(cacheString,
                        new TypeToken<LRUCache<String, ScamCaseWithUser>>(){}.getType());
                Log.d("cacheToStr", JSON.toJSONString(cache));
                CacheToFile.saveCacheToInternalStorage(SearchResultActivity.this, cache);

                startActivity(intent);
            }
        });

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        TextView textView = findViewById(R.id.tv_result);
        textView.setText(searchContent);
        findViewById(R.id.btn_back).setOnClickListener(v -> onBackPressed());
    }
}
