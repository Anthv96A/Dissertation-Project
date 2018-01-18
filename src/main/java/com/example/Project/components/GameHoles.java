package com.example.Project.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
//	public Game createGame(Game game) {
//
//		Goal gameGoal = null;
//
//		boolean doesExist = goalRepository.existsByName(game.getGoals().get(0).getName());
//
//
//		System.out.println(doesExist);
//
//		if(doesExist){
//			Optional<Goal> goalOptional = goalRepository.findByName(game.getGoals().get(0).getName());
//
//			System.out.println(goalOptional.get());
//
//			if(goalOptional.isPresent()){
//				game.getGoals().add(goalOptional.get());
//				goalOptional.get().getGames().add(game);
//	//			goalRepository.save(goalOptional.get());
//			} else{
////				gameGoal = new Goal();
////				gameGoal.setName(game.getGoals().get(0).getName());
////				gameGoal.getGames().add(game);
////				goalRepository.save(gameGoal);
//			}
//
//		} else{
////			gameGoal = new Goal();
////			gameGoal.setName(game.getGoals().get(0).getName());
////			gameGoal.getGames().add(game);
////			game.getGoals().add(gameGoal);
////			goalRepository.save(gameGoal);
//		}
//
//
//
//		List<Hole> holes = new ArrayList<>();
//		Hole hole = new Hole();
//
//		for(Hole h: game.getHoles()) {
//			hole.setHoleNumber(h.getHoleNumber());
//			hole.setScore(h.getScore());
//			hole.setGame(game);
//			holes.add(hole);
//			hole = new Hole();
//		}
//
//		game.setHoles(holes);
//
//
////		for(Goal g: game.getGoals()){
////			if(doesExist){
////				gameGoal.getGames().add(game);
////
////			} else{
////				gameGoal.setName(g.getName());
////				gameGoal.getGames().add(game);
////			}
////
////			goalRepository.save(gameGoal);
////		}
//
//
////		List<Goal> goals = new ArrayList<>();
////		Optional<Goal> goal = null;
////
////		// Optional<Goal> goalOptional = goalRepository.findByName(game.getGoals().get(0).getName());
////	//	boolean doesExist = goalRepository.existsByName(game.getGoals().get(0).getName());
////
////		if(doesExist){
////			System.out.println("Present");
////			goal = goalRepository.findByName(game.getGoals().get(0).getName());
////			goal.get().getGames().add(newGame);
////			goalRepository.save(goal.get());
////
////		} else{
////			System.out.println("Not Present");
////			Goal newGoal = new Goal();
////			newGoal.getGames().add(newGame);
////			goalRepository.save(newGoal);
////		}
//
//
//
////		if(goalOptional.isPresent()){
////			System.out.println("Present");
////		} else{
////			System.out.println("Not Present");
////		}
//
////		List<Goal> goals = new ArrayList<>();
//
////		for(Goal g: game.getGoals()){
////			goal.setName(g.getName());
////			goals.add(goal);
////			goal = new Goal();
////		}
////
////		newGame.setGoals(goals);
//
//
//
//
////		return newGame;
//		return game;
//	}



	private Goal findGoal(){
		return null;
	}


	public Game createGameWithDTO(GameDTO game) {

		Game newGame = new Game();

		newGame.setName(game.getName());
		newGame.setPostEmotions(game.getPostEmotions());
		newGame.setPreEmotions(game.getPreEmotions());
		newGame.setTotalScore(game.getTotalScore());

//		List<Hole> holes = new ArrayList<>();
//		Hole hole = new Hole();
//
//		for(Hole h: game.getHoles()) {
//			hole.setHoleNumber(h.getHoleNumber());
//			hole.setScore(h.getScore());
//			hole.setGame(newGame);
//			holes.add(hole);
//			hole = new Hole();
//		}
//
//		newGame.setHoles(holes);


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
