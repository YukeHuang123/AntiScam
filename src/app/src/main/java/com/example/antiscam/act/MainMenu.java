package com.example.antiscam.act;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.antiscam.AddPostPage;
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
import com.example.antiscam.tool.DoublyLinkedListExclusionStrategy;
import com.example.antiscam.tool.LRUCache;
import com.alibaba.fastjson.TypeReference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    //    private mainMenuCardAdapter cardAdapter;
    private ScamCaseCardAdapter cardAdapter;
    private Button signOutButton;
    private SearchView searchView;
    private float dX;

    private float dY;


    private LRUCache<String, ScamCaseWithUser> cache;

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

    @SuppressLint("ClickableViewAccessibility")
    private void initMainMenu() {
        // Data List for scam case card and set scam case data to adapter
//        List<scamCaseList> dataList = scamCaseListAccess.loadJsonData(this, "scamCase.json");
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        cache = CacheToFile.loadCacheFromInternalStorage(this);
//
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

                cache.put(String.valueOf(scamCaseWithUser.getScamCase().getScam_id()), scamCaseWithUser);
//                Gson gson = new Gson();
                Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new DoublyLinkedListExclusionStrategy())
                        .create();
                String cacheString = gson.toJson(cache);
                LRUCache<String , ScamCaseWithUser> cache = gson.fromJson(cacheString,
                        new TypeToken<LRUCache<String, ScamCaseWithUser>>(){}.getType());
                Log.d("cacheToStr", JSON.toJSONString(cache));
                CacheToFile.saveCacheToInternalStorage(MainMenu.this, cache);

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


        //set onClick and onTouch listener on fab (FloatingActionButton)
        FloatingActionButton fab=findViewById(R.id.fab);

        final boolean[] isDragging = {false};
        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - motionEvent.getRawX();
                        dY = view.getY() - motionEvent.getRawY();
                        isDragging[0] = false; // Reset the flag
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = motionEvent.getRawX() + dX;
                        float newY = motionEvent.getRawY() + dY;

                        // Limit the button's position within the parent layout
                        View parentLayout = (View) view.getParent();
                        if (newX < 0) {
                            newX = 0;
                        } else if (newX > parentLayout.getWidth() - view.getWidth()) {
                            newX = parentLayout.getWidth() - view.getWidth();
                        }
                        if (newY < 0) {
                            newY = 0;
                        } else if (newY > parentLayout.getHeight() - view.getHeight()) {
                            newY = parentLayout.getHeight() - view.getHeight();
                        }

                        view.setX(newX);
                        view.setY(newY);
                        isDragging[0] = true; // Drag is in progress
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!isDragging[0]) {
                            // if not drag, run click event
                            Intent intent = new Intent(MainMenu.this, AddPostPage.class);
                            startActivity(intent);
                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

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

