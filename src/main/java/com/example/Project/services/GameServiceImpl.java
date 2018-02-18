package com.example.Project.services;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.converters.GameToGameDTO;
import com.example.Project.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Project.converters.GameDTOToGame;
import com.example.Project.domain.Game;
import com.example.Project.repositories.GameRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class GameServiceImpl implements GameService {
	

	private final GameDTOToGame gameDTOToGame;
	private final GameRepository gameRepository;
	private final GameToGameDTO gameToGameDTO;


	@Override
	public Game findById(Long id) {
		Optional<Game> gameOptional = gameRepository.findById(id);

		if(!gameOptional.isPresent()){
			throw new NotFoundException("Game not found");
		}
		return gameOptional.get();
	}


	@Override
	public Game create(GameDTO dto) {

		Game returned = gameDTOToGame.createGameWithDTO(dto);
		return gameRepository.save(returned);
	}

	@Override
	public GameDTO findLastGameByGoalName(String name) {

//		Optional<Goal> goal = goalRepository.findByName(name);
//
//		if (!goal.isPresent()) {
//			return null;
//		}
//
//		List<Game> gameList = goal.get().getGames();
//		Game lastGame = gameList.get(gameList.size() - 1);
//
//		GameDTO converted = gameToGameDTO.convert(lastGame);
//
//		if(converted == null){
//			return new GameDTO();
//		}
//
//		return converted;

		Optional<Game> gameOptional = gameRepository.getLastGameByGoal(name);

		if(!gameOptional.isPresent()){
			return new GameDTO();
		}

		return gameToGameDTO.convert(gameOptional.get());


	}

	@Override
	public List<GameDTO> findAllGamesWithinDatePeriod(String from, String to) {

		List<GameDTO> gameDTOList = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date dateFrom = formatter.parse(from);
			Date dateTo = formatter.parse(to);

			gameRepository.findGameByDatePlayedBetweenOrderByDatePlayedDesc(dateFrom,dateTo).forEach(game ->
					gameDTOList.add(gameToGameDTO.convert(game))
			);
		} catch (ParseException e){
			e.printStackTrace();
		} catch (RuntimeException e){
			e.printStackTrace();
		}

		return gameDTOList;
	}
}
