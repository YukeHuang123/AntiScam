package com.example.antiscam;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antiscam.bean.ScamCase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private FirebaseAuth mAuth;
    private Button signInButton;
    private Button firestoreButton;
    private static final String TAG = "EmailPassword";
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress= (EditText) findViewById(R.id.EmailAddressText);
        password= (EditText) findViewById(R.id.PasswordText);
        signInButton = (Button) findViewById(R.id.loginButton);
        firestoreButton = (Button) findViewById(R.id.firestoreButton);

        mAuth = FirebaseAuth.getInstance();

        // 为按钮设置点击监听器
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取 email 和 password 的值
                String email_text = emailAddress.getText().toString().trim();
                String password_text = password.getText().toString().trim();

                // 调用 signIn 方法
                signIn(email_text, password_text);
            }
        });

        firestoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirestore();
            }
       });
    }
//
////    @Override
////    public void onStart() {
////        super.onStart();
////        // Check if user is signed in (non-null) and update UI accordingly.
////        FirebaseUser currentUser = mAuth.getCurrentUser();
////        if(currentUser != null){
////            reload();
////        }
////    }
//
    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // UPDATE Start
//                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                                    .setDisplayName("Emma")
//                                    .setPhotoUri(Uri.parse("userAvatar/user01_avatar.png"))
//                                    .build();
//
//                            user.updateProfile(profileUpdates)
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Log.d(TAG, "User profile updated.");
//                                            } else {
//                                                Log.d(TAG, "User profile update failed.");
//                                            }
//                                        }
//                                    });
                            // UPDATE End

                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }
//
//    private void reload() { }
//
    private void updateUI(FirebaseUser user) {
        if (user == null) {

        }
        else {
            Intent intent = new Intent(LoginActivity.this, MainMenu.class);
            startActivity(intent);
        }
    }

    public void updateFirestore(){

        try {
            AssetManager assetManager = getAssets();

            InputStream is = assetManager.open("scamCase.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonString);

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            WriteBatch batch = db.batch();

            for (int i = 2000; i < 2500; i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                Date date = formatDate.parse(obj.getString("date"));
                double amount = obj.getDouble("amount");
                String victim_city = obj.getString("victim_city");
                int victim_age = obj.getInt("victim_age");
                Date post_date = formatTime.parse(obj.getString("post_date"));
                int scam_id = obj.getInt("scam_id");
                String post_user = obj.getString("post_user");
                String description = obj.getString("description");
                String paymentMethod = obj.getString("paymentMethod");
                String title = obj.getString("title");
                String scam_type = obj.getString("scam_type");
                String contactMethod = obj.getString("contactMethod");

                ScamCase scamCase = new ScamCase(date, amount, victim_city, victim_age,
                        post_date, scam_id, post_user, description, paymentMethod, title, scam_type,
                        contactMethod);

                // Add scamCase
                DocumentReference dRef = db.collection("scam_cases")
                        .document(String.valueOf(i));
                batch.set(dRef, scamCase);

                Log.d(TAG, "DocumentSnapshot is adding: " + i);

            }

            batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(LoginActivity.this, "Add Success!!!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "DocumentSnapshot added success");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}