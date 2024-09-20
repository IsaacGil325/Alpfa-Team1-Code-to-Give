package models;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "job_postings")
public class JobPosting {
    private String id;  // Corresponds to _id in MongoDB
    private String title;  // Job title
    private String description;  // Job details
    private List<String> requiredSkills;  // List of required skills
    private String location;  // Job location
    private String jobType;  // Job type (e.g., full-time, part-time, internship)
    private String sponsorId;  // Employee posting the job
    private Date postedDate;  // Date when the job was posted
    private Date expiryDate;  // Optional: Expiration date for the job post
    private SalaryRange salaryRange;  // Min and max salary range
    private String experienceLevel;  // Experience level (e.g., entry-level, mid-level, senior)
    private String status;  // Job status (e.g., open, closed)
    private List<String> matchedCandidates;  // List of matched candidate IDs
    private Date createdAt;  // Creation date of the job post
    private Date updatedAt;  // Last update date of the job post

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public SalaryRange getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(SalaryRange salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMatchedCandidates() {
        return matchedCandidates;
    }

    public void setMatchedCandidates(List<String> matchedCandidates) {
        this.matchedCandidates = matchedCandidates;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Inner class for salary range
    public static class SalaryRange {
        private double min;
        private double max;

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }
    }
}
