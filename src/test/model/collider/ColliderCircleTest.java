package model.collider;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.object.Ball;
import ui.object.Rect;

import static org.junit.jupiter.api.Assertions.*;

public class ColliderCircleTest {

    protected Ball ball1;
    protected ColliderCircle colliderCircle;

    protected Rect rect1;
    protected ColliderRect colliderRect;


    @BeforeEach
    void runBefore() {
        ball1 = new Ball(new Vector2(0f, 0f),
                        new Vector2(2f, 3f),
                        new Vector2(10f, 10f),
                        new Vector2(2, 2));

        colliderCircle = ball1.getCollider();

        rect1 = new Rect(new Vector2(2f, 2f),
                new Vector2(2f, 3f),
                new Vector2(10f, 10f),
                new Vector2(2, 2));

        colliderRect = rect1.getCollider();
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

        rect1.setPosition(new Vector2(2f, 0f));
        colliderPoints = colliderCircle.findCollision(colliderRect);
        assertEquals(1, colliderPoints.getIntersectDistance());
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2(1, 0)));

        rect1.setPosition(new Vector2(0f, 2f));
        colliderPoints = colliderCircle.findCollision(colliderRect);
        assertEquals(1, colliderPoints.getIntersectDistance());
        assertTrue(colliderPoints.getNormalSlope().equals(new Vector2(0, 1)));

    }

}
