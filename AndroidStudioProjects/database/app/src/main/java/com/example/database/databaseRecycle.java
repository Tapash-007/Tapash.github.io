package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.database.Adapter.DatabaseAdapter;
import com.example.database.model.UserModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class databaseRecycle extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseAdapter adapter;

    MaterialToolbar toolbar;

    DatabaseHelper data = new DatabaseHelper(this);

    ArrayList<UserModel> dbList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_recycle);

        recyclerView = findViewById(R.id.rec);
        toolbar = findViewById(R.id.toolbar);

        dbList = (ArrayList<UserModel>) data.getAllUser();





        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbList = (ArrayList<UserModel>) data.getAllUser();
        adapter = new DatabaseAdapter(this,dbList);
        recyclerView.setAdapter(adapter);

        Log.d(getClass().getSimpleName(), "data got " + dbList);


    }
}