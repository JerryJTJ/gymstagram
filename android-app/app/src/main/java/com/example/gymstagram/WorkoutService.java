package com.example.gymstagram;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface WorkoutService {
<<<<<<< HEAD
@POST("http://10.0.2.2:8080/")
=======
@POST("/")
>>>>>>> 4aabf93 (new connection)
Call<WorkoutResponse> saveWorkout(@Body WorkoutRequest workoutRequest);
}


