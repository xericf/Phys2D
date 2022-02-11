package ui;


import com.googlecode.lanterna.screen.Screen;
import model.collider.Collider;
import model.object.Circle;
import model.object.Player;
import model.object.RigidBody2D;
import model.util.Vector2;

import java.util.ArrayList;

// Represents a physical world
public class World {

    private ArrayList<Circle> worldObjects;
    private Player player = null;
    private Vector2 gravityForce;
    private Vector2 size;

    private Vector2 topLeft;
    private Vector2 bottomRight;

    // Creates a world
    // EFFECTS: Constructor for a simulator for a physical world of a certain size (in meters).
    // Also sets up a bounding box for collision detection, and creates new list for physical objects,
    // defines gravity force with a default of g = 9.81 m/s^2 down, and initializes a player object
    // at point (1, 35) on the terminal.
    public World(Vector2 size) {
        this.size = size;
        this.topLeft = new Vector2(0, 0); // origin point
        this.bottomRight = new Vector2(size.getX(), size.getY()); // screen height, screen width

        worldObjects = new ArrayList<Circle>();
        gravityForce = new Vector2(0, 9.81f);

        // Change this in the future for games that don't need players
        player = new Player(new Vector2(1, 35),
                            new Vector2(0, 0),
                            new Vector2(0, 0));
    }

    // Updates the world every tick
    // MODIFIES: this
    // EFFECTS: updates player (if defined), and every physics object in the world
    // based on a certain amount of time (deltaTime).
    public void tick(float deltaTime) {

        checkCollisions();

        if (player != null) {
            player.tick(deltaTime);
        }

        for (Circle object : worldObjects) {
            object.setForce(gravityForce);
            object.tick(deltaTime);
        }
    }

    // Checks for player and every physical object in the world collisions
    // MODIFIES: this
    // EFFECTS: inverts x or y velocity of any physical object if it has
    // collided with the border.
    public void checkCollisions() {

        if (player != null && player.getCollider() != null) {
            boolean withinBorders = Collider.checkInBorders(player.getCollider(), topLeft, bottomRight);

            if (withinBorders == false) {
                player.getVelocity().multiply(Vector2.invertX);
            }
        }

        for (Circle object1 : worldObjects) {
            Collider.calculateVelocityCollision(object1.getVelocity(),
                                                object1.getCollider(),
                                                topLeft, bottomRight);

        }

    }

    // Provides pressing certain keys with functionality
    // MODIFIES: this
    // EFFECTS: Spawns new Circle physics object on given keycode, forwards keyCode into
    // player for additional player-specific checks
    public void handleInput(Character c) {

        char keyValue = c.charValue();
        if (keyValue == 't') {
            // make it spawn at mouse position too if mouse is on screen
            Circle circle = new Circle(new Vector2(60, 3),
                    new Vector2((float) Math.random() * 22 - 11, 0),
                    new Vector2(0, 0));

            worldObjects.add(circle);
        } else if (keyValue == 'f') {
            worldObjects.clear();
        }

        player.handleInput(c);
    }

    // draws the world on the terminal
    // MODIFIES: screen
    // EFFECTS: Draws all physical objects and the player on the terminal screen.
    public void drawTerminal(Screen screen) {

        if (player != null) {
            player.drawTerminal(screen);
        }

        for (Circle object : worldObjects) {
            object.drawTerminal(screen);
        }

    }

    public void setGravityForce(Vector2 newGravity) {
        gravityForce.set(newGravity);
    }

    public ArrayList<Circle> getWorldObjects() {
        return worldObjects;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getGravityForce() {
        return gravityForce;
    }

    public Player getPlayer() {
        return player;
    }

    // Nanoseconds to seconds
    // EFFECTS: Converts a long Nano seconds value into a float seconds value
    public static float convertNanoToSeconds(long nanoSecs) {
        return (float) nanoSecs / 1000000000;
    }


}
