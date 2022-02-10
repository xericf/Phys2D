package ui;

import model.Vector2;

public class Coordinate {

    private int valX;
    private int valY;

    // Constructor for a 2-dimensional coordinate
    // EFFECTS: Initializes values of the vector with valX = x, and valY = y
    public Coordinate(int x, int y) {
        this.valX = x;
        this.valY = y;
    }

    public int getX() {
        return valX;
    }

    public int getY() {
        return valY;
    }

    public void setX(int x) {
        valX = x;
    }

    public void setY(int y) {
        valY = y;
    }


}
