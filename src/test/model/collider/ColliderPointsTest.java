package model.collider;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColliderPointsTest {

    ColliderPoints colliderPoints;
    Vector2 pointA;
    Vector2 pointB;

    @BeforeEach
    void runBefore() {
        pointA = new Vector2(0, 0);
        pointB = new Vector2(4, 3);
        colliderPoints = new ColliderPoints(pointA, pointB);
    }

    @Test
    void testConstructor() {
        assertTrue(colliderPoints.getPointA().equals(pointA));
        assertTrue(colliderPoints.getPointB().equals(pointB));
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2( 0.8f, 0.6f)));
        assertEquals(5, colliderPoints.getIntersectDistance());
        assertTrue(colliderPoints.isColliding());
    }

    @Test
    void testConstructorIntersectZero() {
        pointB = new Vector2(0, 0);
        colliderPoints = new ColliderPoints(pointA, pointB);
        assertTrue(colliderPoints.getPointA().equals(pointA));
        assertTrue(colliderPoints.getPointB().equals(pointB));
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2( 0, 0)));
        assertEquals(0, colliderPoints.getIntersectDistance());
        assertFalse(colliderPoints.isColliding());
    }


}
