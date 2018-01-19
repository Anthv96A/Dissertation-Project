package com.example.Project.controllers;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.repositories.GameRepository;
import com.example.Project.repositories.GoalRepository;
import com.example.Project.services.GameService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/game")
@CrossOrigin({"http://localhost:8100","file://"})
public class GameController {
    
    private final GameService gameService;
    private final GameRepository gameRepository;

	@GetMapping("/index")
    public List<Game> index(){
	    List<Game> gameList = new ArrayList<>();
        Iterator<Game> ig = gameRepository.findAll().iterator();

        while (ig.hasNext()){
            gameList.add(ig.next());
        }
        return gameList;
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable Long id){
        return gameService.findById(id);
    }

//	@PostMapping("/new")
//    public Game saveNewGame(@RequestBody Game game) {
//		System.out.println("We hit the controller" + game.toString());
//		return gameService.createOrUpdate(game);
//    }

    @PostMapping("/new")
    public Game saveNewGame(@RequestBody GameDTO game) {
        return gameService.create(game);
    }


    @GetMapping("/last/{name}")
    public GameDTO getLastGameOnGoal(@PathVariable String name){
        GameDTO gameDTO = gameService.findLastGameByGoalName(name);

        if(gameDTO == null){
            return new GameDTO();
        }

        return gameDTO;
    }
}
