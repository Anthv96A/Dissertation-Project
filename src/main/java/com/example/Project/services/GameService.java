package com.example.Project.services;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;

public interface GameService extends AbstractService<Game> {

    Game create(GameDTO dto);


    GameDTO findLastGameByGoalName(String name);

}
