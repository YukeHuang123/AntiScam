package com.example.antiscam.dataclass;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.antiscam.R;
import com.example.antiscam.bean.User;
import com.example.antiscam.tool.CircleImageTransformer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserInfoManager {
    static String authUserEmail;
    static String authUserName;
    static String authUserAvatarPath;
    public static void getUserInfo(Context context, TextView userNameView, ImageView imageView) {
        // Get user information from Firebase
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String username = user.getDisplayName();
            Uri photoUrl = user.getPhotoUrl();

            // Set user name
            userNameView.setText(username);

            // Get reference of Firebase storage
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            // Get image path
            String imagePath = String.valueOf(photoUrl);

            // Get image reference
            StorageReference imageRef = storageRef.child(imagePath);

            // load image
            loadUserAvatar(imageRef, imageView);
        }
    }

    public static String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            authUserEmail = user.getEmail();
        }
        return authUserEmail;
    }

    public static void getAuthUserName(AuthUserNameCallback callback) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            authUserEmail = currentUser.getEmail();
            UserDaoImpl userDaoimpl = new UserDaoImpl();
            userDaoimpl.getUserByEmail(authUserEmail, new UserDao.UserCallback() {
                @Override
                public void onUserReceived(User user) {
                    authUserName = user.getUsername();
                    callback.onAuthUserNameReceived(authUserName);
                }

                @Override
                public void onUsersReceived(List<User> users) {

                }
            });
        }
    }

    public interface AuthUserNameCallback {
        void onAuthUserNameReceived(String authUserName);
    }
    public static String getAuthUserDisplayName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            authUserName = user.getDisplayName();
        }
        return authUserName;
    }

    public static String getAuthUserAvatarPath() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Uri photoUrl = user.getPhotoUrl();

            // Get image path
            authUserAvatarPath = String.valueOf(photoUrl);
        }
        return authUserAvatarPath;
    }

    public static StorageReference getUserAvatar(String imagePath) {
        // Get reference of Firebase storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        // Get image reference
        StorageReference imageRef = storageRef.child(imagePath);

        return imageRef;
    }

    public static void loadUserAvatar(StorageReference imageRef, ImageView imageView) {
        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // use Picasso load and transform image to circle
                Picasso.get().load(uri.toString()).transform(new CircleImageTransformer()).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                imageView.setImageResource(R.drawable.default_avatar);
            }
        });
    }
}
