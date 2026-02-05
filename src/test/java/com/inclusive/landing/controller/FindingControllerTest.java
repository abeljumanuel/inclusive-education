package com.inclusive.landing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.inclusive.landing.dto.FindingDTO;
import com.inclusive.landing.error.ApiExceptionHandler;
import com.inclusive.landing.service.FindingService;

@WebMvcTest(FindingController.class)
@Import(ApiExceptionHandler.class)
class FindingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindingService findingService;

    @Test
    void should_return_200_and_findings_array() throws Exception {
        var dto = new FindingDTO();
        dto.setId(1L);
        dto.setTitle("A title");
        dto.setDescription("A description");

        when(findingService.getAllFindings()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/inclusive/finding"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("A title"));
    }
}

