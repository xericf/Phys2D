package model.util;


import model.collider.ColliderPoints;
import ui.object.Ball;

public class Solver {
    public static final float HALF_PI = (float) (Math.PI / 2);
    public static final float SEPARATE_DIST = 0.05f;

    // EFFECTS: Takes in a vector of an object (velocity, etc.) to calculate the intersection angle
    // (measured from x-axis) between it and a collisionAxis.
    public static double findIntersectionAngleOfSlopes(Vector2 vec, Vector2 collisionAxis) {
        float a = vec.getY() / vec.getX();
        float b = collisionAxis.getY() / collisionAxis.getX();

        return Math.abs(Math.atan(a) - Math.atan(b));
    }

    // CITE: https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional

    // EFFECTS:
    // MODIFIES: a, b
    public static void solveCircleCircleCollision(Ball a, Ball b, ColliderPoints cp) {
        computeNewVelocities(a, b, cp);
        separateObjects(a, b, cp);

    }

    public static void computeNewVelocities(Ball a, Ball b, ColliderPoints cp) {

        Vector2 va = a.getVelocity();
        Vector2 vb = b.getVelocity();

        float ha = Vector2.calculateHypotenuse(va);
        float hb = Vector2.calculateHypotenuse(vb);

        // saved old velocities
        float vax = va.getX();
        float vay = va.getY();
        float vbx = vb.getX();
        float vby = vb.getY();

        float ma = a.getMass();
        float mb = b.getMass();

        double theta1 = Math.atan(va.getY() / va.getX());
        double theta2 = Math.atan(vb.getY() / vb.getX());

        double phi = cp.getNormalAngle();

        double v1 = (ha * Math.cos(theta1 - phi) * (ma - mb)
                + (2 * mb * hb * Math.cos(theta2 - phi))) / (ma + mb);

        // new velocity for object A
        va.setX((float) ((v1 * Math.cos(phi))
                + (ha * Math.sin(theta1 - phi) * Math.cos(phi + HALF_PI))));
        va.setY((float) ((v1 * Math.sin(phi))
                + (ha * Math.sin(theta1 - phi) * Math.sin(phi + HALF_PI))));

        // m_1 v_1 + m_2 v_2 = m_1 v_1p + m_2 v_2p
        // v_2p = (m_1 v_1 + m_2 v_2 - m_1 v_1p) / m_2

        vb.setX(((ma * vax) + (mb * vbx) - (ma * va.getX())) / mb);
        vb.setY(((ma * vay) + (mb * vby) - (ma * va.getY())) / mb);

    }

    private static void separateObjects(Ball a, Ball b, ColliderPoints cp) {

        Vector2 posA = a.getPosition();
        Vector2 posB = b.getPosition();

        float intersect = Math.abs((a.getCollider().getRadius() + b.getCollider().getRadius()) - cp.getDistance()) / 2;

        // These are assumed to be the updated velocities in which a and b have already collided
        double theta1 = Math.atan(a.getVelocity().getY() / a.getVelocity().getX());
        double theta2 = Math.atan(b.getVelocity().getY() / b.getVelocity().getX());

        posA.setX((float) (posA.getX() + (intersect * Math.cos(theta1))));
        posB.setX((float) (posB.getX() + (intersect * Math.cos(theta2))));

        posA.setY((float) (posA.getY() + (intersect * Math.sin(theta1))));
        posB.setY((float) (posB.getY() + (intersect * Math.sin(theta2))));

    }

}
