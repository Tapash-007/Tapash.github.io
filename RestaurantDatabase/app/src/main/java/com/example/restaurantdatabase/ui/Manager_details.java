package com.example.restaurantdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.restaurantdatabase.R;
import com.example.restaurantdatabase.databinding.ActivityManagerDetailsBinding;
import com.example.restaurantdatabase.db.DatabaseHelper;
import com.example.restaurantdatabase.model.ManagerModel;

public class Manager_details extends AppCompatActivity {

    ActivityManagerDetailsBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityManagerDetailsBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        DatabaseHelper data = new DatabaseHelper(getApplicationContext());

        bind.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        bind.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bind.name.getText().toString().isEmpty()) {
                    bind.name.requestFocus();
                    Toast.makeText(Manager_details.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if (bind.email.getText().toString().isEmpty()) {
                    bind.email.requestFocus();
                    Toast.makeText(Manager_details.this, "email can't be empty", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(bind.email.getText().toString().trim()).matches()) {

                } else if (bind.password.getText().toString().isEmpty()) {
                    Toast.makeText(Manager_details.this, "Enter your password", Toast.LENGTH_SHORT).show();


                } else if (bind.phoneNo.getText().toString().isEmpty()) {
                    Toast.makeText(Manager_details.this, "Enter your phone no", Toast.LENGTH_SHORT).show();

                } else if (data.checkManagerExist(bind.email.toString())) {
                    Toast.makeText(Manager_details.this, "Teacher Already Exists", Toast.LENGTH_SHORT).show();


                } else {
                    String nameValue = bind.name.getText().toString();
                    String emailValue = bind.email.getText().toString();
                    String passwordValue = bind.password.getText().toString();
                    String phone_noValue = bind.phoneNo.getText().toString();

                    DatabaseHelper data = new DatabaseHelper(Manager_details.this);
                    ManagerModel model = new ManagerModel(nameValue, emailValue, passwordValue, phone_noValue);

                    data.addManager(model);

                    Toast.makeText(Manager_details.this, "Entry successful", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });

    }
}