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
        // ask professor about the redundency of the collision detectors



        return null;
    }

    @Override
    public ColliderPoints findCollision(ColliderRect colliderRect, Transform transformRect) {
        // the circle is intersecting with a box when the box contains some point of the circle.
        // That is to say, when:
        // boxX - width/2 - radius < circleX < boxX + width/2 + radius
        // boxY - height/2 - radius < circleY < boxY + width/2 + radius
/*
        float bx = colliderRect.getCenter().getX();
        float by = colliderRect.getCenter().getY();
        float boxWidthOffset = colliderRect.getWidth() / 2;
        float boxHeightOffset = colliderRect.getHeight() / 2;

        float cx = center.getX();
        float cy = center.getY();

        if (bx - boxWidthOffset - radius < cx
                && cx < bx + boxWidthOffset + radius
                && by - boxHeightOffset - radius < cy
                && cy < by + boxHeightOffset + radius) {
            return null;
        }
        */

        return null;
    }
}
