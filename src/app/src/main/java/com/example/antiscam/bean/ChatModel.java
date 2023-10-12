package com.example.antiscam.bean;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class ChatModel {

    public static final int SEND = 0;
    public static final int RECEIVE = 1;

    private String sendUserEmail;
    private String sendUserImg;
    private String sendUserName;
    private String receiveUserEmail;
    private String receiveUserImg;
    private String receiveUserName;

    private String content;
    private Long sendTime = System.currentTimeMillis();
    private int type;


    public ChatModel() {
    }

    public ChatModel(String sendUserEmail, String sendUserImg, String sendUserName, String receiveUserEmail, String receiveUserImg, String receiveUserName, String content, int type) {
        this.sendUserEmail = sendUserEmail;
        this.sendUserImg = sendUserImg;
        this.sendUserName = sendUserName;
        this.receiveUserEmail = receiveUserEmail;
        this.receiveUserImg = receiveUserImg;
        this.receiveUserName = receiveUserName;
        this.content = content;
        this.type = type;
    }

    public String getSendUserEmail() {
        return sendUserEmail;
    }

    public String getSendUserImg() {
        return sendUserImg;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public String getReceiveUserEmail() {
        return receiveUserEmail;
    }

    public String getReceiveUserImg() {
        return receiveUserImg;
    }
    public void setReceiveUserName(String userName){
        this.receiveUserName = userName;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public String getContent() {
        return content;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatModel{" +
                "sendUserEmail='" + sendUserEmail + '\'' +
                ", sendUserImg='" + sendUserImg + '\'' +
                ", sendUserName='" + sendUserName + '\'' +
                ", receiveUserEmail='" + receiveUserEmail + '\'' +
                ", receiveUserImg='" + receiveUserImg + '\'' +
                ", receiveUserName='" + receiveUserName + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", type=" + type +
                '}';
    }
}
