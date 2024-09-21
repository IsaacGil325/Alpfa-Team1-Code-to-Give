package com.example.alpfateam1backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.alpfateam1backend.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findByUsername(String username);
}
