package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.Post;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PostAPI {
    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/{postId}")
    Call<Post> getPostById(@Path("postId") String postId);

    @GET("/posts/user/{userId}")
    Call<List<Post>> getAllPostsByUserId(@Path("userId") String userId);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @Multipart
    @POST("/photo")
    Call<String> addPhoto(@Part MultipartBody.Part image);

    @PUT("/posts/{id}/like/{userId}")
    Call<Void> likePost(@Path("id") String id, @Path("userId") String userId);



}


