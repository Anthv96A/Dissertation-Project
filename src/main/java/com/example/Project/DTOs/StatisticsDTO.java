package com.example.Project.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class StatisticsDTO {

    /**
     * Data Transfer Object that will fetch the correct statistics
     */
    private long totalGames;
    private Map<String, Object> mostFrequentGoal;
    private Map<String, Object> leastFrequentGoal;
    private Map<String, Object> highestScoredGoal;
    private Map<String, Object> lowestScoredGoal;
    private Map<String, Object> goalsAndGameCount;

}
