package com.example.Project.converters;

import com.example.Project.DTOs.HoleDTO;
import com.example.Project.domain.Hole;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class HoleToHoleDTO {

    /**
     *
     * @param hole
     * @return HOLEDTO with thread safety Synchronised.
     */

    @Synchronized
    public HoleDTO convert(Hole hole){

        if(hole == null){
            return null;
        }
        final HoleDTO holeDTO = new HoleDTO();
        holeDTO.setHoleNumber(hole.getHoleNumber());
        holeDTO.setScore(hole.getScore());

        return holeDTO;
    }
}
