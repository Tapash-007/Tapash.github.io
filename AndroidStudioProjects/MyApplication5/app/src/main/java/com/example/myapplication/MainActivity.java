package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.utils.VolleyMultipartRequest;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextInputEditText name, email, password, Confirm_password;

    ImageView image;

    MaterialButton register;

    Uri camUri;

    Uri imageUri = null;

    Bitmap bitmap;

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getData() != null && result.getResultCode() == RESULT_OK) {
            imageUri = result.getData().getData();
            Log.d(getClass().getSimpleName(), "URI :" + imageUri);
            image.setImageURI(imageUri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Log.d(getClass().getSimpleName(),"bitmap from gallery===>" +bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } else {
            Log.d(getClass().getSimpleName(), "not pick");
        }
        if (imageUri != null) {
            Toast.makeText(this, "image cannot be empty", Toast.LENGTH_SHORT).show();
        }

    });

    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        image.setImageURI(camUri);
                        imageUri = camUri;

                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageUri);
                            Log.d(getClass().getSimpleName(),"bitmap from gallery===>" +bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
    );


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        Confirm_password = findViewById(R.id.Confirm_password);
        register = findViewById(R.id.Register);
        image = findViewById(R.id.image);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();

            }

        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Select Type")
                .setItems(new String[]{"Camera", "Gallery"}, (dialogInterface, i) -> {
                    switch (i) {
                        case 0: {

                            if (checkCameraPermission()) {
                                openCamera();
                            } else {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1
                                );

                            }
                            dialogInterface.dismiss();
                        }
                        case 1: {
                            if (checkGalleryPermission()) {
                                openGallery();
                            } else {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        0
                                );
                            }
                            dialogInterface.dismiss();
                        }
                    }
                })
                .show();

    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        camUri = MainActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, camUri);
        startCamera.launch(cameraIntent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.launch(intent);
    }

    private boolean checkGalleryPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }

        } else if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }

    void validation() {
        if (imageUri == null) {
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
        } else if (name.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "name cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "enter email", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "enter password", Toast.LENGTH_SHORT).show();
        } else if (Confirm_password.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "confirm_password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
//call Api
            apiCall();
        }
    }

    private void apiCall() {


            //"https://2dcc-103-89-61-50.ngrok-free.app/"
            VolleyMultipartRequest request = new VolleyMultipartRequest(
                    Request.Method.POST, "https://2dcc-103-89-61-50.ngrok-free.app/api/v1/register", new Response.Listener<NetworkResponse>() {
                @Override
                public void onResponse(NetworkResponse response) {
                    Log.d(getClass().getSimpleName(), "onResponse: " + response.data.toString());
                    Log.d(getClass().getSimpleName(), "onResponse: " + response.toString());

                    finish();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(getClass().getSimpleName(), "onErrorResponse: " + error.toString());
                }
            }
            ) {
                @Override
                protected Map<String, DataPart> getByteData() {
                    HashMap<String, DataPart> params = new HashMap<>();
                    params.put("avatar", new DataPart("profile_img" + ".jpeg", getFileDataFromDrawable(bitmap)));
                    return params;
                }

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> params = new HashMap<>();

                    params.put("name", name.getText().toString());
                    params.put("phone", "123456");
                    params.put("email", email.getText().toString());
                    params.put("password", password.getText().toString());

                    return params;
                }
            };

            Volley.newRequestQueue(this).add(request);

        }

        private byte[] getFileDataFromDrawable (Bitmap bitmap){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

}





















