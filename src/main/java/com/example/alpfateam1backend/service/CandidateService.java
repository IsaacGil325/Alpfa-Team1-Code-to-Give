package com.example.alpfateam1backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alpfateam1backend.repository.CandidateRepository;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // Add candidate-specific methods here
    // Example method: public Candidate getCandidateById(String id) { return candidateRepository.findById(id).orElse(null); }
}
