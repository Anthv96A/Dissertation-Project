package com.example.Project.controllers;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.exceptions.NotFoundException;
import com.example.Project.services.GameService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest {

    @Mock
    private GameService gameService;

    private GameController gameController;

    private MockMvc mockMvc;

    private Game game;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameController = new GameController(gameService);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
        game = new Game();
    }

    @Test
    public void testFindById() throws Exception {
       // Game game = new Game();
        game.setId(1L);

        when(gameService.findById(anyLong())).thenReturn(game);
        mockMvc.perform(get("/game/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindByIdNotFound() throws Exception {
       // Game game = new Game();
        game.setId(1L);

        when(gameService.findById(anyLong())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/game/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveNewGame() throws Exception {
       // Game game = new Game();
        game.setId(1L);

        when(gameService.create(any())).thenReturn(game);

        mockMvc.perform(get("/game/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void testGetLastGameOnGoal() throws Exception {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("Driver: To hit the fairway");

        when(gameService.findLastGameByGoalName(any())).thenReturn(gameDTO);

        mockMvc.perform(get("/game/last/Driver: To hit the fairway")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

}