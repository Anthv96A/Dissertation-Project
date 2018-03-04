package com.example.Project.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HoleTest {

    private Hole hole;

    @Before
    public void setUp() throws Exception {
        this.hole = new Hole();
    }

    @Test
    public void testSetId() throws Exception {
        final Long expectedId = 1L;
        hole.setId(expectedId);

        assertEquals("Hole id should equal 1", expectedId, hole.getId());
    }

    @Test
    public void testSetHoleNumber() throws Exception {
        final Integer expectedHoleNumber = 1;
        hole.setHoleNumber(expectedHoleNumber);

        assertEquals("Hole number should be 1 ", expectedHoleNumber, hole.getHoleNumber());
    }

    @Test
    public void testSetGame() throws Exception {
        final String gameName = "Driver: To hit the fairway";
        final Game data = new Game();

        data.setName(gameName);
        hole.setGame(data);

        assertNotNull("Game should be assigned to golf hole", hole.getGame());
        assertEquals("Game name should be as expected", gameName,hole.getGame().getName());


    }

    @Test
    public void testSetScore() throws Exception {
        final Integer score = 5;
        hole.setScore(score);

        assertNotNull("Should not be null", hole.getScore());
        assertEquals("Should equal 5 as expected", score, hole.getScore());
    }

}