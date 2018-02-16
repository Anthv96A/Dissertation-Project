package com.example.Project.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "goal")
@Getter
@Setter
@ToString
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "goals", cascade = CascadeType.REFRESH)
    private List<Game> games = new ArrayList<>();

    public Goal addGame(Game game){
        this.games.add(game);
        game.getGoals().add(this);
        return this;
    }
}
