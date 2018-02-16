package com.example.Project.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GoalTest {

    private Goal goal;

    @Before
    public void setUp() throws Exception {
        this.goal = new Goal();
    }

    @Test
    public void setId() throws Exception {
        Long expectedId = 4L;
        goal.setId(expectedId);
        assertEquals("Expects ID to return with long value of 4L",expectedId, goal.getId());
    }

    @Test
    public void setName() throws Exception {
        String expectedName = "Driver: To hit the fairway";
        goal.setName(expectedName);
        assertEquals("Expects name to return 'Driver: To hit the fairway' as goal name ", expectedName, goal.getName());
    }


}