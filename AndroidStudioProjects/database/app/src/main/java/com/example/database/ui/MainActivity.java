package com.example.database.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.database.R;
import com.example.database.model.UserModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText id;

    TextView name,email;

    MaterialToolbar toolbar;

    MaterialButton get_details;
    DatabaseHelper cert;

    SharedPreferences preferences;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        id = findViewById(R.id.get_id);
        get_details = findViewById(R.id.get_details);
        cert = new DatabaseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        preferences = getSharedPreferences("prefers", MODE_PRIVATE);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                 if (item.getItemId() == R.id.logout) {
                     openDialog();
                     return true;
            }
                 return false;
            }

        });

        get_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel user = cert.fetchData(id.getText().toString());
                if (user != null) {

                    name.setText(user.getName().toString());
                    email.setText(user.getEmail().toString());

                    Log.d(getClass().getSimpleName(), "onClick: " + user.getName().toString() + " \n" + user.getEmail().toString());


                } else {
                    Toast.makeText(MainActivity.this, "no data is present", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Logout")
                .setMessage("Are you sure want to logout?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                     preferences.edit().clear().apply();

                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);

                        finish();

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                }).show();
    }

}