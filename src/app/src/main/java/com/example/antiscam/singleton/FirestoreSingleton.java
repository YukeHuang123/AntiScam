package com.example.antiscam.singleton;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreSingleton {
    private static FirebaseFirestore mFirestoreInstance;

    private FirestoreSingleton() {}

    public static synchronized FirebaseFirestore getInstance() {
        if (mFirestoreInstance == null) {
            mFirestoreInstance = FirebaseFirestore.getInstance();
        }
        return mFirestoreInstance;
    }
}
