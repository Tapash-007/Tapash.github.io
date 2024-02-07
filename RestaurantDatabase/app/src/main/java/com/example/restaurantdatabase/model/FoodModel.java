package com.example.restaurantdatabase.model;

public class FoodModel {

    String foodItems;

    int id;

    public FoodModel(String foodItems, int id) {
        this.foodItems = foodItems;
        this.id = id;
    }


    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
