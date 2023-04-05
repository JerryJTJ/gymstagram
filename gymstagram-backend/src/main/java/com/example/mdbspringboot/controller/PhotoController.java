/**

This class represents the controller for Photo entity
*/
package com.example.mdbspringboot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mdbspringboot.Service.PhotoService;

import org.springframework.ui.Model;
import java.io.IOException;


@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    
    /**
     * This method adds a photo to the database.
     * @param image The image to be added.
     * @param model The model object.
     * @return The ID of the added photo.
     * @throws IOException If an I/O error occurs while processing the photo.
     */
    @PostMapping("")
    public String addPhoto(@RequestParam("image") MultipartFile image) 
    throws IOException {
        String id = photoService.addPhoto(image);
        return id;
    }
    
    /**
     * This method retrieves a photo from the database by its ID.
     * @param id The ID of the photo to be retrieved.
     * @param model The model object.
     * @return A response entity containing the retrieved photo.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhoto(@PathVariable String id, Model model) {
        return photoService.getPhoto(id);
    }
    
    /**
     * This method deletes a photo from the database by its ID.
     * @param id The ID of the photo to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        photoService.deletePhotoById(id);
    } 
}