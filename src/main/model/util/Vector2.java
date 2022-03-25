package model.util;

import persistence.Savable;
import org.json.JSONObject;

// class to contain a 2-dimensional vector value
public class Vector2 implements Savable {

    public static final Vector2 invertX = new Vector2(-1f, 1);
    public static final Vector2 invertY = new Vector2(1, -1f);

    private float valX;
    private float valY;

    // Constructor for a 2-dimensional vector, All values are always in their
    // default SI units i.e: 1 kg, 1 m, 1 N, etc.
    // EFFECTS: Initializes values of the vector with valX = x, and valY = y
    public Vector2(float x, float y) {
        this.valX = x;
        this.valY = y;
    }


    // Multiplies two 2-dimensional vectors, and produces a new vector
    // EFFECTS: Returns a new Vector2 object with the x value equal
    // to the product of the first and second vector's x value, and
    // the y value equal to the product of the first and second vector's
    // y value.
    public static Vector2 multiply(Vector2 vector1, Vector2 vector2) {
        Vector2 product = new Vector2(vector1.getX() * vector2.getX(),
                                      vector1.getY() * vector2.getY());

        return product;
    }


    // Multiplies a 2-dimensional vectors and a value, and produces a new vector
    // EFFECTS: Returns a new 2-Dimensional vector equal to a certain
    // vector multiplied by a value.
    public static Vector2 multiply(Vector2 vector1, float magnitude) {
        Vector2 product = new Vector2(vector1.getX() * magnitude,
                                         vector1.getY() * magnitude);

        return product;
    }

    /*
    // Adds two 2-dimensional vectors together
    // EFFECTS: Returns a new 2-Dimensional vector equal to the sum
    // of two vectors.
    public static Vector2 add(Vector2 vector1, Vector2 vector2) {
        Vector2 product = new Vector2(vector1.getX() + vector2.getX(),
                                    vector1.getY() + vector2.getY());

        return product;
    }
    */

    // Multiples current vector with another vector
    // MODIFIES: this
    // EFFECTS: Multiplies this vector by another vector, updates
    // current values.
    public Vector2 multiply(Vector2 vec) {

        valX *= vec.getX();
        valY *= vec.getY();

        return this;
    }

    // Multiples current vector with a value
    // MODIFIES: this
    // EFFECTS: Multiplies this vector by a value, updates
    // current values.
    public Vector2 multiply(float magnitude) {
        valX *= magnitude;
        valY *= magnitude;

        return this;
    }

    // Adds current vector with another vector
    // MODIFIES: this
    // EFFECTS: Adds this vector with another vector, updates values.
    public Vector2 add(Vector2 vec) {

        valX += vec.getX();
        valY += vec.getY();

        return this;
    }

    public static float calculateHypotenuse(Vector2 a, Vector2 b) {
        float ax = a.getX();
        float ay = a.getY();
        float bx = b.getX();
        float by = b.getY();

        float sideX = ax - bx;
        float sideY = ay - by;

        // Hypotenuse magnitude
        return (float) Math.sqrt((sideX * sideX) + (sideY * sideY));
    }

    /*
    // TODO: Probably move this into a different physics util class
    public static Vector2 calculateSlopeTime(Vector2 a, Vector2 b, long deltaTime) {
        // d/t = v
        return new Vector2((b.getY() - a.getY()) / deltaTime,
                (b.getX() - a.getX()) / deltaTime);
    }
    */

    public float getX() {
        return valX;
    }

    public float getY() {
        return valY;
    }

    public void set(Vector2 vec) {
        valX = vec.getX();
        valY = vec.getY();
    }

    public void setX(float x) {
        valX = x;
    }

    public void setY(float y) {
        valY = y;
    }

    // EFFECTS: returns true if the valX and valY is equal to a given vector's x and y components
    public boolean equals(Vector2 vec) {
        return vec.getX() == valX && vec.getY() == valY;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", this.valX);
        jsonObject.put("y", this.valY);
        return jsonObject;
    }

    // EFFECTS: Parses a JSON string and converts it into a Vector2
    public static Vector2 parseJson(JSONObject jsonObject) {
        // extremely simple, test is implicit by Reader and Writer
        Vector2 result = new Vector2(jsonObject.getFloat("x"),
                                    jsonObject.getFloat("y"));
        return result;
    }

}
