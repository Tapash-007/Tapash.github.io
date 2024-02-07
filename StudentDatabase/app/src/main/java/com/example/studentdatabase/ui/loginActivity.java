package com.example.studentdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentdatabase.R;
import com.example.studentdatabase.Teacher_detail_Activity;
import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.model.TeacherModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class loginActivity extends AppCompatActivity {

    TextInputEditText name,password;

    MaterialButton login;


    TextView signup,forgot_password;

    DatabaseHelper database;

    SharedPreferences preferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.sign);
        forgot_password = findViewById(R.id.forgot_pass);

        preferences = getSharedPreferences("prefers",MODE_PRIVATE);
        database = new DatabaseHelper(this);

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),forgot_passwordActivity.class);
                startActivity(intent);
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Teacher_detail_Activity.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()){
                    Toast.makeText(loginActivity.this, "name can't be empty", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(loginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();

                } else {

                    String nameValue = name.getText().toString();
                    String passwordValue = password.getText().toString();

                    boolean loginExist = database.loginTeacher(nameValue,passwordValue);

                    if (loginExist) {

                        preferences.edit().putBoolean("isLogin", true).apply();

                        Toast.makeText(loginActivity.this, "Login successfully....", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(loginActivity.this, BottomNavActivity.class);
                        startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(loginActivity.this, "Invalid Credential!", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
}