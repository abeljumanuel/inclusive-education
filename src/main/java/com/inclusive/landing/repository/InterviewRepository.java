package com.inclusive.landing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inclusive.landing.model.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

}
