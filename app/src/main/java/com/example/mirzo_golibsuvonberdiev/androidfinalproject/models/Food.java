package com.example.mirzo_golibsuvonberdiev.androidfinalproject.models;

/**
 * Created by mirzo-golibsuvonberdiev on 12/17/18.
 */

public class Food {
    private String name;
    private String image;
    private String price;
    private String description;

    public Food() {
    }

    public Food(String name, String image, String price, String description) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
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
