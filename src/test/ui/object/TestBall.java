package ui.object;

import model.collider.ColliderCircle;
import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBall {

    private Ball body;

    @BeforeEach
    void runBefore() {
        body = new Ball(new Vector2(1f, 2f),
                new Vector2(2f, 3f),
                new Vector2(10f, 10f),
                new Vector2(1, 1));
    }

    @Test
    void testConstructor() {
        assertTrue(body.getPosition().equals(new Vector2(1f, 2f)));
        assertTrue(body.getVelocity().equals(new Vector2(2f, 3f)));
        assertTrue(body.getForce().equals(new Vector2(10f, 10f)));

        ColliderCircle circle = body.getCollider();
        assertTrue(circle.getCenter().equals(body.getPosition()));
        assertEquals(0.5f, circle.getRadius());


    }


}
