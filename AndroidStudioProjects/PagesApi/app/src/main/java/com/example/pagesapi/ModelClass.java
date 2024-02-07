package com.example.pagesapi;

import java.util.ArrayList;

public class ModelClass {

    int page, per_page, total, total_pages;

    ArrayList<Data> data;

    Support support;

    public ModelClass(int page, int per_page, int total, int total_pages, ArrayList<Data> data, Support support) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
        this.support = support;
    }

    public static class Data {
        private String id, email, first_name, last_name, avatar;
        public Data(String id, String email, String first_name, String last_name, String avatar) {

            this.id = id;
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.avatar = avatar;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {

            this.id = id;
        }

        public String getEmail() {

            return email;
        }

        public void setEmail(String email) {

            this.email = email;
        }

        public String getFirst_name() {

            return first_name;
        }

        public void setFirst_name(String first_name) {

            this.first_name = first_name;
        }

        public String getLast_name() {

            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {

            return avatar;
        }

        public void setAvatar(String avatar) {

            this.avatar = avatar;
        }


    }

    public static class Support {

        private String url, text;

        public Support(String url, String text) {
            this.url = url;
            this.text = text;
        }


    }

}


