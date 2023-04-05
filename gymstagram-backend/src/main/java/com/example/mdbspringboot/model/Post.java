/**

This class represents the Model for Post entity
*/

package com.example.mdbspringboot.model;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Post")
public class Post {

    @Id
    private String id;
    private String userId;
    private String description;
    private List<String> mediaIds;
    private List<String> userIdLikes;
    private List<Comment> comments;
    private long timestamp;

    public Post(String id, String description, String userId) {
        super();
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.userIdLikes = new ArrayList<>();
        this.mediaIds = new ArrayList<>();
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

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public List<String> getUserIdLikes() {
        return userIdLikes;
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

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setUserIdLikes(List<String> userIdLikes) {
        this.userIdLikes = userIdLikes;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}