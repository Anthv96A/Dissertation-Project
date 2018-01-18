package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GoalRepository extends CrudRepository<Goal,Long> {

    boolean existsByName(String name);

    Optional<Goal> findByName(String name);

 //   Optional<Goal> findGoalByNameAndGame

//    Optional<Goal> findByTopOrderByGamesAsc(String name);

//    @Query("SELECT * FROM Goal g WHERE g.name = :name")
//    Optional<Goal> getLastGame(@Param("name") String name);

//    @Query("SELECT g.name, Count() FROM goal g JOIN g.")
//    int count(@Param("name") String name);

   // int findByNameAndCountGames(String name);

 //    int countByGames();

}
