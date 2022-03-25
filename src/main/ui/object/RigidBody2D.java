package ui.object;

import model.util.Vector2;
import org.json.JSONObject;
import persistence.Savable;

import java.awt.*;

// Represents a physical rigid body that does not change or deform shape
public abstract class RigidBody2D implements Savable {

    // TODO: add anchored capabilities
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 force; // net force
    protected Vector2 scale;
    // protected float rotation;

    protected boolean anchored = false;

    protected Color color;
    // protected float mass;

    // Constructor for a rigid body
    // Effects: Builds a rigid body of a certain position, velocity, force, and scale, and color.
    public RigidBody2D(Vector2 position, Vector2 velocity, Vector2 force, Vector2 scale, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.force = force;
        this.scale = scale;
        this.color = color;
    }

    // Draws the rigid body
    // MODIFIES: g
    // EFFECTS: Draws the rigid body on the screen represented by a shape.
    public abstract void draw(Graphics g);


    // Updates the rigid body's properties every tick
    // MODIFIES: this
    // EFFECTS: On each tick, if this is not anchored, uses a deltaTime value to determine displacement
    // and new velocity based on current force being applied to the rigid body.
    // Resets net force being applied to the object to 0 on both the x and y components.
    public void tick(float deltaTime) {

        if (anchored) {
            return;
        }

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

    public void setVelocity(Vector2 newVelocity) {
        this.velocity = newVelocity;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getForce() {
        return force;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public void setAnchored(boolean anchored) {
        this.anchored = anchored;
    }

    public void setPosition(Vector2 position) {
        this.position = position; // needs to update collider object's position -- simply make them see eachother?
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("position", position.toJson());
        jsonObject.put("velocity", velocity.toJson());
        jsonObject.put("force", force.toJson());
        jsonObject.put("scale", scale.toJson());
        jsonObject.put("color", color.getRGB()); // color.getRGB is the int value of the color
        return jsonObject;
    }

}
