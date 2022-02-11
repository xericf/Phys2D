package model.collider;

import model.object.Circle;
import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestColliderRect {

    private ColliderRect collider;

    @BeforeEach
    void runBefore() {
        collider = new ColliderRect(new Vector2(1f, 2f), 3f, 4f);
    }

    @Test
    void testConstructor() {
        assertTrue(collider.getCenter().equals(new Vector2(1f, 2f)));
        assertEquals(3f, collider.getWidth());
        assertEquals(4f, collider.getHeight());
    }

}
