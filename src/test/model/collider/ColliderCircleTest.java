//package model.collider;
//
//import model.util.Vector2;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.object.Ball;
//import ui.object.Rect;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ColliderCircleTest {
//
//    protected Ball ball1;
//    protected ColliderCircle colliderCircle;
//
//    protected Rect rect1;
//    protected ColliderRect colliderRect;
//
//
//    @BeforeEach
//    void runBefore() {
//        ball1 = new Ball(new Vector2(0f, 0f),
//                        new Vector2(1f, 1f),
//                        new Vector2(10f, 10f),
//                        new Vector2(2, 2));
//
//        colliderCircle = ball1.getCollider();
//
//        rect1 = new Rect(new Vector2(2f, 2f),
//                new Vector2(2f, 3f),
//                new Vector2(10f, 10f),
//                new Vector2(2, 2));
//
//        colliderRect = rect1.getCollider();
//    }
//
//    @Test
//    void testConstructor() {
//        assertTrue(colliderCircle.getCenter().equals(new Vector2(0f, 0f)));
//        assertEquals(1f, colliderCircle.getRadius());
//    }
//
//    @Test
//    void testFindCollisionCircleRectNotFound() {
//
//        ColliderPoints colliderPoints = colliderCircle.findCollision(colliderRect);
//        assertNull(colliderPoints);
//
//    }
//
//    @Test
//    void testFindCollisionCircleRectFound() {
//        ColliderPoints colliderPoints;
//
//        rect1.setPosition(new Vector2(2f, 0f));
//        colliderPoints = colliderCircle.findCollision(colliderRect);
//        assertEquals(1, colliderPoints.getDistance());
//        assertTrue(colliderPoints.getNormal().equals(new Vector2(1, 0)));
//
//        rect1.setPosition(new Vector2(0f, 2f));
//        colliderPoints = colliderCircle.findCollision(colliderRect);
//        assertEquals(1, colliderPoints.getDistance());
//        assertTrue(colliderPoints.getNormal().equals(new Vector2(0, 1)));
//
//        rect1.setPosition(new Vector2(0f, -2f));
//        colliderPoints = colliderCircle.findCollision(colliderRect);
//        assertEquals(1, colliderPoints.getDistance());
//        assertTrue(colliderPoints.getNormal().equals(new Vector2(0, -1)));
//
//    }
//
//    @Test
//    void testFindCollisionCircleCircleNotFound() {
//
//        Ball ball2 = new Ball(new Vector2(2f, 2f),
//                                    new Vector2(1f, 1f),
//                                    new Vector2(10f, 10f),
//                                    new Vector2(2, 2));
//        ColliderCircle colliderCircle2 = ball2.getCollider();
//
//        ColliderPoints colliderPoints = colliderCircle.findCollision(colliderCircle2);
//        assertNull(colliderPoints);
//
//    }
//
//    @Test
//    void testFindCollisionCircleCircleFound() {
//        Ball ball2 = new Ball(new Vector2(0f, 1f),
//                new Vector2(1f, 1f),
//                new Vector2(10f, 10f),
//                new Vector2(2, 2));
//        ColliderCircle colliderCircle2 = ball2.getCollider();
//
//        ColliderPoints colliderPoints = colliderCircle.findCollision(colliderCircle2);
//        assertEquals(1, colliderPoints.getDistance());
//        assertTrue(colliderPoints.getNormal().equals(new Vector2(0, 1)));
//    }
//
//    @Test
//    void testBorderInteraction() {
//        Vector2 topLeft = new Vector2(0, 0);
//        Vector2 botRight = new Vector2(10, 10);
//        Vector2 interaction;
//        // Hit top, bot
//        ball1.setVelocity(new Vector2(1f, 1f));
//        ball1.setPosition(new Vector2(2, 0.9f));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1, 1)));
//
//        ball1.setVelocity(new Vector2(1f, -1f));
//        ball1.setPosition(new Vector2(2, 0.9f));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1, 1)));
//
//        ball1.setVelocity(new Vector2(1f, 1f));
//        ball1.setPosition(new Vector2(2, 9.1f));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1, -1)));
//
//        ball1.setVelocity(new Vector2(1f, -1f));
//        ball1.setPosition(new Vector2(2, 9.1f));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1, -1)));
//
//        // hit right, left
//        ball1.setVelocity(new Vector2(1f, 1f));
//        ball1.setPosition(new Vector2(9.1f, 2));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(-1f, 1f)));
//
//        ball1.setVelocity(new Vector2(-1f, 1f));
//        ball1.setPosition(new Vector2(9.1f, 2));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(-1f, 1f)));
//
//        ball1.setVelocity(new Vector2(1f, 1f));
//        ball1.setPosition(new Vector2(0.9f, 2));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1f, 1f)));
//
//        ball1.setVelocity(new Vector2(-1f, 1f));
//        ball1.setPosition(new Vector2(0.9f, 2));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1f, 1f)));
//
//        // Hit nothing
//        ball1.setVelocity(new Vector2(1f, 1f));
//        ball1.setPosition(new Vector2(2, 2));
//        interaction = colliderCircle.calculateBorderInteraction(topLeft, botRight);
//        assertTrue(interaction.equals(new Vector2(1, 1)));
//    }
//
//}
