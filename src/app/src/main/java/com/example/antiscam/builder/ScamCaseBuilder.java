package com.example.antiscam.builder;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.tool.NewDate;

import java.util.Date;

public class ScamCaseBuilder {


    public static ScamCase makeScamCase(int id,String amount,String contact,String day, String month, String year,String description,String payment, String user,String type, String title, String age, String city){
        ScamCase scamCase=new ScamCase();
        double amount1 = Double.parseDouble(amount);
        scamCase.setAmount(amount1);
        scamCase.setContactMethod(contact);
        scamCase.setScam_id(id);


        Date scamDate = NewDate.createNewDate(day, month, year);
        scamCase.setDate(scamDate);


        scamCase.setDescription(description);
        scamCase.setPaymentMethod(payment);
        scamCase.setPost_user(user);
        scamCase.setScam_type(type);
        scamCase.setTitle(title);


        int age1 = Integer.parseInt(age);
        scamCase.setVictim_age(age1);
        Date postDate=new Date();

        scamCase.setVictim_city(city);
        scamCase.setPost_date(postDate);

        return scamCase;
    }

}
