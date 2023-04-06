package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.User;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {
    @GET("/users")
    Call<List<User>> getAllUsers();

    @GET("/users/{userId}")
    Call<User> getUserById(@Path("userId") String userId);

    @GET("/photo/{photoId}")
    Call<Void> getphoto(@Path("photoId") String photoId);

    @PUT("/users/{followerId}/follow/{receiverId}")
    Call<Void> followUser(@Path("followerId") String followerId, @Path("receiverId") String receiverId);

    @PUT("/users/{followerId}/unfollow/{receiverId}")
    Call<Void> unfollowUser(@Path("followerId") String followerId, @Path("receiverId") String receiverId);


}
