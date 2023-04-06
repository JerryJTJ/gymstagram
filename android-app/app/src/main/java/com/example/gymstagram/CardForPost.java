package com.example.gymstagram;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


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

        //Make retrofit service
        RetrofitService retrofitService = new RetrofitService();
        PostAPI postAPI = retrofitService.getRetrofit().create(PostAPI.class);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleLike){
                    //LIKE POST
                    numm += 1;
                    numLikes.setText(numm + " likes");
                    likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_filled));
                }
                else{
                    //UNLIKE POST
                    numm -= 1;
                    numLikes.setText(numm + " likes");
                    likeButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.heart_empty));
                }
                toggleLike = !toggleLike;
            }
        });
    }
    public void updateCard(String id,String userID_, String locAndDate, String post_content, int num_likes){
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

        locationAndDate.setText(locAndDate);
        post_text.setText(post_content);
        numLikes.setText(num_likes + " likes");

        postID = id;
    }
}
