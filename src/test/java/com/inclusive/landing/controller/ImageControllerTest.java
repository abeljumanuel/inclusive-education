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

import com.inclusive.landing.dto.ImageDTO;
import com.inclusive.landing.error.ApiExceptionHandler;
import com.inclusive.landing.model.Image;
import com.inclusive.landing.service.IImageService;

@WebMvcTest(ImageController.class)
@Import(ApiExceptionHandler.class)
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IImageService imageService;

    @Test
    void should_return_200_and_images_array() throws Exception {
        var image = new Image();
        image.setUrlImage("1.jpg");
        image.setDescription("An image description");

        when(imageService.getAllImages()).thenReturn(List.of(new ImageDTO(image)));

        mockMvc.perform(get("/api/inclusive/image"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].urlImage").value("1.jpg"))
                .andExpect(jsonPath("$[0].description").value("An image description"));
    }
}

