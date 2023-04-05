package com.example.gymstagram.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    public User(String id, String username, String email, String password, List<String> following, Float currentWeight, Float targetWeight){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.following = following;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
    }
    @SerializedName("id")
    private String id;

    @SerializedName("username")
    private String username;

    private String email;

    private String password;


    @SerializedName("following")
    private List<String> following;

    private Float currentWeight;

    private Float targetWeight;

    public String getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public List<String> getFollowing(){
        return following;
    }
}

