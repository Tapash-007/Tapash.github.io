package com.example.productlist.interfaces;

import com.example.productlist.model.ModelClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductInterface {

    @GET("/products")
    Call<ArrayList<ModelClass>> getData();

    @GET("/products/{id}")
    Call<ModelClass> ProductDetails(@Path("id")int id);

}
