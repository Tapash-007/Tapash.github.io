package com.example.productlist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.productlist.interfaces.ProductInterface;
import com.example.productlist.model.ModelClass;
import com.example.productlist.network.RetrofitClient;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class Getdetails extends AppCompatActivity {


    TextView title,price;
    int id;

    MaterialToolbar toolbar;

    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdetails);

        image = findViewById(R.id.img_api);
        title = findViewById(R.id.title);
        price = findViewById(R.id.price);
        toolbar = findViewById(R.id.toolbar);
        id = getIntent().getIntExtra("id",id);




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        String url;

        url = getIntent().getStringExtra("image");

        Picasso.with(this).load(url).into(image);

        title.setText("title:-"+ " "  +getIntent().getStringExtra("title"));
        price.setText("price:-"+ " "  +getIntent().getStringExtra("price"));


    }

    @Override
    protected void onStart() {
        super.onStart();

        apiCall();
    }

    private void apiCall() {

        ProductInterface productInterface;

        productInterface = RetrofitClient.getRetrofit().create(ProductInterface.class);

        Call<ModelClass> apiCall = productInterface.ProductDetails(id);

        apiCall.enqueue(new Callback<ModelClass>() {

            @Override
            public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {

                title.setText(response.body().getTitle());
                price.setText(response.body().getPrice());

                Picasso.with(getApplicationContext()).load(response.body().getImage()).into(image);

            }

            @Override
            public void onFailure(Call<ModelClass> call, Throwable t) {

            }
        });

    }
}



