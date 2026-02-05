package com.inclusive.landing.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inclusive.landing.dto.SchoolDTO;
import com.inclusive.landing.dto.TimelineDTO;
import com.inclusive.landing.model.TimeLine;
import com.inclusive.landing.repository.TimelineRepository;

@Service
public class TimelineService implements ITimelineService {

    private final TimelineRepository timelineRepository;
    private final ISchoolService schoolService;

    public TimelineService(
        TimelineRepository timelineRepository, 
        ISchoolService schoolService
    ) {
        this.timelineRepository = timelineRepository;
        this.schoolService = schoolService;
    }

    @Override
    @Transactional(readOnly = true)
    public TimelineDTO findBySchoolId(Long schoolId) {

        SchoolDTO schoolDTO = schoolService.getSchoolById(schoolId);
        List<TimeLine> timelines = getTimelinesBySchoolId(schoolId);
        var timelineEntries = timelines.stream()
                .map(TimelineDTO.TimelineEntry::new)
                .toList();

        return new TimelineDTO(
            schoolDTO.getFullName(),
            schoolDTO.getCity(),
            schoolDTO.getDepartment(),
            timelineEntries
        );
    }

    @Transactional(readOnly = true)
    public List<TimeLine> getTimelinesBySchoolId(Long schoolId) {
        return timelineRepository.findBySchoolIdOrderByYearAsc(schoolId);
    }

}
