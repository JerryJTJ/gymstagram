/**

This class represents the Model for Comment entity
*/

package com.example.mdbspringboot.model;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Comment")
public class Comment {

    @Id
    private String id;
    private String userId;
    private int likes;
    private String text;
    private long timestamp;

    public Comment(String userId, String text) {
        super();
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.text = text;
        this.likes = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}