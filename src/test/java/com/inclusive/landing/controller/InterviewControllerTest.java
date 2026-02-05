package com.inclusive.landing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.inclusive.landing.dto.InterviewDTO;
import com.inclusive.landing.error.ApiExceptionHandler;
import com.inclusive.landing.model.Interview;
import com.inclusive.landing.model.School;
import com.inclusive.landing.service.IInterviewService;

@WebMvcTest(InterviewController.class)
@Import(ApiExceptionHandler.class)
class InterviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IInterviewService interviewService;

    @Test
    void should_return_200_and_interviews_array() throws Exception {
        var school = new School();
        school.setShortName("School short");

        var interview = new Interview();
        interview.setIntervieweeName("Teacher Name");
        interview.setSchool(school);

        when(interviewService.getAllInterviews()).thenReturn(List.of(new InterviewDTO(interview)));

        mockMvc.perform(get("/api/inclusive/interviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].intervieweeName").value("Teacher Name"))
                .andExpect(jsonPath("$[0].schoolName").value("School short"));
    }

    @Test
    void should_allow_null_school_in_interview_dto() throws Exception {
        var interview = new Interview();
        interview.setIntervieweeName("Teacher Name");
        interview.setSchool(null);

        when(interviewService.getAllInterviews()).thenReturn(List.of(new InterviewDTO(interview)));

        mockMvc.perform(get("/api/inclusive/interviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].intervieweeName").value("Teacher Name"))
                .andExpect(jsonPath("$[0].schoolName").value(Matchers.nullValue()));
    }
}

