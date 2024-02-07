package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class details_info extends AppCompatActivity {

    TextView Id, name, width, height, box_count, captions;

    MaterialToolbar toolbar;

    ImageView image;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);


        Id = findViewById(R.id.Id);
        name = findViewById(R.id.name);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        box_count = findViewById(R.id.box);
        captions = findViewById(R.id.cap);
        toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.img_api);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        String url;

        url = getIntent().getStringExtra("image");

        Picasso.with(this).load(url).into(image);


        Id.setText(getIntent().getStringExtra("Id"));
        name.setText("name :- \n" + getIntent().getStringExtra("name"));
        width.setText("width :- \n" + getIntent().getStringExtra("width"));
        height.setText("height :- \n" + getIntent().getStringExtra("height"));
        box_count.setText("box_count :- \n" + getIntent().getStringExtra("box_count"));
        captions.setText("captions :- \n" + getIntent().getStringExtra("captions"));


    }
}
