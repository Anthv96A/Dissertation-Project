package com.example.Project.services;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.converters.GameDTOToGame;
import com.example.Project.converters.GameToGameDTO;
import com.example.Project.domain.Game;
import com.example.Project.exceptions.NotFoundException;
import com.example.Project.repositories.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * By Anthony Vest
 */

public class GameServiceImplTest {

    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameToGameDTO gameToGameDTO;

    @Mock
    private GameDTOToGame gameDTOToGame;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(gameDTOToGame,gameRepository,gameToGameDTO);
    }

    @Test
    public void testFindById() throws Exception {
        Long testID = 1L;
        Game game = new Game();
        game.setId(testID);
        Optional<Game> gameOptional = Optional.of(game);

        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);


        Game gameReturned = gameService.findById(testID);

        assertNotNull("Game returned not null", gameReturned);
        verify(gameRepository,times(1)).findById(anyLong());
        verify(gameRepository,never()).findAll();

    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundFindById() throws Exception {

        Optional<Game> gameOptional = Optional.empty();

        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);

        Game gameReturned = gameService.findById(1L);

        assertNull("Game returned null", gameReturned);
        verify(gameRepository,times(1)).findById(anyLong());
        verify(gameRepository,never()).findAll();

    }

    @Test
    public void testCreateGame() throws Exception {

        // Given
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("Driver: To hit to fairway");

        Optional<Game> gameOptional = Optional.of(new Game());

        Game savedGame = new Game();
        savedGame.setName("Driver: To hit to fairway");


        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);
        when(gameRepository.save(any(Game.class)) ).thenReturn(savedGame);

        // When
        Game saved = gameService.create(gameDTO);

        // Then
        assertEquals(gameDTO.getName(),saved.getName());
        verify(gameRepository,times(1)).save(any(Game.class));


//



    }

    @Test
    public void testFindLastGameByGoalName() throws Exception {
    }

    @Test
    public void testFindAllGamesWithinDatePeriod() throws Exception {
    }

}