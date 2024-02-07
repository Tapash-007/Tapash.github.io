package com.example.studentdatabase.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.adapter.StudentAdapter;
import com.example.studentdatabase.model.StudentModel;

import com.example.studentdatabase.ui.AddStudentActivity;
import com.example.studentdatabase.R;
import com.example.studentdatabase.ui.loginActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class studentFragment extends Fragment {

    RecyclerView recyclerView;

    StudentAdapter adapter;

    FloatingActionButton fb;

    ArrayList<StudentModel> arrayList = new ArrayList<>();

    ArrayList<StudentModel> mainArrayList = new ArrayList<>();

    SearchView searchView;

    MaterialToolbar toolbar;

    Context context;

    SharedPreferences preferences;

    DatabaseHelper data;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_student, container, false);

        fb = v.findViewById(R.id.fab);
        recyclerView = v.findViewById(R.id.rec);
        toolbar = v.findViewById(R.id.tool);
        context = inflater.getContext();
        preferences = context.getSharedPreferences("prefers", MODE_PRIVATE);
        searchView = v.findViewById(R.id.searchView);
        searchView.clearFocus();

        data = new DatabaseHelper(getContext());

        mainArrayList.clear();

        mainArrayList.addAll(data.fetchData());


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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(getClass().getSimpleName(), "if" + mainArrayList.size());
                if (newText.isEmpty()) {
                    arrayList.clear();
                    arrayList.addAll(mainArrayList);
                    adapter = new StudentAdapter(context, arrayList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.d(getClass().getSimpleName(), "else");
                    filterList(newText);
                }

                return true;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        itemList = new ArrayList<>();

        Context context = inflater.getContext();

        DatabaseHelper data = new DatabaseHelper(context);


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddStudentActivity.class);
                i.putExtra("from", "add");
                startActivity(i);


            }
        });

        arrayList.clear();

        arrayList = data.fetchData();

        adapter = new StudentAdapter(context, arrayList);

        recyclerView.setAdapter(adapter);

        return v;

    }


    @SuppressLint("NotifyDataSetChanged")
    private void filterList(String newtext) {

        ArrayList<StudentModel> filteredList = new ArrayList<>();

        for (StudentModel item : arrayList) {

            Log.d(getClass().getSimpleName(), "filterList: MATCH WITH " + item.getName());
            Log.d(getClass().getSimpleName(), "filterList: MATCH WITH " + newtext);


            if (item.getName().toLowerCase().contains(newtext.toLowerCase())) {
                filteredList.add(item);
            }
        }
        Log.d(getClass().getSimpleName(), "filterList: " + filteredList.size());

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();

        } else {
            arrayList.clear();
            arrayList.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }

    }

    private void openDialog() {

        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), loginActivity.class);
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


//        arrayList.clear();
//        arrayList.addAll(mainArrayList);
        adapter = new StudentAdapter(context,arrayList);
        recyclerView.setAdapter(adapter);
        Log.d(getClass().getSimpleName(), "onStart: " + arrayList);
    }


}



