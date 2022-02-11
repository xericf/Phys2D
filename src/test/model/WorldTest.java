package model;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.World;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {

    private World world;

    @BeforeEach
    void runBefore() {
        Vector2 size = new Vector2(50, 50);
        world = new World(size);
    }

    @Test
    void testConstructor() {


    }

}
