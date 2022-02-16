package model.collider;

import model.util.Vector2;

// Collider class for collision detection between physical objects
public abstract class Collider {

    /*
    protected boolean anchored;

    public boolean isAnchored() {
        return anchored;
    }

    public void setAnchored(boolean anchored) {
        this.anchored = anchored;
    }
     */

    // Checks if a ColliderRect is within a box built by topLeft and bottomRight points
    // EFFECTS: If all points of a ColliderRect are within the rectangle defined by the topLeft
    // and bottomRight (x, y) coordinates, return true, false otherwise
    public static boolean checkInBorders(ColliderRect rect, Vector2 topLeft, Vector2 bottomRight) {
        Vector2 center =  rect.getCenter();
        float centerX = center.getX();
        float centerY = center.getY();

        float width = rect.getWidth();
        float height = rect.getHeight();

        if (centerX - (width / 2) < topLeft.getX()
                || centerX + (width / 2) > bottomRight.getX()
                || centerY - (height / 2) < topLeft.getY()
                || centerY + (height / 2) > bottomRight.getY()) {

            return false;

        }

        return true;
    }

    // Updates the velocity of a collided object
    // EFFECTS: Calculates and modifies the velocity of a particular object based on if
    // its ColliderCircle is no longer fully inscribed within a rectangle specified by
    // topLeft and bottomRight (x, y) coordinates.
    public static void calculateVelocityCollision(Vector2 velocity, ColliderCircle circle,
                                                     Vector2 topLeft, Vector2 bottomRight) {
        Vector2 center =  circle.getCenter();
        float centerX = center.getX();
        float centerY = center.getY();
        float radius = circle.getRadius();

        if (centerX - radius < topLeft.getX()
                || centerX + radius > bottomRight.getX()) {
            velocity.multiply(Vector2.invertX);
        }

        if (centerY - radius < topLeft.getY()
                || centerY + radius > bottomRight.getY()) {
            velocity.multiply(Vector2.invertY);
        }

    }

    /*
    public static boolean checkCollide(ColliderCircle circle, ColliderRect rect) {
        return false;
    }

    public static boolean checkCollide(ColliderRect rect, ColliderCircle circle) {
        return false;
    }

    public static boolean checkCollide(ColliderCircle circle, ColliderCircle rect) {
        return false;
    }

    public static boolean checkCollide(ColliderRect circle, ColliderRect rect) {
        return false;
    }
    */

}
