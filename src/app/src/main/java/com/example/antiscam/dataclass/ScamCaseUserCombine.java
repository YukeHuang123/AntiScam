package com.example.antiscam.dataclass;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.bean.User;
import com.example.antiscam.tool.DataLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class ScamCaseUserCombine {

    public ScamCaseUserCombine() {
    }

    public static void loadScamCases(DataLoadCallback callback) {
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        userDaoImpl.getAllUsers(new UserDao.UserCallback() { // 注意：你需要为这个方法定义一个新的回调接口
            @Override
            public void onUserReceived(User user) {

            }

            @Override
            public void onUsersReceived(List<User> users) {
                allUsers.addAll(users);

                // 然后，当所有的用户都被加载后，获取ScamCases
                scamCaseDaoImpl.getAllScamCase(new ScamCaseDao.ScamCaseCallback() {
                    @Override
                    public void onScamCaseReceived(ScamCase scamcase) {
                        String post_user = scamcase.getPost_user();

                        // 从预加载的用户列表中查找匹配的用户
                        User matchedUser = null;
                        for (User user : allUsers) {
                            if (user.getEmail().equals(post_user)) { // 假设你的User对象有一个getEmail()方法
                                matchedUser = user;
                                break;
                            }
                        }

                        if (matchedUser != null) {
                            ScamCaseWithUser scamCaseWithUser = new ScamCaseWithUser(scamcase, matchedUser);
                            dataList.add(scamCaseWithUser);

                            // 你可以继续使用你的判断逻辑，或者调整它以适应你的需求
                            if (dataList.size() == 100) {
                                callback.onDataLoaded(dataList);
                            }
                        }
                    }
                });
            }
        });
//        return dataList;
    }

}
