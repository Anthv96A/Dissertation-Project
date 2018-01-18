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
// THIS WORKS
	@Transactional
	@Override
	public Game createOrUpdate(Game obj) {

		// Todo
		// Figure out tomorrow the correct hibernate mapping
		// It doesn't quite work on the goals, but works fine on the holes

		for(Hole h: obj.getHoles()){
			h.setGame(obj);
		}

//		for(Goal g: obj.getGoals()){
//			Optional<Goal> goalOptional = goalRepository.findByName(g.getName());
//
//			if(goalOptional.isPresent()){
//				obj.getGoals().add(goalOptional.get());
//			}
//		}


		for(Iterator<Goal> g = obj.getGoals().iterator(); g.hasNext();){

			Goal goal = g.next();
			Optional<Goal> goalOptional = goalRepository.findByName(goal.getName());

			if(goalOptional.isPresent()){
				g.remove();
				obj.getGoals().add(goal);
			}

		}

//		for(Iterator<Goal> g = obj.getGoals().iterator(); g.hasNext();){
//
//			Goal goal = g.next();
//
//			if(goalRepository.existsByName(goal.getName())){
//				g.remove();
//				goal.getGames().add(obj);
//				obj.getGoals().add(goal);
//			}
//
//		}

//		System.out.println("We are here");
//
//
//		for(Goal g: obj.getGoals()){
//			g.getGames().add(obj);
//			obj.getGoals().add(g);
//		}


		System.out.println("About to save");
		return gameRepository.save(obj);
	}

//	@Transactional
//	@Override
//	public Game createOrUpdate(Game obj) {
//		Game returned = gameHoles.createGame(obj);
////		List<Game> games = new ArrayList<>();
////		Goal saved = null;
//
////		for(Goal g: returned.getGoals()){
////
////			Optional<Goal> goal = goalRepository.findByName(g.getName());
////
////			if(!goal.isPresent()){
////
////			//	saved = goalRepository.save(g);
////				games.add(returned);
////				saved = new Goal();
////				saved.setGames(games);
////				goalRepository.save(saved);
////
////			} else{
////				saved = goal.get();
////				games.add(returned);
////				saved.setGames(games);
////				goalRepository.save(saved);
////			}
////
////			System.out.println("Does exist? " + goal.toString());
////
////
////		}
//
//
//
//
//
//		System.out.println("About to save");
//		return gameRepository.save(returned);
//	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Game create(GameDTO dto) {

		Game returned = gameHoles.createGameWithDTO(dto);

		System.out.println("About to save");
		return gameRepository.save(returned);
//		return null;
	}

	@Override
	public GameDTO findLastGameByGoalName(String name) {

		Optional<Goal> goal = goalRepository.findByName(name);

		if (!goal.isPresent()) {
			throw new RuntimeException("Not found");
		}

		List<Game> gameList = goal.get().getGames();
		Game lastGame = gameList.get(gameList.size() - 1);

		return gameToGameDTO.convert(lastGame);
	}
}
