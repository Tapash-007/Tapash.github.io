package com.example.database.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;
import com.example.database.delUp;
import com.example.database.model.DatabaseModel;
import com.example.database.model.UserModel;

import java.util.ArrayList;

  public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    Context context;

    ArrayList<UserModel> dbList;

    public DatabaseAdapter(Context context, ArrayList<UserModel> dbList) {
        this.context = context;
        this.dbList = dbList;
    }


      @NonNull
      @Override
      public DatabaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View v = LayoutInflater.from(context).inflate(R.layout.single_item_view,parent,false);
          return new ViewHolder(v);
      }

      @Override
      public void onBindViewHolder(@NonNull DatabaseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(dbList.get(position).getName());
        holder.email.setText(dbList.get(position).getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, delUp.class);
                intent.putExtra("id",dbList.get(position).getId());



                context.startActivity(intent);
            }
        });


      }

      @Override
      public int getItemCount() {
          return dbList.size();
      }

      public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,email;
          public ViewHolder(@NonNull View itemView) {
              super(itemView);

              name = itemView.findViewById(R.id.name);
              email = itemView.findViewById(R.id.email);

          }
      }


  }
