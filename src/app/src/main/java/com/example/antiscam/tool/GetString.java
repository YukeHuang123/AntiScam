package com.example.antiscam.tool;

import android.widget.EditText;
import android.widget.Spinner;

public class GetString {
    public static String getTextString(EditText editText){
        return editText.getText().toString().trim();
    }
    public static String getSpinnerString(Spinner spinner){
        return spinner.getSelectedItem().toString().trim();
    }
}
