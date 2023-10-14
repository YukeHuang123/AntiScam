package com.example.antiscam.dataclass;

import static com.example.antiscam.act.ChatActivity.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.antiscam.bean.BlockModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BlockManager {
    static List<String> blockedUsers = new ArrayList<>();
    static List<String> blockers = new ArrayList<>();
    static String documentId;
    static List<String> documentIdList = new ArrayList<>();
    static BlockModel blockedData;
    static BlockModel blockingData;

    public static String getDocumentId(String field, String email) {
        FirebaseFirestore.getInstance().collection("block")
                .whereEqualTo(field, email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String documentId = document.getId();
                                if (!documentId.isEmpty()) {
                                    documentIdList.add(documentId);
                                    break;
                                }
                            }
                        } else {
                            Log.d(TAG, "Failed to get documentId");
                        }
                    }
                });
        return documentIdList.get(0);
    }
    // Get blockers
    public static List<String> getBlockedUsers(String blockerEmail) {
        //
        FirebaseFirestore.getInstance().collection("block").whereEqualTo("blocker", blockerEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                documentId = document.getId();
                                blockedData = document.toObject(BlockModel.class);
                                blockedUsers.add(blockedData.getBlocked());
                                Log.e("getBlock", "block retrieve!");
                            }
                        }
                    }
        });
        return blockedUsers;
    }

    // Get blocked users
    public static List<String> getBlockers(String blockedUserEmail) {
        FirebaseFirestore.getInstance().collection("block").whereEqualTo("blocked", blockedUserEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                documentId = document.getId();
                                blockingData = document.toObject(BlockModel.class);
                                blockers.add(blockingData.getBlocker());
                            }
                        }
                    }
                });
        return blockers;
    }

    public static void unblock(String blockedUserEmail) {
        //

    }
}
