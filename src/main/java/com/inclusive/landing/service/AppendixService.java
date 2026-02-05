package com.inclusive.landing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inclusive.landing.dto.AppendixDTO;
import com.inclusive.landing.repository.AppendixRepository;

@Service
public class AppendixService implements IAppendixService {

    private final AppendixRepository appendixRepository;

    public AppendixService(AppendixRepository appendixRepository) {
        this.appendixRepository = appendixRepository;
    }

    @Override
    public List<AppendixDTO> getAllAppendices() {
        return appendixRepository.findAll()
                .stream()
                .map(AppendixDTO::new)
                .toList();
    }

}
