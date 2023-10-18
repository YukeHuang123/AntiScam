package com.example.antiscam.act;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.antiscam.R;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.tool.HistoryCache;
import com.example.antiscam.tool.LRUCache;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ScamCaseCardAdapter cardAdapter;
    private HistoryCache historyCache;
    private LRUCache<String, ScamCaseWithUser> cache;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initHistory();
        swipeRefresh();
        deleteCache();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initHistory() {
        historyCache = HistoryCache.getInstance();
        cache = historyCache.getCache(this);
//
        if (cache == null) {
            Log.d("cache", "no cache find");
            cache = new LRUCache<>(100);
            historyCache.setCache(this, cache);

        }
        Log.d("cache", cache.toString());

        List<ScamCaseWithUser> dataList = new ArrayList<>(cache.getAll());

        cardAdapter = new ScamCaseCardAdapter(dataList, R.layout.mainmenu_cardlist);

        cardAdapter.setOnClickListener(new ScamCaseCardAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position, ScamCaseWithUser scamCaseWithUser) {
                Intent intent = new Intent(History.this, CaseDetail.class);
                intent.putExtra("scamCaseWithUser", scamCaseWithUser);

                cache = historyCache.getCache(History.this);


                cache.put(String.valueOf(scamCaseWithUser.getScamCase().getScam_id()), scamCaseWithUser);
                historyCache.setCache(History.this, cache);

                startActivity(intent);
            }
        });

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        findViewById(R.id.goback_History).setOnClickListener(v -> onBackPressed());

    }

    void swipeRefresh(){
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        // SetOnRefreshListener on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                reloadHistoryPage();
            }
        });
    }

    void reloadHistoryPage() {
        cache = historyCache.getCache(this);

        List<ScamCaseWithUser> dataList = new ArrayList<>(cache.getAll());

        cardAdapter.setData(dataList);
        cardAdapter.notifyDataSetChanged();

        // Set adapter for recyclerView to display scam list cards
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(cardAdapter);
    }

    void deleteCache() {
        ImageView deleteIcon = findViewById(R.id.deleteIcon);

        if (cache.getSize() == 0) {
            deleteIcon.setVisibility(View.GONE);
        }
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建并显示删除确认对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                builder.setTitle("Delete All History"); // 对话框标题
                builder.setMessage("Are you sure you want to delete all history records?"); // 对话框消息

                // 添加 "Yes" 按钮，用户确认删除
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAllHistoryRecords();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户取消删除操作，不执行任何操作
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    void deleteAllHistoryRecords() {
        cache = new LRUCache<>(100);
        historyCache.setCache(this, cache);
        Toast.makeText(History.this, "Delete all history, go back to profile!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}