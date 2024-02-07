package com.example.studentdatabase.model;

public class StudentModel {

    String name, age, fname, contact;

    int id;

    public StudentModel(String name, String age, String fname, String contact,int id) {
        this.name = name;
        this.age = age;
        this.fname = fname;
        this.contact = contact;
        this.id = id;
    }

    public StudentModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}