package com.example.antiscam.singleton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.antiscam.act.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private static FirebaseAuthManager firebaseAuthManager;
    private static FirebaseAuth auth;

    private static FirebaseUser user;

    private FirebaseAuthManager() {
        auth = FirebaseAuth.getInstance();
    }

    public static FirebaseAuthManager getInstance() {
        if (firebaseAuthManager == null) {
            firebaseAuthManager = new FirebaseAuthManager(); // 如果实例不存在，则创建一个新实例
        }
        return firebaseAuthManager;
    }

    public void logIn(String email, String password, SignInCallback callback) {
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

    public void logout(Context context) {
        FirebaseAuth.getInstance().signOut();

        user = null;
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public FirebaseUser getUser() {
        if (user == null) {
            user = auth.getCurrentUser();
        }
        return user;
    }

    public interface SignInCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception exception);
    }
}
