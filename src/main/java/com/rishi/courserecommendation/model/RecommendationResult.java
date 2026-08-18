package com.rishi.courserecommendation.model;

public class RecommendationResult {

    private Course course;
    private int score;
    private String reason;

    public RecommendationResult() {
    }

    public RecommendationResult(Course course, int score, String reason) {
        this.course = course;
        this.score = score;
        this.reason = reason;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}