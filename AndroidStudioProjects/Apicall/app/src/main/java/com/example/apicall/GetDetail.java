package com.example.apicall;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;



public class GetDetail extends AppCompatActivity {

    TextView title,summary, newsSite;

    ImageView image;

    MaterialToolbar toolbar;




    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.title_api);
        summary = findViewById(R.id.summary);
        newsSite = findViewById(R.id.newsSite);
        image = findViewById(R.id.img_api);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        String url;

        url = getIntent().getStringExtra("image");

        Picasso.with(this).load(url).into(image);


        title.setText(getIntent().getStringExtra("title"));
        summary.setText("summary :- \n" + getIntent().getStringExtra("summary"));
        newsSite.setText("newsSite :- \n" + getIntent().getStringExtra("newsSite"));



    }
}