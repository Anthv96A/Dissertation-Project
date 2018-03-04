package com.example.Project.repositories;

import com.example.Project.domain.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GameRepositoryIT {

    @Autowired
    private GameRepository gameRepository;

    private final String sourceStringFrom = "2018-02-10";
    private final String sourceStringTo = "2018-02-17";

    private SimpleDateFormat formatter;
    private Date testFromDate;
    private Date testToDate;

    @Before
    public void setUp() throws Exception {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        testFromDate = formatter.parse(sourceStringFrom);
        testToDate = formatter.parse(sourceStringTo);
    }

    @Test
    public void testFindById() throws Exception {
        Long dataID = 1L;
        final Optional<Game> gameOptional = gameRepository.findById(dataID);

        assertNotNull("Game with ID of 1 should be returned", gameOptional.get().getId());

    }

    @Test
    public void testCalculateMostFrequentGoal() throws Exception {

        final List<Object[]> returned = gameRepository.calculateMostFrequentGoal();

        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));

    }

    @Test
    public void testCalculateLeastFrequentGoal() throws Exception {
        final List<Object[]> returned = gameRepository.calculateLeastFrequentGoal();

        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testCalculateHighestScore() throws Exception {
        final List<Object[]> returned = gameRepository.calculateHighestScore();

        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testCalculateLowestScore() throws Exception {
        final List<Object[]> returned = gameRepository.calculateLowestScore();
        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testAllGoalsAndGameCount() throws Exception {
        final List<Object[]> returned = gameRepository.allGoalsAndGameCount();

        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() >= 1 ) || (returned.size() == 0));
    }

    @Test
    public void testGetLastGameByGoal() throws Exception {
        final String TEST_NAME = "Driver: To hit the fairway";
        final Optional<Game> gameOptional = gameRepository.getLastGameByGoal(TEST_NAME);
        final Optional<Game> empty = Optional.empty();
        final boolean exists = gameOptional.isPresent();

        if(exists){
            assertTrue("Game should be returned", exists);
            assertNotNull("Game returned not null", gameOptional.get());
        } else{
            assertEquals("Game optional was not found", empty, gameOptional);
        }

    }

    @Test
    public void testFindGameByDatePlayedBetweenOrderByDatePlayedDesc() throws Exception {
        final List<Game> returned = gameRepository.
                findGameByDatePlayedBetweenOrderByDatePlayedDesc(testFromDate,testToDate);

        if(returned.isEmpty()){
            assertNull("Not data between dates", returned);
        } else{
            assertTrue("Should contain 1 or more values", returned.size() >= 1);
        }

    }

    @Test
    public void testCalculateMostFrequentGoalForPeriod() throws Exception {
        final List<Object[]> returned = gameRepository.
            calculateMostFrequentGoalForPeriod(testFromDate,testToDate);
    assertTrue("Should only return either '0' or '1' ONLY",
            (returned.size() == 1 ) || (returned.size() == 0));
}

    @Test
    public void testCalculateLeastFrequentGoalForPeriod() throws Exception {
        final List<Object[]> returned = gameRepository.
                calculateLeastFrequentGoalForPeriod(testFromDate,testToDate);
        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testCalculateHighestScoredGoalForPeriod() throws Exception {
        final List<Object[]> returned = gameRepository.
                calculateHighestScoredGoalForPeriod(testFromDate,testToDate);
        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testCalculateLowestScoredGoalForPeriod() throws Exception {
        final List<Object[]> returned = gameRepository.
                calculateLowestScoredGoalForPeriod(testFromDate,testToDate);
        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() == 1 ) || (returned.size() == 0));
    }

    @Test
    public void testTimePeriodGoalsAndGameCount() throws Exception {
        final List<Object[]> returned = gameRepository.
                timePeriodGoalsAndGameCount(testFromDate,testToDate);
        assertTrue("Should only return either '0' or '1' ONLY",
                (returned.size() >= 1 ) || (returned.size() == 0));
    }

    @Test
    public void testCountGamesByDatePlayedBetween() throws Exception {
        final Long returned = gameRepository.
                countGamesByDatePlayedBetween(testFromDate,testToDate);
        assertTrue("Should be more than 1 or 0", returned >=0);
        assertNotNull("Should never be null",returned);
    }

    @Test
    public void testCount() throws Exception {
        final Long returned = gameRepository.count();

        assertTrue("Should be more than 1 or 0", returned >=0);
        assertNotNull("Should never be null",returned);
    }

}