package com.example.gymstagram.model;

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

    public String id;
    public String name;
    public int calories;
    public int carbs;
    public int protein;
    public int fat;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories='" + calories + '\'' +
                ", carbs='" + carbs + '\'' +
                ", protein='" + protein + '\'' +
                ", fat='" + fat + '\'' +
                '}';
    }
}

