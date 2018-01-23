package com.example.Project.controllers;


import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.domain.Game;
import com.example.Project.domain.Goal;
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

//    @GetMapping("/all")
//    public List<Goal> getAllGoals(){
//        List<Goal> goalList = new ArrayList<>();
//
//        Iterator<Goal> ig = goalRepository.findAll().iterator();
//
//        while (ig.hasNext()){
//            goalList.add(ig.next());
//        }
//
//        return goalList;
//    }

    @GetMapping("/{name}")
    public Goal getGoal(@PathVariable String name){

        return goalRepository.findByName(name).get();
    }

//    @GetMapping("/count/{name}")
//    public int getGameCount(@PathVariable String name){
//
//        Optional<Goal> goal = goalRepository.findByName(name);
//
//        if(!goal.isPresent()){
//            throw new RuntimeException("Not found");
//        }
//
//        List<Game> gameList = goal.get().getGames();
//
//
//        return gameList.size();
//    }

//    @GetMapping("/count/total")
//    public long getTotalGameCount(){
//        return statisticsService.totalGames();
//    }

    @GetMapping("/all")
    public StatisticsDTO getAllStats(){
        return statisticsService.getStatistics();
    }





}
