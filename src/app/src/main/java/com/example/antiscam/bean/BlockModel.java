package com.example.antiscam.bean;

public class BlockModel {
    private String blocker;
    private String blocked;

    public BlockModel() {
    }

    public BlockModel(String blocker, String blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
    }

    public String getBlocker() {
        return blocker;
    }

    public void setBlocker(String blocker) {
        this.blocker = blocker;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }
}
