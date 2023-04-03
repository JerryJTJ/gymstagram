/**

This class represents the Model for Photo entity
*/

package com.example.mdbspringboot.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.bson.types.Binary;


@Document(collection = "photos")
public class Photo {
    @Id
    private String id;       
    private Binary image;
    
    public Photo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}