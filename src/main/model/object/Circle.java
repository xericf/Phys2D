package model.object;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.collider.ColliderCircle;
import model.util.Vector2;
import ui.ConsoleDemo;
import model.util.Coordinate;

// Circle physics object, not deformable.
public class Circle extends RigidBody2D {

    private ColliderCircle collider;

    // Represent a 2-dimensional rigid body in the shape of a circle
    // EFFECTS: Constructor for a circle, initializes a new circular collider
    // at a given position with a default radius of 1.
    public Circle(Vector2 position, Vector2 velocity, Vector2 force) {
        super(position, velocity, force);

        collider = new ColliderCircle(position, 1);
    }

    // Draws the rigid body
    // MODIFIES: screen
    // EFFECTS: Draws the rigid body on the terminal represented by a white circular shape
    @Override
    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '⬤', TextColor.ANSI.WHITE);
    }

    public ColliderCircle getCollider() {
        return collider;
    }
}
