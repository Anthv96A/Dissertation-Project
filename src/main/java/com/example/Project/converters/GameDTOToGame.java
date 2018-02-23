package com.example.Project.converters;


import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Goal;
import com.example.Project.exceptions.NotFoundException;
import com.example.Project.repositories.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import com.example.Project.domain.Game;
import com.example.Project.domain.Hole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GameDTOToGame {

	private final GoalRepository goalRepository;

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
			if (goalRepository.existsByName(g.getName())) {
				Optional<Goal> goalOptional = goalRepository.findByName(g.getName());
				if (goalOptional.isPresent()) {
					goal = goalOptional.get();
				} else {
					throw new NotFoundException("Goal not found");
				}
				goal.addGame(newGame);
			} else {
				goal = new Goal();
				goal.setName(g.getName());
				goal.addGame(newGame);
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
