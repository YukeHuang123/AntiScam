package com.example.antiscam.dataclass;

import com.example.antiscam.bean.User;

public interface UserDao {
    void getUserByEmail(String targetEmail, UserCallback userCallback);

    interface UserCallback {
        void onUserReceived(User user);
    }
}
