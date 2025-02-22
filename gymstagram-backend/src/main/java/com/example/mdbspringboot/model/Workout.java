/**

This class represents the Model for Workout entity
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

@Document("Workout")
public class Workout {

	@Id
	private String id;

	private Date creationDate;

	private String name;
	private int reps;
	private int sets;
	private int weight;
	private int duration;
	
	public Workout(String name, int reps, int sets, int weight, int duration) {
		super();
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		Date currentDate = calendar.getTime();

        this.id = UUID.randomUUID().toString();
		this.creationDate = currentDate;
		this.name = name;
		this.reps = reps;
		this.sets = sets;
		this.weight = weight;
		this.duration = duration;
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

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public int getSets() {
		return sets;
	}
	
	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}