/**

This class represents the Model for Meal entity
*/
package com.example.mdbspringboot.model;

public class CreateMealBody {

	private String name;
	private int calories;
	private int carbs;
	private int protein;
	private int fat;
	
	public CreateMealBody(String name, int calories, int carbs, int protein, int fat) {
		super();
		this.name = name;
		this.calories = calories;
		this.carbs = carbs;
		this.protein = protein;
		this.fat = fat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getCarbs() {
		return carbs;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public int getProtein() {
		return protein;
	}
	
	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}
}