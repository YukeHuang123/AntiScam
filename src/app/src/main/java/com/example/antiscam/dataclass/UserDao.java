package com.example.antiscam.dataclass;

import com.example.antiscam.bean.User;

import java.util.List;

public interface UserDao {
    void getUserByEmail(String targetEmail, UserCallback userCallback);
    public void getAllUsers(UserDao.UserCallback usersCallback);
    interface UserCallback {
        void onUserReceived(User user);
        void onUsersReceived(List<User> users);

    }
}
