package com.example.Project.controllers;


import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.services.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatisticsControllerTest {

    private MockMvc mockMvc;

    private StatisticsController statisticsController;

    @Mock
    private StatisticsService statisticsService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        statisticsController = new StatisticsController(statisticsService);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    @Test
    public void testGetAllStats() throws Exception {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        when(statisticsController.getAllStats()).thenReturn(statisticsDTO);

        mockMvc.perform(get("/stats/all"))
                .andExpect(status().isOk());
        verify(statisticsService, times(1)).getStatistics();
    }
    @Test
    public void testGetAllStatsInPeriod() throws Exception {
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        String from = "2018-02-08";
        String to = "2018-02-15";

        when(statisticsController.getAllStatsForPeriod(from,to)).thenReturn(statisticsDTO);

        mockMvc.perform(get("/stats/week/2018-02-08/2018-02-15"))
                .andExpect(status().isOk());
        verify(statisticsService, times(1)).getStatisticsWithinTimePeriod(from,to);
    }


}