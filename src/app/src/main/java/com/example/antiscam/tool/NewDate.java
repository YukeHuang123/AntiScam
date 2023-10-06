package com.example.antiscam.tool;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewDate {
    public static Date createNewDate(String day, String month, String year){
        Date date = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dateString = year + "-" + month + "-" + day;
             date= dateFormat.parse(dateString);
            Log.d("NewDate","scam date get!!!!!!");
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("NewDate","can not get new date");
        }
        return date;
    }

}
