package com.example.firebaseauthorisation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.model.ChatModel;
import com.example.firebaseauthorisation.ui.ChatInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;

    ArrayList<ChatModel> chatList = new ArrayList<>();

    String userId;

    public ChatAdapter(Context context, ArrayList<ChatModel> chatList, String userId) {
        this.context = context;
        this.chatList = chatList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view_chatinterface, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {

        if (Objects.equals(userId, chatList.get(position).getSenderId())) {

            holder.senderLayout.setVisibility(View.VISIBLE);
            holder.receiverLayout.setVisibility(View.GONE);
            holder.sender.setText(chatList.get(position).getMessage());
            holder.time_send.setText(convert(chatList.get(position).getTimeStamp()));
        } else {

            holder.senderLayout.setVisibility(View.GONE);
            holder.receiverLayout.setVisibility(View.VISIBLE);
            holder.receiver.setText(chatList.get(position).getMessage());
            holder.timer_rec.setText(convert(chatList.get(position).getTimeStamp()));

        }
    }

    private String convert(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(Long.parseLong(timeStamp));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView receiver, sender, timer_rec, time_send;

        ImageView image;

        LinearLayout receiverLayout, senderLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            receiver = itemView.findViewById(R.id.receiver);
            sender = itemView.findViewById(R.id.sender);
            image = itemView.findViewById(R.id.img);
            timer_rec = itemView.findViewById(R.id.time_rec);
            time_send = itemView.findViewById(R.id.time_send);
            receiverLayout = itemView.findViewById(R.id.receiverLayout);
            senderLayout = itemView.findViewById(R.id.senderLayout);

        }
    }
}
