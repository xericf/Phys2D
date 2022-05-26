package model.collider;

import model.util.Vector2;
import ui.object.RigidBody2D;

// Collider class for collision detection between physical objects
public abstract class Collider {

    protected RigidBody2D attachedRigidBody;

    // EFFECTS: Constructs a collider with a given center position.
    public Collider(RigidBody2D rigidBody2D) {
        attachedRigidBody = rigidBody2D;
    }

    // TODO: Currently the findCollisions methods do not find the true deepest intersecting points
    // between two objects, which isn't a problem until rotation is implemented. They are only implemented for
    // the normalVector of ColliderPoints to affect a single x or y component to save on complexity.
    // Once rotation is implemented, correct the deepest intersecting points finder to calculate torque correctly.

    // REQUIRES: colliderCircle is not the same object
    // EFFECTS: Finds the collision points between the current collider object and a circular
    // collider with a given transform.
    public abstract ColliderPoints findCollision(ColliderCircle colliderCircle);

    // REQUIRES: colliderRect is not the same object
    // EFFECTS: Finds the collision points between the current collider object and a rectangular
    // collider with a given transform.
    public abstract ColliderPoints findCollision(ColliderRect colliderRect);

    public Vector2 getCenter() {
        return attachedRigidBody.getPosition();
    }


    // Checks if a Collider is within a box built by topLeft and bottomRight points
    // EFFECTS: If all points of a ColliderRect are within the rectangle defined by the topLeft
    // and bottomRight (x, y) coordinates, return a multiplier vector that would return the
    // collider within the borders
    public abstract void calculateBorderInteraction(Vector2 topLeft, Vector2 bottomRight);


}
