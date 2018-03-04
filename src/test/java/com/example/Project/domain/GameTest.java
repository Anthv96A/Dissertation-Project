package com.example.Project.domain;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private List data;

    @Before
    public void setUp() throws Exception {
        this.game = new Game();
        data = new ArrayList<>();
    }

    @Test
    public void testAddHole() throws Exception {
        Game returned = game.addHole(new Hole());

        assertNotNull("Should of added hole correctly", returned.getHoles());
        assertEquals("Should be one hole added", 1, returned.getHoles().size());
    }

    @Test
    public void testSetId() throws Exception {
        final Long expectedId = 1L;
        game.setId(expectedId);

        assertEquals("ID should be equal", expectedId, game.getId());
    }

    @Test
    public void testSetName() throws Exception {
        final String expectedName = "Driver: To hit the fairway";
        game.setName(expectedName);

        assertEquals("Name should be equal", expectedName, game.getName());
    }

    @Test
    public void testSetDatePlayed() throws Exception {
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String testDate = "2018-01-01";

        game.setDatePlayed(formatter.parse(testDate));

        assertNotNull("Should not be null", game.getDatePlayed());
    }

    @Test
    public void testSetTotalScore() throws Exception {
        final Integer totalScore = 20;
        game.setTotalScore(totalScore);

        assertNotNull("Should not be null");
        assertEquals("Should equal 20", totalScore, game.getTotalScore());
    }

    @Test
    public void testSetPreEmotions() throws Exception {
        final String preEmotions = "Testing";
        game.setPreEmotions(preEmotions);

        assertNotNull("Should not be null", game.getPreEmotions());
    }

    @Test
    public void testSetPostEmotions() throws Exception {
        final String postEmotions = "Testing";
        game.setPostEmotions(postEmotions);

        assertNotNull("Should not be null", game.getPostEmotions());
    }

    @Test
    public void testSetHoles() throws Exception {

        data.add(new Hole());
        data.add(new Hole());
        data.add(new Hole());

        game.setHoles(data);

        assertNotNull("Should not be null", game.getHoles());
        assertEquals("Should equal 3",3 ,game.getHoles().size());
    }

    @Test
    public void testSetGoals() throws Exception {

        data.add(new Goal());
        data.add(new Goal());
        data.add(new Goal());

        game.setGoals(data);

        assertNotNull("Should not be null", game.getGoals());
        assertEquals("Should equal 3",3 ,game.getGoals().size());
    }

}