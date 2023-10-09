package com.example.antiscam.singleton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseAuthSingleton {
    private static FirebaseAuth mAuthInstance;

    private FirebaseAuthSingleton() {
    }

    public static synchronized FirebaseAuth getInstance() {
        if (mAuthInstance == null) {
            mAuthInstance = FirebaseAuth.getInstance();
        }
        return mAuthInstance;
    }
}
