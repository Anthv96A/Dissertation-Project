package com.example.Project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Hole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer holeNumber;
	
//	@ManyToOne(cascade = CascadeType.PERSIST)
//	private Game game;

	@ManyToOne
	@JsonBackReference
	private Game game;

	private Integer score;

}
