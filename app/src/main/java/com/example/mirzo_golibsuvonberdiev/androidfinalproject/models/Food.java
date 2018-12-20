package com.example.mirzo_golibsuvonberdiev.androidfinalproject.models;

/**
 * Created by mirzo-golibsuvonberdiev on 12/17/18.
 */

public class Food {
    private String foodId;
    private String name;
    private String image;
    private String price;
    private String description;

    public Food() {
    }

    public Food(String foodId, String name, String image, String price, String description) {
        this.foodId = foodId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
