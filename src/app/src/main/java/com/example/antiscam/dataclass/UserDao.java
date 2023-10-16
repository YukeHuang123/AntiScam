package com.example.antiscam.dataclass;

import com.example.antiscam.bean.User;
import com.example.antiscam.core.Tokenizer;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public interface UserDao {
    void getUserByEmail(String targetEmail, UserCallback userCallback);
    void getAllUsers(UserDao.UserCallback usersCallback);
    interface UserCallback {
        void onUserReceived(User user);
        void onUsersReceived(List<User> users);

    }
    void getAllUsers(Tokenizer tokenizer,UserDao.UserCallback usersCallback);
    DocumentReference userDetails(String documentID);

}
