package com.inclusive.landing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inclusive.landing.dto.AppendixDTO;
import com.inclusive.landing.service.IAppendixService;

@RestController
@RequestMapping("/api/inclusive/appendices")
public class AppendixController {

    private final IAppendixService appendixService;

    public AppendixController(IAppendixService appendixService) {
        this.appendixService = appendixService;
    }

    @GetMapping(produces = "application/json")
    public List<AppendixDTO> getAllAppendices() {
        return appendixService.getAllAppendices();
    }

}
