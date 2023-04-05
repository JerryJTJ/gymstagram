package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.Meal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MealAPI {
    @GET("/meals")
    Call<List<Meal>> getAllMeals();

    @POST("/meals")
    Call<Meal> createMeal(@Body Meal meal);
}

