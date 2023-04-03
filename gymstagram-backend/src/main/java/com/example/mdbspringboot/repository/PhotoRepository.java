package com.example.mdbspringboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mdbspringboot.model.Photo;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> { 
}