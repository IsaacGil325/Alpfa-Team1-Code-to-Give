package com.example.alpfateam1backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.alpfateam1backend.service.SponsorService;

@Controller
@RequestMapping("/sponsor")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Add sponsor-specific logic, e.g., fetching sponsors from the service
        return "sponsor/dashboard"; // Return the dashboard view for sponsors
    }
}
