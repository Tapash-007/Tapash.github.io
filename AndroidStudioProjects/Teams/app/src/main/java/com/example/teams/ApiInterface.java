package com.example.teams;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiInterface {


    @GET("api/v1/teams")
    Call<DataClass> getData();

}


