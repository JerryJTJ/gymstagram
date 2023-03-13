package com.example.gymstagram.retrofit;

import com.example.gymstagram.WorkoutResponse;
import com.example.gymstagram.model.Workout;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface WorkoutAPI {
@POST("workouts")
    Call<WorkoutResponse> saveWorkout(@Body Workout workout);
}


