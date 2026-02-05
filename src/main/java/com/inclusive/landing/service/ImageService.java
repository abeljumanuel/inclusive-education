package com.inclusive.landing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inclusive.landing.dto.ImageDTO;
import com.inclusive.landing.repository.ImageRepository;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(ImageDTO::new)
                .toList();
    }

}
