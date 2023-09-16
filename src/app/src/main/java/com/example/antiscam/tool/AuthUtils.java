package com.example.antiscam.tool;

import android.content.Context;
import android.content.Intent;

import com.example.antiscam.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AuthUtils {

    public static void logout(Context context) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
