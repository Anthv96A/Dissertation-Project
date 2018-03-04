package com.example.Project.controllers;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.services.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin({"http://localhost:8100","file://"})
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable Long id){
        return gameService.findById(id);
    }

    @PostMapping("/new")
    public GameDTO saveNewGame(@RequestBody GameDTO game) {
        return gameService.create(game);
    }

    @GetMapping("/last/{name}")
    public GameDTO getLastGameOnGoal(@PathVariable String name){
        return gameService.findLastGameByGoalName(name);
    }

    @GetMapping("/games-period/{from}/{to}")
    public List<GameDTO> getAllFromGamePeriod(@PathVariable String from, @PathVariable String to){
        return gameService.findAllGamesWithinDatePeriod(from,to);
    }
}
