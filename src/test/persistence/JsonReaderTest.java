package persistence;

import model.util.Vector2;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import ui.ConsoleDemo;
import ui.World;
import ui.object.Ball;
import ui.object.Player;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderWorldFileDoesNotExist() {
        try {
            JsonReader jsonReader = new JsonReader();
            World world = JsonReader.readWorld("./data/FILENOTFOUND.json");
            fail("IOException was expected, but not thrown");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderDefaultWorld() {

        try {
            World world = JsonReader.readWorld("./data/testReaderDefaultConsoleWorld.json");
            assertTrue(world.getSize()
                    .equals(new Vector2(ConsoleDemo.defaultColumns, ConsoleDemo.defaultRows)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(ConsoleDemo.defaultColumns, ConsoleDemo.defaultRows)));
            assertTrue(world.getTopLeft()
                    .equals(new Vector2(0, 0)));
            assertEquals(0, world.getWorldObjects().size());
            assertTrue( world.getGravityForce().equals(new Vector2(0, 9.81f)));

            Player player = world.getPlayer();
            assertTrue(player.getForce().equals(new Vector2(0, 0)));
            assertTrue(player.getVelocity().equals(new Vector2(0, 0)));
            assertTrue(player.getPosition().equals(new Vector2(1, 35)));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorld() {
        try {
            World world = JsonReader.readWorld("./data/testReaderGeneralConsoleWorld.json");
            assertTrue(world.getSize()
                    .equals(new Vector2(ConsoleDemo.defaultColumns, ConsoleDemo.defaultRows)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(ConsoleDemo.defaultColumns, ConsoleDemo.defaultRows)));
            assertTrue(world.getTopLeft()
                    .equals(new Vector2(0, 0)));
            assertTrue( world.getGravityForce().equals(new Vector2(0, 9.81f)));

            Player player = world.getPlayer();
            assertTrue(player.getForce().equals(new Vector2(0, 0)));
            assertTrue(player.getVelocity().equals(new Vector2(25, 0)));
            assertTrue(player.getPosition().equals(new Vector2(40, 35)));

            ArrayList<Ball> worldObjects = world.getWorldObjects();
            assertEquals(2, worldObjects.size());

            Ball ball1 = worldObjects.get(0);
            assertTrue(ball1.getForce().equals(new Vector2(0, 0)));
            assertTrue(ball1.getVelocity().equals(new Vector2(5, -13)));
            assertTrue(ball1.getPosition().equals(new Vector2(81, 12)));

            Ball ball2 = worldObjects.get(1);
            assertTrue(ball2.getForce().equals(new Vector2(0, 0)));
            assertTrue(ball2.getVelocity().equals(new Vector2(-6, 10)));
            assertTrue(ball2.getPosition().equals(new Vector2(52, 8)));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
