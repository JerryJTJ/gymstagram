package com.example.gymstagram.retrofit;

import com.example.gymstagram.model.Comment;
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

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @PUT("/{id}/like")
    void likePost(@Path("id") String postID);

    @Multipart
    @POST("/photo")
    Call<String> addPhoto(@Part MultipartBody.Part image);


    @GET("/posts/user/{userId}")
    public Call<List<Post>> getAllPostsByUserId(@Path("userId") String userId);

    @POST("posts/{id}/comment")
    public Call<Comment> addCommentToPost(@Path("id") String id, @Body Comment comment);
}


