package com.example.memes.controller;

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

import com.example.memes.GetDetails;
import com.example.memes.R;
import com.example.memes.model.ModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass.Data.Memes> memesList;

    public MemesAdapter(Context context, ArrayList<ModelClass.Data.Memes> memesList) {
        this.context = context;
        this.memesList = memesList;
    }

    @NonNull
    @Override
    public MemesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_view,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MemesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ModelClass.Data.Memes item = new ModelClass.Data.Memes();
        item = memesList.get(position);

        holder.id.setText(item.getId());
        holder.name.setText(item.getName());
        holder.width.setText(item.getWidth());
        holder.height.setText(item.getHeight());
        holder.box_count.setText(item.getBox_count());

        Picasso.with(context).load(memesList.get(position).getUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent(context, GetDetails.class);
               intent.putExtra("id",memesList.get(position).getId());
                 intent.putExtra("name",memesList.get(position).getName());
                intent.putExtra("width", memesList.get(position).getWidth());
                intent.putExtra("height",memesList.get(position).getHeight());
                intent.putExtra("box_count",memesList.get(position).getBox_count());
                intent.putExtra("image",memesList.get(position).getUrl());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return memesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

     TextView id,name,width,height,box_count;

     ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            id = itemView.findViewById(R.id.Id);
            name = itemView.findViewById(R.id.name);
            width = itemView.findViewById(R.id.width);
            height = itemView.findViewById(R.id.height);
            box_count = itemView.findViewById(R.id.box_count);

        }
    }
}
