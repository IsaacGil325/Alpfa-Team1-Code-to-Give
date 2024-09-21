package com.example.alpfateam1backend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users") // Specify the collection name
public class User {
    public User(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    @Id
    private String id; // Change Long to String for MongoDB ObjectId

    private String username;
    private String password;
    private String email;
    private String role;
}
