package com.example.antiscam.dataclass;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserDaoImpl implements UserDao {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection= db.collection("users");

    private static final String TAG = "UserDaoImpl";

    // Get user by email
    @Override
    public void getUserByEmail(String targetEmail, UserDao.UserCallback userCallback) {
        usersCollection.whereEqualTo("email", targetEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                // get document data
                                User user = document.toObject(User.class);
                                // pass user class to callback function
                                userCallback.onUserReceived(user);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    // 回调接口，用于将获取的用户数据传递出去
    public interface UserCallback {
        void onUserReceived(User user);
    }
}

