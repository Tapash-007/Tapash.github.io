package com.example.firebaseauthorisation.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.Adapter.UserAdapter;
import com.example.firebaseauthorisation.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserFragment extends Fragment {

    TextView name;

    ImageView image;

    FirebaseAuth mAuth;

    DatabaseReference ref;

    ArrayList<UserModel> list = new ArrayList<>();

    Context context;

    UserAdapter adapter;

    ProgressBar progressBar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        name = v.findViewById(R.id.name);
        image = v.findViewById(R.id.image);
        mAuth = FirebaseAuth.getInstance();
        progressBar = v.findViewById(R.id.progress);
        progressBar.setVisibility(v.getVisibility());

        context = inflater.getContext();
        adapter = new UserAdapter(context, list);

        RecyclerView UserRec = v.findViewById(R.id.rec_user);
        UserRec.setAdapter(adapter);


        ref = FirebaseDatabase.getInstance().getReference().child("user");

        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.d(getClass().getSimpleName(), "onDataChange: " + snapshot);

                list.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {

                    if (!FirebaseAuth.getInstance().getCurrentUser().getUid().equals(snap.getKey())) {


                        list.add(new UserModel(snap.child("name").getValue().toString(), snap.child("email").getValue().toString(),
                                snap.child("phone").getValue().toString(), snap.getKey().toString(),
                                snap.child("image").getValue().toString()));

                        progressBar.setVisibility(v.GONE);


                    }


                    Log.d(getClass().getSimpleName(), "onDataChange: " + snap.child("email").getValue().toString());
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }


}