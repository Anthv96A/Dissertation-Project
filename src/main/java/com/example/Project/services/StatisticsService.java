package com.example.Project.services;

import com.example.Project.DTOs.StatisticsDTO;

import java.math.BigInteger;
import java.util.Map;

public interface StatisticsService {

    StatisticsDTO getStatistics();

    long totalGames();

    Map<String, Object> mostFrequentGoal();

    Map<String, Object> leastFrequentGoal();

    Map<String, Object> highestScoredGoal();

    Map<String, Object> lowestScoredGoal();



}
