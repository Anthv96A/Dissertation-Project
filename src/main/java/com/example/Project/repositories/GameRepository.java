package com.example.Project.repositories;

import com.example.Project.domain.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends CrudRepository<Game,Long> {

    Optional<Game> findById(Long id);

    @Query(value = "SELECT gl.name, count(g.id) AS Games FROM goal gl " +
            "INNER JOIN goal_game gg " +
            "ON gl.id = gg.goal_id " +
            "INNER JOIN game g " +
            "ON gg.game_id = g.id " +
            "GROUP BY gl.name " +
            "ORDER BY Games DESC " +
            "LIMIT 1"
            , nativeQuery = true)
    List<Object[]> calculateMostFrequentGoal();

    @Query(value = "SELECT gl.name, count(g.id) AS Games FROM goal gl " +
            "INNER JOIN goal_game gg " +
            "ON gl.id = gg.goal_id " +
            "INNER JOIN game g " +
            "ON gg.game_id = g.id " +
            "GROUP BY gl.name " +
            "ORDER BY Games ASC " +
            "LIMIT 1"
            , nativeQuery = true)
    List<Object[]> calculateLeastFrequentGoal();


    @Query(value = "SELECT gl.name, SUM(g.total_score) AS HighestScore from goal gl " +
            "INNER JOIN goal_game gg " +
            "ON gl.id = gg.goal_id " +
            "INNER JOIN game g " +
            "ON gg.game_id = g.id " +
            "GROUP BY gl.name " +
            "ORDER BY HighestScore DESC " +
            "LIMIT 1 ", nativeQuery = true)
    List<Object[]> calculateHighestScore();

    @Query(value = "SELECT gl.name, SUM(g.total_score) AS LowestScore from goal gl " +
            "INNER JOIN goal_game gg " +
            "ON gl.id = gg.goal_id " +
            "INNER JOIN game g " +
            "ON gg.game_id = g.id " +
            "GROUP BY gl.name " +
            "ORDER BY LowestScore ASC " +
            "LIMIT 1 ", nativeQuery = true)
    List<Object[]> calculateLowestScore();

}
