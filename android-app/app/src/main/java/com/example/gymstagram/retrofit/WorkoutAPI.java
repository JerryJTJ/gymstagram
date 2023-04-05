package com.example.gymstagram.retrofit;

import com.example.gymstagram.WorkoutResponse;
import com.example.gymstagram.model.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface WorkoutAPI {
@POST("workouts")
    Call<WorkoutResponse> saveWorkout(@Body Workout workout);
@GET("workouts")
    Call<List<Workout>> getAllWorkouts();
}


