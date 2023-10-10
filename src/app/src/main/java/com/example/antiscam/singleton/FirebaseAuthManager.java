package com.example.antiscam.singleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.antiscam.act.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {
    private static FirebaseAuth auth;

    private static FirebaseUser user;

    private FirebaseAuthManager() {
    }

    public FirebaseAuth getInstance() {
        return FirebaseAuth.getInstance();
    }

    public static void signIn(String email, String password, SignInCallback callback) {
        String TAG = "EmailPassword";
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseAuthManager.user = auth.getCurrentUser();;
                        callback.onSuccess(user);
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        callback.onFailure(task.getException());
                    }
                });
    }

    public static void logout(Context context) {
        FirebaseAuth.getInstance().signOut();

        user = null;
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public interface SignInCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }
}
