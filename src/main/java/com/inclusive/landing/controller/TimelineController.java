package com.inclusive.landing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inclusive.landing.dto.TimelineDTO;
import com.inclusive.landing.service.ITimelineService;

@RestController
@RequestMapping("/api/inclusive/{schoolId}/timeline")
public class TimelineController {

    private final ITimelineService timelineService;

    public TimelineController(ITimelineService timelineService) {
        this.timelineService = timelineService;
    }

    @GetMapping(produces = "application/json")
    public TimelineDTO getTimelineDTO(@PathVariable Long schoolId) {
        return timelineService.findBySchoolId(schoolId);
    }


}
