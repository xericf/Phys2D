package persistence;

import model.util.Vector2;
import org.junit.jupiter.api.Test;
import ui.demo.JuggleDemo;
import ui.object.Ball;
import ui.object.Player;
import ui.object.World;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    public static final String testPathDefault = "./data/testReaderDefaultJuggleWorld.json";
    public static final String testPathGeneral = "./data/testReaderGeneralJuggleWorld.json";
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
            World world = JsonReader.readWorld(testPathDefault);
            assertTrue(world.getSize()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getTopLeft()
                    .equals(new Vector2(0, 0)));
            assertEquals(0, world.getWorldObjects().size());
            assertTrue( world.getGravityForce().equals(new Vector2(0, 65f)));

            Player player = world.getPlayer();
            assertTrue(player.getForce().equals(new Vector2(0, 0)));
            assertTrue(player.getVelocity().equals(new Vector2(0, 0)));
            assertTrue(player.getPosition().equals(new Vector2(150, 670)));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorld() {
        try {
            World world = JsonReader.readWorld(testPathGeneral);
            assertTrue(world.getSize()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getTopLeft()
                    .equals(new Vector2(0, 0)));
            assertTrue( world.getGravityForce().equals(new Vector2(0, 65f)));

            Player player = world.getPlayer();
            assertTrue(player.getForce().equals(new Vector2(0, 0)));
            assertTrue(player.getVelocity().equals(new Vector2(0, 0)));
            assertTrue(player.getPosition().equals(new Vector2(150, 670)));

            ArrayList<Ball> worldObjects = world.getWorldObjects();
            assertEquals(1, worldObjects.size());

            Ball ball1 = worldObjects.get(0);
            assertTrue(ball1.getForce().equals(new Vector2(0, 0)));
            assertTrue(ball1.getVelocity().equals(new Vector2(-4, 107)));
            assertTrue(ball1.getPosition().equals(new Vector2(646, 425)));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
