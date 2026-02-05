package com.inclusive.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inclusive.landing.model.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}
