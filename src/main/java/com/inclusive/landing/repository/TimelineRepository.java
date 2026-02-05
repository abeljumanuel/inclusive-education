package com.inclusive.landing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inclusive.landing.model.TimeLine;

public interface TimelineRepository extends JpaRepository<TimeLine, Long> {
    
    List<TimeLine> findBySchoolIdOrderByYearAsc(Long schoolId);
}
    