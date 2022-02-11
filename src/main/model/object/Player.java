package model.object;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.collider.ColliderRect;
import model.util.Vector2;
import ui.ConsoleDemo;
import model.util.Coordinate;

// Represents a physical object that could be controlled by the user
public class Player extends RigidBody2D {

    private float moveVelocity = 25f;
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
        collider.setAnchored(true);
    }

    // Provides pressing certain keys with player-specific functionality
    // MODIFIES: this
    // EFFECTS: Allows for player to change velocity to move to the left or right
    // based on key press
    public void handleInput(Character c) {

        switch (c.charValue()) {
            case 'd':
                velocity.setX(moveVelocity);
                break;
            case 'a':
                velocity.setX(-moveVelocity);
                break;
        }

    }

    // Draws the player
    // MODIFIES: Screen
    // EFFECTS: draws a player on the terminal
    @Override
    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '█', TextColor.ANSI.BLUE);
    }

    public ColliderRect getCollider() {
        return collider;
    }

}
