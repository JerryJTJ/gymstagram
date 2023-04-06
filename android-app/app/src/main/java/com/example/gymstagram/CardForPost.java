package com.example.gymstagram;
import android.content.Context;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gymstagram.model.Post;
import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.MealAPI;
import com.example.gymstagram.retrofit.PostAPI;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import java.util.concurrent.ThreadLocalRandom;

import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardForPost extends LinearLayout {
    private ImageView profilePhoto;
    private TextView username;
    private TextView locationAndDate;
    private ImageView postImage;
    private TextView post_text;
    private TextView numLikes;
    private ImageView likeButton;

    boolean toggleLike;
    //false if already liked, true if not liked
    int numm;
    String postID;

    public CardForPost(Context context, boolean liked, int numLikes)
    {
        super(context);
        initControl(context);

        //Check if post is already liked
        toggleLike = !liked;

        numm = numLikes;

        if(toggleLike){
            //Not liked
            likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_empty));
        }
        else{
            //Liked
            likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_filled));
        }

    }
    private void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.fragment_post_card, this);

        profilePhoto = (ImageView)findViewById(R.id.profile_photo);
        username = (TextView)findViewById(R.id.username);
        locationAndDate = (TextView)findViewById(R.id.locationAndDate);
        postImage = (ImageView)findViewById(R.id.post_image);
        post_text = (TextView)findViewById(R.id.post_text);
        numLikes = (TextView)findViewById(R.id.num_likes);
        likeButton = (ImageView)findViewById(R.id.like);

        //Make retrofit service
        RetrofitService retrofitService = new RetrofitService();
        PostAPI postAPI = retrofitService.getRetrofit().create(PostAPI.class);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleLike){
                    //LIKE POST
                    Log.d("JERRY", "LIKE POST");

                    Call<Void> likeCall = postAPI.likePost(postID, MainActivity.userId);

                    Log.d("JERRY", "POST ID " + postID);
                    Log.d("Jerry", "user id " + MainActivity.userId);

                    likeCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                numm += 1;
                                numLikes.setText(numm + " likes");
                                likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_filled));
                            } else {
                                Toast.makeText(getContext(), "Error liking", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "Error liking", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    //UNLIKE POST
                    Call<Void> unlikeCall = postAPI.unlikePost(postID, MainActivity.userId);
                    Log.d("JERRY", "UNLIKE POST");

                    unlikeCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                // Post unliked successfully
                                numm -= 1;
                                numLikes.setText(numm + " likes");
                                likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_empty));
                            } else {
                                Toast.makeText(getContext(), "Error unliking", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(getContext(), "Error unliking", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                toggleLike = !toggleLike;
            }
        });
    }
    public void updateCard(String id,String userID_, String locAndDate, String post_content, int num_likes, String photo){
        RetrofitService retrofitService = new RetrofitService();
        UserAPI userAPI = retrofitService.getRetrofit().create(UserAPI.class);
        Call<User> postUser = userAPI.getUserById(userID_);
        postUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    username.setText(response.body().getUsername());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                username.setText("default ;(");
            }
        });
        String imageUrl = "http://10.0.2.2:8080/photo/"+photo;
        Log.e("BBBBBBB", "o0: "+photo);
// Create a new instance of the RequestOptions class with the centerCrop() and diskCacheStrategy() methods
        RequestOptions requestOptions = new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

// Load the image into the ImageView using Glide
        Glide.with(this)
                .load(imageUrl)
                .apply(requestOptions)
                .into(postImage);


        locationAndDate.setText(locAndDate);
        post_text.setText(post_content);
        numLikes.setText(num_likes + " likes");


        postID = id;
    }
}
