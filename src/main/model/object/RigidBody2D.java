package model.object;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.collider.Collider;
import model.collider.ColliderRect;
import model.util.Transform;
import model.util.Vector2;
import ui.ConsoleDemo;
import model.util.Coordinate;

// Represents a physical rigid body that does not change or deform shape
public class RigidBody2D {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 force; // net force
    protected float mass;

    protected Transform transform;

    // Constructor for a rigid body
    // Effects: Builds a rigid body of a certain position, velocity, and force vector
    public RigidBody2D(Vector2 position, Vector2 velocity, Vector2 force) {
        this.transform = new Transform(position);

        this.position = position;
        this.velocity = velocity;
        this.force = force;
    }

    // Draws the rigid body
    // MODIFIES: screen
    // EFFECTS: Draws the rigid body on the terminal represented by a rectangle shape
    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '█', TextColor.ANSI.WHITE);
    }

    // Updates the rigid body's properties every tick
    // MODIFIES: this
    // EFFECTS: On each tick, uses a deltaTime value to determine displacement
    // and new velocity based on current force being applied to the rigid body.
    // Resets net force being applied to the object to 0 on both the x and y components.
    public void tick(float deltaTime) {

        // Formula: d = vt + (1/2) a t^(1/2)
        // d = vt
        Vector2 displacement = Vector2.multiply(velocity, deltaTime);

        // d += at^2 * 0.5
        displacement.add(Vector2.multiply(force, deltaTime * deltaTime).multiply(0.5f));
        position.add(displacement);

        // Formula: v = v0 + at, velocity changed after displacement change to obey conservation of energy
        Vector2 velocityChange = Vector2.multiply(force, deltaTime);
        velocity.add(velocityChange);

        // force is reset after it is applied
        force = new Vector2(0, 0);
    }

    public void setForce(Vector2 netForce) {
        force.set(netForce);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

}
