package com.rishi.courserecommendation.controller;

import com.rishi.courserecommendation.model.Recommendation;
import com.rishi.courserecommendation.model.StudentProfile;
import com.rishi.courserecommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class CourseRecommendationController {
    
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping
    public List<Recommendation> getRecommendations(@RequestBody StudentProfile profile) {
        return recommendationService.getRecommendations(profile);
    }

    @GetMapping("/courses")
    public List<com.rishi.courserecommendation.model.Course> getAllCourses() {
        return recommendationService.getAllCourses();
    }
}