package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GoalRepository extends CrudRepository<Goal,Long> {

    boolean existsByName(String name);

    Optional<Goal> findByName(String name);


}
