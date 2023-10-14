package com.example.antiscam.act;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.antiscam.builder.ScamCaseBuilder;
import com.example.antiscam.databinding.ActivityCaseDetailBinding;
import com.example.antiscam.dataclass.ScamCaseDao;
import com.example.antiscam.dataclass.ScamCaseDaoImpl;
import com.example.antiscam.tool.CheckInput;
import com.example.antiscam.tool.GetString;

public class AddPostPage extends AppCompatActivity {

    ScamCaseDaoImpl scamCaseDaoImpl = new ScamCaseDaoImpl();
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
                //set scam id


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
                String age1 = GetString.getTextString(age);
                String amount1 = GetString.getTextString(amount);
                String description1 = GetString.getTextString(description);
                String day1 = GetString.getTextString(day);
                String month1 = GetString.getTextString(month);
                String year1 = GetString.getTextString(year);
                String title1 = GetString.getTextString(title);
                String type = GetString.getSpinnerString(spinnerType);
                String payment = GetString.getSpinnerString(spinnerPayment);
                String city = GetString.getSpinnerString(spinnerCity);
                String contact = GetString.getSpinnerString(spinnerContact);


                /**
                 * create instance of ScamCase and change String input to correct type within makeScamCase method
                 */
                String finalUser = user;


                /**
                 * check whether all the fields are filled.
                 * check the format of user's input on the EditText
                 * check the validation of input date
                 */
                if (age1.isEmpty() || amount1.isEmpty() || description1.isEmpty() || day1.isEmpty() || month1.isEmpty() || year1.isEmpty() || title1.isEmpty()) {
                    Toast.makeText(AddPostPage.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else if (!CheckInput.checkAge(age1) || !CheckInput.checkDay(day1) || !CheckInput.checkMonth(month1) || !CheckInput.checkYear(year1)) {
                    Toast.makeText(AddPostPage.this, "Date or age is not valid!", Toast.LENGTH_SHORT).show();
                } else {
                    // Retrieve the nextId value
                    String finalUser1 = user;
                    scamCaseDaoImpl.getNextId(new ScamCaseDao.NextIdCallback() {
                        @Override
                        public void onNextId(int nextId) {
                            // Create the ScamCase object with the retrieved nextId
                            ScamCase scamCase = ScamCaseBuilder.makeScamCase(nextId, amount1, contact, day1, month1, year1, description1, payment, finalUser1, type, title1, age1, city);
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
}