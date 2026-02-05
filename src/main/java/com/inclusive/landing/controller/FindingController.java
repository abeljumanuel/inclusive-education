package com.inclusive.landing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inclusive.landing.dto.FindingDTO;
import com.inclusive.landing.service.FindingService;

@RestController
@RequestMapping("/api/inclusive/finding")
public class FindingController {
    

    private final FindingService findingService;

    public FindingController(FindingService findingService) {
        this.findingService = findingService;
    }

    @GetMapping(produces = "application/json")
    public List<FindingDTO> getAllFindings() {
        return findingService.getAllFindings();
    }

}