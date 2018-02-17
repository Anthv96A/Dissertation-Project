package com.example.Project.controllers;


import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/stats")
@CrossOrigin({"http://localhost:8100","file://"})
public class StatisticsController {


    private final StatisticsService statisticsService;



    @GetMapping("/all")
    public StatisticsDTO getAllStats(){
        return statisticsService.getStatistics();
    }


    @GetMapping("/week/{from}/{to}")
    public StatisticsDTO getAllStatsForWeek(@PathVariable("from") String from, @PathVariable("to") String to){
        System.out.println("HIT");
        return statisticsService.getStatisticsWithinTimePeriod(from,to);
    }








}
