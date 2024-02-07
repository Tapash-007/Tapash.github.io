package com.example.productlist.model;

import android.media.Rating;

import java.util.ArrayList;

public class ModelClass {

 String image, title, price;

 int id;

 public Rating rating;

 public ModelClass(String image, String title, String price, int id, Rating rating) {
  this.image = image;
  this.title = title;
  this.price = price;
  this.id = id;
  this.rating = rating;

 }



 public static class Rating {

  float rate;

  int count;

  public Rating(float rate, int count) {
   this.rate = rate;
   this.count = count;
  }

  public float getRate() {
   return rate;
  }

  public void setRate(float rate) {
   this.rate = rate;
  }

  public int getCount() {
   return count;
  }

  public void setCount(int count) {
   this.count = count;
  }
 }

 public String getImage() {
  return image;
 }

 public void setImage(String image) {
  this.image = image;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getPrice() {
  return price;
 }

 public void setPrice(String price) {
  this.price = price;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }




  public class rating {

   String rate;

   public String getRate() {
    return rate;
   }

   public void setRate(String rate) {
    this.rate = rate;
   }

   public rating(String rate) {
    this.rate = rate;


   }
  }

 }


























