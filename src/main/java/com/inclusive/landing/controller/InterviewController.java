package com.inclusive.landing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inclusive.landing.dto.InterviewDTO;
import com.inclusive.landing.service.IInterviewService;

@RestController
@RequestMapping("/api/inclusive/interviews")
public class InterviewController {

    private final IInterviewService interviewService;

    public InterviewController(IInterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping(produces = "application/json")
    public List<InterviewDTO> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

}
