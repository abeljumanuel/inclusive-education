package com.inclusive.landing.service;

import com.inclusive.landing.dto.TimelineDTO;

public interface ITimelineService {

    TimelineDTO findBySchoolId(Long schoolId);

}
