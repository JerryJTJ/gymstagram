package com.example.gymstagram;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface WorkoutService {
@POST("workouts")
Call<WorkoutResponse> saveWorkout(@Body WorkoutRequest workoutRequest);
}


