package com.example.Project.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Map;

@Getter
@Setter
public class StatisticsDTO {

    /**
     * Data Transfer Object that will fetch the correct statistics
     */
    private long totalGames;
    private Map<String, Object> mostFrequentGoal;
    private Map<String, Object> leastFrequentGoal;
    private Map<String, Object> highestScoredGoal;
    private Map<String, Object> lowestScoredGoal;

}
