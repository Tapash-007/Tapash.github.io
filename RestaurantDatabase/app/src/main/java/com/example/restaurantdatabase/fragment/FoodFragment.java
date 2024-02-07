package com.example.restaurantdatabase.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantdatabase.R;
import com.example.restaurantdatabase.controller.FoodAdapter;
import com.example.restaurantdatabase.db.DatabaseHelper;
import com.example.restaurantdatabase.model.FoodModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FoodFragment extends Fragment {

    RecyclerView recyclerView;

    FoodAdapter adapter;

    ArrayList<FoodModel> foodList = new ArrayList<>();

    FloatingActionButton fabFood;

    Context context;

    DatabaseHelper dataList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_food, container, false);

        recyclerView = v.findViewById(R.id.rec);
        fabFood = v.findViewById(R.id.fab_food);

        context = inflater.getContext();
        dataList = new DatabaseHelper(context);

        fabFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new FoodAdapter(getContext(),foodList);
        recyclerView.setAdapter(adapter);
    }
}