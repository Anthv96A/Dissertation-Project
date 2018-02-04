package com.example.Project.components;


import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Goal;
import com.example.Project.repositories.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.example.Project.domain.Game;
import com.example.Project.domain.Hole;

@Component
@AllArgsConstructor
public class GameHoles {

	private final GoalRepository goalRepository;

	public Game createGameWithDTO(GameDTO game) {

		Game newGame = new Game();

		newGame.setName(game.getName());
		newGame.setPostEmotions(game.getPostEmotions());
		newGame.setPreEmotions(game.getPreEmotions());
		newGame.setTotalScore(game.getTotalScore());


		Goal goal = null;

		for(Goal g: game.getGoals()){

			if(goalRepository.existsByName(g.getName())){
				goal = goalRepository.findByName(g.getName()).get();
				goal.getGames().add(newGame);
				newGame.getGoals().add(goal);
			} else{
				goal = new Goal();
				goal.setName(g.getName());
				goal.getGames().add(newGame);
				newGame.getGoals().add(goal);
			}

		}

		Hole hole = null;

		for(Hole h: game.getHoles()){
			hole = new Hole();
			hole.setHoleNumber(h.getHoleNumber());
			hole.setScore(h.getScore());
			hole.setGame(newGame);
			newGame.getHoles().add(hole);
		}

		return newGame;

	}

}
