package com.rishi.courserecommendation.model;

public class StudentProfile {
    private String name;
    private String background;
    private String goals;
    private String skills;

    // Default constructor (required for JSON deserialization)
    public StudentProfile() {
    }

    // Constructor with all fields
    public StudentProfile(String name, String background, String goals, String skills) {
        this.name = name;
        this.background = background;
        this.goals = goals;
        this.skills = skills;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}