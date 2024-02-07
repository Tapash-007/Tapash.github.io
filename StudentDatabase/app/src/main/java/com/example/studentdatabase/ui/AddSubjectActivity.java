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

import com.example.studentdatabase.model.SubjectModel;

public class AddSubjectActivity extends AppCompatActivity {

    TextInputEditText subj;
    MaterialButton addSubj;

    MaterialToolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        subj = findViewById(R.id.subj);
        addSubj = findViewById(R.id.add);
        toolbar = findViewById(R.id.toolbarView);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        DatabaseHelper data = new DatabaseHelper(getApplicationContext());


        addSubj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subj.getText().toString().isEmpty()){
                    Toast.makeText(AddSubjectActivity.this, "please enter subject", Toast.LENGTH_SHORT).show();
                } else {
                    String NewSubject = subj.getText().toString();
                    SubjectModel model = new SubjectModel(NewSubject);
                    data.addSubject(model);
                    Toast.makeText(AddSubjectActivity.this, "Subject added successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}