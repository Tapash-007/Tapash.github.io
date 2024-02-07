package com.example.memes.model;

import java.util.ArrayList;

public class ModelClass {

    Boolean success;

    public Data data;


    public ModelClass(Boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

    public static class Data {

        public ArrayList<Memes> memes;

        public Data(ArrayList<Memes> memes) {
            this.memes = memes;
        }

        public static class Memes {
            String id, name, url, width, height, box_count;

            public Memes() {

            }


            public Memes(String id, String name, String url, String width, String height, String box_count) {
                this.id = id;
                this.name = name;
                this.url = url;
                this.width = width;
                this.height = height;
                this.box_count = box_count;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public String getBox_count() {
                return box_count;
            }

            public void setBox_count(String box_count) {
                this.box_count = box_count;
            }
        }
    }
}







