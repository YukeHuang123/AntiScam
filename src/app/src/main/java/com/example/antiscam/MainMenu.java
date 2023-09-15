package com.example.antiscam;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antiscam.adapter.mainMenuCardAdapter;
import com.example.antiscam.dataclass.scamCase;
import com.example.antiscam.dataclass.scamCaseListAccess;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private mainMenuCardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Data List for scam case card and set scam case data to adapter
//        List<scamCase> dataList = createDataList();
        List<scamCase> dataList = scamCaseListAccess.loadJsonData(this, "scamCase.json");
        cardAdapter = new mainMenuCardAdapter(dataList);

        // Initialize recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        // Set adapter for recyclerView to display scam list cards
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardAdapter);

        // Get user information from firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String username = user.getDisplayName();
//            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            TextView userNameView = findViewById(R.id.userName);
            userNameView.setText(username);
            userNameView.getText();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
    }
    // For Yijing Test
//    private List<scamCase> createDataList() {
//        List<scamCase> dataList = new ArrayList<>();
//        dataList.add(new scamCase("Card 1", "Description 1"));
//        dataList.add(new scamCase("Card 2", "Description 2"));
//        dataList.add(new scamCase("Card 3", "Description 3"));
//        dataList.add(new scamCase("Card 4", "Description 4"));
//        dataList.add(new scamCase("Card 5", "Description 5"));
//        dataList.add(new scamCase("Card 6", "Description 6"));
//        dataList.add(new scamCase("Card 7", "Description 7"));
//        dataList.add(new scamCase("Card 8", "Description 8"));
//        dataList.add(new scamCase("Card 9", "Description 9"));
//        dataList.add(new scamCase("Card 10", "Description 10"));
//        return dataList;
//    }
}