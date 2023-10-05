package com.example.antiscam.builder;


import com.example.antiscam.bean.ScamCase;

import java.util.Date;

public abstract class Builder {
    public abstract void putDate(Date date);
    public abstract void putAmount(double amount);
    public abstract void putCity(String city);
    public abstract void putAge(int age);
    public abstract void putPostDate(Date postDate);
    public abstract void putScamId(int scamId);
    public abstract void putUser(String user);
    public abstract void putDescription(String description);
    public abstract void putPaymentMethod(String paymentMethod);
    public abstract void putTitle(String title);
    public abstract void putType (String type);
    public abstract void putContactMethod (String contactMethod);
    public abstract ScamCase getScamCase();
}
