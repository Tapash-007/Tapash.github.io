package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelAp> memesList;

    public ListAdapter(Context context, ArrayList<ModelAp> memesList) {
        this.context = context;
        this.memesList = memesList;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(context).load(memesList.get(position).image).into(holder.image);
        holder.Id.setText(memesList.get(position).Id);
        holder.name.setText(memesList.get(position).name);
        holder.width.setText(memesList.get(position).width);
        holder.height.setText(memesList.get(position).height);
        holder.box_count.setText(memesList.get(position).box_count);
        holder.captions.setText(memesList.get(position).captions);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,memesList.get(position).toString(), Toast.LENGTH_SHORT);

                Intent intent = new Intent(context,details_info.class);
                intent.putExtra("image",memesList.get(position).image);
                intent.putExtra("Id",memesList.get(position).Id);
                intent.putExtra("name",memesList.get(position).name);
                intent.putExtra("width",memesList.get(position).width);
                intent.putExtra("height",memesList.get(position).height);
                intent.putExtra("box_count",memesList.get(position).box_count);
                intent.putExtra("captions",memesList.get(position).captions);

                context.startActivity(intent);

            }
        });

    }





    @Override
    public int getItemCount() {
        return memesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Id, name, width, height, box_count, captions;

        ImageView image;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            Id = itemView.findViewById(R.id.Id);
            name = itemView.findViewById(R.id.name);
            width = itemView.findViewById(R.id.width);
            height = itemView.findViewById(R.id.height);
            box_count = itemView.findViewById(R.id.box_count);
            captions = itemView.findViewById(R.id.captions);
            image = itemView.findViewById(R.id.img_api);


        }
    }
}