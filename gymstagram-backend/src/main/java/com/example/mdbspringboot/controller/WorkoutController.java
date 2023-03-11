package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.model.Workout;
import com.example.mdbspringboot.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository repository;

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        return repository.save(workout);
    }

    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable String id, @RequestBody Workout workout) {
        Workout existingWorkout = repository.findById(id).orElse(null);
        if (existingWorkout != null) {
            existingWorkout.setName(workout.getName());
            existingWorkout.setReps(workout.getReps());
            existingWorkout.setSets(workout.getSets());
            existingWorkout.setWeight(workout.getWeight());
            existingWorkout.setDuration(workout.getDuration());
            return repository.save(existingWorkout);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteWorkout(@PathVariable String id) {
        repository.deleteById(id);
        return id;
    }
}