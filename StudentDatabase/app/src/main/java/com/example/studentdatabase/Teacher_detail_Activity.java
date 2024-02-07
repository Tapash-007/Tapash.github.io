package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.studentdatabase.databinding.ActivityTeacherDetailBinding;
import com.example.studentdatabase.db.DatabaseHelper;
import com.example.studentdatabase.model.TeacherModel;

public class Teacher_detail_Activity extends AppCompatActivity {

    ActivityTeacherDetailBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityTeacherDetailBinding.inflate(getLayoutInflater());
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
                    Toast.makeText(Teacher_detail_Activity.this, "Name can't be empty", Toast.LENGTH_SHORT).show();
                } else if (bind.email.getText().toString().isEmpty()) {
                    bind.email.requestFocus();
                    Toast.makeText(Teacher_detail_Activity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(bind.email.getText().toString().trim()).matches()) {

            } else if (bind.password.getText().toString().isEmpty()) {
                    Toast.makeText(Teacher_detail_Activity.this, "Enter your password", Toast.LENGTH_SHORT).show();


                } else if (bind.phoneNo.getText().toString().isEmpty()) {
                    Toast.makeText(Teacher_detail_Activity.this, "Enter your phone no", Toast.LENGTH_SHORT).show();

                } else if (data.checkTeacherExists(bind.email.toString())) {
                    Toast.makeText(Teacher_detail_Activity.this, "Teacher Already Exists", Toast.LENGTH_SHORT).show();
                }else {

                    String nameValue = bind.name.getText().toString();
                    String emailValue = bind.email.getText().toString();
                    String passwordValue = bind.password.getText().toString();
                    String phone_noValue = bind.phoneNo.getText().toString();

                    DatabaseHelper data = new DatabaseHelper(Teacher_detail_Activity.this);
                    TeacherModel model = new TeacherModel(nameValue,emailValue,passwordValue,phone_noValue);

                    data.addTeacher(model);


                    Toast.makeText(Teacher_detail_Activity.this, "Entry successful", Toast.LENGTH_SHORT).show();
                    finish();

                }

                }
            });

    }
}