package ui.object;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRigidBody2D {

    private RigidBody2D body;

    @BeforeEach
    void runBefore() {
       body = new RigidBody2D(new Vector2(1f, 2f),
                            new Vector2(2f, 3f),
                            new Vector2(10f, 10f));
    }

    @Test
    void testConstructor() throws IOException {
        assertTrue(body.getPosition().equals(new Vector2(1f, 2f)));
        assertTrue(body.getVelocity().equals(new Vector2(2f, 3f)));
        assertTrue(body.getForce().equals(new Vector2(10f, 10f)));

        // for simple setForce coverage
        body.setForce(new Vector2(10f, 10f));

    }

    @Test
    void testTick() {

        assertTrue(body.getPosition().equals(new Vector2(1f, 2f)));
        assertTrue(body.getVelocity().equals(new Vector2(2f, 3f)));
        assertTrue(body.getForce().equals(new Vector2(10f, 10f)));

        float deltaTime = 1f;
        body.tick(deltaTime);

        assertTrue(body.getPosition().equals(new Vector2(1f + (2f * deltaTime + 0.5f * 10f * deltaTime * deltaTime),
                2f + (3f * deltaTime + 0.5f * 10f * deltaTime * deltaTime))));
        assertTrue(body.getVelocity().equals(new Vector2(12f, 13f)));
        assertTrue(body.getForce().equals(new Vector2(0f, 0f)));
    }

}
