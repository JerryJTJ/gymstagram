package com.example.gymstagram.model;

import java.util.Date;

import androidx.annotation.NonNull;

public class Meal {

    public Meal(String id, String name, int calories, int carbs, int protein, int fat){
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this. protein = protein;
        this.fat = fat;
    }

    private String id;
    private String name;
    private int calories;
    private int carbs;
    private int protein;
    private int fat;
    private Date creationDate;


    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                ", carbs='" + carbs + '\'' +
                ", protein='" + protein + '\'' +
                ", fat='" + fat + '\'' +
                '}';
    }

    public int getCalories() {
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public int getFat() {
        return fat;
    }

    public int getProtein() {
        return protein;
    }

    public Date getCreationDate() {
        return creationDate;
    }
}

