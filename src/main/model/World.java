package model;


import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;

// Represents a physical world
public class World {

    private ArrayList<RigidBody2D> worldObjects;
    private Player player = null;
    private Vector2 gravityForce;
    private Vector2 size;

    // Creates a world
    public World(Vector2 size) {
        this.size = size;

        worldObjects = new ArrayList<RigidBody2D>();
        gravityForce = new Vector2(0, -9.81f);

        // Change this in the future for games that don't need players
        player = new Player(new Vector2(1, 1),
                            new Vector2(0, 0),
                            new Vector2(0, 0));
    }

    public void tick(long deltaTime) {
        for (RigidBody2D object : worldObjects) {
            object.tick(deltaTime);
        }
    }

    public void handleInput(Character c) {

    }

    public void drawTerminal(Screen screen) {

        if (player != null) {
            player.drawTerminal(screen);
        }

        for (RigidBody2D object : worldObjects) {
            object.drawTerminal(screen);
        }

    }


    public ArrayList<RigidBody2D> getWorldObjects() {
        return worldObjects;
    }


}
