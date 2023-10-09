package com.example.antiscam.dataclass;

import android.util.Log;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.bean.User;
import com.example.antiscam.core.Tokenizer;
import com.example.antiscam.tool.DataLoadCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScamCaseUserCombine {

    private static String TAG = "ScamCaseUserCombine";

    public ScamCaseUserCombine() {
    }

    public static void loadScamCases(DataLoadCallback callback) {
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        List<User> allUsers = new ArrayList<>();
        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        userDaoImpl.getAllUsers(new UserDao.UserCallback() {
            @Override
            public void onUserReceived(User user) {

            }

            @Override
            public void onUsersReceived(List<User> users) {
                allUsers.addAll(users);

                scamCaseDaoImpl.getAllScamCase(new ScamCaseDao.ScamCaseCallback() {
                    @Override
                    public void onScamCaseReceived(ScamCase scamcase) {
                        String post_user = scamcase.getPost_user();

                        User matchedUser = null;
                        for (User user : allUsers) {
//                            System.out.println("user:" + user);
                            if (user.getEmail().equals(post_user)) { // 假设你的User对象有一个getEmail()方法
                                matchedUser = user;
                                break;
                            }
                        }

                        if (matchedUser != null) {
                            ScamCaseWithUser scamCaseWithUser = new ScamCaseWithUser(scamcase, matchedUser);
                            Log.i(TAG, "onScamCaseReceived: "+scamCaseWithUser);
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

    public static void loadScamCases(Tokenizer tokenizer, DataLoadCallback callback) {
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        userDaoImpl.getAllUsers(tokenizer, new UserDao.UserCallback() { // 注意：你需要为这个方法定义一个新的回调接口
            @Override
            public void onUserReceived(User user) {

            }

            @Override
            public void onUsersReceived(List<User> users) {
                // 然后，当所有的用户都被加载后，获取ScamCases
                scamCaseDaoImpl.getAllScamCases(tokenizer,
                        new ScamCaseDao.ScamCasesCallback() {
                            @Override
                            public void onScamCaseReceived(List<ScamCase> scamcase) {
                                for (User user : users) {
                                    for (ScamCase scamCase : scamcase) {
                                        if (Objects.equals(scamCase.getPost_user(), user.getEmail()))
                                            dataList.add(new ScamCaseWithUser(scamCase, user));
                                    }
                                }
                                Log.i(TAG, "search: "+dataList);
                                callback.onDataLoaded(dataList);
                            }
                        });

            }
        });
    }
}
