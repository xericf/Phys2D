package model.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    }

}