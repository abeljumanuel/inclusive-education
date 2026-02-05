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

import com.inclusive.landing.dto.AppendixDTO;
import com.inclusive.landing.error.ApiExceptionHandler;
import com.inclusive.landing.model.Appendix;
import com.inclusive.landing.service.IAppendixService;

@WebMvcTest(AppendixController.class)
@Import(ApiExceptionHandler.class)
class AppendixControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAppendixService appendixService;

    @Test
    void should_return_200_and_appendices_array() throws Exception {
        var appendix = new Appendix();
        appendix.setTitle("Appendix title");
        appendix.setFunctionUrl("GALLERY");

        when(appendixService.getAllAppendices()).thenReturn(List.of(new AppendixDTO(appendix)));

        mockMvc.perform(get("/api/inclusive/appendices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Appendix title"))
                .andExpect(jsonPath("$[0].functionUrl").value("GALLERY"));
    }
}

