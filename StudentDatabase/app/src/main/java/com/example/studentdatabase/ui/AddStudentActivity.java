package com.example.studentdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import com.example.studentdatabase.model.StudentModel;

public class AddStudentActivity extends AppCompatActivity {

    TextInputEditText name, age, f_name, contact;

    MaterialButton addNew;

    MaterialToolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        f_name = findViewById(R.id.father_name);
        contact = findViewById(R.id.contact);
        addNew = findViewById(R.id.addNew);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "name can't be empty", Toast.LENGTH_SHORT).show();
                } else if (age.getText().toString().isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "please enter age", Toast.LENGTH_SHORT).show();

                } else if (f_name.getText().toString().isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "please enter father name", Toast.LENGTH_SHORT).show();

                } else if (contact.getText().toString().isEmpty()) {
                    Toast.makeText(AddStudentActivity.this, "please provide contact", Toast.LENGTH_SHORT).show();

                } else {

                    String NameValue = name.getText().toString();
                    String ageValue = age.getText().toString();
                    String f_nameValue = f_name.getText().toString();
                    String ContactValue = contact.getText().toString();


                    DatabaseHelper data = new DatabaseHelper(AddStudentActivity.this);
                    StudentModel model = new StudentModel(NameValue, ageValue, f_nameValue, ContactValue, getTaskId());


                    data.addStudent(model);
                    Toast.makeText(AddStudentActivity.this, "Entry added successfully", Toast.LENGTH_SHORT).show();
                    finish();


//                  if (data.addStudent(model)) {
//                      Toast.makeText(AddStudentActivity.this, "Entry added successfully", Toast.LENGTH_SHORT).show();
//                      finish();
//                  }else {
//                      Toast.makeText(AddStudentActivity.this, "wrong", Toast.LENGTH_SHORT).show();
//
//                  }


                }
            }
        });


    }
}