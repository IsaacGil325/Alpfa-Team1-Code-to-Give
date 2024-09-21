package com.example.alpfateam1backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "candidates")
public class Candidate {

    @Id
    private String id;  // MongoDB ObjectId

    @DBRef
    private User user;  // MongoDB reference to the User entity

    private String fullName;
    private String email;
    private String phone;
    private List<String> skills = new ArrayList<>();

    private List<Experience> experience = new ArrayList<>();
    private List<Education> education = new ArrayList<>();
    private List<String> certifications = new ArrayList<>();

    private String humanAspects;
    private String resumeUrl;
    private String preferredLocation;
    private String preferredJobType;
    
    private Date createdAt;
    private Date updatedAt;

    // Constructor including all fields
    public Candidate(String id, User user, String fullName, String email, String phone, List<String> skills,
                     List<Experience> experience, List<Education> education, List<String> certifications,
                     String humanAspects, String resumeUrl, String preferredLocation, String preferredJobType,
                     Date createdAt, Date updatedAt) {
        this.id = id;
        this.user = user;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
        this.certifications = certifications;
        this.humanAspects = humanAspects;
        this.resumeUrl = resumeUrl;
        this.preferredLocation = preferredLocation;
        this.preferredJobType = preferredJobType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Nested classes for Experience and Education
    @Data
    public static class Experience {
        private String jobTitle;
        private String company;
        private String duration;
        private String description;

        public Experience(String jobTitle, String company, String duration, String description) {
            this.jobTitle = jobTitle;
            this.company = company;
            this.duration = duration;
            this.description = description;
        }

        public Experience() {
        }
    }

    @Data
    public static class Education {
        private String degree;
        private String institution;
        private String graduationYear;

        public Education(String degree, String institution, String graduationYear) {
            this.degree = degree;
            this.institution = institution;
            this.graduationYear = graduationYear;
        }

        public Education() {
        }
    }
}
