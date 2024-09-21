package com.example.alpfateam1backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users") // Collection name
public class User {
    public enum Role {
        ADMIN,
        SPONSOR,
        USER
    };

    @Id
    private String id; // Corresponds to _id in MongoDB
    private String username;
    private String password;
    private String email;
    private Role role;
    private Date createdAt;
    private Date updatedAt;


    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
