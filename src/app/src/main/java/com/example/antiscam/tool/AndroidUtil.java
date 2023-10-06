package com.example.antiscam.tool;

import android.content.Context;
import android.widget.Toast;

public class AndroidUtil {
    // How to use
    // AndroidUtil.showToast(getApplicationContext(), "your message");
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
