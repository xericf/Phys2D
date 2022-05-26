package ui.object;

import model.collider.ColliderRect;
import model.util.Vector2;

import java.awt.*;

// Rectangular physics object, not deformable
public class Rect extends RigidBody2D {

    private ColliderRect collider;

    // Represent a 2-dimensional rigid body in the shape of a rectangle
    // EFFECTS: Constructor for a rectangle, initializes a new rectangle collider
    // at a given position.
    public Rect(Vector2 position, Vector2 velocity, Vector2 force, Vector2 scale) {
        super(position, velocity, force, scale, Color.GREEN);

        collider = new ColliderRect(this, scale.getX(), scale.getY());
    }

    // EFFECTS: Constructor for a rectangle with color, initializes a new rectangular collider
    // at a given position.
    public Rect(Vector2 position, Vector2 velocity, Vector2 force, Vector2 scale, Color color) {
        super(position, velocity, force, scale, color);

        collider = new ColliderRect(this, scale.getX(), scale.getY());
    }

    // Draws the rigid body
    // MODIFIES: g
    // EFFECTS: Draws the rigid body on the screen represented by a circular shape of a particular color
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        float scaleX = scale.getX();
        float scaleY = scale.getY();
        g.fillRect((int) (position.getX() - (scaleX / 2)),
                (int) (position.getY() - (scaleY / 2)), (int) scaleX, (int) scaleY);
    }

    public ColliderRect getCollider() {
        return collider;
    }
}
