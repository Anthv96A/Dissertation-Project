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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

        when(gameRepository.findById(anyLong())).thenThrow(NotFoundException.class);

        Game gameReturned = gameService.findById(1L);

        assertNull("Game returned null", gameReturned);
        verify(gameRepository,times(1)).findById(anyLong());
        verify(gameRepository,never()).findAll();

    }

    @Test
    public void testCreateGame() throws Exception {

        final String TEST_STRING = "Driver: To hit to fairway";

        // Given
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(TEST_STRING);

        Optional<Game> gameOptional = Optional.of(new Game());

        Game savedGame = new Game();
        savedGame.setName(TEST_STRING);

        when(gameRepository.findById(anyLong())).thenReturn(gameOptional);
        when(gameRepository.save(any(Game.class)) ).thenReturn(savedGame);

        // When
        Game saved = gameService.create(gameDTO);

        // Then
        assertEquals(gameDTO.getName(),saved.getName());
        verify(gameRepository,times(1)).save(any(Game.class));
    }

    @Test
    public void testFindLastGameByGoalName() throws Exception {
        String TEST_STRING = "Driver: To hit to fairway";

        Game obj = new Game();
        obj.setName(TEST_STRING);
        Optional<Game> gameOptional = Optional.of(obj);

        GameDTO gameDTO = new GameDTO();
        gameDTO.setName(TEST_STRING);

        when(gameRepository.getLastGameByGoal(anyString()))
                .thenReturn(gameOptional);
        when(gameToGameDTO.convert(obj))
                .thenReturn(gameDTO);

        GameDTO returned = gameService.findLastGameByGoalName(TEST_STRING);

        assertEquals("Game returned not null ", TEST_STRING, returned.getName());
        assertNotNull("Game converted and returned not null", returned);

        verify(gameRepository, times(1)).getLastGameByGoal(TEST_STRING);
        verify(gameToGameDTO,times(1)).convert(obj);

    }


    @Test(expected = RuntimeException.class)
    public void testGameNotCreated() throws Exception {

        // Given Bad Request
        when(gameRepository.save(any(Game.class)) ).thenThrow(RuntimeException.class);

        // When
        Game saved = gameService.create(new GameDTO());

        // Then
        assertNull("Throw Runtime exception ",saved.getName());
    }

    @Test
    public void testFindAllGamesWithinDatePeriod() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String testFrom = "2018-02-08";
        String testTo = "2018-02-15";

        List<GameDTO> data = new ArrayList<>();
        data.add(new GameDTO());
        List<Game> games = new ArrayList<>();
        games.add(new Game());

       when(gameRepository
               .findGameByDatePlayedBetweenOrderByDatePlayedDesc
                       (formatter.parse(testFrom),formatter.parse(testTo)))
               .thenReturn(games);
       games.forEach( game ->
           when(gameToGameDTO.convert(game))
                   .thenReturn(new GameDTO())
       );
       List<GameDTO> returnedList = gameService.findAllGamesWithinDatePeriod(testFrom,testTo);
       assertNotNull("Objects returned", returnedList);
       // Expected 1 in games size and 1 in returned list size.
       assertEquals("Games List count matches DTO List count", games.size(), returnedList.size());
       verify(gameRepository,times(1))
               .findGameByDatePlayedBetweenOrderByDatePlayedDesc
                       (formatter.parse(testFrom),formatter.parse(testTo));
       // Verify it has converted 4 games into transfer objects
        verify(gameToGameDTO,times(1)).convert(any(Game.class));
    }

    @Test(expected = ParseException.class)
    public void testFindAllGamesWithinDatePeriodFormatException() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyS-MM-dd");
        String testFrom = "2018-02-08";
        String testTo = "2018-02-15";

        List<GameDTO> data = new ArrayList<>();
        data.add(new GameDTO());

        List<Game> games = new ArrayList<>();
        games.add(new Game());


        when(gameRepository
                .findGameByDatePlayedBetweenOrderByDatePlayedDesc
                        (formatter.parse(testFrom),formatter.parse(testTo)))
                .thenThrow(ParseException.class);

        List<GameDTO> returnedList = gameService.findAllGamesWithinDatePeriod(testFrom,testTo);
        assertNull("Parsing error", returnedList);
        verify(gameRepository,times(1))
                .findGameByDatePlayedBetweenOrderByDatePlayedDesc
                        (formatter.parse(testFrom),formatter.parse(testTo));

    }

}