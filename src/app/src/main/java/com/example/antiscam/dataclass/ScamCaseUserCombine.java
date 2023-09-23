package com.example.antiscam.dataclass;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.User;
import com.example.antiscam.bean.ScamCaseWithUser;

import java.util.ArrayList;
import java.util.List;

public class ScamCaseUserCombine {

    public ScamCaseUserCombine() {
    }

    public static List<ScamCaseWithUser> loadScamCases() {
        List<ScamCaseWithUser> dataList = new ArrayList<>();
        ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
        scamCaseDaoImpl.getAllScamCase(new ScamCaseDao.ScamCaseCallback() {
            @Override
            public void onScamCaseReceived(ScamCase scamcase) {
                String post_user = scamcase.getPost_user();
                UserDaoImpl userDaoImpl = new UserDaoImpl();
                userDaoImpl.getUserByEmail(post_user, new UserDao.UserCallback() {
                    @Override
                    public void onUserReceived(User user) {
                        ScamCaseWithUser scamCaseWithUser = new ScamCaseWithUser(scamcase, user);
                        dataList.add(scamCaseWithUser);
                    }
                });
            }
        });
        return dataList;
    }
}
