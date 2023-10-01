package com.example.antiscam.bean;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class ChatModel  {

    public static final int SEND = 0;
    public static final int RECEIVE = 1;
    private String img;
    private String name;
    private String content;
    private String email;
    private Long timestamp;
    private int type;


    public ChatModel(){}

    public ChatModel(String img, String name, String content, String email, Long timestamp, int type) {
        this.img = img;
        this.name = name;
        this.content = content;
        this.email = email;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", email='" + email + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }
}
