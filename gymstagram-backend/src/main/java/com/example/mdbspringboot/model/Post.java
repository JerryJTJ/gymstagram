/**

This class represents the Model for Post entity
*/

package com.example.mdbspringboot.model;
import java.util.List;
import java.util.ArrayList;

import com.example.mdbspringboot.model.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Post")
public class Post {

    @Id
    private String id;
    private String userId;
    private String description;
    // private String mediaUrl;
    private int likes;
    private List<Comment> comments;
    private long timestamp;

    public Post(String id, String description, String userId) {
        super();
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.likes = 0;
        this.comments = new ArrayList<>();
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    // public String getMediaUrl() {
    //     return mediaUrl;
    // }

    public int getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public void setMediaUrl(String mediaUrl) {
    //     this.mediaUrl = mediaUrl;
    // }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}