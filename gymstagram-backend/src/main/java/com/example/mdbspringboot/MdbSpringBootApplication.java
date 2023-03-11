package com.example.mdbspringboot;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.Workout;
import com.example.mdbspringboot.repository.WorkoutRepository;

import com.example.mdbspringboot.model.Meal;
import com.example.mdbspringboot.repository.MealRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
    
    @Autowired
    WorkoutRepository workoutRepo;

	@Autowired
	MealRepository mealRepo;
    
	List<Workout> workoutList = new ArrayList<Workout>();
	List<Meal> mealList = new ArrayList<Meal>();

    public static void main(String[] args) {
        SpringApplication.run(MdbSpringBootApplication.class, args);
    }

	public void run(String... args) {
	}
}