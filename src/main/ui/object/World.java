package ui.object;


import model.collider.ColliderCircle;
import model.collider.ColliderPoints;
import model.util.Vector2;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Savable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

// TODO: Create the most general world WITHOUT the MouseListener.

// Represents a physical world
public class World implements Savable, MouseListener, MouseMotionListener {

    private ArrayList<Ball> worldObjects;
    private Player player;
    private Vector2 gravityForce;
    private Vector2 size;

    private Vector2 topLeft;
    private Vector2 bottomRight;

    // Creates a world
    // EFFECTS: Constructor for a simulator for a physical world of a certain size (in meters).
    // Also sets up a bounding box for collision detection, and creates new list for physical objects,
    // defines gravity force with a default of g = 9.81 m/s^2 down, and initializes a player object
    // at point (1, 35) on the terminal.
    public World(Vector2 size) {
        this.size = size;
        this.topLeft = new Vector2(0, 0); // origin point
        this.bottomRight = new Vector2(size.getX(), size.getY()); // screen height, screen width

        worldObjects = new ArrayList<>();
        gravityForce = new Vector2(0, 65f);

        // Change this in the future for games that don't need players
        player = new Player(new Vector2(150f, size.getY() - 50),
                            new Vector2(0, 0),
                            new Vector2(0, 0),
                            new Vector2(150f, 25f));
    }

    // Updates the world every tick
    // MODIFIES: this
    // EFFECTS: updates player (if defined), and every physics object in the world
    // based on a certain amount of time (deltaTime).
    public void tick(float deltaTime) {

        checkCollisions();

        if (player != null) {
            player.tick(deltaTime);
        }

        for (Ball object : worldObjects) {
            object.setForce(gravityForce);
            object.tick(deltaTime);
        }
    }

    // Checks for player and every physical object in the world collisions
    // MODIFIES: this
    // EFFECTS: inverts x or y velocity of any physical object if it has
    // collided with the border.
    public void checkCollisions() {
        checkPlayerCollisions();
        checkWorldObjectsCollisions();
    }

