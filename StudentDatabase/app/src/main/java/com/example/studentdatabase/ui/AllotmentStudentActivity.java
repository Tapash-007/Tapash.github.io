package com.example.studentdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import com.example.studentdatabase.model.StudentModel;
import com.example.studentdatabase.model.SubjectModel;

public class AllotmentStudentActivity extends AppCompatActivity {


    TextInputEditText name, age, f_name, contact;

    MaterialButton allotSubject;

//    String[] items = {"English", "Physics", "Chemistry", "Mathematics"};

    ArrayList<String> items = new ArrayList<>();

    //    ArrayList<SubjectModel> subjList;
    ArrayList<SubjectModel> subjList = new ArrayList<>();

    ArrayList<String> temp = new ArrayList<>();

    List<Integer> allotSubId = new ArrayList<>();


    MaterialToolbar toolbar;


    int stuId;
    int subId = -1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allotment_student);


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        f_name = findViewById(R.id.father_name);
        contact = findViewById(R.id.contact);
        allotSubject = findViewById(R.id.add);
        toolbar = findViewById(R.id.tool);
        Spinner dropdown = findViewById(R.id.spin);

        Spinner allot = findViewById(R.id.spinner);




        DatabaseHelper data = new DatabaseHelper(this);

        stuId = getIntent().getIntExtra("id", 0);

        subjList.clear();
        subjList = data.fetchSubject();

        allotSubId.clear();
        allotSubId = data.allot_Subject(stuId);

        Log.d(getClass().getSimpleName(), "onCreate: " + allotSubId);


        if (allotSubId != null && subjList.size() > 0) {

            items.clear();

            for (SubjectModel sub : subjList) {
                for (Integer allotId : allotSubId) {
                    if (sub.getId() == allotId) {
                        items.add(sub.getSubject());
                    }

                }
            }


        }
        stuId = getIntent().getIntExtra("id", 0);

        StudentModel student;

        student = data.getData(String.valueOf(stuId));

        name.setText(" " + " " + student.getName());
        age.setText(" " + " " + student.getAge());
        f_name.setText("" + "" + student.getFname());
        contact.setText("" + "" + student.getContact());


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        ArrayAdapter<String> allotAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, items);

        dropdown.setAdapter(allotAdapter);

        allotSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getClass().getSimpleName(), "onClick: " + subId);
                if (subId == -1) {
                    Toast.makeText(AllotmentStudentActivity.this, "please select subject", Toast.LENGTH_SHORT).show();
                } else {

                    if (data.checkAllotSubject(stuId, subId)) {
                        Toast.makeText(AllotmentStudentActivity.this, "Subject Already Allotted", Toast.LENGTH_SHORT).show();
                    } else {
                        data.allotSubject(subId, stuId);
                        Toast.makeText(AllotmentStudentActivity.this, "Subject Allotted", Toast.LENGTH_SHORT).show();

                        for (SubjectModel subjectModel : subjList) {
                            if (subjectModel.getId() == subId) {
                                items.add(subjectModel.getSubject());
                                allotAdapter.notifyDataSetChanged();

                            }
                        }
                    }

                }
            }
        });


        subjList = data.fetchSubject();

        temp.clear();
        for (int i = 0; i < subjList.size(); i++) {
            temp.add(subjList.get(i).getSubject());


        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, temp);

        allot.setAdapter(adapter);

        allot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                for (SubjectModel item : subjList) {
                    if (item.getSubject() == temp.get(position)) {
                        subId = item.getId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}




