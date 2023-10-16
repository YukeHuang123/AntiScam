package com.example.antiscam.dataclass;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.core.TokenHelper;
import com.example.antiscam.core.Tokenizer;
import com.example.antiscam.singleton.FirestoreSingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.LinkedList;

public class ScamCaseDaoImpl implements ScamCaseDao {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();;
    private static ScamCaseDaoImpl scamCaseDao;
    private CollectionReference casesCollection = db.collection("scam_cases");
    private CollectionReference counter = db.collection("counter");
    DocumentReference nextIdDocument = counter.document("caseID");

    private static final String TAG = "ScamCaseDaoImpl";

    private ScamCaseDaoImpl() {
    }

    public static ScamCaseDaoImpl getInstance() {
        if (scamCaseDao == null) {
            scamCaseDao = new ScamCaseDaoImpl();
        }
        return scamCaseDao;
    }

    @Override
    public void getAllScamCase(ScamCaseCallback scamCaseCallback) {
        casesCollection.orderBy("post_date", Query.Direction.DESCENDING).limit(100).get()
//        usersCollection.limit(100).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData().get("post_user"));
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

    @Override
    public void getAllScamCases(Tokenizer tokenizer, ScamCasesCallback scamCasesCallback) {
        Query query = TokenHelper.getInstance().genQuery(casesCollection, tokenizer);
        query.limit(100).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            LinkedList<ScamCase> scamCases = new LinkedList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ScamCase scamCase = document.toObject(ScamCase.class);
                                scamCases.add(scamCase);
                            }
                            scamCasesCallback.onScamCaseReceived(scamCases);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    @Override
    public void addScamCase(ScamCase scamcase) {
        casesCollection
                .add(scamcase)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "ScamCase added successfully");
                    } else {
                        Log.e(TAG, "Error adding ScamCase", task.getException());
                    }
                });

    }

    public void getNextId(NextIdCallback callback) {
        //callback is a interface
        nextIdDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Get the value of the "nextID" field as an integer
                        int nextId = document.getLong("nextID").intValue();
                        Log.d(TAG, "Next ID: " + nextId);
                        // Pass the result back to the caller using the callback
                        //onNextId method will be implemented when calling scamCaseDaoImpl.getNextId
                        callback.onNextId(nextId);
                    } else {
                        Log.d(TAG, "do not find nextId");
                    }
                } else {
                    Log.e(TAG, "Error getting document", task.getException());
                }
            }
        });
    }
    @Override
    public void updateNextId(NextIdCallback callback) {
        nextIdDocument.update("nextID", FieldValue.increment(1))
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "Next ID incremented successfully");
                    // Fetch the updated nextID value and pass it to the callback
                    getNextId(callback);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Error incrementing nextID", e);
                });
    }

    @Override
    public void getDocumentId(int scam_id, OnDocumentIdCallback callback) {
        casesCollection
                .whereEqualTo("scam_id", scam_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String documentId = document.getId();
                                if (!documentId.isEmpty()) {
                                    callback.onDocumentIdReceived(documentId);
                                    return; // Exit the loop if a valid documentId is found.
                                }
                            }
                            callback.onDocumentIdNotFound(); // Handle the case where no matching document is found.
                        } else {
                            Log.d(TAG, "Get DocumentId Failed");
                        }
                    }
                });
    }


}
