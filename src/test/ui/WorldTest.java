package ui;

import model.object.Player;
import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.World;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
/*
public class WorldTest {

    private World world;

    @BeforeEach
    void runBefore() {
        Vector2 size = new Vector2(50, 50);
        world = new World(size);
    }

    @Test
    void testConstructor() {
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

    }

}
 */
