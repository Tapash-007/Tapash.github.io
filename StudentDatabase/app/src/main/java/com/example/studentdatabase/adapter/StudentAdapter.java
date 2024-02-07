package com.example.studentdatabase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentdatabase.ui.AllotmentStudentActivity;
import com.example.studentdatabase.R;

import java.util.ArrayList;

import com.example.studentdatabase.model.StudentModel;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;

    ArrayList<StudentModel> studentList;



    public StudentAdapter(Context context, ArrayList<StudentModel> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_view_student,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(studentList.get(position).getName());
        holder.age.setText(studentList.get(position).getAge());
        holder.fname.setText(studentList.get(position).getFname());
        holder.contact.setText(studentList.get(position).getContact());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, AllotmentStudentActivity.class);
                i.putExtra("id",studentList.get(position).getId());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return studentList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,age,fname,contact;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            fname = itemView.findViewById(R.id.fname);
            contact = itemView.findViewById(R.id.contact);

        }
    }
}
