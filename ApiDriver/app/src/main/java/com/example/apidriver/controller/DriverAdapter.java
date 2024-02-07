package com.example.apidriver.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apidriver.GetDetails;
import com.example.apidriver.model.ModelClass;
import com.example.apidriver.R;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {

    Context context;

    ArrayList<ModelClass.MRDataTable.DriverTable.DriversTable> driverList;


    public DriverAdapter(Context context, ArrayList<ModelClass.MRDataTable.DriverTable.DriversTable> driverList) {
        this.context = context;
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.driverId.setText(driverList.get(position).getDriverId().toString());
        holder.name.setText(driverList.get(position).getGivenName().toString());
        holder.family_name.setText(driverList.get(position).getFamilyName().toString());
        holder.dob.setText(driverList.get(position).getDateOfBirth().toString());
        holder.nationality.setText(driverList.get(position).getNationality().toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, GetDetails.class);

                intent.putExtra("driverId",driverList.get(position).getDriverId());
                intent.putExtra("name",driverList.get(position).getGivenName());
                intent.putExtra("family_name",driverList.get(position).getFamilyName());
                intent.putExtra("dob",driverList.get(position).getDateOfBirth());
                intent.putExtra("nationality",driverList.get(position).getNationality());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView driverId, name, family_name, dob, nationality;

        public ViewHolder(View itemView) {
            super(itemView);

            driverId = itemView.findViewById(R.id.Id);
            name = itemView.findViewById(R.id.name);
            family_name = itemView.findViewById(R.id.family);
            dob = itemView.findViewById(R.id.dob);
            nationality = itemView.findViewById(R.id.nationality);

        }
    }
}

