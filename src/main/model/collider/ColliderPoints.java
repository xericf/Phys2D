package model.collider;

import model.util.Vector2;

// Class for storing information about a collision
public class ColliderPoints {

    // General information about this class inspired by:
    // https://www.youtube.com/watch?v=-_IspRG548E&t=157s&ab_channel=Winterdev

    // Point of object A deepest within the bounds of object B
    private Vector2 pointA;

    // Point of object B deepest within the bounds of object A
    private Vector2 pointB;

    // Normalized vector that represents the slope of a line segment A to B
    private Vector2 normalSlope;

    // Distance between point A and B
    private float intersectDistance;

    // If the two points are currently colliding
    private boolean colliding;

    // EFFECTS:
    public ColliderPoints(Vector2 a, Vector2 b) {
        pointA = a;
        pointB = b;
        computeNormalSlope(a, b);
    }

    // MODIFIES: this
    // EFFECTS: Computes and sets the intersectDistance. If intersectDistance is not zero, calculate
    // and set the slope from point A to point B normalized (length of 1), otherwise set the normalSlope
    // to a zero vector
    public void computeNormalSlope(Vector2 a, Vector2 b) {
        // The reason this is not placed within Vector2's util class is to save on computation, as
        // I prefer to calculate the magnitude only once by setting the intersectDistance property as it.
        float ax = a.getX();
        float ay = a.getY();
        float bx = b.getX();
        float by = b.getY();

        float sideX = ax - bx;
        float sideY = ay - by;

        // Hypotenuse magnitude
        intersectDistance = Vector2.calculateHypotenuse(a, b);

        if (intersectDistance != 0) {
            // Divided by magnitude to ensure hypotenuse = 1, while keeping scale of sideX and side Y
            normalSlope = new Vector2(sideX / intersectDistance, sideY / intersectDistance);
            colliding = true;
        } else {
            normalSlope = new Vector2(0, 0);
            colliding = false;
        }

    }


}

