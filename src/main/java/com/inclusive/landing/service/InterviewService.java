package com.inclusive.landing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inclusive.landing.dto.InterviewDTO;
import com.inclusive.landing.model.Interview;
import com.inclusive.landing.repository.InterviewRepository;

@Service
public class InterviewService implements IInterviewService {
    
    private final InterviewRepository interviewRepository;

    public InterviewService(
        InterviewRepository interviewRepository
    ) {
        this.interviewRepository = interviewRepository;
    }

    @Override
    public List<InterviewDTO> getAllInterviews() {
        List<Interview> interviews = interviewRepository.findAll();
        return interviews.stream()
                .map(InterviewDTO::new)
                .toList();
    }

}
