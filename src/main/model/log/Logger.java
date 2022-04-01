package model.log;

import model.util.Vector2;
import ui.object.Ball;

// Logger class to log various events and information
public class Logger {

    // EFFECTS: Adds a new event to the EventLog based on an added ball
    public static void logAddBall(Ball ball) {
        logEvent("New ball has been added at: x = " + ball.getPosition().getX()
                + ", y = " + ball.getPosition().getY()
                + ", with an initial velocity of dx = " + ball.getVelocity().getX()
                + ", dy = " + ball.getVelocity().getY());
    }

    // EFFECTS: Adds a new event to the EventLog of all balls being removed
    public static void logRemoveBalls() {
        logEvent("All balls have been cleared");
    }

    // EFFECTS: Adds a new event to the EventLog of an existing ball being moved from pointA to pointB
    public static void logMoveBall(Vector2 pointA, Vector2 pointB, float deltaTime) {
        logEvent("Threw ball from starting point: x = " + pointA.getX() + ", y = " + pointA.getY()
                        + ", to: x = " + pointB.getX() + ", y = " + pointB.getY()
                        + ", in " + deltaTime + " seconds");
    }

    private static void logEvent(String description) {
        EventLog log = EventLog.getInstance();
        Event event = new Event(description);
        log.logEvent(event);
    }

    // EFFECTS: Prints all the descriptions of the events found within the EventLog
    public static void printLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString() + "\n");
        }
    }

}
