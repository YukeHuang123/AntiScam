package com.example.antiscam;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.adapter.mainMenuCardAdapter;
import com.example.antiscam.dataclass.scamCaseList;
import com.example.antiscam.dataclass.scamCaseListAccess;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private mainMenuCardAdapter cardAdapter;
    private String searchContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchContent = getIntent().getStringExtra("search_content");

        List<scamCaseList> dataList = scamCaseListAccess.loadJsonData(this, "scamCase.json");

        cardAdapter = new mainMenuCardAdapter(SearchParser.search(searchContent, dataList));

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
