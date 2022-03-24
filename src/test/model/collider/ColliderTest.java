package model.collider;

public class ColliderTest {
/*
    @Test
    void testCheckInBorders() {

        ColliderRect rect = new ColliderRect(new Vector2(10, 10), 2, 2);

        Vector2 topLeft = new Vector2(0, 0);
        Vector2 bottomRight = new Vector2(20, 20);

        assertEquals(true, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));

        // left, on boundary and past
        rect = new ColliderRect(new Vector2(1, 10), 2, 2);
        assertEquals(true, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));
        rect = new ColliderRect(new Vector2(0, 10), 2, 2);
        assertEquals(false, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));

        // Right, on boundary and past
        rect = new ColliderRect(new Vector2(19, 10), 2, 2);
        assertEquals(true, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));
        rect = new ColliderRect(new Vector2(20, 10), 2, 2);
        assertEquals(false, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));

        // top, on boundary and past
        rect = new ColliderRect(new Vector2(10, 1), 2, 2);
        assertEquals(true, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));
        rect = new ColliderRect(new Vector2(10, 0), 2, 2);
        assertEquals(false, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));

        // bottom, on boundary and past
        rect = new ColliderRect(new Vector2(10, 19), 2, 2);
        assertEquals(true, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));
        rect = new ColliderRect(new Vector2(10, 20), 2, 2);
        assertEquals(false, Collider.calculateBorderInteraction(rect, topLeft, bottomRight));
    }

    @Test
    void testCalculateVelocityCollision() {

        Vector2 velocityBottomRight = new Vector2(5f, 5f);
        Vector2 velocityTopLeft = new Vector2(-5f, -5f);
        ColliderCircle circle = new ColliderCircle(new Vector2(2, 2), 2);
        Vector2 topLeft = new Vector2(0, 0);
        Vector2 bottomRight = new Vector2(10f, 10f);

        // Nothing happens first, as hit nothing
        Collider.calculateVelocityCollision(velocityBottomRight, circle, topLeft, bottomRight);
        assertTrue(velocityBottomRight.equals(new Vector2(5, 5)));
        velocityBottomRight = new Vector2(5f, 5f); // reset to original velocity

        // Hits vertical-borders, reverse x direction
        circle = new ColliderCircle(new Vector2(9, 5), 2);
        Collider.calculateVelocityCollision(velocityBottomRight, circle, topLeft, bottomRight);
        assertTrue(velocityBottomRight.equals(new Vector2(-5, 5)));
        velocityBottomRight = new Vector2(5f, 5f);

        circle = new ColliderCircle(new Vector2(1, 5), 2);
        Collider.calculateVelocityCollision(velocityTopLeft, circle, topLeft, bottomRight);
        assertTrue(velocityTopLeft.equals(new Vector2(5, -5)));
        velocityTopLeft = new Vector2(-5f, -5f);

        // Hits horizontal-borders, reverse y direction
        circle = new ColliderCircle(new Vector2(5, 9), 2);
        Collider.calculateVelocityCollision(velocityBottomRight, circle, topLeft, bottomRight);
        assertTrue(velocityBottomRight.equals(new Vector2(5, -5)));
        velocityBottomRight = new Vector2(5f, 5f);

        circle = new ColliderCircle(new Vector2(5, 1), 2);
        Collider.calculateVelocityCollision(velocityTopLeft, circle, topLeft, bottomRight);
        assertTrue(velocityTopLeft.equals(new Vector2(-5, 5)));
        velocityTopLeft = new Vector2(-5f, -5f);

    }*/
}
