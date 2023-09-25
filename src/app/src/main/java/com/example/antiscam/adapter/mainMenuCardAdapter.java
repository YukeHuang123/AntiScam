package com.example.antiscam.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.caseDetail;
import com.example.antiscam.dataclass.UserInfoManager;
import com.example.antiscam.dataclass.scamCaseList;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class mainMenuCardAdapter extends RecyclerView.Adapter<mainMenuCardAdapter.CardViewHolder> {
    private List<scamCaseList> dataList;

    public mainMenuCardAdapter(List<scamCaseList> dataList) {
        this.dataList = dataList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainmenu_cardlist, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        scamCaseList data = dataList.get(position);
        holder.titleTextView.setText(data.getTitle());
        holder.descriptionTextView.setText(data.getDescription());
        holder.scamTypeTextView.setText(data.getScam_type());
        holder.usernameView.setText(data.getUser_name());
        //create link from each card to case detail page(不确定有没有用，看到请试一下）
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), caseDetail.class);
                intent.putExtra("title", data.getTitle());
                intent.putExtra("userName", data.getUser_name());
                intent.putExtra("description", data.getDescription());
                intent.putExtra("type", data.getScam_type());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        // Get image path
        String imagePath = data.getUser_avatar();
        StorageReference imageRef;
        // Get image reference and load to ImageView
        try {
            StorageReference useravatar = UserInfoManager.getUserAvatar(imagePath);
//            UserInfoManager.loadUserAvatar(storageRef.child(imagePath), holder.avatarView);
            UserInfoManager.loadUserAvatar(useravatar, holder.avatarView);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
