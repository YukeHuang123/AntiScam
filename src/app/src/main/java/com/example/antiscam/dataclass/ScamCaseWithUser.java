package com.example.antiscam.dataclass;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.User;

public class ScamCaseWithUser {
    private ScamCase scamCase;
    private User user;

    public ScamCaseWithUser(ScamCase scamCase, User user) {
        this.scamCase = scamCase;
        this.user = user;
    }

    public ScamCase getScamCase() {
        return scamCase;
    }

    public User getUser() {
        return user;
    }
}
