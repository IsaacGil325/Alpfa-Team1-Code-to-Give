package com.example.alpfateam1backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.alpfateam1backend.model.Sponsor;

public interface SponsorRepository extends MongoRepository<Sponsor, Long> {
    // You can add custom query methods here if needed
}