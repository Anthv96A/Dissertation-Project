package com.example.Project.services;

import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.repositories.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * By Anthony Vest
 */

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {

    /**
     * Game Repository to select all the necessary queries
     * Final to make the repository immutable.
     */

    private final GameRepository gameRepository;

    /**
     *
     * @param gameRepository
     *  Constuctor based dependency injection
     */

    public StatisticsServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     *
     * @return a statistics DTO Object, setting the values of all data using methods in class
     */

    @Override
    public StatisticsDTO getStatistics(){
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        statisticsDTO.setTotalGames(totalGames());
        statisticsDTO.setMostFrequentGoal(mostFrequentGoal());
        statisticsDTO.setLeastFrequentGoal(leastFrequentGoal());
        statisticsDTO.setHighestScoredGoal(highestScoredGoal());
        statisticsDTO.setLowestScoredGoal(lowestScoredGoal());
        statisticsDTO.setGoalsAndGameCount(goalsAndGameCount());
        return statisticsDTO;
    }

    /**
     *
     *
     * @param from
     * @param to
     * @return Time period data
     */
    @Override
    public StatisticsDTO getStatisticsWithinTimePeriod(String from, String to) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        try {
            Date dateFrom = formatter.parse(from);
            Date dateTo = formatter.parse(to);

            statisticsDTO.setTotalGames(totalGamesInPeriod(dateFrom,dateTo));
            statisticsDTO.setMostFrequentGoal(mostFrequentGoalForPeriod(dateFrom,dateTo));
            statisticsDTO.setLeastFrequentGoal(leastFrequentGoalForPeriod(dateFrom,dateTo));
            statisticsDTO.setHighestScoredGoal(highestScoredGoalForPeriod(dateFrom,dateTo));
            statisticsDTO.setLowestScoredGoal(lowestScoredGoalForPeriod(dateFrom,dateTo));
            statisticsDTO.setGoalsAndGameCount(goalsAndGameCountForPeriod(dateFrom,dateTo));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return statisticsDTO;
    }

    /**
     *
     * @param from
     * @param to
     * @return Most Frequent Goal between selected dates
     */

    @Override
    public Map<String, Object> mostFrequentGoalForPeriod(Date from, Date to) {
        try{
            List<Object[]> result = gameRepository.calculateMostFrequentGoalForPeriod(from,to);
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param from
     * @param to
     * @return Least Frequent Goal between selected dates
     */

    @Override
    public Map<String, Object> leastFrequentGoalForPeriod(Date from, Date to) {
        try{
            List<Object[]> result = gameRepository.calculateLeastFrequentGoalForPeriod(from,to);
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param from
     * @param to
     * @return Highest Scored Goal between selected dates
     */

    @Override
    public Map<String, Object> highestScoredGoalForPeriod(Date from, Date to) {
        try{
            List<Object[]> result = gameRepository.calculateHighestScoredGoalForPeriod(from,to);
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param from
     * @param to
     * @return Lowest Scored Goal between selected dates
     */

    @Override
    public Map<String, Object> lowestScoredGoalForPeriod(Date from, Date to) {
        try{
            List<Object[]> result = gameRepository.calculateLowestScoredGoalForPeriod(from,to);
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param from
     * @param to
     * @return Returns number of games per goal between dates
     */
    @Override
    public Map<String, Object> goalsAndGameCountForPeriod(Date from, Date to) {
        try{
            List<Object[]> result = gameRepository.timePeriodGoalsAndGameCount(from,to);
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param from
     * @param to
     * @return a long value counting the number of games between two dates.
     */

    @Override
    public long totalGamesInPeriod(Date from, Date to){
        return gameRepository.countGamesByDatePlayedBetween(from,to);
    }

    /**
     *
     * @return a long value counting the number of games.
     */

    @Override
    public long totalGames(){
        return gameRepository.count();
    }

    /**
     *
     * @return The most frequent goal
     */
    @Override
    public Map<String, Object> mostFrequentGoal() {

        try{
            List<Object[]> result = gameRepository.calculateMostFrequentGoal();
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @return The least frequent goal
     */

    @Override
    public Map<String, Object> leastFrequentGoal() {
        try{
            List<Object[]> result = gameRepository.calculateLeastFrequentGoal();
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @return The highest scored goal
     */
    @Override
    public Map<String, Object> highestScoredGoal() {
        try{
            List<Object[]> result = gameRepository.calculateHighestScore();
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();
    }
    /**
     *
     * @return The lowest scored goal
     */
    @Override
    public Map<String, Object> lowestScoredGoal() {
        try{
            List<Object[]> result = gameRepository.calculateLowestScore();
            return extractData(result);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<>();

    }

    /**
     *
     * @return Returns number of games per goal
     */

    @Override
    public Map<String, Object> goalsAndGameCount() {

        try{
            List<Object[]> result = gameRepository.allGoalsAndGameCount();
            return extractData(result);

        } catch (RuntimeException e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     *
     * @param result
     * @return Extracts the data from the List<Object[]>
     *     and puts the data in a key value hash map.
     *     This is consistently reused through the class
     *     to stop duplication of code.
     */

    private Map<String, Object> extractData(List<Object[]> result){
        Map<String,Object> map = new HashMap<>();
        if(result != null && !result.isEmpty()){
            for(Object[] objects: result){
                if(objects[1] instanceof BigInteger){
                    map.put((String) objects[0], (BigInteger) objects[1]);
                } else if(objects[1] instanceof BigDecimal){
                    map.put((String) objects[0], (BigDecimal) objects[1]);
                }

            }
        }
        return map;
    }




}
