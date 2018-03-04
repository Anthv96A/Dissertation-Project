package com.example.Project.converters;

import com.example.Project.DTOs.GameDTO;
import com.example.Project.domain.Game;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GameToGameDTO {
    private final HoleToHoleDTO holeToHoleDTO;
    /**
     * @param game
     * @return GAMEDTO with thread safety Synchronised.
     */
    @Synchronized
    public GameDTO convert(Game game){
        if(game == null){
            return null;
        }
        final GameDTO gameDTO = new GameDTO();
        gameDTO.setName(game.getName());
        gameDTO.setDatePlayed(game.getDatePlayed().toString());
        if(game.getHoles() != null && game.getHoles().size() > 0){
            game.getHoles()
                    .forEach(hole -> gameDTO.getHoles().add(holeToHoleDTO.convert(hole)));
        }
        gameDTO.setPostEmotions(game.getPostEmotions());
        gameDTO.setPreEmotions(game.getPreEmotions());
        gameDTO.setTotalScore(game.getTotalScore());

        return gameDTO;
    }
}
