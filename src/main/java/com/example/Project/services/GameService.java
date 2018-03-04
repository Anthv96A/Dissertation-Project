package com.example.Project.services;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;

import java.util.List;

public interface GameService extends AbstractService<GameDTO> {

    GameDTO create(GameDTO dto);
    GameDTO findLastGameByGoalName(String name);
    List<GameDTO> findAllGamesWithinDatePeriod(String from, String to);

}
