package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.model.Model;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.interfac.RecyclerClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerClick {

    RecyclerView recView;
    UserAdapter adapter;

    List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recView = findViewById(R.id.recView);

        list.add(new Model("John", "1234567890", R.drawable.user1));
        list.add(new Model("ALEx", "1214567890", R.drawable.aa));
        list.add(new Model("ABC", "12334567890", R.drawable.ima));
        list.add(new Model("KOHLI", "123427890", R.drawable.trt));
        list.add(new Model("DHONI", "123426790", R.drawable.yy));
        list.add(new Model("SACHIN", "1234267890", R.drawable.ic_person));
        list.add(new Model("RAHUL", "1234267890", R.drawable.pp));


        adapter = new UserAdapter(this, list, this);
        recView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int pos) {
        // String.valueOf(pos)
        //Toast.makeText(this, list.get(pos).getUserName(), Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, PageActivity.class));
    }
}