package com.example.calculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class ImageMain extends AppCompatActivity {

    TextInputEditText Name, Email, password;

    ImageView image;

    Uri camUri;

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getData() != null && result.getResultCode() == RESULT_OK) {
            Uri imageUri = result.getData().getData();
            Log.d(getClass().getSimpleName(), "URI :" + imageUri);
            image.setImageURI(imageUri);
        } else {
            Log.d(getClass().getSimpleName(), "not pick");

        }

    });

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        image.setImageURI(camUri);
                    }
                }
            }
    );



    @SuppressLint({"MissingInflated", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_main);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        image = findViewById(R.id.image);

        String NameData = getIntent().getStringExtra("Name");
        String EmailData = getIntent().getStringExtra("Email");
        String passwordData = getIntent().getStringExtra("password");

        Name.setText(NameData);
        Name.setEnabled(false);
        Email.setText(EmailData);
        Email.setEnabled(false);
        password.setText(passwordData);
        password.setEnabled(false);


        Log.d(getClass().getSimpleName(), "onCreate: " + Name);
        Log.d(getClass().getSimpleName(), "onCreate: " + Email);
        Log.d(getClass().getSimpleName(), "onCreate: " + password);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    openDialog();
                } else {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0
                    );

                }
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.launch(intent);

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        camUri = ImageMain.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, camUri);
        startCamera.launch(cameraIntent);

    }

    private Boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;

        }
        return false;
    }

    private void openDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Select Type")
                .setItems(new String[]{"Camera", "Gallery"}, (dialogInterface, i) -> {
                    switch (i) {
                        case 0: {
                            dialogInterface.dismiss();
                            openCamera();
                        }
                        case 1: {
                            dialogInterface.dismiss();
                            openGallery();
                        }
                    }
                })
                .show();

    }

}