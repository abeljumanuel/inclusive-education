package com.inclusive.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inclusive.landing.model.Finding;

public interface FindingRepository extends JpaRepository<Finding, Long> {

}
