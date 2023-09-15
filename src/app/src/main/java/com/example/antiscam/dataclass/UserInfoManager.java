package com.example.antiscam.dataclass;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.antiscam.R;
import com.example.antiscam.tool.CircleImageTransformer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class UserInfoManager {

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

    private static void loadUserAvatar(StorageReference imageRef, ImageView imageView) {
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
