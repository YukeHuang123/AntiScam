package com.example.antiscam.dataclass;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.User;

public interface ScamCaseDao {
    void getAllScamCase(ScamCaseCallback scamCaseCallback);
    interface ScamCaseCallback {
        void onScamCaseReceived(ScamCase scamcase);
    }
}
