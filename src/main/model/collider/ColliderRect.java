package model.collider;

import model.util.Vector2;
import ui.object.RigidBody2D;

// Provides properties necessary for basic collision detection of rectangular shaped objects
public class ColliderRect extends Collider {

    protected float width;
    protected float height;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderRect centered at a given position,
    // with a certain width and height.
    public ColliderRect(RigidBody2D rigidBody2D, float width, float height) {
        super(rigidBody2D);
        this.width = width;
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public ColliderPoints findCollision(ColliderCircle colliderCircle) {
        float boxWidthOffset = width / 2;
        float boxHeightOffset = height / 2;

        float bxLeft = getCenter().getX() - boxWidthOffset;
        float byTop = getCenter().getY() - boxHeightOffset;
        float bxRight = getCenter().getX() + boxWidthOffset;
        float byBot = getCenter().getY() + boxHeightOffset;

        float radius = colliderCircle.getRadius();
        float cx = colliderCircle.getCenter().getX();
        float cy = colliderCircle.getCenter().getY();

        float pointX = Math.max(bxLeft, Math.min(bxRight, cx));
        float pointY = Math.max(byTop, Math.min(byBot, cy));
        Vector2 closestToCenter = new Vector2(pointX, pointY);
        float hypotenuse = Vector2.calculateHypotenuse(getCenter(), closestToCenter);

        if (hypotenuse <= radius) {
            if (pointX == cx) {
                return new ColliderPoints(getCenter(), new Vector2(cx, cy < pointY ? cy + radius : cy - radius));
            }
            float slope = (pointY - cy) / (pointX - cx);
            // x = (radius^2 / (slope^2 + 1))
            float radiusX = (float) Math.sqrt((radius * radius) / (slope * slope + 1));
            float radiusY = radiusX * slope;
            return new ColliderPoints(getCenter(), new Vector2(radiusX, radiusY));
        }
        return null;
    }

    @Override
    public ColliderPoints findCollision(ColliderRect colliderRect) {

        Vector2 otherCenter = colliderRect.getCenter();
        float bx = otherCenter.getX();
        float by = otherCenter.getY();
        float boxWidthOffset = colliderRect.getWidth() / 2;
        float boxHeightOffset = colliderRect.getHeight() / 2;

        float cx = getCenter().getX();
        float cy = getCenter().getY();
        float currentWidthOffset = width / 2;
        float currentHeightOffset = height / 2;

        // If some point of the current colliderRect intersects with another colliderRect
        // then calculate a new collision based on the two centers
        // TODO: Think of a new method to add torque and rotation, because this only works for 0 rotation objects.
        if (bx - boxWidthOffset - currentWidthOffset < cx
                && cx < bx + boxWidthOffset + currentWidthOffset
                && by - boxHeightOffset - currentHeightOffset < cy
                && cy < by + boxHeightOffset + currentHeightOffset) {
            return new ColliderPoints(getCenter(), otherCenter);
        }


        return null;
    }

    @Override
    public Vector2 calculateBorderInteraction(Vector2 topLeft, Vector2 bottomRight) {
        float centerX = getCenter().getX();
        float centerY = getCenter().getY();
        Vector2 velocity = attachedRigidBody.getVelocity();
        if ((centerX - (width / 2) < topLeft.getX() && velocity.getX() < 0)
                || (centerX + (width / 2) > bottomRight.getX() && velocity.getX() > 0)) {
            return Vector2.multiply(velocity, new Vector2(-1, 1));
        } else if ((centerY - (height / 2) < topLeft.getY() && velocity.getY() < 0)
                || (centerY + (height / 2) > bottomRight.getY() && velocity.getY() > 0)) {
            return Vector2.multiply(velocity, new Vector2(1, -1));
        }
        return velocity; // doesn't mutate the velocity directly for good practice
    }
}
