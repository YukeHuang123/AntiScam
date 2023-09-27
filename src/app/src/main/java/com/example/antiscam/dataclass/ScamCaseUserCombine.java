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
        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
        scamCaseDaoImpl.getAllScamCase(new ScamCaseDao.ScamCaseCallback() {
            @Override
            public void onScamCaseReceived(ScamCase scamcase) {
                String post_user = scamcase.getPost_user();
                UserDaoImpl userDaoImpl = new UserDaoImpl();



                userDaoImpl.getUserByEmail(post_user, new UserDao.UserCallback(){
                    @Override
                    public void onUserReceived(User user) {
                        ScamCaseWithUser scamCaseWithUser = new ScamCaseWithUser(scamcase, user);
                        dataList.add(scamCaseWithUser);

                        if (dataList.size() == 100) { // 这里假设你确实只期望100个条目
                            callback.onDataLoaded(dataList);
                        }
                    }
                });
            }
        });
//        return dataList;
    }

}
