package com.example.Project.services;

import com.example.Project.DTOs.StatisticsDTO;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public interface StatisticsService {

    StatisticsDTO getStatistics();
    StatisticsDTO getStatisticsWithinTimePeriod(String from, String to);

    long totalGames();
    Map<String, Object> mostFrequentGoal();
    Map<String, Object> leastFrequentGoal();
    Map<String, Object> highestScoredGoal();
    Map<String, Object> lowestScoredGoal();
    Map<String, Object> goalsAndGameCount();

    long totalGamesInPeriod(Date from, Date to);
    Map<String, Object> mostFrequentGoalForPeriod(Date from, Date to);
    Map<String, Object> leastFrequentGoalForPeriod(Date from, Date to);
    Map<String, Object> highestScoredGoalForPeriod(Date from, Date to);
    Map<String, Object> lowestScoredGoalForPeriod(Date from, Date to);
    Map<String, Object> goalsAndGameCountForPeriod(Date from, Date to);
}
