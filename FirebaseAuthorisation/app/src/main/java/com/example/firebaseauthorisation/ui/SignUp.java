package com.example.firebaseauthorisation.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.firebaseauthorisation.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding bind;
    Utils utils;
    Uri imageUri = null;
    String userId;
    String imgUrl;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    FirebaseStorage storage;

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                        Uri cam = result.getData().getData();
                        Log.d(getClass().getSimpleName(), "URI:" + imageUri);
                        bind.image.setImageURI(cam);
                        imageUri = cam;
                    } else {
                        Log.d(getClass().getSimpleName(), "Not Pick");
                    }

                }

            });

    private final ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        bind.image.setImageURI(imageUri);
                    } else {
                        Log.d(getClass().getSimpleName(), "onActivityResult: Not picked");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        utils = new Utils();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        userRef = database.getReference("user");

        bind.imageSelection.setOnClickListener(v -> openDialog());

        bind.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    createAccount();
                }
            }
        });

    }

    private void createAccount() {
        String email = bind.email.getText().toString().trim();
        String password = bind.password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userId = task.getResult().getUser().getUid();
                            uploadUserImage();
                            Log.d(getClass().getSimpleName(), "onComplete: " +userId);

                        } else {
                            Toast.makeText(SignUp.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }


    public void openDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Select Type")
                .setItems(new String[]{"Camera", "Gallery"}, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            if (checkCameraPermission()) {
                                openCamera();
                            } else {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                            }
                            break;

                        case 1:
                            if (checkGalleryPermission()) {
                                openGallery();
                            } else {

                                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 0);
                                } else {
                                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                                }


                            }
                    }
                }).show();
    }

    private boolean checkValidation() {
        if (imageUri == null) {
            utils.showToast(this, "Image Pick");
            return false;
        } else if (bind.name.getText().toString().isEmpty()) {
            bind.name.requestFocus();
            utils.showToast(this, "Enter Name");
            return false;
        } else if (bind.email.getText().toString().isEmpty()) {
            bind.email.requestFocus();
            utils.showToast(this, "Invalid email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(bind.email.getText().toString().trim()).matches()) {
            bind.email.requestFocus();
            utils.showToast(this, "enter email");

        } else if (bind.password.getText().toString().isEmpty()) {
            bind.password.requestFocus();
            utils.showToast(this, "Enter password");
            return false;
        } else if (bind.phone.length() < 10) {
            bind.phone.requestFocus();
            utils.showToast(this, "Enter phone no");
            return false;
        } else {
            return true;
        }
        return false;
    }

    private void openCamera() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Pictures");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        imageUri = SignUp.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startCamera.launch(cameraIntent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.launch(intent);
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkGalleryPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
            } else {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
            }


        } else if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }

    }

    private void uploadUserImage() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Image Uploading...");
        dialog.show();
        StorageReference uploader = storage.getReference().child("image").child(String.valueOf(System.currentTimeMillis()));
        uploader.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dialog.dismiss();
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.d(getClass().getSimpleName(), "onSuccess: Url" + uri);
                                imgUrl = uri.toString();

                                storeUserValue();

                            }
                        });

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        dialog.setMessage("uploaded:" + (int) percent + " %");

                    }

                }).addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Log.d(getClass().getSimpleName(), "onFailure: ");
                    }
                });

    }

    private void storeUserValue() {
        if (userId != null && imgUrl!=null) {

            HashMap<String,Object> userData = new HashMap<>();

            userData.put("image",imgUrl);
            userData.put("name",bind.name.getText().toString());
            userData.put("email",bind.email.getText().toString());
            userData.put("phone",bind.phone.getText().toString());

            userRef.child(userId).setValue(userData).addOnSuccessListener(this,new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(SignUp.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

}






















