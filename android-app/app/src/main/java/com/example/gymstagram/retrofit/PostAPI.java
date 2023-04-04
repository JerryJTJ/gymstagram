package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostAPI {
    @GET("posts")
    Call<List<Post>> getAllPosts();

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @PUT("/{id}/like")
    void likePost(@Path("id") String postID);
}


