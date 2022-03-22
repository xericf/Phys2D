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

        float boxWidthOffset = colliderRect.getWidth() / 2;
        float boxHeightOffset = colliderRect.getHeight() / 2;

        float bxLeft = colliderRect.getCenter().getX() - boxWidthOffset;
        float byTop = colliderRect.getCenter().getY() - boxHeightOffset;
        float bxRight = colliderRect.getCenter().getX() + boxWidthOffset;
        float byBot = colliderRect.getCenter().getY() + boxHeightOffset;
        float cx = center.getX();
        float cy = center.getY();

        // Closest points to the center
        // Essentially squeezes the potential points
        // Case 1: Rectangle intersects to the left of the circle center
        // Solution: Take the rightmost point of the rectangle, or the x-center of the circle if intersecting the
        //           center.
        // Case 2: Rectangle intersects to the right of the circle center
        // Solution: Take the leftmost point of the rectangle, or the x-center of the circle if intersecting the
        //          center.
        float pointX = Math.max(bxLeft, Math.min(bxRight, cx));
        // Without loss of generalization, the same formula applies to the Y component, too.
        float pointY = Math.max(byTop, Math.min(byBot, cy));

        Vector2 closestPoint = new Vector2(pointX, pointY);
        float hypotenuse = Vector2.calculateHypotenuse(center, closestPoint);
        return hypotenuse < radius ? new ColliderPoints(center, closestPoint) : null;
    }
}
