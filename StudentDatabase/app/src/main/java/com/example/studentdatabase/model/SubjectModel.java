package com.example.studentdatabase.model;

public class SubjectModel {

    int id;
    String subject;

    public SubjectModel(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public SubjectModel(String subject) {

        this.subject = subject;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {

        return subject;
    }
}







  