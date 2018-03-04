package com.example.Project.converters;
import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import com.example.Project.domain.Goal;
import com.example.Project.domain.Hole;
import com.example.Project.repositories.GoalRepository;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Optional;

@Component
public class GameDTOToGame {

	private final GoalRepository goalRepository;

	public GameDTOToGame(GoalRepository goalRepository) {
		this.goalRepository = goalRepository;
	}

	@Synchronized
	public Game createGameWithDTO(final GameDTO game) {

		final Game newGame = new Game();

		newGame.setName(game.getName());
		newGame.setPostEmotions(game.getPostEmotions());
		newGame.setPreEmotions(game.getPreEmotions());
		newGame.setTotalScore(game.getTotalScore());

		final java.sql.Date today = new java.sql.Date(
				Calendar.getInstance()
				.getTime().getTime());

		newGame.setDatePlayed(today);

		game.getGoals().forEach(g -> {
			final Goal goal;
			final Optional<Goal> goalOptional = goalRepository.findByName(g.getName());
			try{
				if(goalOptional.isPresent()){
					goal = goalOptional.get();
					goal.addGame(newGame);
				} else {
					goal = new Goal();
					goal.setName(g.getName());
					goal.addGame(newGame);
				}
			} catch (RuntimeException e){
				throw new RuntimeException("Some error occurred: " + e.getMessage());
			}
		});

		game.getHoles().forEach(h ->{
			final Hole hole = new Hole();
			hole.setHoleNumber(h.getHoleNumber());
			hole.setScore(h.getScore());
			newGame.addHole(hole);
		});

		return newGame;

	}

}
