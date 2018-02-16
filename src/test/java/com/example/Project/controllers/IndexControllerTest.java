package com.example.Project.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IndexControllerTest {

    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        // Isolating the Index control to mock Model View Controller
        MockitoAnnotations.initMocks(this);
        this.indexController = new IndexController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void index() throws Exception {
        // Given
        String expected = "Sports Performance";

        // When
        String actual = indexController.index();

        // Then
        this.mockMvc.perform(get("/")).andExpect(status().isOk());
        assertEquals(actual,expected);
    }

}