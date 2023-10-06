package com.example.antiscam.adapter;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.UserInfoManager;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ScamCaseCardProfileAdapter extends ScamCaseCardAdapter {
    private OnDelBtnClickListener onDelBtnClickListener;
    private String userEmail;

    public ScamCaseCardProfileAdapter(List<ScamCaseWithUser> dataList, int layoutResourceID, String userEmail) {
        super(dataList, layoutResourceID);
        this.userEmail = userEmail;
    }

    public void setOnDelBtnClickListener(OnDelBtnClickListener listener) {
        this.onDelBtnClickListener = listener;
    }

    public interface OnDelBtnClickListener {
        void onDelBtnClick(int position, ScamCaseWithUser scamCaseWithUser);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ScamCaseWithUser scamCaseWithUser = dataList.get(position);

        holder.titleTextView.setText(scamCaseWithUser.getScamCase().getTitle());
        holder.descriptionTextView.setText(scamCaseWithUser.getScamCase().getDescription());
        holder.scamTypeTextView.setText(scamCaseWithUser.getScamCase().getScam_type());
        holder.usernameView.setText(scamCaseWithUser.getUser().getUsername());

        // Set an OnClickListener for the item view(ScamCaseCardAdapter)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    int position1 = holder.getAdapterPosition();
                    onClickListener.onItemClick(position1, scamCaseWithUser);
                }
            }
        });

        holder.itemView.findViewById(R.id.delBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 处理点击头像事件并跳转到个人资料页面
                if (onDelBtnClickListener != null) {
                    int position1 = holder.getAdapterPosition();
                    onDelBtnClickListener.onDelBtnClick(position1, scamCaseWithUser);
                }
            }
        });

        // Get image path
        String imagePath = scamCaseWithUser.getUser().getAvatar();
        StorageReference imageRef;
        // Get image reference and load to ImageView
        try {
            StorageReference useravatar = UserInfoManager.getUserAvatar(imagePath);
//            UserInfoManager.loadUserAvatar(storageRef.child(imagePath), holder.avatarView);
            UserInfoManager.loadUserAvatar(useravatar, holder.avatarView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String authUserEmail = UserInfoManager.getAuthUserEmail();
        // Delete button visible on auth user's Profile page
        Button deleteBtn = holder.itemView.findViewById(R.id.delBtn);
        if (deleteBtn != null) {
            String userEmail = scamCaseWithUser.getUser().getEmail();

            if (authUserEmail != null && authUserEmail.equals(userEmail)) {
                deleteBtn.setVisibility(View.VISIBLE);
            } else {
                deleteBtn.setVisibility(View.GONE);
            }
        }
    }
}

