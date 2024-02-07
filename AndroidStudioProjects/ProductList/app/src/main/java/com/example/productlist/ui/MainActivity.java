package com.example.productlist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.example.productlist.R;
import com.example.productlist.controller.ProductAdapter;
import com.example.productlist.interfaces.ProductInterface;
import com.example.productlist.model.ModelClass;
import com.example.productlist.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ProductAdapter adapter;

    ArrayList<ModelClass> productList = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rec);
        adapter = new ProductAdapter(this,productList);
        recyclerView.setAdapter(adapter);

//        progressDialog.show(this,"Loading...","wait while loading....");
        progressDialog  = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Wait while loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        apiCall();

    }

    private void apiCall() {

        ProductInterface productInterface;

        productInterface = RetrofitClient.getRetrofit().create(ProductInterface.class);

        Call<ArrayList<ModelClass>> apiCall = productInterface.getData();

        apiCall.enqueue(new Callback<ArrayList<ModelClass>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelClass>> call, Response<ArrayList<ModelClass>> response) {
                Log.d(getClass().getSimpleName(), "onResponse: " + response.body());
                progressDialog.dismiss();

                productList.clear();
                for (int i=0;  i<response.body().size(); i++) {
                    Log.d(getClass().getSimpleName(), "onResponse: " + response.body().get(i).getTitle());

                    productList.add(new ModelClass (
                            response.body().get(i).getImage(),
                            response.body().get(i).getTitle(),
                            response.body().get(i).getPrice(), response.body().get(i).getId(),
                           new ModelClass.Rating(
                            response.body().get(i).rating.getRate(),
                            response.body().get(i).rating.getCount()


                            )));

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelClass>> call, Throwable t) {
                Log.d(getClass().getSimpleName(), "onFailure: " + t.getMessage());
                progressDialog.dismiss();


            }
        });

    }

}