package com.example.antiscam.tool;

import android.widget.EditText;
import android.widget.Spinner;

public class CheckInput {
    public static boolean checkDay(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 31;
        }
        return false;
    }
    public static boolean checkMonth(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 12;
        }
        return false;
    }
    public static boolean checkYear(String s){
        if(s.matches("^[0-9]{4}$")){
            return Integer.parseInt(s) >= 1970 && Integer.parseInt(s) <= 2023;
        }
        return false;
    }
    public static boolean checkAge(String s){
        if(s.matches("^[0-9]{1}$")||s.matches("^[0-9]{2}$")){
            int age=Integer.parseInt(s);
            return age<100;
        }
        return false;
    }

}
