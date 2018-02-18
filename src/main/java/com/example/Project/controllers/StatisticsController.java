package com.example.Project.controllers;

import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.services.StatisticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@CrossOrigin({"http://localhost:8100","file://"})
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/all")
    public StatisticsDTO getAllStats(){
        return statisticsService.getStatistics();
    }


    @GetMapping("/week/{from}/{to}")
    public StatisticsDTO getAllStatsForPeriod(@PathVariable("from") String from, @PathVariable("to") String to){
        return statisticsService.getStatisticsWithinTimePeriod(from,to);
    }


}
