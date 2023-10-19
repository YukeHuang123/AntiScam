package com.example.antiscam.tool;

import com.example.antiscam.bean.ScamCaseWithUser;

import java.util.List;

/**
 * @author Zhaoyun Xu, u7558707
 */
public interface DataLoadCallback {
    void onDataLoaded(List<ScamCaseWithUser> dataList);
}
