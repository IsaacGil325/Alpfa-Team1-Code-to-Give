package com.example.alpfateam1backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Document(collection = "sponsors")
public class Sponsor extends User {
    @Id
    private String id; // MongoDB uses String for ObjectId

    private String companyName;
    private String industry;

    public Sponsor(String id, String username, String password, String email, String role, String companyName, String industry) {
        super(username, password, email, role);
        this.id = id;
        this.companyName = companyName;
        this.industry = industry;
    }
}
