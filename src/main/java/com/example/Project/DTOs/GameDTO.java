package com.example.Project.DTOs;

import com.example.Project.domain.Goal;
import com.example.Project.domain.Hole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class GameDTO {


    private String name;
    private String datePlayed;
    private Integer totalScore;
    private String preEmotions;
    private String postEmotions;

    private List<HoleDTO> holes = new ArrayList<>();

    private List<Goal> goals = new ArrayList<>();


}
