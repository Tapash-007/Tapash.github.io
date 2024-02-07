package com.example.apiguru;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GuruInterface {

    @GET("/v2/list.json")
    Call<String> getData();
}
