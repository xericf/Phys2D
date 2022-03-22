package model.collider;

import model.util.Transform;
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

    @Override
    public ColliderPoints findCollision(ColliderCircle colliderCircle, Transform transformCircle) {

        // The circles are intersecting when the distance between the centers
        // is less than the radius of the two circles

        float radiusSum = radius + colliderCircle.getRadius();
        float hypotenuse = Vector2.calculateHypotenuse(center, colliderCircle.getCenter());
        if (radiusSum < hypotenuse) {
            // We are putting in the center of the two collider circles as the collision points
            // as a generalization of the points that are furthest intercepting because the normalized
            // slope vector will the same regardless of using a point on the edge of the both circles that is
            // the furthest intersecting.
            return new ColliderPoints(center, colliderCircle.getCenter());
        }

        return null;
    }

    @Override
    public ColliderPoints findCollision(ColliderRect colliderRect, Transform transformRect) {

        // the circle is intersecting with a box when the box contains some point of the circle.
        // That is to say, when:
        // boxX - width/2 - radius < circleX < boxX + width/2 + radius
        // boxY - height/2 - radius < circleY < boxY + width/2 + radius

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

        return null;
    }
}
