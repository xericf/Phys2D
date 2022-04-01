package model.log;

import model.util.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.object.Ball;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoggerTest {
    EventLog el;


    @BeforeEach
    void runBefore() {
        el = EventLog.getInstance();
        Logger log = new Logger();
    }

    @Test
    void testLogAddBall() {
        Ball ball = new Ball(new Vector2(1f, 2f),
                             new Vector2(2f, 3f),
                             new Vector2(10f, 10f),
                             new Vector2(1, 1));
        Logger.logAddBall(ball);

        Event e = new Event("New ball has been added at: x = " + ball.getPosition().getX()
                + ", y = " + ball.getPosition().getY()
                + ", with an initial velocity of dx = " + ball.getVelocity().getX()
                + ", dy = " + ball.getVelocity().getY());

        Iterator<Event> iterator = el.iterator();
        assertTrue(iterator.next().getDescription().equals(e.getDescription()));

    }

    @Test
    void testLogRemoveBalls() {
        Logger.logRemoveBalls();

        Event e = new Event("All balls have been cleared");

        Iterator<Event> iterator = el.iterator();
        assertTrue(iterator.next().getDescription().equals(e.getDescription()));

    }

    @Test
    void testLogMoveBall() {
        Vector2 pointA = new Vector2(0, 0);
        Vector2 pointB = new Vector2(2, 3);
        float deltaTime = 3f;

        Logger.logMoveBall(pointA, pointB, deltaTime);

        Event e = new Event("Threw ball from starting point: x = " + pointA.getX() + ", y = " + pointA.getY()
                + ", to: x = " + pointB.getX() + ", y = " + pointB.getY()
                + ", in " + deltaTime + " seconds");

        Iterator<Event> iterator = el.iterator();
        assertTrue(iterator.next().getDescription().equals(e.getDescription()));

    }

    @Test
    void testPrintLog() {
        Event e = new Event("Something...");
        Date d = e.getDate();
        el.logEvent(e);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        Logger.printLog();

        assertEquals(d.toString() + "\n" +
                "Something...", output.toString().trim());
        System.setOut(System.out); // restore to original state
    }





}
