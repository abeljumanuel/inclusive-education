package com.inclusive.landing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inclusive.landing.dto.FindingDTO;
import com.inclusive.landing.model.Finding;
import com.inclusive.landing.repository.FindingRepository;
import com.inclusive.landing.repository.ModalFindingRepository;

@Service
public class FindingService implements IFindingService {

    private final FindingRepository findingRepository;

    public FindingService(
        FindingRepository findingRepository, 
        ModalFindingRepository modalFindingRepository
    ) {
        this.findingRepository = findingRepository;
    }

    @Override
    public List<FindingDTO> getAllFindings() {
        List<Finding> findings = findingRepository.findAll();
        return findings.stream()
                .map(FindingDTO::new)
                .toList();
    }
    

}
