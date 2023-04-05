package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.WorkoutAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiClient {

    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static WorkoutAPI getWorkoutService(){
        WorkoutAPI workoutAPI = getRetrofit().create(WorkoutAPI.class);
        return workoutAPI;
    }

    public static PostAPI getPostService(){
        PostAPI postAPI = getRetrofit().create(PostAPI.class);
        return postAPI;
    }
    public static UserAPI getUserService(){
        UserAPI userAPI = getRetrofit().create(UserAPI.class);
        return userAPI;
    }
}
