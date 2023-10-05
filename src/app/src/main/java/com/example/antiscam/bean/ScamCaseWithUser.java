package com.example.antiscam.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.bean.User;

import java.io.Serializable;

public class ScamCaseWithUser implements Parcelable {
    private ScamCase scamCase;
    private User user;

    public ScamCaseWithUser(ScamCase scamCase, User user) {
        this.scamCase = scamCase;
        this.user = user;
    }

    public void setScamCase(ScamCase scamCase) {
        this.scamCase = scamCase;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected ScamCaseWithUser(Parcel in) {
        // Read data from Parcel and initialize object
        scamCase = in.readParcelable(ScamCase.class.getClassLoader());
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<ScamCaseWithUser> CREATOR = new Creator<ScamCaseWithUser>() {
        @Override
        public ScamCaseWithUser createFromParcel(Parcel in) {
            return new ScamCaseWithUser(in);
        }

        @Override
        public ScamCaseWithUser[] newArray(int size) {
            return new ScamCaseWithUser[size];
        }
    };

    public ScamCase getScamCase() {
        return scamCase;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable((Parcelable) scamCase, i);
        parcel.writeParcelable((Parcelable) user, i);
    }

    @Override
    public String toString() {
        return "ScamCaseWithUser{" +
                "scamCase=" + scamCase +
                ", user=" + user +
                '}';
    }
}
