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

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            World world = new World(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openWriter();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorld() {
        try {
            World world = new World(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight));
            JsonWriter writer = new JsonWriter("./data/testWriterDefaultConsoleWorld.json");
            writer.openWriter();
            writer.write(world);
            writer.closeWriter();

            world = JsonReader.readWorld("./data/testWriterDefaultConsoleWorld.json");
            assertTrue(world.getSize()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getTopLeft()
                    .equals(new Vector2(0, 0)));
            assertEquals(0, world.getWorldObjects().size());
            assertTrue( world.getGravityForce().equals(new Vector2(0, 9.81f)));

            Player player = world.getPlayer();
            assertTrue(player.getForce().equals(new Vector2(0, 0)));
            assertTrue(player.getVelocity().equals(new Vector2(0, 0)));
            assertTrue(player.getPosition().equals(new Vector2(1, 35)));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralWorld() {
        try {
            World world = makeGeneralWorld();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralConsoleWorld.json");
            writer.openWriter();
            writer.write(world);
            writer.closeWriter();

            assertTrue(world.getSize()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
            assertTrue(world.getBottomRight()
                    .equals(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight)));
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
            fail("Exception should not have been thrown");
        }
    }

    public static World makeGeneralWorld() {
        World world = new World(new Vector2(JuggleDemo.defaultWidth, JuggleDemo.defaultHeight));
        world.setPlayer(new Player(new Vector2(40, 35),
                new Vector2(25, 0),
                new Vector2(0, 0)));
        ArrayList<Ball> worldObjects = new ArrayList<>();
        worldObjects.add(new Ball(new Vector2(81, 12),
                new Vector2(5, -13),
                new Vector2(0, 0)));
        worldObjects.add(new Ball(new Vector2(52, 8),
                new Vector2(-6, 10),
                new Vector2(0, 0)));
        world.setWorldObjects(worldObjects);
        return world;
    }

}
