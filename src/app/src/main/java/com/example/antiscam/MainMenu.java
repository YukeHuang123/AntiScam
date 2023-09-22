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
import com.example.antiscam.dataclass.ScamCaseWithUser;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AuthUtils;

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
        cardAdapter = new ScamCaseCardAdapter(dataList);

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Set adapter for recyclerView to display scam list cards

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cardAdapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int totalItemCount = layoutManager.getItemCount();
//                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
//
//                if (!isLoading && !isLastPage) {
//                    if (totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                        // 加载更多数据
//                        currentPage++;
//                        loadMoreData();
//                        isLoading = true;
//                    }
//                }
//            }
//        });
//
//        // 初始加载数据
//        loadInitialData();

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

//    private void loadInitialData() {
//        firestore.collection("your_collection_name")
//                .orderBy("timestamp", Query.Direction.DESCENDING)
//                .limit(ITEMS_PER_PAGE)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            // 处理从 Firestore 获取的数据，将其添加到 dataList
//                            YourDataModel data = document.toObject(YourDataModel.class);
//                            dataList.add(data);
//                        }
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        // 处理加载数据失败的情况
//                    }
//                });
//    }

//    private void loadMoreData() {
//        firestore.collection("your_collection_name")
//                .orderBy("timestamp", Query.Direction.DESCENDING)
//                .startAfter(dataList.get(dataList.size() - 1).getTimestamp()) // 获取上一页最后一条数据的时间戳
//                .limit(ITEMS_PER_PAGE)
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            // 处理从 Firestore 获取的数据，将其添加到 dataList
//                            YourDataModel data = document.toObject(YourDataModel.class);
//                            dataList.add(data);
//                        }
//                        adapter.notifyDataSetChanged();
//                        isLoading = false;
//                    } else {
//                        // 处理加载数据失败的情况
//                    }
//                });
//    }

    void search() {
        String query = searchView.getQuery().toString();
        if (query.length() == 0) {
            return;
        }
        startActivity(new Intent(this, SearchResultActivity.class).putExtra("search_content", query));
    }
}