package com.example.Project.services;

import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.repositories.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final GameRepository gameRepository;


    @Override
    public StatisticsDTO getStatistics(){
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        statisticsDTO.setTotalGames(totalGames());
        statisticsDTO.setMostFrequentGoal(mostFrequentGoal());
        statisticsDTO.setLeastFrequentGoal(leastFrequentGoal());
        statisticsDTO.setHighestScoredGoal(highestScoredGoal());
        statisticsDTO.setLowestScoredGoal(lowestScoredGoal());
        return statisticsDTO;
    }

    @Override
    public long totalGames(){
        return gameRepository.count();
    }

    @Override
    public Map<String, Object> mostFrequentGoal() {

        try{
            List<Object[]> result = gameRepository.calculateMostFrequentGoal();
            Map<String,Object> map = null;
            return extractData(result, map);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    @Override
    public Map<String, Object> leastFrequentGoal() {
        try{
            List<Object[]> result = gameRepository.calculateLeastFrequentGoal();
            Map<String,Object> map = null;
            return extractData(result, map);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    @Override
    public Map<String, Object> highestScoredGoal() {
        try{
            List<Object[]> result = gameRepository.calculateHighestScore();
            Map<String,Object> map = null;
            return extractData(result, map);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    @Override
    public Map<String, Object> lowestScoredGoal() {
        try{
            List<Object[]> result = gameRepository.calculateLowestScore();
            Map<String,Object> map = null;
            return extractData(result, map);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        return new HashMap<String, Object>();

    }


    private Map<String, Object> extractData(List<Object[]> result, Map<String, Object> map){
        if(result != null && !result.isEmpty()){
            map = new HashMap<>();
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


//    @Override
//    public Map<String, BigInteger> mostFrequentGoal() {
//
//        try{
//            List<Object[]> result = gameRepository.calculateMostFrequentGoal();
//            Map<String,BigInteger> map = null;
//
//            if(result != null && !result.isEmpty()){
//                map = new HashMap<>();
//                for(Object[] objects: result){
//                    map.put((String) objects[0], (BigInteger)objects[1]);
//                }
//            }
//            return map;
//        } catch (RuntimeException e){
//            e.printStackTrace();
//        }
//        return new HashMap<String, BigInteger>();
//    }


}
