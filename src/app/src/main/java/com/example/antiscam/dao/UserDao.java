package com.example.antiscam.dao;

import com.example.antiscam.bean.User;
import com.example.antiscam.searchCore.Tokenizer;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public interface UserDao {
    void getUserByEmail(String targetEmail, UserCallback userCallback);
    void getAllUsers(UserDao.UserCallback usersCallback);
    void getAllUsers(Tokenizer tokenizer,UserDao.UserCallback usersCallback);
    interface UserCallback {
        void onUserReceived(User user);
        void onUsersReceived(List<User> users);

    }

    DocumentReference userDetails(String documentID);

}
