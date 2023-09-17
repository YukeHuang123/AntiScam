package com.example.antiscam.bean;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class ScamCase {

    private Date date;

    private float amount;

    private String victim_city;

    private int victim_age;

    private int scam_id;

    private int post_user;

    private String description;

    private String paymentMethod;

    private String title;

    private String scam_type;

    private String contactMethod;

    public ScamCase() {
        // Default constructor required for calls to DataSnapshot.getValue(ScamCase.class)
    }

    public ScamCase(Date date, float amount, String victim_city, int victim_age, int scam_id, int post_user, String description, String paymentMethod, String title, String scam_type, String contactMethod) {
        this.date = date;
        this.amount = amount;
        this.victim_city = victim_city;
        this.victim_age = victim_age;
        this.scam_id = scam_id;
        this.post_user = post_user;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.title = title;
        this.scam_type = scam_type;
        this.contactMethod = contactMethod;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getVictim_city() {
        return victim_city;
    }

    public void setVictim_city(String victim_city) {
        this.victim_city = victim_city;
    }

    public int getVictim_age() {
        return victim_age;
    }

    public void setVictim_age(int victim_age) {
        this.victim_age = victim_age;
    }

    public int getScam_id() {
        return scam_id;
    }

    public void setScam_id(int scam_id) {
        this.scam_id = scam_id;
    }

    public int getPost_user() {
        return post_user;
    }

    public void setPost_user(int post_user) {
        this.post_user = post_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScam_type() {
        return scam_type;
    }

    public void setScam_type(String scam_type) {
        this.scam_type = scam_type;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
}
