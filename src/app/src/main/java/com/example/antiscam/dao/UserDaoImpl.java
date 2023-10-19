package com.example.antiscam.dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.User;
import com.example.antiscam.searchCore.Tokenizer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersCollection = db.collection("users");

    private static final String TAG = "UserDaoImpl";

    private static UserDaoImpl userDao;

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    /**
     * @author Yijing Jia u7566045
     */
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
                                Log.d(TAG, document.getId() + " => " + document.getData() + "=>" + targetEmail);
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

    public void getAllUsers(UserDao.UserCallback usersCallback) {
        usersCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<User> users = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        User user = document.toObject(User.class);
                        users.add(user);
                    }
                    usersCallback.onUsersReceived(users);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    @Override
    public void getAllUsers(Tokenizer tokenizer, UserDao.UserCallback usersCallback) {
        usersCollection.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    System.out.println("users-------------");
                    List<User> users = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        User user = document.toObject(User.class);
                        users.add(user);
                    }
                    usersCallback.onUsersReceived(users);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    /**
     * @author Yijing Jia u7566045
     */
    public DocumentReference userDetails(String DocumentID) {
        return FirebaseFirestore.getInstance().collection("users").document(DocumentID);
    }

}

