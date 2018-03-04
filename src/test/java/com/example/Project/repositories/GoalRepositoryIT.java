package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GoalRepositoryIT {

    @Autowired
    private GoalRepository goalRepository;

    @Test
    public void testFindByName() throws Exception {
        Optional<Goal> goalOptional = goalRepository.findByName("Driver: To hit the fairway");
        assertEquals("Driver: To hit the fairway",
                goalOptional.get().getName());
    }

    @Test
    public void testFindByNameExists() throws Exception {
        final Optional<Goal> goalOptional = goalRepository.findByName("Driver: To hit the fairway");
        final boolean expected = true;
        // isPresent() returns a boolean true or false
        assertEquals("'Driver: To hit the fairway' should exist",
                expected,
                goalOptional.isPresent());
    }

    @Test
    public void testFindByNameNotExists() throws Exception {
        final Optional<Goal> goalOptional = goalRepository.findByName("SOME DATA");
        final boolean expected = false;

        assertEquals("To be false",
                expected,
                goalOptional.isPresent());
    }

    @Test
    public void testCheckIfExists() throws Exception {
        final String TEST_NAME = "Driver: To hit the fairway";
        final boolean checkIfExists = goalRepository.existsByName(TEST_NAME);

        assertTrue("'Driver: To hit the fairway' should exist", checkIfExists);
        assertNotNull("Should always return a value of 'true' or 'false'",checkIfExists);

    }

    @Test
    public void testCheckIfNOTExists() throws Exception {
        final String TEST_NAME = "SOME DATA";
        final boolean expected = false;
        final boolean actual = goalRepository.existsByName(TEST_NAME);

        assertEquals("'SOME DATA' should NOT exist",expected ,actual);
        assertNotNull("Should always return a value of 'true' or 'false'",actual);
    }

}