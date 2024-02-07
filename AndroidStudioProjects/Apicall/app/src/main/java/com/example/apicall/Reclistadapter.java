package com.example.apicall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
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

public class Reclistadapter extends RecyclerView.Adapter<Reclistadapter.ViewHolder> {

    Context context;

    ArrayList<ModelApi> arrList;


    public Reclistadapter(Context context, ArrayList<ModelApi> arrList) {
        this.context = context;
        this.arrList = arrList;
    }


    @NonNull
    @Override
    public Reclistadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycleview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Reclistadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//   holder.img.setText(arrList.get(position).image);
        holder.title_api.setText(arrList.get(position).title);
        holder.summary.setText(arrList.get(position).summary);
        holder.newsSite.setText(arrList.get(position).newsSite);
        Picasso.with(context).load(arrList.get(position).image).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,arrList.get(position).toString(), Toast.LENGTH_SHORT);

                Intent intent = new Intent(context,GetDetail.class);
                intent.putExtra("image",arrList.get(position).image);
                intent.putExtra("title",arrList.get(position).title);
                intent.putExtra("summary",arrList.get(position).summary);
                intent.putExtra("newsSite",arrList.get(position).newsSite);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_api, summary, newsSite;

        ImageView image;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            title_api = itemView.findViewById(R.id.title_api);
            summary = itemView.findViewById(R.id.summary);
            newsSite = itemView.findViewById(R.id.newsSite);
            image = itemView.findViewById(R.id.img_api);
        }
    }
}

