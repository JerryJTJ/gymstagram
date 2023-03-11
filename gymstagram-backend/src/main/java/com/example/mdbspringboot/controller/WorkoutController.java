/**

This class represents the controller for Workout entity
*/
package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.model.Workout;
import com.example.mdbspringboot.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository repository;

    /**
     * Get all workouts
     * @return a list of all workouts
     */
    @GetMapping
    public List<Workout> getAllWorkouts() {
        return repository.findAll();
    }

    /**
     * Get a specific workout by ID
     * @param id the ID of the workout to retrieve
     * @return the workout with the given ID, or null if no such workout exists
     */
    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable String id) {
        Optional<Workout> workout = repository.findById(id);
        return workout.orElse(null);
    }

    /**
     * Create a new workout
     * @param workout the workout to create
     * @return the newly created workout
     * @throws IllegalArgumentException if the workout object is null
     */
    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout cannot be null");
        }
        return repository.save(workout);
    }

    /**
     * Update an existing workout
     * @param id the ID of the workout to update
     * @param workout the updated workout data
     * @return the updated workout, or null if no such workout exists
     * @throws IllegalArgumentException if the workout object is null
     */
    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable String id, @RequestBody Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout cannot be null");
        }
        Optional<Workout> existingWorkout = repository.findById(id);
        if (existingWorkout.isPresent()) {
            Workout updatedWorkout = existingWorkout.get();
            updatedWorkout.setName(workout.getName());
            updatedWorkout.setReps(workout.getReps());
            updatedWorkout.setSets(workout.getSets());
            updatedWorkout.setWeight(workout.getWeight());
            updatedWorkout.setDuration(workout.getDuration());
            return repository.save(updatedWorkout);
        } else {
            return null;
        }
    }

    /**
     * Delete a workout by ID
     * @param id the ID of the workout to delete
     * @return the ID of the deleted workout
     * @throws IllegalArgumentException if no workout with the given ID exists
     */
    @DeleteMapping("/{id}")
    public String deleteWorkout(@PathVariable String id) {
        Optional<Workout> existingWorkout = repository.findById(id);
        if (existingWorkout.isPresent()) {
            repository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("No workout with ID " + id + " exists");
        }
    }
}