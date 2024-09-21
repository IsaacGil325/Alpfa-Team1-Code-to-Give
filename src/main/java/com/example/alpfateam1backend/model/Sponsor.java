package com.example.alpfateam1backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection = "sponsors")
public class Sponsor extends User {
    @Id
    private String id; // MongoDB uses String for ObjectId

    private String companyName;
    private String industry;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private List<String> jobOpenings;

    public Sponsor(String username, String password, String email, Role role, 
                   String companyName, String industry, 
                   String contactPerson, String contactEmail, String contactPhone, List<String> jobOpenings) {
        super(username, password, email, role);
        this.companyName = companyName;
        this.industry = industry;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.jobOpenings = jobOpenings;
    }

    // Add methods to manage job openings if needed
}
