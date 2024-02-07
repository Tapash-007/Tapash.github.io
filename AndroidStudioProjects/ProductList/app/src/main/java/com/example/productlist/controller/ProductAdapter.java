package com.example.productlist.controller;

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

import com.example.productlist.Getdetails;
import com.example.productlist.R;
import com.example.productlist.model.ModelClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass> productList;

    public ProductAdapter(Context context, ArrayList<ModelClass> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(productList.get(position).getTitle());
        holder.price.setText(productList.get(position).getPrice());


        Picasso.with(context).load(productList.get(position).getImage()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, productList.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Getdetails.class);
                intent.putExtra("image",productList.get(position).getImage());
                intent.putExtra("title",productList.get(position).getTitle());
                intent.putExtra("price",productList.get(position).getPrice());

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return productList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,price;

        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);



        }
    }
}
