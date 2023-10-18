package com.example.antiscam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antiscam.R;
import com.example.antiscam.bean.ChatModel;
import com.example.antiscam.dataclass.UserInfoManager;
import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    LinkedList<ChatModel> chatModelList;

    public ChatAdapter(LinkedList<ChatModel> chatModelList) {
        this.chatModelList = chatModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatModel chatModel = chatModelList.get(position);
        if (chatModel.getType() == ChatModel.SEND){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightNickView.setText(chatModel.getSendUserName());
            holder.rightContentView.setText(chatModel.getContent());
            loadImage(chatModel.getSendUserImg(),holder.rightImgView);
        }else {
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.leftNickView.setText(chatModel.getReceiveUserName());
            holder.leftContentView.setText(chatModel.getContent());
            loadImage(chatModel.getSendUserImg(),holder.leftImgView);
        }
    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    private void loadImage(String path,ImageView view){
        try {
            StorageReference useravatar = UserInfoManager.getUserAvatar(path);
            UserInfoManager.loadUserAvatar(useravatar, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView leftImgView;
        TextView leftNickView;
        TextView leftContentView;
        LinearLayout leftLayout;

        ImageView rightImgView;
        TextView rightNickView;
        TextView rightContentView;
        LinearLayout rightLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout = itemView.findViewById(R.id.left_layout);
            leftImgView = itemView.findViewById(R.id.left_layout_img);
            leftNickView = itemView.findViewById(R.id.left_layout_nick);
            leftContentView = itemView.findViewById(R.id.left_layout_content);
            rightLayout = itemView.findViewById(R.id.right_layout);
            rightImgView = itemView.findViewById(R.id.right_layout_img);
            rightNickView = itemView.findViewById(R.id.right_layout_nick);
            rightContentView = itemView.findViewById(R.id.right_layout_content);
        }
    }
}
