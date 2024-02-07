package com.example.database.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database.DatabaseHelper;
import com.example.database.R;
import com.example.database.databaseRecycle;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {


    //variables
    TextInputEditText email, password;

    MaterialButton login, register,getdetails;

    DatabaseHelper database;

    SharedPreferences preferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //initialize
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        getdetails = findViewById(R.id.getdetails);

        preferences = getSharedPreferences("prefers", MODE_PRIVATE);
        database = new DatabaseHelper(this);

        boolean alreadyLogin = false;
        alreadyLogin = preferences.getBoolean("isLogin", false);

        if (alreadyLogin) {
            Intent i = new Intent(Login.this, databaseRecycle.class);
            startActivity(i);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "password cannot be empty", Toast.LENGTH_SHORT).show();

                } else {

                    String emailValue = email.getText().toString();
                    String passwordValue = password.getText().toString();

                    boolean userExist = database.checkUserExists(emailValue, passwordValue);

                    if (userExist) {

                        preferences.edit().putBoolean("isLogin", true).apply();
                        preferences.edit().putString("email", emailValue).apply();

                        Toast.makeText(getApplicationContext(), "user login successfully", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Login.this, databaseRecycle.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid Credential!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });


         getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });


        
    }
}
