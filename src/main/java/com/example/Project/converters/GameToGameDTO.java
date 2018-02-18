package com.example.Project.converters;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class GameToGameDTO {


    @Synchronized
    public GameDTO convert(Game game){

        if(game == null){
            return null;
        }

        final GameDTO gameDTO = new GameDTO();
        gameDTO.setName(game.getName());
        gameDTO.setDatePlayed(game.getDatePlayed().toString());
        gameDTO.setHoles(game.getHoles());
        gameDTO.setPostEmotions(game.getPostEmotions());
        gameDTO.setPreEmotions(game.getPreEmotions());
        gameDTO.setTotalScore(game.getTotalScore());

        return gameDTO;
    }
}
