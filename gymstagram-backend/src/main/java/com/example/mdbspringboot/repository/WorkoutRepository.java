package com.example.mdbspringboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.Workout;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
	
	@Query("{id:'?0'}")
	Workout findWorkoutById(String id);
	
	@Query(value="{id:'?0'}")
	List<Workout> findAll(String id);
	
	public long count();

}