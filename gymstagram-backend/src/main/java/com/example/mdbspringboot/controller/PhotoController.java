/**

This class represents the controller for Meal entity
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

    @PostMapping("")
    public String addPhoto(@RequestParam("image") MultipartFile image, Model model) 
    throws IOException {
        String id = photoService.addPhoto(image);
        return id;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhoto(@PathVariable String id, Model model) {
    return photoService.getPhoto(id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        photoService.deletePhotoById(id);
    } 
}