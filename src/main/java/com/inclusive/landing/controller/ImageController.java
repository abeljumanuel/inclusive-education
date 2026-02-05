package com.inclusive.landing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inclusive.landing.dto.ImageDTO;
import com.inclusive.landing.service.IImageService;

@RestController
@RequestMapping("/api/inclusive/image")
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(produces = "application/json")
    public List<ImageDTO> getAllImages() {
        return imageService.getAllImages();
    }
}