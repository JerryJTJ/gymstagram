package com.example.mdbspringboot.Service;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.mdbspringboot.model.Photo;
import com.example.mdbspringboot.repository.PhotoRepository;

import java.io.IOException;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    public String addPhoto(MultipartFile file) throws IOException { 
        Photo photo = new Photo(); 
        photo.setImage(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        photo = photoRepo.insert(photo); 
        return photo.getId().toString(); 
    }

    public ResponseEntity<Object> getPhoto(String id) { 
        Photo photo = photoRepo.findById(id).get(); 
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
    
        // Set the Content-Type header to image/png
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
    
        // Return the raw image data in the response body
        return new ResponseEntity<>(photo.getImage().getData(), headers, HttpStatus.OK);
    }
}