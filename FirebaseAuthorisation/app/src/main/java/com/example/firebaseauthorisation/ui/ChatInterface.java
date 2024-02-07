package com.example.firebaseauthorisation.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseauthorisation.Adapter.ChatAdapter;
import com.example.firebaseauthorisation.model.ChatModel;
import com.example.firebaseauthorisation.R;
import com.example.firebaseauthorisation.model.ChatRecModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatInterface extends AppCompatActivity {

    String receiverId, senderId, receiverImage, senderName, senderImage, chatKey;
    MaterialToolbar toolbar;
    FloatingActionButton fab;
    CircleImageView profile_img;

    String userId, img;
    TextView name;
    FirebaseAuth mAuth;
    DatabaseReference chatRef, recentChatRef, ref, refer;
    ArrayList<String> chatUser = new ArrayList<>();
    ArrayList<ChatModel> chatList = new ArrayList<>();
    TextInputEditText message;

    TextInputLayout attach;
    RecyclerView recView;
    ChatAdapter adapter;

    Uri imageUri = null;
    ImageView back, image;


    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                        Uri cam = result.getData().getData();
                        Log.d(getClass().getSimpleName(), "URI:" + imageUri);
                        image.setImageURI(cam);
                        imageUri = cam;

                    } else {
                        Log.d(getClass().getSimpleName(), "not Pick");
                    }
                }
            });

    private final ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        image.setImageURI(imageUri);
                    } else {
                        Log.d(getClass().getSimpleName(), "onActivityResult: Not picked");
                    }
                }
            }
    );


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_interface);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        back = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();
        profile_img = findViewById(R.id.profile_img);
        name = findViewById(R.id.receiverChat);
        message = findViewById(R.id.message);
        attach = findViewById(R.id.attach);
        recView = findViewById(R.id.rec);
        senderId = mAuth.getCurrentUser().getUid();
        adapter = new ChatAdapter(this, chatList, senderId);
        recView.setAdapter(adapter);


        userId = mAuth.getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("user");
        chatRef = FirebaseDatabase.getInstance().getReference().child("chats");
        recentChatRef = FirebaseDatabase.getInstance().getReference().child("recentChat");

        attach.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        name.setText(getIntent().getStringExtra("name"));

        receiverId = getIntent().getStringExtra("receiverId");

        chatUser.add(senderId);
        chatUser.add(receiverId);
        Collections.sort(chatUser);

        chatKey = chatUser.get(0) + "_chat_" + chatUser.get(1);

        Log.d(getClass().getSimpleName(), "onCreate: " + chatKey);

        chatRef.child(chatKey).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                chatList.clear();

                for (DataSnapshot snap : snapshot.getChildren()) {

                    chatList.add(new ChatModel(snap.child("receiverId").getValue().toString(),
                            snap.child("receiverName").getValue().toString(),
                            snap.child("senderId").getValue().toString(),
                            snap.child("message").getValue().toString(),
                            snap.child("timeStamp").getValue().toString()));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Picasso.get().load(getIntent().getStringExtra("image")).into(profile_img);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (message.getText().toString().isEmpty()) {
                    Toast.makeText(ChatInterface.this, "message can't be empty", Toast.LENGTH_SHORT).show();

                } else {

                    sendMsg();
                }

            }
        });

        refer = FirebaseDatabase.getInstance().getReference().child("user").child(mAuth.getCurrentUser().getUid());

        refer.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.d(getClass().getSimpleName(), "onDataChange: " + snapshot);

                senderName = snapshot.child("name").getValue().toString();
                senderImage = snapshot.child("image").getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void sendMsg() {

        Log.d(getClass().getSimpleName(), "sendMsg: " + chatRef.push().getKey());

        String pushKey = chatRef.push().getKey();

        ChatModel chatModel = new ChatModel(
                receiverId,
                getIntent().getStringExtra("name"),
                senderId,
                message.getText().toString(),
                String.valueOf(System.currentTimeMillis())
        );

        ChatRecModel chatRec = new ChatRecModel(
                receiverId,
                getIntent().getStringExtra("name"),
                getIntent().getStringExtra("image"),
                senderId,
                senderName,
                senderImage,
                message.getText().toString(),
                String.valueOf(System.currentTimeMillis())

        );

        chatRef.child(chatKey).child(pushKey).setValue(chatModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                message.setText("");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                e.printStackTrace();
            }
        });


        recentChatRef.child(senderId).child(receiverId).setValue(chatRec);
        recentChatRef.child(receiverId).child(senderId).setValue(chatRec);
    }

//    public void openDialog() {
//
//        new MaterialAlertDialogBuilder(this)
//                .setTitle("Select Type")
//                .setItems(new String[]{"Camera", "Gallery"}, (dialog, which) -> {
//
//                            switch (which) {
//
//                                case 0:
//                                    if (checkCameraPermission()) {
//                                        openCamera();
//                                    } else {
//                                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
//                                    }
//                                    break;
//
//                                case 1:
//                                    if (checkGalleryPermission()) {
//                                        openGallery();
//                                    } else {
//
//                                        if (android.os.)
//
//
//                                    }
//                            }
//
//                        }
//
//                )


    }
