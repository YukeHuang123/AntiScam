package com.example.antiscam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.UserInfoManager;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ScamCaseCardAdapter extends RecyclerView.Adapter<ScamCaseCardAdapter.CardViewHolder> {
    List<ScamCaseWithUser> dataList = new ArrayList<>();
    private int layoutResourceID;

    int clickPosition;

    public ScamCaseCardAdapter(List<ScamCaseWithUser> dataList, int layoutResourceID) {
        this.dataList = dataList;
        this.layoutResourceID = layoutResourceID;
    }

    // Define the OnClickListener interface
    public interface OnClickListener {
        void onItemClick(int position, ScamCaseWithUser scamCase);
    }

    private OnClickListener onClickListener;

    // Method to set the OnClickListener
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    @NonNull
    @Override
    public ScamCaseCardAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResourceID, parent, false);
        return new ScamCaseCardAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        ScamCaseWithUser scamCaseWithUser = dataList.get(position);
        holder.titleTextView.setText(scamCaseWithUser.getScamCase().getTitle());
        holder.descriptionTextView.setText(scamCaseWithUser.getScamCase().getDescription());
        holder.scamTypeTextView.setText(scamCaseWithUser.getScamCase().getScam_type());
        holder.usernameView.setText(scamCaseWithUser.getUser().getUsername());

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

        // Set an OnClickListener for the item view(ScamCaseCardAdapter)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    int adapterPosition = holder.getAdapterPosition();// if remove other error come
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onClickListener.onItemClick(adapterPosition, scamCaseWithUser);
                    }
                }
            }
        });
    }

    public void setData(List<ScamCaseWithUser> newDataList) {
        this.dataList = newDataList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView scamTypeTextView;
        public TextView usernameView;
        public ImageView avatarView;

        public CardViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            scamTypeTextView = itemView.findViewById(R.id.scamTypeTextView);
            usernameView = itemView.findViewById(R.id.userName);
            avatarView = itemView.findViewById(R.id.avatarImgViewCard);
        }
    }
}
