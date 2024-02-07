package com.example.memes.interfaces;

import com.example.memes.model.ModelClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MemesInterface {

@GET("/get_memes")
    Call<ModelClass> getData();
}
