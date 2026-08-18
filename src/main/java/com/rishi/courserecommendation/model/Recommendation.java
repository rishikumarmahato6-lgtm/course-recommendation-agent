package com.rishi.courserecommendation.model;

public class Recommendation {
    private Course course;
    private int order;
    private String rationale;

    public Recommendation() {
    }

    public Recommendation(Course course, int order, String rationale) {
        this.course = course;
        this.order = order;
        this.rationale = rationale;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }
}