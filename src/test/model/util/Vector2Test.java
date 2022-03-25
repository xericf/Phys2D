package model.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2Test {

    private Vector2 vec;

    @BeforeEach
    void runBefore() {
        vec = new Vector2(14f, 12f);
    }

    @Test
    void testConstructor() {
        assertEquals(14f, vec.getX());
        assertEquals(12f, vec.getY());

        vec.setX(14f);
        vec.setY(12f);
    }

    @Test
    void testStaticMultiply() {
        Vector2 product = Vector2.multiply(vec, 2f);

        // product object right solution
        assertEquals(28f, product.getX());
        assertEquals(24f, product.getY());

        // check vec object is not mutated
        assertEquals(14f, vec.getX());
        assertEquals(12f, vec.getY());

        Vector2 vec2 = new Vector2(2f, 2f);
        product = Vector2.multiply(vec2, new Vector2(3, 4));
        assertTrue(product.equals(new Vector2(6, 8)));
    }

    @Test
    void testMultiply() {

        // single float multiplication
        vec.multiply(2f);
        assertEquals(28f, vec.getX());
        assertEquals(24f, vec.getY());

        vec.multiply(new Vector2(2f, 2f));
        assertEquals(56f, vec.getX());
        assertEquals(48f, vec.getY());

    }

    @Test
    void testAdd() {

        vec.add(new Vector2(1f, 1f));
        assertEquals(15f, vec.getX());
        assertEquals(13f, vec.getY());

    }

    @Test
    void testEquals() {

        assertTrue(vec.equals(new Vector2(14f, 12f)));

        vec.set(new Vector2(2f, 2f));
        vec.setX(2f);
        vec.setY(2f);

        assertFalse(vec.equals(new Vector2(1f, 1f)));
        assertFalse(vec.equals(new Vector2(2f, 1f)));
    }

    @Test
    void testHypotenuse() {
        Vector2 a = new Vector2(0f, 0f);
        Vector2 b = new Vector2(3, 4);

        assertEquals(5, Vector2.calculateHypotenuse(a, b));


    }

}
