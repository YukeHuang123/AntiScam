package com.example.antiscam.builder;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.tool.NewDate;

import java.util.Date;

public class WholeScamCase {
    private ScamCaseBuilder scamCaseBuilder;

    public void setScamCaseBuilder(ScamCaseBuilder scamCaseBuilder) {
        this.scamCaseBuilder = scamCaseBuilder;
    }

    public ScamCase makeScamCase(String amount,String contact,String day, String month, String year,String description,String payment, String user, int id,String type, String title, String age, String city){
        double amount1 = Double.parseDouble(amount);
        scamCaseBuilder.putAmount(amount1);
        scamCaseBuilder.putContactMethod(contact);

        Date scamDate = NewDate.createNewDate(day, month, year);
        scamCaseBuilder.putDate(scamDate);
        scamCaseBuilder.putDescription(description);
        scamCaseBuilder.putPaymentMethod(payment);
        scamCaseBuilder.putUser(user);
        scamCaseBuilder.putScamId(id);
        scamCaseBuilder.putType(type);
        scamCaseBuilder.putTitle(title);

        int age1 = Integer.parseInt(age);
        scamCaseBuilder.putAge(age1);
        scamCaseBuilder.putCity(city);

        Date postDate=new Date();
        scamCaseBuilder.putPostDate(postDate);


        //Date postDay=
        return scamCaseBuilder.getScamCase();
    }

}
