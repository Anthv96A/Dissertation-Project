package com.example.Project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "game")
@Getter
@Setter
public class Game {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String name;
    private LocalDate datePlayed;
    private Integer totalScore;
    @Lob
    private String preEmotions;
    @Lob
    private String postEmotions;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
//    @JsonBackReference
//    private List<Hole> holes = new ArrayList<>();

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

//    @ManyToMany
//    @JoinTable(
//            name = "goal_game",
//            joinColumns = @JoinColumn(name = "game_id"),
//            inverseJoinColumns = @JoinColumn(name = "goal_id"))
//    private List<Goal> goals = new ArrayList<>();



}
