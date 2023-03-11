package com.example.mdbspringboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdbspringboot.model.Meal;
import com.example.mdbspringboot.repository.MealRepository;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    /**
     * Returns a list of all meals in the database.
     * @return List of meals
     */
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    /**
     * Returns the meal with the specified ID, if it exists.
     * @param id The ID of the meal to retrieve
     * @return The meal with the specified ID, or null if it does not exist
     */
    public Meal getMealById(String id) {
        Optional<Meal> meal = mealRepository.findById(id);
        return meal.orElse(null);
    }

    /**
     * Creates a new meal in the database.
     * @param meal The meal to create
     * @return The created meal
     * @throws IllegalArgumentException If the meal is null
     */
    public Meal createMeal(Meal meal) {
        if (meal == null) {
            throw new IllegalArgumentException("Meal cannot be null");
        }
        return mealRepository.save(meal);
    }

    /**
     * Updates the meal with the specified ID in the database.
     * @param id The ID of the meal to update
     * @param meal The updated meal data
     * @return The updated meal, or null if the meal does not exist
     */
    public Meal updateMeal(String id, Meal meal) {
        Optional<Meal> existingMeal = mealRepository.findById(id);
        if (existingMeal.isPresent()) {
            Meal updatedMeal = existingMeal.get();
            updatedMeal.setName(meal.getName());
            updatedMeal.setCalories(meal.getCalories());
            updatedMeal.setCarbs(meal.getCarbs());
            updatedMeal.setProtein(meal.getProtein());
            updatedMeal.setFat(meal.getFat());
            return mealRepository.save(updatedMeal);
        }
        return null;
    }

    /**
     * Deletes the meal with the specified ID from the database.
     * @param id The ID of the meal to delete
     * @throws IllegalArgumentException If the ID is null
     * @throws RuntimeException If the meal does not exist or could not be deleted
     */
    public void deleteMeal(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try {
            mealRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete meal", e);
        }
    }
}