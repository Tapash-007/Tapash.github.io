package com.example.studentdatabase.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.adapter.SubjectAdapter;

import com.example.studentdatabase.model.SubjectModel;

import com.example.studentdatabase.ui.AddSubjectActivity;
import com.example.studentdatabase.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class subjectFragment extends Fragment {

    RecyclerView recycleV;

    SubjectAdapter adapter;

    FloatingActionButton fab;

    ArrayList<SubjectModel> subList = new ArrayList<>();

    Context context;

    DatabaseHelper data;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_subject, container, false);

        recycleV = v.findViewById(R.id.recycle);
        fab = v.findViewById(R.id.fabIcon);

        context = inflater.getContext();
         data = new DatabaseHelper(context);

//        data.deleteDatabase(21);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddSubjectActivity.class);
                startActivity(i);

            }
        });


        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        subList = data.fetchSubject();

        adapter = new SubjectAdapter(getContext(), subList);
        recycleV.setAdapter(adapter);

    }
}