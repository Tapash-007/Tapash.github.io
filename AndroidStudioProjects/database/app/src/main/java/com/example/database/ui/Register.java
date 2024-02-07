package com.example.database.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.database.DatabaseHelper;
import com.example.database.R;
import com.example.database.model.UserModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {


    TextInputEditText name, email, password;

    MaterialButton register;

    DatabaseHelper database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        database = new DatabaseHelper(this);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
                    String nameValue = name.getText().toString();
                    String emailValue = email.getText().toString();
                    String passwordValue = password.getText().toString();

                boolean userExist = database.checkUserAlreadyExists(emailValue);
                if (!userExist) {


                    UserModel newUser = new UserModel();
                    newUser.setName(nameValue);
                    newUser.setEmail(emailValue);
                    newUser.setPassword(passwordValue);
                    database.adduser(newUser);
                    Toast.makeText(getApplicationContext(), "user registered successfully", Toast.LENGTH_SHORT).show();

//                    startActivity(new Intent(Register.this, Login.class));
                    finish();

                } else {
                    Toast.makeText(Register.this, "user Already Exist!", Toast.LENGTH_SHORT).show();
                }
                }

            }
        });
    }

    public boolean validation() {

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter name", Toast.LENGTH_SHORT).show();
            return false;


        } else if (email.getText().toString().isEmpty()) {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
            return false;


        } else {
            if (password.getText().toString().isEmpty()) {
                Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }


            }

        }

}



