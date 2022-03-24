package model.collider;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColliderCircleTest {

    private ColliderCircle colliderCircle;
    private ColliderRect colliderRect;

    @BeforeEach
    void runBefore() {
        colliderCircle = new ColliderCircle(new Vector2(0f, 0f), 1f);
        colliderRect = new ColliderRect(new Vector2(2,2), 2, 2);
    }

    @Test
    void testConstructor() {
        assertTrue(colliderCircle.getCenter().equals(new Vector2(0f, 0f)));
        assertEquals(1f, colliderCircle.getRadius());
    }

    @Test
    void testFindCollisionCircleRectNotFound() {

        ColliderPoints colliderPoints = colliderCircle.findCollision(colliderRect);
        assertNull(colliderPoints);

    }

    @Test
    void testFindCollisionCircleRectFound() {
        ColliderPoints colliderPoints;

        colliderRect.setCenter(new Vector2(2f, 0f));
        colliderPoints = colliderCircle.findCollision(colliderRect);
        assertEquals(1, colliderPoints.getIntersectDistance());
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2(1, 0)));


        colliderRect.setCenter(new Vector2(0f, 2f));
        colliderPoints = colliderCircle.findCollision(colliderRect);
        assertEquals(1, colliderPoints.getIntersectDistance());
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2(0, 1)));


    }

}
