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
		
		// Clean up any previous data
		workoutRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE WORKOUTS-------------------------------\n");
		
		createWorkouts();
		
		System.out.println("\n----------------SHOW ALL WORKOUTS---------------------------\n");
		
		showAllWorkouts();
		
		System.out.println("\n--------------GET WORKOUT BY ID-----------------------------------\n");
		
		getWorkoutById("2");
		
		System.out.println("\n----------DELETE A WORKOUT----------------------------------\n");
		
		deleteWorkout("1");
		
		System.out.println("\n------------FINAL COUNT OF WORKOUTS-------------------------\n");
		
		findCountOfWorkouts();

		// MEALS
		mealRepo.deleteAll(); // Doesn't delete the collection
		
		System.out.println("-------------CREATE MEALS-------------------------------\n");
		
		createMeals();
		
		System.out.println("\n----------------SHOW ALL MEALS---------------------------\n");
		
		showAllMeals();
		
		System.out.println("\n--------------GET MEAL BY ID-----------------------------------\n");
		
		getMealById("2");
		
		System.out.println("\n----------DELETE A MEAL----------------------------------\n");
		
		deleteMeal("1");
		
		System.out.println("\n------------FINAL COUNT OF MEALS-------------------------\n");
		
		findCountOfMeals();
								
	}

	// WORKOUTS

	//CREATE
	void createWorkouts() {
		System.out.println("Data creation started...");

		workoutRepo.save(new Workout("1", "Pec flys", 10, 5, 100, 600));
		workoutRepo.save(new Workout("2", "Chest press", 5, 5, 405, 600));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllWorkouts() {
		 
		 workoutList = workoutRepo.findAll();
		 
		 workoutList.forEach(workout -> System.out.println(getWorkoutDetails(workout)));
	 }
	 
	 // 2. Get item by id
	 public void getWorkoutById(String id) {
		 System.out.println("Getting workout by id: " + id);
		 Workout workout = workoutRepo.findWorkoutById(id);
		 System.out.println(getWorkoutDetails(workout));
	 }
	 
	 // 3. Get count of documents in the collection
	 public void findCountOfWorkouts() {
		 long count = workoutRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }
	 
	 // DELETE
	 public void deleteWorkout(String id) {
		 workoutRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getWorkoutDetails(Workout workout) {

		 System.out.println(
		 "Workout Name: " + workout.getName() + 
		 ", \nReps: " + workout.getReps() + 
		 ", \nSets: " + workout.getSets() +
		 ", \nWeight: " + workout.getWeight() + 
		 ", \nDuration: " + workout.getDuration()
		 );
		 
		 return "";
	 }

	// MEALS
	//CREATE
	void createMeals() {
		System.out.println("Data creation started...");

		mealRepo.save(new Meal("1", 2000, 100, 120, 150));
		mealRepo.save(new Meal("2", 3000, 200, 170, 120));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllMeals() {
		 
		 mealList = mealRepo.findAll();
		 
		 mealList.forEach(meal -> System.out.println(getMealDetails(meal)));
	 }
	 
	 // 2. Get item by id
	 public void getMealById(String id) {
		 System.out.println("Getting meal by id: " + id);
		 Meal meal = mealRepo.findMealById(id);
		 System.out.println(getMealDetails(meal));
	 }
	 
	 // 3. Get count of documents in the collection
	 public void findCountOfMeals() {
		 long count = mealRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }
	 
	 // DELETE
	 public void deleteMeal(String id) {
		 mealRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getMealDetails(Meal meal) {

		 System.out.println(
		 "Meal Id: " + meal.getId() + 
		 ", \nCalories: " + meal.getCalories() + 
		 ", \nCarbs: " + meal.getCarbs() +
		 ", \nProtein: " + meal.getProtein() + 
		 ", \nFat: " + meal.getFat()
		 );
		 
		 return "";
	 }
}