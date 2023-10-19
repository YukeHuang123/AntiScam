package com.example.antiscam.dao;

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

public class BlockDaoImpl implements BlockDao {
    private List<String> blockedUsers = new ArrayList<>();
    private List<String> blockers = new ArrayList<>();
    private String documentId;
    private List<String> documentIdList = new ArrayList<>();
    private BlockModel blockedData;
    private BlockModel blockingData;

    public List<String> getDocumentId(String field, String email) {
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
                                }
                            }
                        } else {
                            Log.d(TAG, "Failed to get documentId");
                        }
                    }
                });
        return documentIdList;
    }
    // Get blockers
    public List<String> getBlockedUsers(String blockerEmail) {
        //
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.clearPersistence();
        blockedUsers.clear();
        firestore.collection("block").whereEqualTo("blocker", blockerEmail)
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
    public List<String> getBlockers(String blockedUserEmail) {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.clearPersistence();
        Log.d("getBlock", "start search");
        blockers.clear();

        firestore.collection("block").whereEqualTo("blocked", blockedUserEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                documentId = document.getId();
                                blockingData = document.toObject(BlockModel.class);
                                blockers.add(blockingData.getBlocker());
                                Log.d("getBlock", "add Block log!");
                            }
                        }
                    }
                });
        return blockers;
    }
}
