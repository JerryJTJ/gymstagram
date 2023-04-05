/**

This class represents the Model for Workout entity
*/
package com.example.mdbspringboot.model;

public class CreateWorkoutBody {

	private String name;
	private int reps;
	private int sets;
	private int weight;
	private int duration;
	
	public CreateWorkoutBody(String name, int reps, int sets, int weight, int duration) {
		super();
		this.name = name;
		this.reps = reps;
		this.sets = sets;
		this.weight = weight;
		this.duration = duration;
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