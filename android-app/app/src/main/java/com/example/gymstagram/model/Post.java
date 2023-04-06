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
    private List<String> userIdLikes;
    private long timestamp;


    public Post(){
        userId = "some title";
        description = "some description";
    }

    public Post(String id_, String userId_, String description_, List<String> photos){
        id = id_;
        userId = userId_;
        description = description_;


        mediaIds = photos;
        userIdLikes = Collections.emptyList();
        mediaIds = photos;

    }
    public String getDescription(){
        return description;
    }
    public List<String> getphoto(){
        return mediaIds;
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
    public List<String> getLikes() {
        return userIdLikes == null ? Collections.emptyList() : userIdLikes;
    }

    public int getNumLikes(){
        return userIdLikes == null ? 0 : userIdLikes.size();
    }

    public String getId() {
        return id;
    }
}
