package ui.object;

import model.collider.ColliderCircle;
import model.util.Vector2;

import java.awt.*;

// Circle physics object, not deformable.
public class Ball extends RigidBody2D {

    private ColliderCircle collider;

    // Represent a 2-dimensional rigid body in the shape of a circle
    // EFFECTS: Constructor for a circle, initializes a new circular collider
    // at a given position with a default radius of 1.
    public Ball(Vector2 position, Vector2 velocity, Vector2 force) {
        super(position, velocity, force);

        collider = new ColliderCircle(position, 1);
    }

    // Draws the rigid body
    // MODIFIES: g
    // EFFECTS: Draws the rigid body on the screen represented by a circular shape of a particular color
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillArc((int) position.getX(), (int) position.getY(),
                25, 25,0, 360);
    }

    public ColliderCircle getCollider() {
        return collider;
    }
}
