package model.collider;

import model.util.Transform;
import model.util.Vector2;

// Provides properties necessary for basic collision detection of rectangular shaped objects
public class ColliderRect extends Collider {

    protected Vector2 center;
    protected float width;
    protected float height;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderRect centered at a given position,
    // with a certain width and height.
    public ColliderRect(Vector2 position, float width, float height) {
        this.center = position;
        this.width = width;
        this.height = height;
    }

    public Vector2 getCenter() {
        return center;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public ColliderPoints findCollision(ColliderCircle colliderCircle, Transform transformCircle) {
        return null;
    }

    @Override
    public ColliderPoints findCollision(ColliderRect colliderRect, Transform transformRect) {
        return null;
    }
}
