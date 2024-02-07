package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView username,email,password,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        username = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        username.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        password.setText(getIntent().getStringExtra("password"));
        phone.setText(getIntent().getStringExtra("phone"));

    }
}
