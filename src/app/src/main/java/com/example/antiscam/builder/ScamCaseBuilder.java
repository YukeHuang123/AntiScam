package com.example.antiscam.builder;

import com.example.antiscam.bean.ScamCase;

import java.util.Date;

public class ScamCaseBuilder extends Builder{
    private ScamCase scamCase=new ScamCase();


    @Override
    public void putDate(Date date) {
        scamCase.setDate(date);
    }

    @Override
    public void putAmount(double amount) {
        scamCase.setAmount(amount);
    }

    @Override
    public void putCity(String city) {
        scamCase.setVictim_city(city);

    }

    @Override
    public void putAge(int age) {
        scamCase.setVictim_age(age);
    }

    @Override
    public void putPostDate(Date postDate) {
        scamCase.setPost_date(postDate);
    }

    @Override
    public void putScamId(int scamId) {
        scamCase.setScam_id(scamId);
    }

    @Override
    public void putUser(String user) {
        scamCase.setPost_user(user);
    }

    @Override
    public void putDescription(String description) {
        scamCase.setDescription(description);
    }

    @Override
    public void putPaymentMethod(String paymentMethod) {
        scamCase.setPaymentMethod(paymentMethod);

    }

    @Override
    public void putTitle(String title) {
        scamCase.setTitle(title);

    }

    @Override
    public void putType(String type) {
        scamCase.setScam_type(type);

    }

    @Override
    public void putContactMethod(String contactMethod) {
        scamCase.setContactMethod(contactMethod);

    }

    @Override
    public ScamCase getScamCase() {
        return scamCase;
    }
}
