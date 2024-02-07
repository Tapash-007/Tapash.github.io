package com.example.articles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class GetDetails extends AppCompatActivity {

    TextView id,title,summary,newsSite;

    ImageView image;

    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        id = findViewById(R.id.Id);
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

        id.setText(getIntent().getStringExtra("id"));
        title.setText("title :- \n" + getIntent().getStringExtra("title"));
        summary.setText("summary :- \n" + getIntent().getStringExtra("summary"));
        newsSite.setText("newsSite :- \n" + getIntent().getStringExtra("newsSite"));



    }
}

