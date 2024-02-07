package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //variable define
    EditText userName, email,phone;
    TextInputEditText password;
    MaterialButton register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        userName = findViewById(R.id.userName);
        register = findViewById(R.id.register);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        email =findViewById(R.id.email);

        //uses
        // userName.setText("Hello");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });

    }

    void validation() {
        if (userName.getText().toString().isEmpty()) {
            showToast("User name is empty");
        }
        else if (email.getText().toString().isEmpty()){
            showToast("email cannot be empty");
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())) {
            

        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        }
        else if ( password.getText().toString().length() < 5) {
            showToast("Password must contains 6 digits");

        }
        else if (register.getText().toString().isEmpty()) {
            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        }

        else if ( phone.getText().toString().length() < 9) {
            showToast("phone contain less than 10");
        }
        else{
            Intent i = new Intent(MainActivity.this,SecondActivity.class);

            i.putExtra("name",userName.getText().toString());
            i.putExtra("email",email.getText().toString());
            i.putExtra("password",password.getText().toString());
            i.putExtra("phone",phone.getText().toString());


            startActivity(i);
        }



       // showToast("Successfully Completed!");
        }



    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}