    // MODIFIES: this
    // EFFECTS: Handles the collisions of the player and worldObjects
    private void checkPlayerCollisions() {
        if (player != null && player.getCollider() != null) {
            Vector2 newVelocity = player.getCollider()
                    .calculateBorderInteraction(player.getVelocity(), topLeft, bottomRight);
            player.setVelocity(newVelocity);

            for (Ball object1 : worldObjects) {
                ColliderPoints objectColliderPoints = object1.getCollider().findCollision(player.getCollider());
                if (objectColliderPoints != null) {
                    Vector2 normalSlope = objectColliderPoints.getNormalSlope();
                    // TODO: Do this without mutating the velocity vectors for obvious reasons.
                    // TODO: Instead of multiplying the velocity, merely ensure that the sign of X and Y
                    // for the normal slope is the opposite of velocity
                    // object1.setVelocity(Vector2.multiply(object1.getVelocity(), normalSlope));
                    if ((normalSlope.getY() < 0 && object1.getVelocity().getY() < 0)
                            || (normalSlope.getY() > 0 && object1.getVelocity().getY() > 0)) {
                        object1.getVelocity().multiply(new Vector2(1, -1));
                    }

                }

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles collisions of all objects in worldObjects
    private void checkWorldObjectsCollisions() {

        // uses this method of for loop to save on half of the computation time
        for (int objectAIdx = 0; objectAIdx < worldObjects.size(); objectAIdx++) {
            Ball objectA = worldObjects.get(objectAIdx);
            ColliderCircle colliderA = objectA.getCollider();
            for (int objectBIdx = objectAIdx + 1; objectBIdx < worldObjects.size(); objectBIdx++) {
                Ball objectB = worldObjects.get(objectBIdx);
                ColliderCircle colliderB = objectB.getCollider();
                if (objectB.equals(objectA)) {
                    continue; // sanity check
                }

                ColliderPoints colliderPoints = colliderA.findCollision(colliderB);
                if (colliderPoints != null) {
                    Vector2 normalSlope = colliderPoints.getNormalSlope();
                    // TODO: Make it reflect the velocity over the slope line for true bounce

                    if ((normalSlope.getY() < 0 && objectA.getVelocity().getY() < 0)
                            || (normalSlope.getY() > 0 && objectA.getVelocity().getY() > 0)) {
                        objectA.getVelocity().multiply(new Vector2(1, -1));
                        objectA.getVelocity().multiply(new Vector2(1, -1));
                    }
                }
            }

            Vector2 newVelocity = colliderA.calculateBorderInteraction(objectA.getVelocity(), topLeft, bottomRight);
            objectA.setVelocity(newVelocity);

        }
    }



    // Provides pressing certain keys with functionality
    // MODIFIES: this
    // EFFECTS: Spawns new Circle physics object on given keycode with a random x velocity,
    // forwards keyCode into player for additional player-specific checks
    public void handleInput(int key) {

        if (key == 84) { // t
            // make it spawn at mouse position too if mouse is on screen
            addBall(new Vector2(200, 80));

        } else if (key == 70) { // F
            worldObjects.clear();
        }

        player.handleInput(key);
    }

    // MODIFIES: this
    // EFFECTS: Adds a ball at a given position vector, and random x velocity, and returns the ball made.
    private Ball addBall(Vector2 position) {
        Ball ball = new Ball(position,
                new Vector2((float) Math.random() * 100 - 50, 0),
                new Vector2(0, 0),
                new Vector2(20, 20));

        worldObjects.add(ball);
        return ball;
    }

    private void moveBall() {

    }

    // draws the world on the screen
    // MODIFIES: g
    // EFFECTS: Draws all physical objects and the player on the screen.
    public void draw(Graphics g) {

        if (player != null) {
            player.draw(g);
        }

        for (Ball object : worldObjects) {
            object.draw(g);
        }

    }

    public ArrayList<Ball> getWorldObjects() {
        return worldObjects;
    }

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getGravityForce() {
        return gravityForce;
    }

    public Player getPlayer() {
        return player;
    }

    public Vector2 getTopLeft() {
        return topLeft;
    }

    public Vector2 getBottomRight() {
        return bottomRight;
    }

    public void setGravityForce(Vector2 gravityForce) {
        this.gravityForce = gravityForce;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setWorldObjects(ArrayList<Ball> objects) {
        this.worldObjects = objects;
    }

    /*
    // TODO, set size must always change the topLeft and bottomRight Vector2 data
    public void setSize(Vector2 size) {
        this.size = size;
        this.BottomRight = this.size;
    }
    */

    // Nanoseconds to seconds
    // EFFECTS: Converts a long Nano seconds value into a float seconds value
    public static float convertNanoToSeconds(long nanoSecs) {
        return (float) nanoSecs / 1000000000;
    }

    private Ball heldBall;
    private Vector2 lastMousePosition;

    private Ball getBallAtMousePosition(int x, int y) {
        Vector2 mousePosition = new Vector2(x, y);
        for (Ball ball : worldObjects) {
            if (Vector2.calculateHypotenuse(ball.getPosition(), mousePosition) < ball.getCollider().getRadius()) {
                return ball;
            }
        }
        return null;
    }



    @Override
    public JSONObject toJson() {
        // PLAYER
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("player", player.toJson());

        // ALL OTHER WORLD OBJECTS
        JSONArray rigidBodies = new JSONArray();
        for (RigidBody2D rigidBody : worldObjects) {
            rigidBodies.put(rigidBody.toJson());
        }

        jsonObject.put("world_objects", rigidBodies);

        // WORLD PROPERTIES
        jsonObject.put("size", size.toJson());
        jsonObject.put("gravityForce", gravityForce.toJson());
        jsonObject.put("topLeft", topLeft.toJson());
        jsonObject.put("bottomRight", bottomRight.toJson());

        return jsonObject;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // useless
    }

    @Override
    public void mousePressed(MouseEvent e) {
        heldBall = getBallAtMousePosition(e.getX(), e.getY());
        if (heldBall == null) {
            // if null, make new ball and set it to heldBall
            heldBall = addBall(new Vector2(e.getX(), e.getY()));
            lastMousePosition = new Vector2(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (heldBall != null) {
            //
        }

        heldBall = null;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Useless
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Useless
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (heldBall != null) {
            heldBall.setPosition(new Vector2(e.getX(), e.getY()));

            // we do not want the vectors to be the same object
            lastMousePosition = new Vector2(e.getX(), e.getY());
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Useless
    }
}
