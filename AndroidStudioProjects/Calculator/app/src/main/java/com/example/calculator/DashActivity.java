package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class DashActivity extends AppCompatActivity {

    TextInputEditText name, email, password, phone_number;

    MaterialButton login, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashh);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone_number = findViewById(R.id.phone_number);
        login = findViewById(R.id.login);
        sign_up = findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
    }

    void validation() {
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(DashActivity.this, "name cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
        }else if (phone_number.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter phone_number", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "empty password", Toast.LENGTH_SHORT).show();
        }    else{
            Intent i =new Intent(DashActivity.this,ImageMain.class);

            i.putExtra("name",name.getText().toString());
            i.putExtra("email",email.getText().toString());
            i.putExtra("password",password.getText().toString());
            i.putExtra("phone_number",phone_number.getText().toString());

            startActivity(i);

        }

    }
}

