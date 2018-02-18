package com.example.Project.converters;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.domain.Hole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class GameToGameDTOTest {

    private GameToGameDTO gameToGameDTO;

    private static final Long GAME_ID = 1L;
    private static final String GAME_NAME = "Driver: To hit to fairway";
    private static final Integer GAME_TOTAL_SCORE = 20;
    private static final String GAME_PRE_EMOTIONS = "None";
    private static final String GAME_POST_EMOTIONS = "None";

    private static final Long GOAL_ID = 1L;
    private static final String GOAL_NAME = "Driver: To hit to fairway";

    private static final Long HOLE_ID = 1L;
    private static final Integer HOLE_NUMBER = 1;
    private static final Integer HOLE_SCORE = 5;

    @Before
    public void setUp() throws Exception {
        gameToGameDTO = new GameToGameDTO();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(gameToGameDTO.convert(null));
    }

    @Test
    public void convert() throws Exception {

        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        // Given
        Game game = new Game();

        game.setId(GAME_ID);
        game.setName(GAME_NAME);
        game.setPreEmotions(GAME_PRE_EMOTIONS);
        game.setPostEmotions(GAME_POST_EMOTIONS);
        game.setTotalScore(GAME_TOTAL_SCORE);
        game.setDatePlayed(today);

        Hole hole = new Hole();
        hole.setId(HOLE_ID);
        hole.setHoleNumber(HOLE_NUMBER);
        hole.setScore(HOLE_SCORE);

        game.getHoles().add(hole);

        // When
        GameDTO gameDTO = gameToGameDTO.convert(game);

        // Then
        assertNotNull(gameDTO);
        assertEquals(GAME_NAME, gameDTO.getName());
        assertEquals(GAME_PRE_EMOTIONS, gameDTO.getPreEmotions());
        assertEquals(GAME_POST_EMOTIONS, gameDTO.getPostEmotions());
        assertEquals(GAME_TOTAL_SCORE, gameDTO.getTotalScore());
        assertEquals(1, gameDTO.getHoles().size());



    }

}