package com.example.Project.services;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.components.GameToGameDTO;
import com.example.Project.domain.Goal;
import com.example.Project.domain.Hole;
import com.example.Project.repositories.GoalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Project.components.GameHoles;
import com.example.Project.domain.Game;
import com.example.Project.repositories.GameRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class GameServiceImpl implements GameService {
	

	private final GameHoles gameHoles;
	private final GameRepository gameRepository;
	private final GoalRepository goalRepository;
	private final GameToGameDTO gameToGameDTO;


	@Override
	public Game findById(Long id) {
		Optional<Game> gameOptional = gameRepository.findById(id);

		if(!gameOptional.isPresent()){
			throw new RuntimeException("Game not found");
		}
		return gameOptional.get();
	}



	@Override
	public Game create(GameDTO dto) {

		Game returned = gameHoles.createGameWithDTO(dto);

		System.out.println("About to save");
		return gameRepository.save(returned);
	}

	@Override
	public GameDTO findLastGameByGoalName(String name) {

		Optional<Goal> goal = goalRepository.findByName(name);

		if (!goal.isPresent()) {
			return null;
		}

		List<Game> gameList = goal.get().getGames();
		Game lastGame = gameList.get(gameList.size() - 1);

		return gameToGameDTO.convert(lastGame);
	}
}
