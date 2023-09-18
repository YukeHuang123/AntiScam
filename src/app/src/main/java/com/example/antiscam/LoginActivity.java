package com.example.antiscam;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public void updateFirestore() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get a new write batch
        WriteBatch batch = db.batch();

//         Set the value of 'NYC'
        DocumentReference t1Ref = db.collection("case_test").document("1");

        batch.set(t1Ref, new ScamCase(new Date(), 1, "Harbour", 19, new Date(), 1, "dfdf",
                "@asnu", "They had convinced me that their weight-loss supplemen",
                "cryptocurrency", "Promised", "Website"));


//         Commit the batch
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(LoginActivity.this, "已添加完成" + t1Ref.getId(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "DocumentSnapshot added with ID: " + t1Ref.getId());
            }
        });
    }
}