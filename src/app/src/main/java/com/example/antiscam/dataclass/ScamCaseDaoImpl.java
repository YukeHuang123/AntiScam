package com.example.antiscam.dataclass;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.ScamCase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ScamCaseDaoImpl implements ScamCaseDao{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection= db.collection("scam_cases");
    private static final String TAG = "ScamCaseDaoImpl";

    @Override
    public void getAllScamCase(ScamCaseCallback scamCaseCallback) {
        usersCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            // get document data
                            ScamCase scamCase = document.toObject(ScamCase.class);
                            // pass user class to callback function
                            scamCaseCallback.onScamCaseReceived(scamCase);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error querying all scam cases", e);
                    }
                });
    }
}
