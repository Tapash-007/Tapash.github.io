package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class CalActivity extends AppCompatActivity {

    TextView result;

    EditText fNum, sNum;

    MaterialButton add, sub, multi, div;

    private char ACTION;

    private final char ADD = '+';
    private final char SUB = '-';

    private final char MULTI = '*';
    private final char DIV = '/';


    char currentSymbol;

    private int num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        fNum = findViewById(R.id.first_number);
        sNum = findViewById(R.id.second_number);
        result = findViewById(R.id.result);

        add = findViewById(R.id.addition);
        sub = findViewById(R.id.subtract);
        multi = findViewById(R.id.multiply);
        div = findViewById(R.id.divide);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fNum.getText().toString().isEmpty() || sNum.getText().toString().isEmpty()) {
                    Toast.makeText(CalActivity.this, "Number can't be empty", Toast.LENGTH_SHORT).show();
                } else {

                    ACTION = ADD;
                    operation();
                }

            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fNum.getText().toString().isEmpty() || sNum.getText().toString().isEmpty()) {
                    Toast.makeText(CalActivity.this, "Number can't be empty", Toast.LENGTH_SHORT).show();
                } else {


                    {
                        ACTION = SUB;
                        operation();
                    }
                }
            }
        });


        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fNum.getText().toString().isEmpty() || sNum.getText().toString().isEmpty()) {
                    Toast.makeText(CalActivity.this, "Number can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    ACTION = MULTI;
                    operation();
                }
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fNum.getText().toString().isEmpty() || sNum.getText().toString().isEmpty()) {
                    Toast.makeText(CalActivity.this, "Number can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    ACTION = DIV;
                    operation();
                }
            }
        });

    }

    private void operation() {

        num1 = Integer.parseInt(fNum.getText().toString());
        num2 = Integer.parseInt(sNum.getText().toString());

        switch (ACTION) {
            case ADD: {
                String res = String.valueOf(num1 + num2);
                result.setText("Result: " + res);
                break;
            }

            case SUB: {
                String res = String.valueOf(num1 - num2);
                result.setText("Result: " + res);
                break;
            }

            case MULTI: {
                String res = String.valueOf(num1 * num2);
                result.setText("Result: " + res);
                break;
            }
            case DIV: {
                String res = String.valueOf(num1 / num2);
                result.setText("Result: " + res);
                break;

            }
        }
    }
}















