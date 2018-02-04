package com.example.Project.controllers;


import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.domain.Game;
import com.example.Project.domain.Goal;
import com.example.Project.repositories.GameRepository;
import com.example.Project.repositories.GoalRepository;
import com.example.Project.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/stats")
@CrossOrigin({"http://localhost:8100","file://"})
public class StatisticsController {

    private final GoalRepository goalRepository;
    private final StatisticsService statisticsService;



    @GetMapping("/{name}")
    public Goal getGoal(@PathVariable String name){
        return goalRepository.findByName(name).get();
    }


    @GetMapping("/all")
    public StatisticsDTO getAllStats(){
        StatisticsDTO dto = statisticsService.getStatistics();
     //   System.out.println(dto.toString());
        return dto;
    }





}
