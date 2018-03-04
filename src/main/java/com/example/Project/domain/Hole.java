package com.example.Project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "hole")
@Getter
@Setter
@ToString
public class Hole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer holeNumber;

	@ManyToOne
	@JsonBackReference
	private Game game;

	private Integer score;
}
