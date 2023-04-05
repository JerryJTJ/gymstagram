/**

This class represents the controller for Meal entity
*/
package com.example.mdbspringboot.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mdbspringboot.Service.MealService;
import com.example.mdbspringboot.model.Meal;
import com.example.mdbspringboot.model.CreateMealBody;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    /**
     * This method returns a list of all meals
     * 
     * @return List<Meal> list of all meals
     */
    @GetMapping("")
    public List<Meal> getAllMeals() {
        return mealService.getAllMeals();
    }

    /**
     * This method returns a meal by id
     * 
     * @param id String id of the meal
     * @return Meal meal with the given id
     */
    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable String id) {
        return mealService.getMealById(id);
    }

    /**
     * This method creates a new meal
     * 
     * @param meal Meal object to be created
     * @return Meal created meal object
     */
    @PostMapping("")
    public Meal createMeal(@RequestBody CreateMealBody createMealBody) {
        Meal meal = new Meal(createMealBody.getName(), createMealBody.getCalories(), createMealBody.getCarbs(), createMealBody.getProtein(), createMealBody.getFat());
        return mealService.createMeal(meal);
    }

    /**
     * This method updates an existing meal by id
     * 
     * @param id String id of the meal to be updated
     * @param meal Meal object containing updated information
     * @return Meal updated meal object
     */
    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable String id, @RequestBody Meal meal) {
        return mealService.updateMeal(id, meal);
    }

    /**
     * This method deletes a meal by id
     * 
     * @param id String id of the meal to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable String id) {
        mealService.deleteMeal(id);
    }
}