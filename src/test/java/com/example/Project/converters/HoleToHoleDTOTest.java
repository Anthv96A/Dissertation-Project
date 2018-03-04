package com.example.Project.converters;

import com.example.Project.DTOs.HoleDTO;
import com.example.Project.domain.Hole;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HoleToHoleDTOTest {

    private static final Integer HOLE_NUMBER = 1;
    private static final Integer HOLE_SCORE = 5;
    private HoleToHoleDTO holeToHoleDTO;

    @Before
    public void setUp() throws Exception {
        this.holeToHoleDTO = new HoleToHoleDTO();
    }
    @Test
    public void testAssertNullConvert() throws Exception {
        assertNull(holeToHoleDTO.convert(null));
    }
    @Test
    public void testConvert() throws Exception {
        // Given
        Hole hole = new Hole();
        hole.setHoleNumber(HOLE_NUMBER);
        hole.setScore(HOLE_SCORE);

        // When
        HoleDTO holeDTO = holeToHoleDTO.convert(hole);

        // Then
        assertNotNull("Should not be null", holeDTO);
        assertEquals("Hole score should be 5", HOLE_SCORE, holeDTO.getScore());
        assertEquals("Hole number should be 1", HOLE_NUMBER, holeDTO.getHoleNumber());

    }

}