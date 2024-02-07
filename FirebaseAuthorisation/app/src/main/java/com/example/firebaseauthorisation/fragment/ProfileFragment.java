package com.example.firebaseauthorisation.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.ui.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class ProfileFragment extends Fragment {

    ImageView img;

    TextInputEditText name, email, phone;

    MaterialButton save;

    FirebaseAuth mAuth;

    DatabaseReference refer;

    Utils utils;

    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.email);
        phone = v.findViewById(R.id.phone);
        save = v.findViewById(R.id.save);
        img = v.findViewById(R.id.image);
        context = inflater.getContext();
        mAuth = FirebaseAuth.getInstance();
        utils = new Utils();


        refer = FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid());

     refer.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot snapshot) {

             {

                 Log.d(getClass().getSimpleName(), "onDataChange: " + snapshot);

                 name.setText(snapshot.child("name").getValue().toString());
                 email.setText(snapshot.child("email").getValue().toString());
                 phone.setText(snapshot.child("phone").getValue().toString());
                 Picasso.get().load(snapshot.child("image").getValue().toString()).into(img);

             }

         }

         @Override
         public void onCancelled(@NonNull DatabaseError error) {

         }
     });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
                HashMap<String,Object> map = new HashMap<>();
                map.put("name",name.getText().toString());
                map.put("phone",phone.getText().toString());


                refer.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "data uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.getMessage();

                    }
                });
            }
        });

        return v;

    }

    public void validation() {

        if (name.getText().toString().isEmpty()) {
            name.requestFocus();
            utils.showToast(context, "Enter name");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            email.requestFocus();
            utils.showToast(context, "Enter email");
        } else if (phone.length() < 10) {
            phone.requestFocus();
            utils.showToast(context, "Enter phone_no");

        }
    }

}