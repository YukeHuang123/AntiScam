package com.example.antiscam.dataclass;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.ScamCase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ScamCaseDaoImpl implements ScamCaseDao{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection= db.collection("scam_cases");
    private static final String TAG = "ScamCaseDaoImpl";

    @Override
    public void getAllScamCase(ScamCaseCallback scamCaseCallback) {
        usersCollection.orderBy("title", Query.Direction.ASCENDING).limit(100).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData().get("post_user"));
                                // get document data
                                ScamCase scamCase = document.toObject(ScamCase.class);
                                // pass user class to callback function
                                scamCaseCallback.onScamCaseReceived(scamCase);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
