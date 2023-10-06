package com.example.antiscam.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class ScamCase implements Parcelable {

    private Date date;

    private double amount;

    private String victim_city;

    private int victim_age;

    private Date post_date;

    private int scam_id;

    private String post_user;

    private String description;

    private String paymentMethod;

    private String title;

    private String scam_type;

    private String contactMethod;

    public ScamCase() {
        // Default constructor required for calls to DataSnapshot.getValue(ScamCase.class)
    }

    public ScamCase(Date date, double amount, String victim_city, int victim_age, Date post_date, int scam_id, String post_user, String description, String paymentMethod, String title, String scam_type, String contactMethod) {
        this.date = date;
        this.amount = amount;
        this.victim_city = victim_city;
        this.victim_age = victim_age;
        this.post_date = post_date;
        this.scam_id = scam_id;
        this.post_user = post_user;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.title = title;
        this.scam_type = scam_type;
        this.contactMethod = contactMethod;
    }

    public ScamCase(Date date, double amount, String victim_city, int victim_age, Date post_date, String post_user, String description, String paymentMethod, String title, String scam_type, String contactMethod) {
        this.date = date;
        this.amount = amount;
        this.victim_city = victim_city;
        this.victim_age = victim_age;
        this.post_date = post_date;
        this.post_user = post_user;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.title = title;
        this.scam_type = scam_type;
        this.contactMethod = contactMethod;
    }

    protected ScamCase(Parcel in) {
        amount = in.readDouble();
        victim_city = in.readString();
        victim_age = in.readInt();
        scam_id = in.readInt();
        post_user = in.readString();
        description = in.readString();
        paymentMethod = in.readString();
        title = in.readString();
        scam_type = in.readString();
        contactMethod = in.readString();
    }

    public static final Creator<ScamCase> CREATOR = new Creator<ScamCase>() {
        @Override
        public ScamCase createFromParcel(Parcel in) {
            return new ScamCase(in);
        }

        @Override
        public ScamCase[] newArray(int size) {
            return new ScamCase[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public int getScam_id() {
        return scam_id;
    }

    public void setScam_id(int scam_id) {
        this.scam_id = scam_id;
    }

    public String getPost_user() {
        return post_user;
    }

    public void setPost_user(String post_user) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(amount);
        parcel.writeString(victim_city);
        parcel.writeInt(victim_age);
        parcel.writeInt(scam_id);
        parcel.writeString(post_user);
        parcel.writeString(description);
        parcel.writeString(paymentMethod);
        parcel.writeString(title);
        parcel.writeString(scam_type);
        parcel.writeString(contactMethod);
    }

    @Override
    public String toString() {
        return "ScamCase{" +
                "date=" + date +
                ", amount=" + amount +
                ", victim_city='" + victim_city + '\'' +
                ", victim_age=" + victim_age +
                ", post_date=" + post_date +
                ", scam_id=" + scam_id +
                ", post_user='" + post_user + '\'' +
                ", description='" + description + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", title='" + title + '\'' +
                ", scam_type='" + scam_type + '\'' +
                ", contactMethod='" + contactMethod + '\'' +
                '}';
    }
}
