package com.inclusive.landing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.inclusive.landing.dto.TimelineDTO;
import com.inclusive.landing.error.ApiExceptionHandler;
import com.inclusive.landing.error.ResourceNotFoundException;
import com.inclusive.landing.service.ITimelineService;

@WebMvcTest(TimelineController.class)
@Import(ApiExceptionHandler.class)
class TimelineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITimelineService timelineService;

    @Test
    void should_return_400_when_school_id_is_not_a_number() throws Exception {
        mockMvc.perform(get("/api/inclusive/not-a-number/timeline"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid school ID format"))
                .andExpect(jsonPath("$.error").value("School ID must be a valid number"));
    }

    @Test
    void should_return_404_when_school_is_not_found() throws Exception {
        when(timelineService.findBySchoolId(999L))
                .thenThrow(new ResourceNotFoundException("School not found"));

        mockMvc.perform(get("/api/inclusive/999/timeline"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("School not found"))
                .andExpect(jsonPath("$.error").value("School not found"));
    }

    @Test
    void should_return_200_and_timeline_dto_when_school_exists() throws Exception {
        var timeline = org.mockito.Mockito.mock(com.inclusive.landing.model.TimeLine.class);
        when(timeline.getYear()).thenReturn(2020);
        when(timeline.getTitle()).thenReturn("Title");
        when(timeline.getDescription()).thenReturn("Description");

        var dto = new TimelineDTO(
                "Full School Name",
                "City",
                "Department",
                java.util.List.of(new TimelineDTO.TimelineEntry(timeline)));

        when(timelineService.findBySchoolId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/inclusive/1/timeline"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolFullName").value("Full School Name"))
                .andExpect(jsonPath("$.timelineEntries[0].year").value(2020))
                .andExpect(jsonPath("$.timelineEntries[0].title").value("Title"));
    }
}

