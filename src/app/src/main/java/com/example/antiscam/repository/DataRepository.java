package com.example.antiscam.repository;

import com.example.antiscam.bean.ScamCaseWithUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataRepository {
    private static final DataRepository instance = new DataRepository();
    private DataRepository(){}
    public static DataRepository getInstance(){
        return instance;
    }
    /////////////////////  data
    private List<ScamCaseWithUser> scamCaseWithUsers = new ArrayList<>();
    public List<ScamCaseWithUser> getScamCaseWithUsers(){
        return scamCaseWithUsers;
    }
    public void clearScamWithUsers(){
        scamCaseWithUsers.clear();
    }
    public void addScamWithUsers(ScamCaseWithUser scamCaseWithUser){
        scamCaseWithUsers.add(scamCaseWithUser);
    }
    public void addAllScamCaseWithUsers(Collection<ScamCaseWithUser> v){
        scamCaseWithUsers.clear();
        scamCaseWithUsers.addAll(v);
    }
}
