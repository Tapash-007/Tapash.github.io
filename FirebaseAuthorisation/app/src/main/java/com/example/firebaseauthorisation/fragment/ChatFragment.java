package com.example.firebaseauthorisation.fragment;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.example.firebaseauthorisation.Adapter.ChatAdapter;
import com.example.firebaseauthorisation.Adapter.RecentChatAdapter;
import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.model.ChatModel;
import com.example.firebaseauthorisation.model.ChatRecModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {


    TextView name, lastChat;

    ImageView image;

    Context context;

    FirebaseAuth mAuth;

    RecentChatAdapter adapter;

    ArrayList<ChatRecModel> arrayList = new ArrayList<>();

    String userId;

    DatabaseReference recentChatRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        name = v.findViewById(R.id.nameChat);
        lastChat = v.findViewById(R.id.lastSeen);
        image = v.findViewById(R.id.image);
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        context = inflater.getContext();
        adapter = new RecentChatAdapter(context, arrayList, userId);
        RecyclerView ChatRec = v.findViewById(R.id.rec);
        ChatRec.setAdapter(adapter);

        recentChatRef = FirebaseDatabase.getInstance().getReference().child("recentChat");


        recentChatRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    arrayList.add(new ChatRecModel(snap.child("receiverId").getValue().toString(),
                            snap.child("receiverName").getValue().toString(),
                            snap.child("receiverImage").getValue().toString(),
                            snap.child("senderId").getValue().toString(),
                            snap.child("senderName").getValue().toString(),
                            snap.child("senderImage").getValue().toString(),
                            snap.child("message").getValue().toString(),
                            snap.child("timeStamp").getValue().toString()));

                    Log.d(getClass().getSimpleName(), "onDataChange: " +snap.child("receiverName").toString());

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