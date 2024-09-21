package com.example.alpfateam1backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alpfateam1backend.repository.SponsorRepository;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    // Add sponsor-specific methods here
    // Example method: public Sponsor getSponsorById(String id) { return sponsorRepository.findById(id).orElse(null); }
}
