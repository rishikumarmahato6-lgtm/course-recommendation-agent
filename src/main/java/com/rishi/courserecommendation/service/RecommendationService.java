package com.rishi.courserecommendation.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishi.courserecommendation.model.Course;
import com.rishi.courserecommendation.model.Recommendation;
import com.rishi.courserecommendation.model.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {
    
    @Autowired
    private GroqService groqService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Course> courseCatalog;

    public RecommendationService() {
        courseCatalog = new ArrayList<>();
        
        courseCatalog.add(new Course(1L, "Python Fundamentals", "Programming", "Beginner",
                "Learn Python programming from scratch", "Python, Programming", "None"));
        
        courseCatalog.add(new Course(2L, "Data Science with Python", "Data Science", "Intermediate",
                "Data analysis with Pandas and NumPy", "Pandas, NumPy", "Python Fundamentals"));
        
        courseCatalog.add(new Course(3L, "Machine Learning Basics", "AI", "Intermediate",
                "Introduction to ML algorithms", "Scikit-learn", "Data Science with Python"));
        
        courseCatalog.add(new Course(4L, "Deep Learning", "AI", "Advanced",
                "Neural networks and deep learning", "TensorFlow, Keras", "Machine Learning Basics"));
        
        courseCatalog.add(new Course(5L, "Web Development", "Web", "Intermediate",
                "Full stack web development", "React, Spring Boot", "Python Fundamentals"));
        
        courseCatalog.add(new Course(6L, "Cloud Computing", "DevOps", "Intermediate",
                "AWS and cloud fundamentals", "AWS, Cloud", "Python Fundamentals"));
        
        courseCatalog.add(new Course(7L, "DevOps Engineering", "DevOps", "Advanced",
                "CI/CD, Docker, Kubernetes", "Docker, Jenkins", "Cloud Computing"));
        
        courseCatalog.add(new Course(8L, "Data Engineering", "Data", "Advanced",
                "Big data and ETL pipelines", "Spark, Hadoop", "Data Science with Python"));
        
        courseCatalog.add(new Course(9L, "Frontend Development", "Web", "Beginner",
                "HTML, CSS, JavaScript", "HTML, CSS, JavaScript", "None"));
        
        courseCatalog.add(new Course(10L, "Mobile Development", "Mobile", "Intermediate",
                "Flutter mobile app development", "Flutter, Dart", "Python Fundamentals"));
    }

    public List<Recommendation> getRecommendations(StudentProfile profile) {
        List<Recommendation> recommendations = new ArrayList<>();
        
        try {
            // Get AI recommendations from Groq
            String aiResponse = groqService.getRecommendations(profile, courseCatalog);
            
            // Parse JSON response
            JsonNode array = objectMapper.readTree(aiResponse);
            
            for (JsonNode node : array) {
                Long courseId = node.path("courseId").asLong();
                int order = node.path("order").asInt();
                String rationale = node.path("rationale").asText();
                
                // Find the course by ID
                Course course = courseCatalog.stream()
                    .filter(c -> c.getId().equals(courseId))
                    .findFirst()
                    .orElse(null);
                
                if (course != null) {
                    recommendations.add(new Recommendation(course, order, rationale));
                }
            }
            
            // If AI fails or returns empty, use fallback
            if (recommendations.isEmpty()) {
                recommendations = getFallbackRecommendations(profile);
            }
            
        } catch (Exception e) {
            // If AI fails, use fallback
            recommendations = getFallbackRecommendations(profile);
        }
        
        return recommendations;
    }

    private List<Recommendation> getFallbackRecommendations(StudentProfile profile) {
        List<Recommendation> recommendations = new ArrayList<>();
        String goals = profile.getGoals().toLowerCase();
        
        if (goals.contains("data") || goals.contains("ai") || goals.contains("machine")) {
            recommendations.add(new Recommendation(courseCatalog.get(0), 1, 
                    "Python is the foundation for all data science work"));
            recommendations.add(new Recommendation(courseCatalog.get(1), 2, 
                    "Data Science with Python teaches essential analysis skills"));
            recommendations.add(new Recommendation(courseCatalog.get(2), 3, 
                    "Machine Learning Basics introduces AI concepts"));
        } else if (goals.contains("web")) {
            recommendations.add(new Recommendation(courseCatalog.get(0), 1, 
                    "Python is essential for backend development"));
            recommendations.add(new Recommendation(courseCatalog.get(8), 2, 
                    "Frontend Development teaches user interface skills"));
            recommendations.add(new Recommendation(courseCatalog.get(4), 3, 
                    "Web Development combines everything into full stack"));
        } else {
            recommendations.add(new Recommendation(courseCatalog.get(0), 1, 
                    "Python is a versatile starting point for any career"));
            recommendations.add(new Recommendation(courseCatalog.get(9), 2, 
                    "Mobile Development is in high demand"));
            recommendations.add(new Recommendation(courseCatalog.get(5), 3, 
                    "Cloud Computing skills are valuable"));
        }
        
        return recommendations;
    }

    public List<Course> getAllCourses() {
        return courseCatalog;
    }
}