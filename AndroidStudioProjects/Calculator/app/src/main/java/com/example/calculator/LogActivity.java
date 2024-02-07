package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LogActivity extends AppCompatActivity {

    TextInputEditText username, password;

    MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        login = findViewById(R.id.login);
        username = findViewById(R.id.name);
        password = findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
    }

    void validation() {
        if (username.getText().toString().isEmpty())
            Toast.makeText(this, "enter username", Toast.LENGTH_SHORT).show();
        else if (password.getText().toString().isEmpty())
        {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }
            else
            {
                Intent i = new Intent(this, DashActivity.class);
                startActivity(i);
            }


        }
    }

