package ui;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.object.Ball;
import ui.object.Player;
import ui.object.World;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldTest {

    private World world;

    @BeforeEach
    void runBefore() {
        Vector2 size = new Vector2(50, 50);
        world = new World(size);
    }

    @Test
    void testConstructor() throws IOException {
        assertTrue(world.getSize().equals(new Vector2(50, 50)));
        Player player = world.getPlayer();

        assertTrue(player.getPosition().equals(new Vector2(1, 35)));
        assertTrue(player.getVelocity().equals(new Vector2(0, 0)));
        assertTrue(player.getForce().equals(new Vector2(0, 0)));

        assertTrue(world.getBottomRight().equals(new Vector2(50, 50)));
        assertTrue(world.getTopLeft().equals(new Vector2(0, 0)));

        assertTrue(world.getGravityForce().equals(new Vector2(0, 9.81f)));

        assertTrue(world.getWorldObjects().isEmpty());

    }

    @Test
    void testTick() {
        // Functionality is directly related to the UI, not tested
    }

    @Test
    void testCheckCollisions() {
        Vector2 vp = world.getPlayer().getPosition();
        Vector2 vv = world.getPlayer().getVelocity();
        vv.setX(-2f);
        vp.setX(0f);

        world.checkCollisions();

        assertEquals(2f, vv.getX());
        assertEquals(0f, vv.getY());
        assertEquals(0f, vp.getX());
        assertEquals(35f, vp.getY());

    }



    @Test
    void testHandleInput() {
        // Testing handle input because at its core it has basic functionality
        // based on certain char codes from 'keys', it is not actually the function
        // that polls for input.

        world.handleInput('t');
        assertEquals(1, world.getWorldObjects().size());
        assertTrue(world.getWorldObjects().get(0) instanceof Ball);

        world.handleInput('f');
        assertEquals(0, world.getWorldObjects().size());

        // nothing happens on v
        world.handleInput('v');

        // other functionality is directly related with the UI, not tested
    }

    @Test
    void testConvertNanoSeconds() {
        assertEquals(1, World.convertNanoToSeconds((long) Math.pow(10, 9)));
    }

}

