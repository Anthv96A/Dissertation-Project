package com.example.Project.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Project.domain.Game;

import java.util.Optional;

public interface GameRepository extends CrudRepository<Game,Long> {

    Optional<Game> findById(Long id);

}
