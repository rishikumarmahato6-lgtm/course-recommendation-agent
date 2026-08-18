package com.rishi.courserecommendation.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rishi.courserecommendation.model.Course;
import com.rishi.courserecommendation.model.StudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GroqService {
    
    @Autowired
    private WebClient groqWebClient;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public String getRecommendations(StudentProfile profile, List<Course> courses) {
        String prompt = buildPrompt(profile, courses);
        
        String requestBody = """
            {
                "model": "mixtral-8x7b-32768",
                "messages": [
                    {"role": "system", "content": "You are a course advisor. Recommend 3-5 courses from the list. For each course, explain exactly why it's recommended based on student's background, goals, and skills. Return ONLY JSON format: [{\"courseId\":1,\"order\":1,\"rationale\":\"explanation\"}]"},
                    {"role": "user", "content": "%s"}
                ],
                "temperature": 0.7,
                "max_tokens": 600
            }
            """.formatted(prompt.replace("\"", "\\\"").replace("\n", " "));
        
        try {
            String response = groqWebClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            
            return extractResponse(response);
        } catch (Exception e) {
            return "[]";
        }
    }
    
    private String buildPrompt(StudentProfile profile, List<Course> courses) {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Profile:\n");
        sb.append("- Name: ").append(profile.getName()).append("\n");
        sb.append("- Background: ").append(profile.getBackground()).append("\n");
        sb.append("- Goals: ").append(profile.getGoals()).append("\n");
        sb.append("- Skills: ").append(profile.getSkills()).append("\n\n");
        
        sb.append("Available Courses (ID, Name, Level, Prerequisites):\n");
        for (Course course : courses) {
            sb.append("ID:").append(course.getId())
              .append(", Name:").append(course.getName())
              .append(", Level:").append(course.getLevel())
              .append(", Prerequisites:").append(course.getPrerequisites())
              .append("\n");
        }
        
        return sb.toString();
    }
    
    private String extractResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            String content = root.path("choices").get(0).path("message").path("content").asText();
            
            // Try to extract JSON from the response
            int start = content.indexOf('[');
            int end = content.lastIndexOf(']');
            if (start != -1 && end != -1) {
                return content.substring(start, end + 1);
            }
            return content;
        } catch (Exception e) {
            return "[]";
        }
    }
}