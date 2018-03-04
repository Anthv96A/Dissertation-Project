package com.example.Project.controllers;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.exceptions.NotFoundException;
import com.example.Project.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;
    private MockMvc mockMvc;
    private Game game;
    private GameDTO gameDTO;
    private List<GameDTO> gameDTOList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
        game = new Game();
        gameDTO =  new GameDTO();
        gameDTOList = new ArrayList<>();
    }

    @Test
    public void testFindById() throws Exception {

        when(gameService.findById(anyLong())).thenReturn(new GameDTO()
        );
        mockMvc.perform(get("/game/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {

        when(gameService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/game/122222"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveNewGame() throws Exception {
        game.setId(1L);

        when(gameService.create(any())).thenReturn(new GameDTO());

        mockMvc.perform(get("/game/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testCouldNotSaveNewGame() throws Exception {

        when(gameService.create(any())).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/game/bad-request")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetLastGameOnGoal() throws Exception {
        gameDTO.setName("Driver: To hit the fairway");

        when(gameService.findLastGameByGoalName(any())).thenReturn(gameDTO);

        mockMvc.perform(get("/game/last/Driver: To hit the fairway")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void testGetLastGameOnGoalNotFound() throws Exception {
        gameDTO.setName("Driver: To hit the fairway");

        when(gameService.findLastGameByGoalName(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/game/last/TEST")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());

    }

    @Test
    public void testGetAllFromGamePeriod() throws Exception{
        gameDTOList.add(new GameDTO());

        String from = "2018-02-08";
        String to = "2018-02-15";

        when(gameService.findAllGamesWithinDatePeriod(from,to)).thenReturn(gameDTOList);

        mockMvc.perform(get("/game/games-period/2018-02-08/2018-02-15")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(gameService, times(1)).findAllGamesWithinDatePeriod(from,to);

    }

}