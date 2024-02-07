package com.example.myapplication.model;

public class Model {

    String userName;
    String phone;

    int image;

    public Model(String userName, String phone, int image) {
        this.userName = userName;
        this.phone = phone;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
