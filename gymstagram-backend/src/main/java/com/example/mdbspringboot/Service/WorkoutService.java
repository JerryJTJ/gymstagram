/**

This class represents the Service for Workout entity
*/
package com.example.mdbspringboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mdbspringboot.model.Workout;
import com.example.mdbspringboot.repository.WorkoutRepository;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    /**
     * Constructor for WorkoutService that initializes the workout repository.
     * 
     * @param workoutRepository the workout repository
     */
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    /**
     * Retrieves all workouts from the workout repository.
     * 
     * @return the list of all workouts
     */
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    /**
     * Retrieves a workout by its ID from the workout repository.
     * 
     * @param id the ID of the workout to retrieve
     * @return an Optional containing the retrieved workout, or an empty Optional if the workout is not found
     */
    public Optional<Workout> getWorkoutById(String id) {
        return workoutRepository.findById(id);
    }

    /**
     * Creates a new workout in the workout repository.
     * 
     * @param workout the workout to create
     * @return the created workout
     */
    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

    /**
     * Updates an existing workout in the workout repository.
     * 
     * @param id the ID of the workout to update
     * @param workout the updated workout
     * @throws IllegalArgumentException if the workout is not found in the repository
     */
    public void updateWorkout(String id, Workout workout) {
        Optional<Workout> existingWorkout = workoutRepository.findById(id);
        if (existingWorkout.isPresent()) {
            Workout updatedWorkout = existingWorkout.get();
            updatedWorkout.setName(workout.getName());
            updatedWorkout.setReps(workout.getReps());
            updatedWorkout.setSets(workout.getSets());
            updatedWorkout.setWeight(workout.getWeight());
            updatedWorkout.setDuration(workout.getDuration());
            workoutRepository.save(updatedWorkout);
        } else {
            throw new IllegalArgumentException("Workout not found");
        }
    }

    /**
     * Deletes a workout from the workout repository.
     * 
     * @param id the ID of the workout to delete
     * @throws IllegalArgumentException if the workout is not found in the repository
     */
    public void deleteWorkout(String id) {
        Optional<Workout> existingWorkout = workoutRepository.findById(id);
        if (existingWorkout.isPresent()) {
            workoutRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Workout not found");
        }
    }
}