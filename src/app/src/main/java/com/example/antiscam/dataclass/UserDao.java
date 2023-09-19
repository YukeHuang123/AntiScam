package com.example.antiscam.dataclass;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserDao {
    private FirebaseFirestore db;
    private CollectionReference usersCollection;
    private static final String TAG = "UserDao";

    public UserDao() {
        db = FirebaseFirestore.getInstance();
        usersCollection = db.collection("users");
    }

    // Get user by email
    public void getUserByEmail(String targetEmail, final UserCallback userCallback) {
        usersCollection.whereEqualTo("email", targetEmail)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            // get document data
                            User user = document.toObject(User.class);
                            // pass user class to callback function
                            userCallback.onUserReceived(user);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Error querying users by email", e);
                    }
                });
    }

    // 回调接口，用于将获取的用户数据传递出去
    public interface UserCallback {
        void onUserReceived(User user);
    }
}

