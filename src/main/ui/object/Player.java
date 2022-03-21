package ui.object;

import model.collider.ColliderRect;
import model.util.Vector2;

import java.awt.*;

// Represents a physical object that could be controlled by the user
public class Player extends RigidBody2D {

    public static final float moveVelocity = 150f;
    private float width = 1f;
    private float height = 1f;

    // Player contains a rectangle collider
    private ColliderRect collider;

    // Creates a Player
    // EFFECTS: Creates a player based on the RigidBody2D class and Initializes a
    // ColliderRect to give player rectangular collision detection
    public Player(Vector2 position, Vector2 velocity, Vector2 force) {
        super(position, velocity, force);

        collider = new ColliderRect(position, width, height);
    }

    // Provides pressing certain keys with player-specific functionality
    // MODIFIES: this
    // EFFECTS: Allows for player to change velocity to move to the left or right
    // based on key press
    public void handleInput(int key) {

        switch (key) {
            case 68: // d
                velocity.setX(moveVelocity);
                break;
            case 65: // a
                velocity.setX(-moveVelocity);
                break;
        }

    }

    // Draws the player
    // MODIFIES: Screen
    // EFFECTS: draws a player on the screen
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) position.getX(), (int) position.getY(), 100, 20);
    }

    public ColliderRect getCollider() {
        return collider;
    }

}
