package com.example.mdbspringboot.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mdbspringboot.model.Meal;
import com.example.mdbspringboot.repository.MealRepository;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(String id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal createMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public Meal updateMeal(String id, Meal meal) {
        Meal existingMeal = mealRepository.findById(id).orElse(null);
        if (existingMeal != null) {
            existingMeal.setName(meal.getName());
            existingMeal.setCalories(meal.getCalories());
            existingMeal.setCarbs(meal.getCarbs());
            existingMeal.setProtein(meal.getProtein());
            existingMeal.setFat(meal.getFat());
            return mealRepository.save(existingMeal);
        }
        return null;
    }

    public void deleteMeal(String id) {
        mealRepository.deleteById(id);
    }
}