package com.example.apidriver.interfaces;

import com.example.apidriver.model.ModelClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Driver_Interface {

    @GET("/api/f1/drivers.json")
    Call<ModelClass> getData();
}
