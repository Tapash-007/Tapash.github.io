package com.example.studentdatabase.model;

public class TeacherModel {

    String name,email,password,phone_no;

    int id;

    public TeacherModel(String name, String email, String password, String phone_no) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone_no = phone_no;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
