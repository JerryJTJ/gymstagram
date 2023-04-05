package com.example.gymstagram.model;

import java.util.Date;

public class Workout {
    private String name;
    private int reps;
    private int sets;
    private int weight;
    private int duration;
    private Date creationDate;

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


    public Date getCreationDate() {
        return creationDate;
    }


        public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
