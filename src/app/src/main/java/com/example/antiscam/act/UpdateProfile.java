package com.example.antiscam.act;

import static com.example.antiscam.dataclass.UserInfoManager.getAuthUserEmail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.antiscam.R;
import com.example.antiscam.bean.User;
import com.example.antiscam.dataclass.UserDaoImpl;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.tool.AndroidUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class UpdateProfile extends AppCompatActivity {
    ImageView updateAvatar;
    EditText usernameInput;
    Button updateProfileBtn;
    ProgressBar progressBar;
    String authUserEmail;
    String authUserName;
    String authUserAvatarPath;
    Map<String, User> currentUsers = new HashMap<>();
    User currentUser;
    String documentId;
    ActivityResultLauncher<Intent> imagePickLauncher;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updateAvatar = findViewById(R.id.avatarUpdate);
        usernameInput = findViewById(R.id.userNameUpdate);
        updateProfileBtn = findViewById(R.id.saveProfileUpdate);
        progressBar = findViewById(R.id.updateProgressBar);
        ImageView closeProfileBtn = findViewById(R.id.closeUpdateProfile);

        getUserData();

        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getData() != null) {
                            selectedImageUri = data.getData();
                            AndroidUtil.setProfilePic(getApplicationContext(), selectedImageUri, updateAvatar);
                        }
                    }
                }
        );

        updateAvatar.setOnClickListener((v -> {
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512,512)
                    .createIntent(new Function1<Intent, Unit>() {
                        @Override
                        public Unit invoke(Intent intent) {
                            imagePickLauncher.launch(intent);
                            return null;
                        }
                    });
        }));

        updateProfileBtn.setOnClickListener((v -> {
            updateBtnClick();
        }));

        // close profile
        closeProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToProfile = new Intent(UpdateProfile.this, Profile.class);
                intentToProfile.putExtra("username", currentUser.getUsername());
                intentToProfile.putExtra("email", currentUser.getEmail());
                intentToProfile.putExtra("avatarPath", currentUser.getAvatar());
                startActivity(intentToProfile);
                // close current Activity
                finish();
            }
        });
    }

    void updateBtnClick(){
        //Get user name from input
        String newUsername = usernameInput.getText().toString();
        if(newUsername.isEmpty() || newUsername.length()<3) {
            usernameInput.setError("Username length should be at least 3 characters");
            return;
        }
        currentUser.setUsername(newUsername);
        setInProgress(true);

        if (selectedImageUri != null) {
            UserInfoManager.getUserAvatar(authUserAvatarPath).putFile(selectedImageUri)
                    .addOnCompleteListener(task -> {
                        updateToFirestore();
                    });
        } else {
            updateToFirestore();
        }
    }

    void updateToFirestore(){
        //
        UserDaoImpl userDaoimpl = UserDaoImpl.getInstance();
        userDaoimpl.userDetails(documentId).set(currentUser)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                    if(task.isSuccessful()) {
                        AndroidUtil.showToast(getApplicationContext(), "Updated Successfully");
                    } else{
                        AndroidUtil.showToast(getApplicationContext(), "Updated Failed");
                    }
                });
    }
    void getUserData(){
        authUserEmail = getAuthUserEmail();
        setInProgress(true);
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("email", authUserEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            setInProgress(false);

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documentId = document.getId();
                                currentUser = document.toObject(User.class);
                                currentUsers.put(documentId, currentUser);
                                if (currentUsers.size()==1) {
                                    authUserName = currentUser.getUsername();
                                    authUserAvatarPath = currentUser.getAvatar();
                                    usernameInput.setText(authUserName);
                                    // Get image reference and load to ImageView
                                    try {
                                        StorageReference userAvatar = UserInfoManager.getUserAvatar(authUserAvatarPath);
                                        UserInfoManager.loadUserAvatar(userAvatar, updateAvatar);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            }
                        }
                    }
                });
    }
    void setInProgress(boolean inProgress) {
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}