package model.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateTest {

    private Coordinate coordinate;

    @BeforeEach
    void runBefore() {
        coordinate = new Coordinate(10, 11);
    }

    @Test
    void testConstructor() {
        assertEquals(10, coordinate.getX());
        assertEquals(11, coordinate.getY());
    }

}
