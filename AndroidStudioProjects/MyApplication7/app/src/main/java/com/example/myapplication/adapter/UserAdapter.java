package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Model;
import com.example.myapplication.R;
import com.example.myapplication.interfac.RecyclerClick;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    List<Model> modelList;

    RecyclerClick mClick;

    public UserAdapter(Context context, List<Model> modelList, RecyclerClick mClick) {
        this.context = context;
        this.modelList = modelList;
        this.mClick = mClick;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Model item = modelList.get(position);
        holder.userName.setText(item.getUserName());
        holder.phone.setText(item.getPhone());
        holder.image.setImageDrawable(ContextCompat.getDrawable(context, item.getImage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, phone;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            phone = itemView.findViewById(R.id.phone);
            image = itemView.findViewById(R.id.image);
        }
    }

}
