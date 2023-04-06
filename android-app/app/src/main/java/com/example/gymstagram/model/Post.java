package com.example.gymstagram.model;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.List;

public class Post {
    private String id;
    private String userId;
    private String description;
    private List<String> mediaIds;
    private int likes;
//    private List<String> comments;
    //List<Comment>
    private long timestamp;
    private List<Comment> comments;


    public Post(){
        userId = "some title";
        description = "some description";
    }
    public Post(String id_, String userId_, String description_){
        id = id_;
        userId = userId_;
        description = description_;
        mediaIds = Collections.emptyList();
        likes = 0;
        comments = Collections.emptyList();
        timestamp = 1;
    }
    public String getDescription(){
        return description;
    }
    public String getUserId() { return userId; }
//    public String getDateCreated() {
//        String pattern = "MM/dd/yyyy HH:mm:ss";
//        DateFormat df = new SimpleDateFormat(pattern);
//        String dateString = df.format(dateCreated);
//        return dateString;
//    }

    public long getTimestamp() {
        return timestamp;
    }
    public int getLikes() {
        return likes;
    }
    public String getId() {
        return id;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
