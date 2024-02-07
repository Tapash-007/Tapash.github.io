package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    TextView inputText, result;

    MaterialButton one, two, three, four, five, six, seven, eight, nine, zero, decimal, multiplication,addition, subtraction, divide, equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_num);
        result = findViewById(R.id.result);

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine= findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        decimal = findViewById(R.id.decimal);
        divide = findViewById(R.id.divide);
        multiplication = findViewById(R.id.multiplication);
        subtraction = findViewById(R.id.subtraction);
        addition = findViewById(R.id.addition);
        equal = findViewById(R.id.equal);





        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "3");
            }
        });



        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "4");

            }
        });


        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "5");

            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "6");

            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "7");

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "9");

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "0");

            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ ".");

            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "/");

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "*");

            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "-");

            }
        });

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "+");

            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText.setText(inputText.getText().toString()+ "=");

            }
        });


    }


}
