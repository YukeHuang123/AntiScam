package com.example.antiscam.tool;

import com.example.antiscam.bean.ScamCaseWithUser;

import java.util.List;

public interface DataLoadCallback {
    void onDataLoaded(List<ScamCaseWithUser> dataList);
}
