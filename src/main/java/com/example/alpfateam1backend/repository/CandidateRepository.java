package com.example.alpfateam1backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.alpfateam1backend.model.Candidate;

public interface CandidateRepository extends MongoRepository<Candidate, String> {
    // You can add custom query methods here if needed
}
