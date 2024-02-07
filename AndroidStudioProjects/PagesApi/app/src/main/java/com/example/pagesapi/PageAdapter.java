package com.example.pagesapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass.Data> pageList;

    public PageAdapter(Context context, ArrayList<ModelClass.Data> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public PageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PageAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.id.setText(pageList.get(position).getId().toString());
        holder.email.setText(pageList.get(position).getEmail());
       holder.first_name.setText(pageList.get(position).getFirst_name());
       holder.last_name.setText(pageList.get(position).getLast_name());


       Picasso.with(context).load(pageList.get(position).getAvatar()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, pageList.get(position).getId().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, email, first_name, last_name, avatar;

        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.Id1);
            email = itemView.findViewById(R.id.email);
            first_name = itemView.findViewById(R.id.first_name);
            last_name = itemView.findViewById(R.id.last_name);
            avatar = itemView.findViewById(R.id.avatar);
            image = itemView.findViewById(R.id.img_api);
        }
    }
}
