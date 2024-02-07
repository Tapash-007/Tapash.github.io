package com.example.memes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.memes.interfaces.MemesInterface;
import com.example.memes.model.ModelClass;
import com.example.memes.network.RetrofitClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class GetDetails extends AppCompatActivity {


    TextView id,name,width,height,box_count;

    ImageView image;

    MaterialToolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        id = findViewById(R.id.Id);
        name = findViewById(R.id.name);
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        box_count = findViewById(R.id.box_count);
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
        id.setText("id:-"+ " " +getIntent().getStringExtra("id"));
        name.setText("name:-"+ " " +getIntent().getStringExtra("name"));
        width.setText("width:-"+ " " +getIntent().getStringExtra("width"));
        height.setText("height:-"+ " " +getIntent().getStringExtra("height"));
        box_count.setText("box_count:-"+ " " +getIntent().getStringExtra("box_count"));


    }
}