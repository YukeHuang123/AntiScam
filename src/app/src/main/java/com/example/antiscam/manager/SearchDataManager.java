package com.example.antiscam.manager;

import com.example.antiscam.bean.ScamCaseWithUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchDataManager {
    private static final SearchDataManager instance = new SearchDataManager();
    private SearchDataManager(){}
    public static SearchDataManager getInstance(){
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

    /////////////////////  search result
    private List<ScamCaseWithUser> searchDatas = new ArrayList<>();
    public List<ScamCaseWithUser> getSearchDatas(){
        return searchDatas;
    }
    public void clearSearchDatas(){
        searchDatas.clear();
    }
    public void addSearchData(ScamCaseWithUser scamCaseWithUser){
        searchDatas.add(scamCaseWithUser);
    }
    public void addAllSearchDatas(Collection<ScamCaseWithUser> v){
        searchDatas.clear();
        searchDatas.addAll(v);
    }
}
