package com.example.restaurantdatabase.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantdatabase.R;
import com.example.restaurantdatabase.model.ChefModel;

import java.util.ArrayList;

public class ChefAdapter extends RecyclerView.Adapter<ChefAdapter.ViewHolder> {

    Context context;

    ArrayList<ChefModel> chefList;

    public ChefAdapter(Context context, ArrayList<ChefModel> chefList) {
        this.context = context;
        this.chefList = chefList;
    }

    @NonNull
    @Override
    public ChefAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_chef,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefAdapter.ViewHolder holder, int position) {

        holder.name.setText(chefList.get(position).getName());
        holder.age.setText(chefList.get(position).getAge());
        holder.country.setText(chefList.get(position).getCountry());
        holder.contact.setText(chefList.get(position).getContact());

    }

    @Override
    public int getItemCount() {
        return chefList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,country,contact;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            country = itemView.findViewById(R.id.country);
            contact = itemView.findViewById(R.id.contact);


        }
    }
}
