package models;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "userprofiles")
public class UserProfile {
    private String id;  // Corresponds to _id in MongoDB
    private String userId;  // References the _id of the applicant (User)
    private String fullName;  // Full name of the applicant
    private String email;  // Applicant's email
    private String phone;  // Applicant's phone number
    private List<String> skills;  // List of skills
    private List<Experience> experience;  // List of job experiences
    private List<Education> education;  // List of educational qualifications
    private List<String> certifications;  // List of certifications
    private String humanAspects;  // Human aspects like leadership, communication skills
    private String resumeUrl;  // URL of the uploaded resume
    private String preferredLocation;  // Preferred job location
    private String preferredJobType;  // Preferred job type (e.g., full-time, internship)
    private Date createdAt;  // Profile creation date
    private Date updatedAt;  // Profile last updated date

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public String getHumanAspects() {
        return humanAspects;
    }

    public void setHumanAspects(String humanAspects) {
        this.humanAspects = humanAspects;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPreferredJobType() {
        return preferredJobType;
    }

    public void setPreferredJobType(String preferredJobType) {
        this.preferredJobType = preferredJobType;
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

    // Inner class for Experience
    public static class Experience {
        private String jobTitle;  // Job title
        private String company;  // Company name
        private String duration;  // Duration (e.g., "June 2021 - Present")
        private String description;  // Job description

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    // Inner class for Education
    public static class Education {
        private String degree;  // Degree (e.g., "B.Sc. in Computer Science")
        private String institution;  // Educational institution
        private String graduationYear;  // Graduation year

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getInstitution() {
            return institution;
        }

        public void setInstitution(String institution) {
            this.institution = institution;
        }

        public String getGraduationYear() {
            return graduationYear;
        }

        public void setGraduationYear(String graduationYear) {
            this.graduationYear = graduationYear;
        }
    }
}