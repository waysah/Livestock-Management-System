package com.example.livestockmanagement;

import java.io.Serializable;

public class Cow implements Serializable {
    private String name;
    private String breed;
    private String tagNumber;
    private Gender gender;
    private int age;
    private String purpose;
    private double weight;



    public void setMilkProductionPerDay(double milkProductionPerDay) {
        this.milkProductionPerDay = milkProductionPerDay;
    }

    private double milkProductionPerDay;
    public static HealthStatus healthStatus;




    //Constructor
    public Cow(String breed, String tagNumber, Gender gender,
               HealthStatus healthStatus, String name, int age, String purpose, double weight) {
        this.name = name;
        this.breed = breed;
        this.tagNumber = tagNumber;
        this.gender = gender;
        this.age = age;
        this.purpose = purpose;
        this.weight = weight;
        Cow.healthStatus = healthStatus;
    }

    //Getters and setters
    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getName() { return name;    }

    public void setName(String name) {  this.name = name;  }

    public int getAge() {   return age;  }

    public void setAge(int age) {   this.age = age;  }

    public String getPurpose() { return purpose; }

    public void setPurpose(String purpose) { this.purpose = purpose; }

    public double getWeight() {  return weight;  }

    public void setWeight(double weight) {  this.weight = weight; }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        Cow.healthStatus = healthStatus;
    }

    public void updateHealthStatus(HealthStatus newHealthStatus) {
        healthStatus = newHealthStatus;

        CowDAO.updateCowHealthStatus(this.tagNumber, newHealthStatus);

    }
    public double getMilkProductionPerDay() {return milkProductionPerDay;  }


    public enum HealthStatus {
        SICK,
        HEALTHY

    }
}

