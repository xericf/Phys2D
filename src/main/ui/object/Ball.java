package ui.object;

import model.collider.ColliderCircle;
import model.util.Vector2;

import java.awt.*;

// Circle physics object, not deformable.
public class Ball extends RigidBody2D {

    private ColliderCircle collider;

    // Represent a 2-dimensional rigid body in the shape of a circle
    // EFFECTS: Constructor for a circle, initializes a new circular collider
    // at a given position with a given radius.
    public Ball(Vector2 position, Vector2 velocity, Vector2 force, Vector2 scale) {
        super(position, velocity, force, scale);

        // approximation of radius, divided by 4 because scale is width and height, and radius is half a circle's
        // diameter
        collider = new ColliderCircle(position, (scale.getX() + scale.getY()) / 4);
    }


    // Draws the rigid body
    // MODIFIES: g
    // EFFECTS: Draws the rigid body on the screen represented by a circular shape of a particular color
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        float scaleX = scale.getX();
        float scaleY = scale.getY();
        g.fillArc((int) (position.getX() - scaleX / 2), // subtract by radius
                (int) (position.getY() - scaleY / 2),
                (int) scaleX, (int) scaleY,0, 360);
    }

    public ColliderCircle getCollider() {
        return collider;
    }
}
