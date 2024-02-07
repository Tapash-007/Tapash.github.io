package com.example.apicall;

public class ModelApi {

    public String image;

    String title, summary, newsSite;


    public ModelApi(String title, String summary, String newsSite, String image) {

        this.image = image;
        this.title = title;
        this.summary = summary;
        this.newsSite = newsSite;
    }
}
