package com.example.antiscam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private FirebaseAuth mAuth;
    private Button signInButton;
    private static final String TAG = "EmailPassword";
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress= (EditText) findViewById(R.id.EmailAddressText);
        password= (EditText) findViewById(R.id.PasswordText);
        signInButton = (Button) findViewById(R.id.loginButton);

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
}