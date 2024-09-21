package models;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "sponsors")
public class SponsorProfile {

    private String id;
    private String userId;
    private String name;
    private String industry;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private List<String> jobOpenings; // List of job IDs posted by the sponsor

    // Constructor
    public SponsorProfile() {}

    public SponsorProfile(String name, String industry, String contactPerson, String contactEmail, String contactPhone, List<String> jobOpenings) {
        this.name = name;
        this.industry = industry;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.jobOpenings = jobOpenings;
    }

    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<String> getJobOpenings() {
        return jobOpenings;
    }

    public void setJobOpenings(List<String> jobOpenings) {
        this.jobOpenings = jobOpenings;
    }

    @Override
    public String toString() {
        return "Sponsor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", jobOpenings=" + jobOpenings +
                '}';
    }
}
