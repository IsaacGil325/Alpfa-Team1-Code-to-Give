package com.example.alpfateam1backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.alpfateam1backend.service.CandidateService;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Add candidate-specific logic, e.g., fetching candidates from the service
        return "candidate/dashboard"; // Return the dashboard view for candidates
    }
}
