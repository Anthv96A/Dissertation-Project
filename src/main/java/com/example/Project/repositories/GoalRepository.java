package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GoalRepository extends CrudRepository<Goal,Long> {

    boolean existsByName(String name);

    Optional<Goal> findByName(String name);

//    @Query(value = "SELECT * FROM goal g " +
//            "INNER JOIN goal_game gg ON g.id = gg.goal_id" +
//            "INNER JOIN game gm ON gg.game_id = gm.id" +
//            "WHERE g.name = : name ", nativeQuery = true)
//    Optional<Goal> getLastGameByGoal(@Param("name") String name);





}
