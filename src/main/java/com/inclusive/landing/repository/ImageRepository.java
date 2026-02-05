package com.inclusive.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inclusive.landing.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
