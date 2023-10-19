package com.example.antiscam.act;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.dao.ScamCaseDao;
import com.example.antiscam.dao.ScamCaseDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author Yuke Huang, u7618794
 */
public class AddPostPage extends AppCompatActivity {

    private final ScamCaseDaoImpl scamCaseDaoImpl = ScamCaseDaoImpl.getInstance();
    private static final String TAG = "AddPostPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post_page);
        ImageView close = findViewById(R.id.close2);
        close.setOnClickListener(view -> onBackPressed());

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get user's email
                String user = null;
                Intent intent = getIntent();
                if (intent.hasExtra("user")) {
                    user = intent.getStringExtra("user");
                }

                //find editText and spinner by id
                EditText age = findViewById(R.id.editAge);
                EditText amount = findViewById(R.id.editAmount);
                EditText description = findViewById(R.id.editDescription);
                EditText day = findViewById(R.id.DD);
                EditText month = findViewById(R.id.MM);
                EditText year = findViewById(R.id.YYYY);
                EditText title = findViewById(R.id.editTitle);
                Spinner spinnerType = findViewById(R.id.spinner_type);
                Spinner spinnerPayment = findViewById(R.id.spinner_payment);
                Spinner spinnerCity = findViewById(R.id.spinner_city);
                Spinner spinnerContact = findViewById(R.id.spinner_contact);


                //get all the input details from add-Post page
                String age1 = getTextString(age);
                String amount1 = getTextString(amount);
                String description1 = getTextString(description);
                String day1 = getTextString(day);
                String month1 = getTextString(month);
                String year1 = getTextString(year);
                String title1 = getTextString(title);
                String type = getSpinnerString(spinnerType);
                String payment = getSpinnerString(spinnerPayment);
                String city = getSpinnerString(spinnerCity);
                String contact = getSpinnerString(spinnerContact);

                StringBuilder typeBuilder = new StringBuilder(type);
                if (!type.equals("unexpected money")) {
                    typeBuilder.append(" scams");
                }
                String typeNew = typeBuilder.toString();

                /**
                 * check whether all the fields are filled.
                 * check the format of user's input on the EditText
                 * check the validation of input date
                 */
                if (age1.isEmpty() || amount1.isEmpty() || description1.isEmpty() || day1.isEmpty() || month1.isEmpty() || year1.isEmpty() || title1.isEmpty()) {
                    Toast.makeText(AddPostPage.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else if (!checkAge(age1) || !checkDay(day1) || !checkMonth(month1) || !checkYear(year1)) {
                    Toast.makeText(AddPostPage.this, "Date or age is not valid!", Toast.LENGTH_SHORT).show();
                } else {
                    // Retrieve the nextId value
                    String finalUser1 = user;
                    scamCaseDaoImpl.getNextId(new ScamCaseDao.NextIdCallback() {
                        @Override
                        public void onNextId(int nextId) {
                            // Create the ScamCase object with the retrieved nextId
                            ScamCase scamCase = makeScamCase(nextId, amount1, contact, day1, month1, year1, description1, payment, finalUser1, typeNew, title1, age1, city);
                            Log.d(TAG, "set id successfully: " + nextId);

                            // Store the new case in Firebase
                            scamCaseDaoImpl.addScamCase(scamCase);

                            // Update the NextID
                            scamCaseDaoImpl.updateNextId(new ScamCaseDao.NextIdCallback() {
                                @Override
                                public void onNextId(int updatedNextId) {
                                    Log.d(TAG, "Updated NextID: " + updatedNextId);
                                }
                            });

                            Log.d(TAG, "add data + update NextID");

                            //go to SubmitSuccessPage
                            Intent intent2 = new Intent(AddPostPage.this, SubmitSuccessPage.class);
                            startActivity(intent2);
                        }
                    });
                }
            }
        });
    }

    public String getTextString(EditText editText){
        return editText.getText().toString().trim();
    }
    public String getSpinnerString(Spinner spinner){
        return spinner.getSelectedItem().toString().trim();
    }
    public boolean checkDay(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 31;
        }
        return false;
    }
    public boolean checkMonth(String s){
        if(s.matches("^[0-9]{2}$")){
            return Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 12;
        }
        return false;
    }
    public boolean checkYear(String s){
        if(s.matches("^[0-9]{4}$")){
            return Integer.parseInt(s) >= 1970 && Integer.parseInt(s) <= 2023;
        }
        return false;
    }
    public boolean checkAge(String s){
        if(s.matches("^[0-9]{1}$")||s.matches("^[0-9]{2}$")){
            int age=Integer.parseInt(s);
            return age<100;
        }
        return false;
    }
    public ScamCase makeScamCase(int id,String amount,String contact,String day, String month, String year,String description,String payment, String user,String type, String title, String age, String city){
        ScamCase scamCase=new ScamCase();
        double amount1 = Double.parseDouble(amount);
        scamCase.setAmount(amount1);
        scamCase.setContactMethod(contact);
        scamCase.setScam_id(id);
        Date scamDate = createNewDate(day, month, year);
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
    public Date createNewDate(String day, String month, String year){
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