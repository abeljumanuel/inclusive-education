package com.inclusive.landing.service;

import org.springframework.stereotype.Service;

import com.inclusive.landing.dto.SchoolDTO;
import com.inclusive.landing.error.ResourceNotFoundException;
import com.inclusive.landing.repository.SchoolRepository;

@Service
public class SchoolService implements ISchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @SuppressWarnings("null")
    @Override
    public SchoolDTO getSchoolById(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .map(SchoolDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("School not found"));
    }
}
