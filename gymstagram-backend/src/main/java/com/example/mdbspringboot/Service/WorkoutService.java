package com.example.mdbspringboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.mdbspringboot.model.Workout;
import com.example.mdbspringboot.repository.WorkoutRepository;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Optional<Workout> getWorkoutById(String id) {
        return workoutRepository.findById(id);
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }

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
        }
    }

    public void deleteWorkout(String id) {
        workoutRepository.deleteById(id);
    }
}