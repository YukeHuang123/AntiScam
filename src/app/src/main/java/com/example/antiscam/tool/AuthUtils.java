package com.example.antiscam.tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.antiscam.act.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AuthUtils {

    public static void logout(Context context) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}
