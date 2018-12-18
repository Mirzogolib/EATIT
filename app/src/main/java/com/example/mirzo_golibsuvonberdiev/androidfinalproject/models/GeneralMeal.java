package com.example.mirzo_golibsuvonberdiev.androidfinalproject.models;

import java.util.List;

/**
 * Created by mirzo-golibsuvonberdiev on 12/18/18.
 */

public class GeneralMeal {
    private String name;
    private String image;
    private List<Food> foodList;

    public GeneralMeal() {
    }

    public GeneralMeal(String name, String image, List<Food> foodList) {
        this.name = name;
        this.image = image;
        this.foodList = foodList;
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
