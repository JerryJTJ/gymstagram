package com.example.mdbspringboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.Meal;

public interface MealRepository extends MongoRepository<Meal, String> {
	
	@Query("{id:'?0'}")
	Meal findMealById(String id);
	
	@Query(value="{id:'?0'}")
	List<Meal> findAll(String id);
	
	public long count();

}