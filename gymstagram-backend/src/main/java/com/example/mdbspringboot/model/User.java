package com.example.mdbspringboot.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {
    @Id
    private String id;
    
    private String username;

    private String email;
    
    private String password;
    
    private List<String> following;

    private Float currentWeight;

    private Float targetWeight;

    public User(String id, String username, String email, String password, List<String> following, Float currentWeight, Float targetWeight) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.following = following;
		this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public Float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Float getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Float targetWeight) {
        this.targetWeight = targetWeight;
    }
}
