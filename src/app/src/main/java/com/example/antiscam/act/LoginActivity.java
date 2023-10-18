package com.example.antiscam.act;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.manager.FirebaseAuthManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private EditText emailAddress;
    private EditText password;
    private FirebaseAuth mAuth;
    private Button logInButton;
    private Button firestoreButton;
    private static final String TAG = "EmailPassword";
    private FirebaseAuthManager firebaseAuthManager = FirebaseAuthManager.getInstance();;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress= (EditText) findViewById(R.id.EmailAddressText);
        password= (EditText) findViewById(R.id.PasswordText);
        logInButton = (Button) findViewById(R.id.loginButton);
        firestoreButton = (Button) findViewById(R.id.firestoreButton);
        TextInputLayout emailInputLayout = findViewById(R.id.emailInputLayout);
        TextInputLayout passwordInputLayout = findViewById(R.id.passwordInputLayout);


        emailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordInputLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        mAuth = FirebaseAuth.getInstance();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = emailAddress.getText().toString().trim();
                String password_text = password.getText().toString().trim();



                if (email_text.isEmpty() || password_text.isEmpty()) {
                    if (email_text.isEmpty()) {
                        emailAddress.setBackgroundResource(R.drawable.background_edittext_error);
                        emailInputLayout.setError("Email can't be empty!");
                    }

                    if (password_text.isEmpty()) {
                        password.setBackgroundResource(R.drawable.background_edittext_error);
                        passwordInputLayout.setError("password can't be empty!");
                    }

                } else {
                    logIn(email_text, password_text);
                }
            }
        });

        firestoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFirestore();
            }
       });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            updateUI(currentUser);
        }
    }

    private void logIn(String email, String password) {

        firebaseAuthManager.logIn(email, password, new FirebaseAuthManager.SignInCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                updateUI(user);
            }

            @Override
            public void onFailure(Exception exception) {
                updateUI(null);
            }
        });

    }
//
//    private void reload() { }
//
    private void updateUI(FirebaseUser user) {
        if (user == null) {
            emailAddress.setText("");
            password.setText("");
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