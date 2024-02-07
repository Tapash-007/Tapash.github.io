package com.example.articles;

public class ModelClass {

    String image,id,title,summary,newsSite;

    public ModelClass(String image,String id, String title, String summary, String newsSite) {
        this.image = image;
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.newsSite = newsSite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNewsSite() {
        return newsSite;
    }

    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }
}
