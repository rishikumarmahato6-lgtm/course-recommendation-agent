package com.rishi.courserecommendation.model;

import java.util.List;

public class UserRequest {

    private String goal;
    private String level;
    private List<String> skills;

    public UserRequest() {
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}