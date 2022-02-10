package model.util;

// class to contain a 2-dimensional vector value
public class Vector2 {

    private float valX;
    private float valY;

    // Constructor for a 2-dimensional vector, All values are always in their
    // default SI units i.e: 1 kg, 1 m, 1 N, etc.
    // EFFECTS: Initializes values of the vector with valX = x, and valY = y
    public Vector2(float x, float y) {
        this.valX = x;
        this.valY = y;
    }

    public Vector2 multiply(Vector2 vec) {
        valX *= vec.getX();
        valY *= vec.getY();

        return this;
    }

    public Vector2 add(Vector2 vec) {
        valX += vec.getX();
        valY += vec.getY();

        return this;
    }

    public float getX() {
        return valX;
    }

    public float getY() {
        return valY;
    }

    public void setX(float x) {
        valX = x;
    }

    public void setY(float y) {
        valY = y;
    }


}
