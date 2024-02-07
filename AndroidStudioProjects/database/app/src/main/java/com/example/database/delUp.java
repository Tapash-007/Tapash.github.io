package com.example.database;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.database.model.UserModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class delUp extends AppCompatActivity {

    MaterialButton update, delete;

    MaterialToolbar toolbar;

    TextInputEditText name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_update);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        DatabaseHelper data = new DatabaseHelper(this);

        int id;
        id = getIntent().getIntExtra("id", 0);

        UserModel user = new UserModel();

        user = data.fetchData(String.valueOf(id));


        data.fetchData("id");


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }

        });

        name.setText("" + " " + user.getName());
        email.setText("" + " " + user.getEmail());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.updateDatabase(id,name.getText().toString());
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteDatabase(id);
                finish();

            }
        });
    }
}