package model;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import ui.ConsoleDemo;
import ui.Coordinate;

// Represents a physical rigid body that does not change or deform shape
public class RigidBody2D {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 force; // net force
    protected float mass;

    protected Collider collider;
    protected Transform transform;

    // Constructor
    // Effects: Builds a rigid body of a certain position, velocity, and force vector
    public RigidBody2D(Vector2 position, Vector2 velocity, Vector2 force) {
        this.transform = new Transform(position);
        this.position = position;
        this.velocity = velocity;
        this.force = force;
    }

    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '█', TextColor.ANSI.WHITE);
    }


    public void applyForce() {

    }

    public void tick(long deltaTime) {
        position.add(velocity);
    }



}
