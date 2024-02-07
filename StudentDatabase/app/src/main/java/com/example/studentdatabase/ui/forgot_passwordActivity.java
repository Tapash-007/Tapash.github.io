package com.example.studentdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studentdatabase.R;
import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.model.TeacherModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class forgot_passwordActivity extends AppCompatActivity {

    TextInputEditText email, password, confirm_pass;

    MaterialButton update_pass, back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_pass = findViewById(R.id.confirm_password);
        update_pass = findViewById(R.id.update_pass);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(forgot_passwordActivity.this, loginActivity.class));
                finish();

            }
        });


        DatabaseHelper data = new DatabaseHelper(this);


        update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().isEmpty()) {
                    email.requestFocus();
                    Toast.makeText(forgot_passwordActivity.this, "email can't be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(forgot_passwordActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    password.requestFocus();

                } else if (confirm_pass.getText().toString().isEmpty()) {
                    confirm_pass.requestFocus();
                    Toast.makeText(forgot_passwordActivity.this, "Enter confirm_password", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confirm_pass.getText().toString())) {
                    Toast.makeText(forgot_passwordActivity.this, "password not Match", Toast.LENGTH_SHORT).show();

                } else {
                    if (data.checkTeacherExists(email.toString())) {
                        Toast.makeText(forgot_passwordActivity.this, "email already exists", Toast.LENGTH_SHORT).show();
                    } else {

                        String emailValue = email.getText().toString();
                        String passwordValue = password.getText().toString();


                        data.updateDatabase(emailValue, passwordValue);

                        Toast.makeText(forgot_passwordActivity.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                }
            }

        });


    }
}