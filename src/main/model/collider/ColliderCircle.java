package model.collider;

import model.util.Vector2;
import ui.demo.JuggleDemo;
import ui.object.RigidBody2D;

// Provides properties necessary for basic collision detection of circular objects
public class ColliderCircle extends Collider {

    protected float radius;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderCircle centered at a given position,
    // with a certain radius
    public ColliderCircle(RigidBody2D rigidBody2D, float radius) {
        super(rigidBody2D);
        this.radius = radius;
    }

    @Override
    public ColliderPoints findCollision(ColliderCircle colliderCircle) {

        // The circles are intersecting when the distance between the centers
        // is less than the radius of the two circles

        float radiusSum = radius + colliderCircle.getRadius();
        float hypotenuse = Vector2.calculateHypotenuse(getCenter(), colliderCircle.getCenter());
        if (hypotenuse < radiusSum) {
            // We are putting in the center of the two collider circles as the collision points
            // as a generalization of the points that are furthest intercepting because the normalized
            // slope vector will the same regardless of using a point on the edge of the both circles that is
            // the furthest intersecting.
            return new ColliderPoints(getCenter(), colliderCircle.getCenter());
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
        float cx = getCenter().getX();
        float cy = getCenter().getY();

        // Closest points to the center of circle
        float pointX = Math.max(bxLeft, Math.min(bxRight, cx));
        float pointY = Math.max(byTop, Math.min(byBot, cy));
        Vector2 closestToCenter = new Vector2(pointX, pointY);
        float hypotenuse = Vector2.calculateHypotenuse(getCenter(), closestToCenter);

        // If hypotenuse <= radius, get point on the radius furthest in the rectangle
        if (hypotenuse < radius) {
            if (pointX == cx) {
                return new ColliderPoints(getCenter(), new Vector2(cx, cy < pointY ? cy + radius : cy - radius));
            }
            Vector2 closestPoint = new Vector2(pointX, pointY);
            return new ColliderPoints(getCenter(), closestPoint);
        }

        return null;

    }

    @Override
    public void calculateBorderInteraction(Vector2 topLeft, Vector2 bottomRight) {
        Vector2 position = getCenter();
        float centerX = position.getX();
        float centerY = position.getY();
        Vector2 velocity = attachedRigidBody.getVelocity();
        if ((centerX - radius < topLeft.getX() && velocity.getX() < 0)) {
            velocity.multiply(new Vector2(-1, 1));
            position.setX(0 + radius);
        } else if ((centerX + radius > bottomRight.getX() && velocity.getX() > 0)) {
            velocity.multiply(new Vector2(-1, 1));
            position.setX(JuggleDemo.defaultWidth - radius); // TODO: Clearly a connection with the game window's
            // current width and height needs to be made.
        } else if ((centerY - radius < topLeft.getY() && velocity.getY() < 0)) {
            velocity.multiply(new Vector2(1, -1));
            position.setY(0 + radius);
        } else if ((centerY + radius > bottomRight.getY() && velocity.getY() > 0)) {
            velocity.multiply(new Vector2(1, -1));
            position.setY(JuggleDemo.defaultHeight - radius);
        }


    }

    public float getRadius() {
        return radius;
    }
}
