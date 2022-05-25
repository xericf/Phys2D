package model.collider;

import model.util.Vector2;

// Class for storing information about a collision
public class ColliderPoints {

    // General information about this class inspired by:
    // SOURCE: https://www.youtube.com/watch?v=-_IspRG548E&t=157s&ab_channel=Winterdev

    // Point of object A deepest within the bounds of object B
    private Vector2 pointA;

    // Point of object B deepest within the bounds of object A
    private Vector2 pointB;

    // Normalized vector that represents the slope of a line segment A to B
    private Vector2 normal;
    private double normalAngle;

    // Normalized vector that represents the slope of negative reciprocal of the normal
    private Vector2 tangent;

    // Distance between point A and B
    private float distance;

    // If the two points are currently colliding
    private boolean colliding;

    // EFFECTS:
    public ColliderPoints(Vector2 a, Vector2 b) {
        pointA = a;
        pointB = b;
        computeUnitVectors(a, b);
    }

    // MODIFIES: this
    // EFFECTS: Computes and sets the intersectDistance. If intersectDistance is not zero, calculate
    // and set the slope from point A to point B normalized (length of 1), otherwise set the normalSlope
    // to a zero vector
    public void computeUnitVectors(Vector2 a, Vector2 b) {
        // The reason this is not placed within Vector2's util class is to save on computation, as
        // I prefer to calculate the magnitude only once by setting the intersectDistance property as it.
        float ax = a.getX();
        float ay = a.getY();
        float bx = b.getX();
        float by = b.getY();

        float sideX = bx - ax;
        float sideY = by - ay;

        // Hypotenuse magnitude
        distance = Vector2.calculateHypotenuse(a, b);

        if (distance != 0) {
            // Divided by magnitude to ensure hypotenuse = 1, while keeping scale of sideX and side Y
            normal = new Vector2(sideX / distance, sideY / distance);
            normalAngle = Math.atan(normal.getY() / normal.getX());
            tangent = new Vector2(- normal.getY(), normal.getX());
            colliding = true;
        } else {
            normal = new Vector2(0, 0);
            normalAngle = 0;
            tangent = new Vector2(- normal.getY(), normal.getX());
            colliding = false;
        }
    }

    public Vector2 getPointA() {
        return pointA;
    }

    public Vector2 getPointB() {
        return pointB;
    }

    public Vector2 getNormal() {
        return normal;
    }

    public float getDistance() {
        return distance;
    }

    public boolean isColliding() {
        return colliding;
    }

    public double getNormalAngle() {
        return normalAngle;
    }

    public Vector2 getTangent() {
        return tangent;
    }

    /*
    // EFFECTS: True if the other ColliderPoints has the same property values as this object,
    // false otherwise.
    public boolean equals(ColliderPoints colliderPoints) {

        return pointA.equals(colliderPoints.getPointA())
                && pointB.equals(colliderPoints.getPointB())
                && normalSlope.equals(colliderPoints.getNormalSlope())
                && intersectDistance == colliderPoints.getIntersectDistance()
                && colliding == colliderPoints.isColliding();

    }
    */


}

