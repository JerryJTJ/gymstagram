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


import com.example.gymstagram.model.User;
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
    int numm;
    String postID;

    public CardForPost(Context context)
    {
        super(context);
        initControl(context);
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

        toggleLike = true;
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleLike){
                    numm += 1;
                    numLikes.setText(numm + " likes");
                    likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_filled));
                    //TODO:call like API
                }
                else{
                    numm -= 1;
                    numLikes.setText(numm + " likes");
                    likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_empty));
                }
                toggleLike = !toggleLike;
            }
        });
    }
    public void updateCard(String id,String userID_, String locAndDate, String post_content, String num_likes, String photo){
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
        numm = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        numLikes.setText(numm + " likes");
//        postImage =
////        theres a setVisibility(GONE) if we don't want to show "0 liked"

        postID = id;
    }
}
