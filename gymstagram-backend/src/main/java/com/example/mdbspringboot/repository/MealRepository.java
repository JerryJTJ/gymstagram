package com.example.mdbspringboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mdbspringboot.model.Meal;

@Repository
public interface MealRepository extends MongoRepository<Meal, String> {

}