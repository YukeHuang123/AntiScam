package com.example.antiscam.act;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.adapter.ScamCaseCardAdapter;
import com.example.antiscam.repository.DataRepository;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScamCaseCardAdapter cardAdapter;
    private String searchContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        searchContent = getIntent().getStringExtra("search_content");


        cardAdapter = new ScamCaseCardAdapter(DataRepository.getInstance().getScamCaseWithUsers(), R.layout.mainmenu_cardlist);

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
