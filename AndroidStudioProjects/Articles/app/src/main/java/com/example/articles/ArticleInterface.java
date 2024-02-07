package com.example.articles;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ArticleInterface {

    @GET("/v3/articles")
    Call<ArrayList<ModelClass>> getData();
}
