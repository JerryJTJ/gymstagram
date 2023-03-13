package com.example.gymstagram;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface WorkoutService {
@POST("http://10.0.2.2:8080/")
Call<WorkoutResponse> saveWorkout(@Body WorkoutRequest workoutRequest);
}


