package com.example.mdbspringboot.model;

import java.util.List;
import java.util.UUID;

public class CreateUserBody {    
    private String username;

    private String email;
    
    private String password;
    
    private Float currentWeight;

    private Float targetWeight;

    public CreateUserBody(String username, String email, String password, Float currentWeight, Float targetWeight) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
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
