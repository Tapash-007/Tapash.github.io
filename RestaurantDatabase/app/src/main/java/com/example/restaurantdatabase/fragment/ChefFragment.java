package com.example.restaurantdatabase.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurantdatabase.R;
import com.example.restaurantdatabase.controller.ChefAdapter;
import com.example.restaurantdatabase.db.DatabaseHelper;
import com.example.restaurantdatabase.model.ChefModel;
import com.example.restaurantdatabase.ui.MainActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ChefFragment extends Fragment {

    RecyclerView rec;

    ChefAdapter adapter;

    MaterialToolbar toolbar;

    FloatingActionButton fab;

    SharedPreferences preferences;

    ArrayList<ChefModel> chefList = new ArrayList<>();

    Context context;

    DatabaseHelper data;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chef, container, false);

        rec = v.findViewById(R.id.rec);
        fab = v.findViewById(R.id.fab);
        toolbar = v.findViewById(R.id.toolbar);
        context = inflater.getContext();
        preferences = context.getSharedPreferences("prefers", MODE_PRIVATE);
        data = new DatabaseHelper(context);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));

        chefList.clear();



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.logout) {

                    Log.d(getClass().getSimpleName(), "onMenuItemClick: " + item.getTitle());

                    openDialog();

                    return true;

                }

                return false;
            }
        });



//        Context context = inflater.getContext();
//
//        data = new DatabaseHelper(context);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return  v;

    }

    private void openDialog() {

        new MaterialAlertDialogBuilder(getContext())
                .setTitle("logout")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        requireActivity().finish();

                      preferences.edit().clear().apply();

                    }
                })

                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).show();


    }

    @Override
    public void onStart() {
        super.onStart();


        adapter = new ChefAdapter(getContext(),chefList);
        rec.setAdapter(adapter);

    }
}