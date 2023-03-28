package com.example.gymstagram;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
public class Post {
    String postTitle;
    String description;
    Date dateCreated;


    public Post(){
        postTitle = "some title";
        description = "some description";
        dateCreated = Calendar.getInstance().getTime();
    }
    public Post(String title_, String description_){
        postTitle = title_;
        description = description_;
        dateCreated = Calendar.getInstance().getTime();
    }
    public String getDescription(){
        return description;
    }
    public String getTitle() { return postTitle; }
    public String getDateCreated() {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateString = df.format(dateCreated);
        return dateString;
    }
}
