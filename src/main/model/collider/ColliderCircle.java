package model.collider;

import model.util.Vector2;

// Provides properties necessary for basic collision detection of circular objects
public class ColliderCircle extends Collider {

    protected Vector2 center;
    protected float radius;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderCircle centered at a given position,
    // with a certain radius
    public ColliderCircle(Vector2 position, float radius) {
        this.center = position;
        this.radius = radius;
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
