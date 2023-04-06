package com.example.gymstagram;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.gymstagram.model.Comment;
import com.example.gymstagram.model.Post;
import com.example.gymstagram.model.User;
import com.example.gymstagram.retrofit.ApiClient;
import com.example.gymstagram.retrofit.RetrofitService;
import com.example.gymstagram.retrofit.UserAPI;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class CardForPost extends LinearLayout {
    private ImageView profilePhoto;
    private TextView username;
    private TextView locationAndDate;
    private ImageView postImage;
    private TextView post_text;
    private TextView numLikes;
    private ImageView likeButton;
    private ImageView commentButton;
    private EditText addComment;
    private String post_id;
    boolean toggleShowComment;
    ImageView addCommentButton;

    boolean toggleLike;
    int numm;
    String postID;
    LinearLayout linearLayout;
    LinearLayout thewholecommentthingLinLayout ;

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
        linearLayout = (LinearLayout)findViewById(R.id.card);

        profilePhoto = (ImageView)findViewById(R.id.profile_photo);
        username = (TextView)findViewById(R.id.username);
        locationAndDate = (TextView)findViewById(R.id.locationAndDate);
        postImage = (ImageView)findViewById(R.id.post_image);
        post_text = (TextView)findViewById(R.id.post_text);
        numLikes = (TextView)findViewById(R.id.num_likes);
        likeButton = (ImageView)findViewById(R.id.like);
        commentButton = (ImageView)findViewById(R.id.comment) ;
        addComment = (EditText)findViewById(R.id.newComment);
        addCommentButton = (ImageView)findViewById(R.id.sendComment);
        thewholecommentthingLinLayout = (LinearLayout)findViewById(R.id.thewholecommentthing);
        toggleShowComment = true;
        toggleLike = true;
        thewholecommentthingLinLayout.setVisibility(GONE);
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
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleShowComment){
                    thewholecommentthingLinLayout.setVisibility(VISIBLE);

                }
                else{
                    thewholecommentthingLinLayout.setVisibility(GONE);
                }
                toggleShowComment = !toggleShowComment;
            }
        });
        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hhhh", "onClick: comment");
                String commentToSendStr = addComment.getText().toString();
                Comment commentToSend = new Comment(MainActivity.userId, commentToSendStr);
                Call<Comment> newComment = ApiClient.getPostService().addCommentToPost(postID, commentToSend);
                //then in enqueue call add comment to post, which should set the ui stuff
                newComment.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if(response.isSuccessful()){
                            displayComment(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        username.setText("default ;(");
                    }
                });
            }
        });
    }
    public void updateCard(String id,String userID_, String locAndDate, String post_content, String num_likes, List<Comment> listComments){
        post_id = id;
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
        numm = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        numLikes.setText(numm + " likes");
//        theres a setVisibility(GONE) if we don't want to show "0 liked"
        Collections.reverse(listComments);
        for (int i = 0; i < listComments.size(); i++){
            displayComment(listComments.get(i));
        }
        postID = id;
    }

    public void displayComment(Comment commentToDisplay){
        TextView newText = new TextView(getContext());
        newText.setText(commentToDisplay.getText());
        newText.setPadding(30, 30, 0,0);
        thewholecommentthingLinLayout.addView(newText);
    }
}
