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
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.dataclass.scamCase;
import com.example.antiscam.dataclass.scamCaseListAccess;
import com.example.antiscam.tool.CircleImageTransformer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

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

        TextView userNameView = findViewById(R.id.userName);
        ImageView imageView = findViewById(R.id.avatarImgView);
        UserInfoManager.getUserInfo(this, userNameView, imageView);

//        // Get user information from firebase
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Name, email address, and profile photo Url
//
//            String username = user.getDisplayName();
////            String email = user.getEmail();
//            Uri photoUrl = user.getPhotoUrl();
//
//            TextView userNameView = findViewById(R.id.userName);
//            userNameView.setText(username);
//
//            // Check if user's email is verified
////            boolean emailVerified = user.isEmailVerified();
//
//            // The user's ID, unique to the Firebase project. Do NOT use this value to
//            // authenticate with your backend server, if you have one. Use
//            // FirebaseUser.getIdToken() instead.
//            String uid = user.getUid();
//
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageRef = storage.getReference();
//
//            // Set path
//            String imagePath = String.valueOf(photoUrl); // 替换为你的存储路径
//
//            // Get StorageReference
//            StorageReference imageRef = storageRef.child(imagePath);
//
//            // Download image and load to ImageView
//            ImageView imageView = findViewById(R.id.avatarImgView); // 替换为你的 ImageView
//
//            imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    // Use Picasso to load and transform image
//                    Picasso.get().load(uri.toString()).transform(new CircleImageTransformer()).into(imageView);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception exception) {
//                    imageView.setImageResource(R.drawable.default_avatar);
//                }
//            });
//
//        }
    }
}