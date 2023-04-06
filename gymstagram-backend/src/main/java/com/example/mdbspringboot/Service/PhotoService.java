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
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    public Map<String, Object> addPhoto(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
        
        Map<String, Object> result = new HashMap<>();
        result.put("photoId", photo.getId().toString());
        
        return result;
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
        try {
            return new ResponseEntity<>(photo.getImage().getData(), headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // Handle the exception
            System.out.println("No value present in the optional");
            return null;
        }
    }

    public void deletePhotoById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try {
            photoRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete post");
        }
    }
}