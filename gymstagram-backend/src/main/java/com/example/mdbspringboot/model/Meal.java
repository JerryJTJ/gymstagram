/**

This class represents the Model for Meal entity
*/
package com.example.mdbspringboot.model;

import java.util.Date;
import java.util.UUID;
import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document("Meal")
public class Meal {

	@Id
	private String id;

	private Date creationDate;

	private String name;
	private int calories;
	private int carbs;
	private int protein;
	private int fat;
	
	public Meal(String name, int calories, int carbs, int protein, int fat) {
		super();
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = calendar.getTime();

        this.id = UUID.randomUUID().toString();
		this.creationDate = currentDate;
		this.name = name;
		this.calories = calories;
		this.carbs = carbs;
		this.protein = protein;
		this.fat = fat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
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