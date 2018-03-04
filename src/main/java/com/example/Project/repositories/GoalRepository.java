package com.example.Project.repositories;

import com.example.Project.domain.Goal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalRepository extends CrudRepository<Goal,Long> {

    boolean existsByName(String name);
    Optional<Goal> findByName(String name);
}
