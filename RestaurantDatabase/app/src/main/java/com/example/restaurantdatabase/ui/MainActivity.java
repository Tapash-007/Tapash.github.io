package com.example.restaurantdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantdatabase.R;
import com.example.restaurantdatabase.db.DatabaseHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText email, password;

    TextView signUp, forgotPass;

    MaterialButton login;

    DatabaseHelper db;

    SharedPreferences preferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        forgotPass = findViewById(R.id.forgotPass);
        login = findViewById(R.id.login);

        preferences = getSharedPreferences("prefers", MODE_PRIVATE);
        db = new DatabaseHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Manager_details.class);
                startActivity(i);

            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "email Can't be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();

                } else {


                    String emailValue = email.getText().toString();
                    String passwordValue = password.getText().toString();

                    boolean loginExist = db.loginManager(emailValue,passwordValue);

                    if (loginExist) {

//                        preferences.edit().putBoolean("isLogin", true).apply();

                        Toast.makeText(MainActivity.this, "login successfully.......", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, bottom_navigation.class);
                        startActivity(i);
                        finish();

                    } else {

                        Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}