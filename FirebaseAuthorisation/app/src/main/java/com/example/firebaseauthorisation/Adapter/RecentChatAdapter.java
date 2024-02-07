package com.example.firebaseauthorisation.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.model.ChatRecModel;
import com.example.firebaseauthorisation.ui.ChatInterface;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecentChatAdapter extends RecyclerView.Adapter<RecentChatAdapter.ViewHolder> {

    Context context;

    ArrayList<ChatRecModel> RecChat = new ArrayList<>();

    String userId;

    public RecentChatAdapter(Context context, ArrayList<ChatRecModel> recChat, String userId) {
        this.context = context;
        this.RecChat = recChat;
        this.userId = userId;
    }

    @NonNull
    @Override
    public RecentChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view_chat,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentChatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(RecChat.get(position).getReceiverName());
        holder.lastChat.setText(RecChat.get(position).getMessage());
        holder.time.setText(convert(RecChat.get(position).getTimeStamp()));

        Picasso.get().load(RecChat.get(position).getReceiverImage().toString()).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(context, ChatInterface.class);
                i.putExtra("name", RecChat.get(position).getReceiverName());
                i.putExtra("receiverId", RecChat.get(position).getReceiverId());
                i.putExtra("image", RecChat.get(position).getReceiverImage());

               context.startActivity(i);

            }
        });

    }

    private String convert(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(Long.parseLong(timeStamp));
    }

    @Override
    public int getItemCount() {
        return RecChat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,lastChat,time;

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameChat);
            lastChat = itemView.findViewById(R.id.lastSeen);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.image);
        }
    }
}
