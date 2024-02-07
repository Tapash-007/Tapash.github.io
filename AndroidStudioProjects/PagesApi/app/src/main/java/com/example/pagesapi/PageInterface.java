package com.example.pagesapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PageInterface {

   @GET("api/users")
    Call<ModelClass> getData(@Query("page") int page);
}
