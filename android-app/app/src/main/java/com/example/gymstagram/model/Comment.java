package com.example.gymstagram.model;

public class Comment {
    private String id;
    private String userId;
    private int likes;
    private String text;
    private long timestamp;

    public Comment(String userId, String text) {
        this.id = "some";
        this.userId = userId;
        this.text = text;
        this.likes = 0;
        this.timestamp = 1;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
