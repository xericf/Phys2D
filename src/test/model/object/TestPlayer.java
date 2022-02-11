package model.object;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.collider.ColliderRect;
import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayer {

    private Player body;

    @BeforeEach
    void runBefore() {
        body = new Player(new Vector2(1f, 2f),
                new Vector2(2f, 3f),
                new Vector2(10f, 10f));
    }

    @Test
    void testConstructor() throws IOException {
        assertTrue(body.getPosition().equals(new Vector2(1f, 2f)));
        assertTrue(body.getVelocity().equals(new Vector2(2f, 3f)));
        assertTrue(body.getForce().equals(new Vector2(10f, 10f)));

        ColliderRect rect = body.getCollider();
        assertTrue(rect.getCenter().equals(body.getPosition()));
        assertEquals(1f, rect.getHeight());
        assertEquals(1f, rect.getWidth());

        // for UI code coverage (as exempted from rubric)
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Screen screen = terminalFactory.createScreen();
        body.drawTerminal(screen);
    }

    @Test
    void testHandleInput() {
        char a = 'a';
        char d = 'd';

        body.handleInput(a);
        assertEquals(-Player.moveVelocity, body.getVelocity().getX());

        body.handleInput(d);
        assertEquals(Player.moveVelocity, body.getVelocity().getX());

    }

}
