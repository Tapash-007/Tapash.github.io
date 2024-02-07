package com.example.myapplication;

public class ModelAp {

    public String image;

    String Id,name,width,height,box_count,captions;


    public ModelAp(String image, String Id,String name, String width, String height, String box_count, String captions) {

        this.image = image;
        this.Id = Id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.box_count= box_count;
        this.captions = captions;
    }
}


