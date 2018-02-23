package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.junit.Before;
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
public class GoalRepositoryIT {

    @Autowired
    GoalRepository goalRepository;


    @Transactional
    @Test
    public void findByName() throws Exception {
        Optional<Goal> goalOptional = goalRepository.findByName("Driver: To hit the fairway");

        assertEquals("Driver: To hit the fairway", goalOptional.get().getName());

    }

}