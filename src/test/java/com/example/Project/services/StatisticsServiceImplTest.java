package com.example.Project.services;

import com.example.Project.DTOs.StatisticsDTO;
import com.example.Project.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StatisticsServiceImplTest {

    /**
     * Testing the statistics service, using game repository as dependency
     */
    private StatisticsServiceImpl statisticsService;

    @Mock
    private GameRepository gameRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        statisticsService = new StatisticsServiceImpl(gameRepository);
    }

    @Test
    public void testGetStatistics() throws Exception {
        // Given
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<Object[]> returnedData = new ArrayList<>();

        Long testLong = 10L;

        statisticsDTO.setTotalGames(testLong);
        statisticsDTO.setGoalsAndGameCount(new HashMap<>());
        statisticsDTO.setLowestScoredGoal(new HashMap<>());
        statisticsDTO.setHighestScoredGoal(new HashMap<>());
        statisticsDTO.setMostFrequentGoal(new HashMap<>());
        statisticsDTO.setLeastFrequentGoal(new HashMap<>());

        // When
        when(gameRepository.count()).thenReturn(testLong);
        when(gameRepository.calculateHighestScore()).thenReturn(returnedData);
        when(gameRepository.calculateLowestScore()).thenReturn(returnedData);
        when(gameRepository.calculateLeastFrequentGoal()).thenReturn(returnedData);
        when(gameRepository.calculateMostFrequentGoal()).thenReturn(returnedData);
        when(gameRepository.allGoalsAndGameCount()).thenReturn(returnedData);

        // Then

        StatisticsDTO returned = statisticsService.getStatistics();

        assertNotNull("NOT null", returned);
        verify(gameRepository, times(1)).count();
        verify(gameRepository, times(1)).calculateHighestScore();
        verify(gameRepository, times(1)).calculateLowestScore();
        verify(gameRepository, times(1)).calculateLeastFrequentGoal();
        verify(gameRepository, times(1)).calculateMostFrequentGoal();
        verify(gameRepository, times(1)).allGoalsAndGameCount();

}

    @Test
    public void testGetStatisticsWithinTimePeriod() throws Exception {

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<Object[]> returnedData = new ArrayList<>();
        Long testLong = 10L;

        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        statisticsDTO.setTotalGames(testLong);
        statisticsDTO.setGoalsAndGameCount(new HashMap<>());
        statisticsDTO.setLowestScoredGoal(new HashMap<>());
        statisticsDTO.setHighestScoredGoal(new HashMap<>());
        statisticsDTO.setMostFrequentGoal(new HashMap<>());
        statisticsDTO.setLeastFrequentGoal(new HashMap<>());

        when(gameRepository.countGamesByDatePlayedBetween(testFromDate,testToDate)).thenReturn(testLong);
        when(gameRepository.calculateHighestScoredGoalForPeriod(testFromDate,testToDate)).thenReturn(returnedData);
        when(gameRepository.calculateLowestScoredGoalForPeriod(testFromDate,testToDate)).thenReturn(returnedData);
        when(gameRepository.calculateLeastFrequentGoalForPeriod(testFromDate,testToDate)).thenReturn(returnedData);
        when(gameRepository.calculateMostFrequentGoalForPeriod(testFromDate,testToDate)).thenReturn(returnedData);
        when(gameRepository.timePeriodGoalsAndGameCount(testFromDate,testToDate)).thenReturn(returnedData);


        StatisticsDTO returned = statisticsService.getStatisticsWithinTimePeriod(sourceStringFrom,sourceStringTo);

        assertNotNull("NOT null from date range",returned);

        verify(gameRepository,times(1)).countGamesByDatePlayedBetween(testFromDate,testToDate);
        verify(gameRepository,times(1)).calculateHighestScoredGoalForPeriod(testFromDate,testToDate);
        verify(gameRepository,times(1)).calculateLowestScoredGoalForPeriod(testFromDate,testToDate);
        verify(gameRepository,times(1)).calculateLeastFrequentGoalForPeriod(testFromDate,testToDate);
        verify(gameRepository,times(1)).calculateMostFrequentGoalForPeriod(testFromDate,testToDate);
        verify(gameRepository,times(1)).timePeriodGoalsAndGameCount(testFromDate,testToDate);

    }

    @Test(expected = ParseException.class)
    public void testGetStatisticsWithinTimePeriodThrowParseException() throws Exception {

        String sourceStringFrom = "20ss18-02-10";
        String sourceStringTo = "201s8-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        when(gameRepository.countGamesByDatePlayedBetween(testFromDate,testToDate)).thenThrow(ParseException.class);

        StatisticsDTO returned = statisticsService.getStatisticsWithinTimePeriod(sourceStringFrom,sourceStringTo);

        assertNull("Throw parse error", returned);



    }

    @Test
    public void testMostFrequentGoalForPeriod() throws Exception {

        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateMostFrequentGoalForPeriod(testFromDate,testToDate))
                .thenReturn(data);

        List<Object[]> returned = gameRepository
                .calculateMostFrequentGoalForPeriod(testFromDate,testToDate);

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1))
                .calculateMostFrequentGoalForPeriod(testFromDate,testToDate);
    }

    @Test
    public void testLeastFrequentGoalForPeriod() throws Exception {
        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateLeastFrequentGoalForPeriod(testFromDate,testToDate))
                .thenReturn(data);

        List<Object[]> returned = gameRepository
                .calculateLeastFrequentGoalForPeriod(testFromDate,testToDate);

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1))
                .calculateLeastFrequentGoalForPeriod(testFromDate,testToDate);
    }

    @Test
    public void testHighestScoredGoalForPeriod() throws Exception {
        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateHighestScoredGoalForPeriod(testFromDate,testToDate))
                .thenReturn(data);

        List<Object[]> returned = gameRepository
                .calculateHighestScoredGoalForPeriod(testFromDate,testToDate);

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1))
                .calculateHighestScoredGoalForPeriod(testFromDate,testToDate);
    }

    @Test
    public void testLowestScoredGoalForPeriod() throws Exception {
        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateLowestScoredGoalForPeriod(testFromDate,testToDate))
                .thenReturn(data);

        List<Object[]> returned = gameRepository
                .calculateLowestScoredGoalForPeriod(testFromDate,testToDate);

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1))
                .calculateLowestScoredGoalForPeriod(testFromDate,testToDate);
    }

    @Test
    public void testGoalsAndGameCountForPeriod() throws Exception {
        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);

        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.timePeriodGoalsAndGameCount(testFromDate,testToDate))
                .thenReturn(data);

        List<Object[]> returned = gameRepository
                .timePeriodGoalsAndGameCount(testFromDate,testToDate);

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1))
                .timePeriodGoalsAndGameCount(testFromDate,testToDate);
    }

    @Test
    public void testTotalGamesInPeriod() throws Exception {
        String sourceStringFrom = "2018-02-10";
        String sourceStringTo = "2018-02-17";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date testFromDate = formatter.parse(sourceStringFrom);
        Date testToDate = formatter.parse(sourceStringTo);
        Long testLong = 10L;
        when(gameRepository.countGamesByDatePlayedBetween(testFromDate,testToDate)).thenReturn(testLong);

        Long returned = statisticsService.totalGamesInPeriod(testFromDate,testToDate);

        assertEquals("Should return 10 games", testLong, returned);

    }

    @Test
    public void testTotalGames() throws Exception {
        Long testLong = 10L;
        when(gameRepository.count()).thenReturn(testLong);

        Long returned = statisticsService.totalGames();

        assertEquals("Should return 10 games", testLong, returned);

    }

    @Test
    public void testMostFrequentGoal() throws Exception {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateMostFrequentGoal()).thenReturn(data);

        List<Object[]> returned = gameRepository.calculateMostFrequentGoal();

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1)).calculateMostFrequentGoal();

    }

    @Test
    public void testLeastFrequentGoal() throws Exception {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateLeastFrequentGoal()).thenReturn(data);

        List<Object[]> returned = gameRepository.calculateLeastFrequentGoal();

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1)).calculateLeastFrequentGoal();
    }

    @Test
    public void testHighestScoredGoal() throws Exception {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateHighestScore()).thenReturn(data);

        List<Object[]> returned = gameRepository.calculateHighestScore();

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1)).calculateHighestScore();
    }

    @Test
    public void testLowestScoredGoal() throws Exception {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);

        when(gameRepository.calculateLowestScore()).thenReturn(data);

        List<Object[]> returned = gameRepository.calculateLowestScore();

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be one result", 1,returned.size());
        verify(gameRepository, times(1)).calculateLowestScore();
    }

    @Test
    public void testGoalsAndGameCount() throws Exception {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[0]);
        data.add(new Object[0]);
        data.add(new Object[0]);
        data.add(new Object[0]);
        data.add(new Object[0]);

        when(gameRepository.allGoalsAndGameCount()).thenReturn(data);

        List<Object[]> returned = gameRepository.allGoalsAndGameCount();

        assertNotNull("Should not be null",returned);
        assertEquals("Should only be five result", 5,returned.size());
        verify(gameRepository, times(1)).allGoalsAndGameCount();
    }

}