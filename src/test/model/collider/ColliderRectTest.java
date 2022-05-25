package model.collider;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.object.Ball;
import ui.object.Rect;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColliderRectTest {


    protected Ball ball1;
    protected ColliderCircle colliderCircle;

    protected Rect rect1;
    protected ColliderRect colliderRect;


    @BeforeEach
    void runBefore() {
        ball1 = new Ball(new Vector2(0f, 0f),
                new Vector2(1f, 1f),
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
        assertTrue(colliderRect.getCenter().equals(new Vector2(2f, 2f)));
        assertEquals(2, colliderRect.getHeight());
        assertEquals(2, colliderRect.getWidth());
    }

    @Test
    void testFindCollisionRectRectNotFound() {
        Rect rect2 = new Rect(new Vector2(0f, 0f),
                new Vector2(2f, 3f),
                new Vector2(10f, 10f),
                new Vector2(2, 2));
        ColliderRect colliderRect2 = rect2.getCollider();
        ColliderPoints colliderPoints = colliderRect.findCollision(colliderRect2);
        assertNull(colliderPoints);

    }


    @Test
    void testFindCollisionRectCircleNotFound() {
        ColliderPoints colliderPoints = colliderRect.findCollision(colliderCircle);
        assertNull(colliderPoints);
    }

    @Test
    void testFindCollisionRectCircleFound() {
        ColliderPoints colliderPoints;

        rect1.setPosition(new Vector2(2f, 0f));
        colliderPoints = colliderRect.findCollision(colliderCircle);
        assertEquals(1, colliderPoints.getDistance());
        assertTrue(colliderPoints.getNormal().equals(new Vector2(-1, 0)));

        rect1.setPosition(new Vector2(0f, 2f));
        colliderPoints = colliderRect.findCollision(colliderCircle);
        assertEquals(1, colliderPoints.getDistance());
        assertTrue(colliderPoints.getNormal().equals(new Vector2(0, -1)));

        rect1.setPosition(new Vector2(0f, -2f));
        colliderPoints = colliderRect.findCollision(colliderCircle);
        assertEquals(1, colliderPoints.getDistance());
        assertTrue(colliderPoints.getNormal().equals(new Vector2(0, 1)));
    }

    @Test
    void testBorderInteraction() {
        Vector2 topLeft = new Vector2(0, 0);
        Vector2 botRight = new Vector2(10, 10);
        Vector2 interaction;

        // TOP, bot
        rect1.setPosition(new Vector2(2, 0.9f));
        rect1.setVelocity(new Vector2(1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, 1)));

        rect1.setPosition(new Vector2(2, 0.9f));
        rect1.setVelocity(new Vector2(1, -1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, 1)));

        rect1.setPosition(new Vector2(2, 9.9f));
        rect1.setVelocity(new Vector2(1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, -1)));

        rect1.setPosition(new Vector2(2, 9.9f));
        rect1.setVelocity(new Vector2(1, -1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, -1)));

        // Left, right

        rect1.setPosition(new Vector2(9.9f, 2f));
        rect1.setVelocity(new Vector2(-1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(-1, 1)));

        rect1.setPosition(new Vector2(9.9f, 2f));
        rect1.setVelocity(new Vector2(1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(-1, 1)));

        rect1.setPosition(new Vector2(0.9f, 2f));
        rect1.setVelocity(new Vector2(-1f, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, 1)));

        rect1.setPosition(new Vector2(0.9f, 2f));
        rect1.setVelocity(new Vector2(1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, 1)));

        // nothing
        rect1.setPosition(new Vector2(2, 2));
        rect1.setVelocity(new Vector2(1, 1));
        interaction = colliderRect.calculateBorderInteraction(topLeft, botRight);
        assertTrue(interaction.equals(new Vector2(1, 1)));
    }

}
