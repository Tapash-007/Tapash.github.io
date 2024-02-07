package com.example.apidriver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class GetDetails extends AppCompatActivity {

    TextView driverId,name,family_name,dob,nationality;

    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        driverId = findViewById(R.id.Id);
        name = findViewById(R.id.name);
        family_name = findViewById(R.id.family_name);
        dob = findViewById(R.id.dob);
        nationality = findViewById(R.id.nationality);
        toolbar = findViewById(R.id.tool);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        driverId.setText("DriverId:-" + " " + getIntent().getStringExtra("driverId"));
        name.setText("Name:-" + " " + getIntent().getStringExtra("name"));
        family_name.setText("Family_name:-" + " " + getIntent().getStringExtra("family_name"));
        dob.setText("DateOfBirth:-" + " " + getIntent().getStringExtra("dob"));
        nationality.setText("Nationality:-" + " " + getIntent().getStringExtra("nationality"));

    }
}