package com.example.Project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "game")
@Getter
@Setter
public class Game {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String name;

    @Temporal(TemporalType.DATE)
    private Date datePlayed;

    private Integer totalScore;
    @Lob
    private String preEmotions;
    @Lob
    private String postEmotions;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "game", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Hole> holes = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
	@JoinTable(
			name = "goal_game",
			joinColumns = @JoinColumn(name = "game_id"),
			inverseJoinColumns = @JoinColumn(name = "goal_id"))
	private List<Goal> goals = new ArrayList<>();

    /**
     *
     * @param hole
     * @return Game back, this is custom helper method which helps stop duplication of code
     *  and stops unnecessary code to achieve the same result.
     */
    public Game addHole(Hole hole){
        hole.setGame(this);
        this.holes.add(hole);
        return this;
    }



}
