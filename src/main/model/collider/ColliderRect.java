package model.collider;

import model.util.Transform;
import model.util.Vector2;

// Provides properties necessary for basic collision detection of rectangular shaped objects
public class ColliderRect extends Collider {

    protected float width;
    protected float height;

    // Constructor for a rectangular collider
    // EFFECTS: Constructs a ColliderRect centered at a given position,
    // with a certain width and height.
    public ColliderRect(Vector2 position, float width, float height) {
        super(position);
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

        float bxLeft = center.getX() - boxWidthOffset;
        float byTop = center.getY() - boxHeightOffset;
        float bxRight = center.getX() + boxWidthOffset;
        float byBot = center.getY() + boxHeightOffset;

        Vector2 centerCircle = colliderCircle.getCenter();
        float radius = colliderCircle.getRadius();
        float cx = centerCircle.getX();
        float cy = centerCircle.getY();

        float pointX = Math.max(bxLeft, Math.min(bxRight, cx));
        float pointY = Math.max(byTop, Math.min(byBot, cy));
        Vector2 closestToCenter = new Vector2(pointX, pointY);
        float hypotenuse = Vector2.calculateHypotenuse(center, closestToCenter);

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

    @Override
    public ColliderPoints findCollision(ColliderRect colliderRect) {

        Vector2 otherCenter = colliderRect.getCenter();
        float bx = otherCenter.getX();
        float by = otherCenter.getY();
        float boxWidthOffset = colliderRect.getWidth() / 2;
        float boxHeightOffset = colliderRect.getHeight() / 2;

        float cx = center.getX();
        float cy = center.getY();
        float currentWidthOffset = width / 2;
        float currentHeightOffset = height / 2;

        // If some point of the current colliderRect intersects with another colliderRect
        // then calculate a new collision based on the two centers
        // TODO: Think of a new method to add torque and rotation, because this only works for 0 rotation objects.
        if (bx - boxWidthOffset - currentWidthOffset < cx
                && cx < bx + boxWidthOffset + currentWidthOffset
                && by - boxHeightOffset - currentHeightOffset < cy
                && cy < by + boxHeightOffset + currentHeightOffset) {
            return new ColliderPoints(center, otherCenter);
        }


        return null;
    }
}
