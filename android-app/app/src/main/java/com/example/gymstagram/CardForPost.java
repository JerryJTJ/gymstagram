package com.example.gymstagram;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.concurrent.ThreadLocalRandom;

import androidx.core.content.ContextCompat;

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
    public void updateCard(String id,String username_, String locAndDate, String post_content, String num_likes){
        username.setText(username_);
        locationAndDate.setText(locAndDate);
        post_text.setText(post_content);
        numm = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        numLikes.setText(numm + " likes");
//        theres a setVisibility(GONE) if we don't want to show "0 liked"

        postID = id;
    }
}
