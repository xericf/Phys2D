package model.util;


import model.collider.ColliderPoints;
import ui.object.Ball;
import ui.object.Rect;

public class Solver {
    public static final float HALF_PI = (float) (Math.PI / 2);
    public static final float SEPARATE_DIST = 0.1f;

    // EFFECTS: Takes in a vector of an object (velocity, etc.) to calculate the intersection angle
    // (measured from x-axis) between it and a collisionAxis.
    public static double findIntersectionAngleOfSlopes(Vector2 vec, Vector2 collisionAxis) {
        float a = vec.getY() / vec.getX();
        float b = collisionAxis.getY() / collisionAxis.getX();

        return Math.abs(Math.atan(a) - Math.atan(b));
    }

    // CITE: https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional

    // EFFECTS: Computes the new velocity of ball a and b, then separates them to prevent sticking
    // MODIFIES: a, b
    public static void solveCircleCircleCollision(Ball a, Ball b, ColliderPoints cp) {
        computeNewVelocities(a, b, cp);
        separateObjects(a, b, cp);

    }

    // EFFECTS: Computes the new velocities for ball a and b based on conservation of momentum and kinetic energy
    // (elastic collisions)
    // MODIFIES: a, b
    public static void computeNewVelocities(Ball a, Ball b, ColliderPoints cp) {

        Vector2 va = a.getVelocity();
        Vector2 vb = b.getVelocity();

        float ha = Vector2.calculateHypotenuse(va);
        float hb = Vector2.calculateHypotenuse(vb);

        float ma = a.getMass();
        float mb = b.getMass();

        double theta1 =  Math.asin(va.getY() / ha); // if va.getY() > 0, asin will be positive, next you need to choose
        // from the two potential solutions.
        theta1 = va.getX() < 0 ? Math.PI - theta1 : theta1; // this is required to choose from the left or right
        // angle to know if it's moving left or right

        double theta2 = Math.asin(vb.getY() / hb);
        theta2 = vb.getX() < 0 ? Math.PI - theta2 : theta2;

        double phi = cp.getNormalAngle();

        double v1 = (ha * Math.cos(theta1 - phi) * (ma - mb)
                + (2 * mb * hb * Math.cos(theta2 - phi))) / (ma + mb);

        // new velocity for object A
        va.setX((float) ((v1 * Math.cos(phi))
                + (ha * Math.sin(theta1 - phi) * Math.cos(phi + HALF_PI))));
        va.setY((float) ((v1 * Math.sin(phi))
                + (ha * Math.sin(theta1 - phi) * Math.sin(phi + HALF_PI))));

        double v2 = (hb * Math.cos(theta2 - phi) * (mb - ma)
                + (2 * ma * ha * Math.cos(theta1 - phi))) / (ma + mb);
        vb.setX((float) ((v2 * Math.cos(phi))
                + (hb * Math.sin(theta2 - phi) * Math.cos(phi + HALF_PI))));
        vb.setY((float) ((v2 * Math.sin(phi))
                + (hb * Math.sin(theta2 - phi) * Math.sin(phi + HALF_PI))));

    }

    // EFFECTS: Separates Ball a and Ball b, based on the normal angle from cp, moves them apart from eachother
    // MODIFIES: a, b
    private static void separateObjects(Ball a, Ball b, ColliderPoints cp) {

        Vector2 posA = a.getPosition();
        Vector2 posB = b.getPosition();

        float intersect = Math.abs((a.getCollider().getRadius() + b.getCollider().getRadius()) - cp.getDistance()) / 2
                + SEPARATE_DIST;

        // These are assumed to be the updated velocities in which a and b have already collided
        double theta = cp.getNormalAngle();
        float cosMult = (float) Math.abs(Math.cos(theta));

        if (posA.getX() < posB.getX()) {
            posA.setX((posA.getX() - (intersect * cosMult)));
            posB.setX((posB.getX() + (intersect * cosMult)));
        } else {
            posA.setX((posA.getX() + (intersect * cosMult)));
            posB.setX((posB.getX() - (intersect * cosMult)));
        }

        if (posA.getY() < posB.getY()) {
            posA.setY((posA.getY() - (intersect * cosMult)));
            posB.setY((posB.getY() + (intersect * cosMult)));
        } else {
            posA.setY((posA.getY() + (intersect * cosMult)));
            posB.setY((posB.getY() - (intersect * cosMult)));
        }

    }

    public static void separateRectCircle(Rect a, Ball b, ColliderPoints cp) {
        // TODO: Use the anchor property in decided if posA, posB should be moved or not, also add in the posA.setX, etc.
        Vector2 posA = a.getPosition();
        Vector2 posB = b.getPosition();

        float intersect = 2f;
        double theta = cp.getNormalAngle();
        float cosMult = (float) Math.abs(Math.cos(theta));

        if (posA.getX() < posB.getX()) {
//            posA.setX((posA.getX() - (intersect * cosMult)));
            posB.setX((posB.getX() + (intersect * cosMult)));
        } else {
//            posA.setX((posA.getX() + (intersect * cosMult)));
            posB.setX((posB.getX() - (intersect * cosMult)));
        }

        if (posA.getY() < posB.getY()) {
//            posA.setY((posA.getY() - (intersect * cosMult)));
            posB.setY((posB.getY() + (intersect * cosMult)));
        } else {
//            posA.setY((posA.getY() + (intersect * cosMult)));
            posB.setY((posB.getY() - (intersect * cosMult)));
        }


    }


}
