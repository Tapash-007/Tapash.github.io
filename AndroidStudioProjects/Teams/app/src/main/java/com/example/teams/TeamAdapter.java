package com.example.teams;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    Context context;

    ArrayList<DataClass.Data> dataArrayList;


    public TeamAdapter(Context context, ArrayList<DataClass.Data> dataArrayList) {
        this.context = context;
        this.dataArrayList= dataArrayList;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.Id.setText(dataArrayList.get(position).getId());
       holder.abbreviation.setText(dataArrayList.get(position).getAbbreviation());
        holder.city.setText(dataArrayList.get(position).getCity());
        holder.conference.setText(dataArrayList.get(position).getConference());
        holder.division.setText(dataArrayList.get(position).getDivision());
        holder.full_name.setText(dataArrayList.get(position).getFull_name());
        holder.name.setText(dataArrayList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,dataArrayList .get(position).getId() +" " +dataArrayList .get(position).getCity() , Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView Id, abbreviation, city, conference, division, full_name, name;


        public ViewHolder(@NonNull View itemView) {


            super(itemView);

            Id = itemView.findViewById(R.id.Id);
            abbreviation = itemView.findViewById(R.id.ab);
            city = itemView.findViewById(R.id.city);
            conference = itemView.findViewById(R.id.conf);
            division = itemView.findViewById(R.id.div);
            full_name = itemView.findViewById(R.id.full_name);
            name = itemView.findViewById(R.id.name);
        }
    }


}