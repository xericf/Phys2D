package model.collider;

import model.util.Transform;
import model.util.Vector2;

// Provides properties necessary for basic collision detection of circular objects
public class ColliderCircle extends Collider {

    protected float radius;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderCircle centered at a given position,
    // with a certain radius
    public ColliderCircle(Vector2 position, float radius) {
        super(position);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public ColliderPoints findCollision(ColliderCircle colliderCircle) {

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
    public ColliderPoints findCollision(ColliderRect colliderRect) {
        // the circle is intersecting with a box when the box contains some point of the circle.

        float boxWidthOffset = colliderRect.getWidth() / 2;
        float boxHeightOffset = colliderRect.getHeight() / 2;
        float bxLeft = colliderRect.getCenter().getX() - boxWidthOffset;
        float byTop = colliderRect.getCenter().getY() - boxHeightOffset;
        float bxRight = colliderRect.getCenter().getX() + boxWidthOffset;
        float byBot = colliderRect.getCenter().getY() + boxHeightOffset;
        float cx = center.getX();
        float cy = center.getY();

        // Closest points to the center of circle
        float pointX = Math.max(bxLeft, Math.min(bxRight, cx));
        float pointY = Math.max(byTop, Math.min(byBot, cy));
        Vector2 closestToCenter = new Vector2(pointX, pointY);
        float hypotenuse = Vector2.calculateHypotenuse(center, closestToCenter);

        // If hypotenuse <= radius, get point on the radius furthest in the rectangle
        if (hypotenuse <= radius) {
            if (pointX == cx) {
                return new ColliderPoints(center, new Vector2(cx, cy < pointY ? cy + radius : cy - radius));
            }
            float slope = (pointY - cy) / (pointX - cx);
            // x = (radius^2 / (slope^2 + 1))
            float radiusX = (float) Math.sqrt((radius * radius) / (slope * slope + 1));
            float radiusY = radiusX * slope;
            return new ColliderPoints(center, new Vector2(radiusX, radiusY));
        }
        return null;
    }

}
