package model.object;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.util.Vector2;
import ui.ConsoleDemo;
import ui.Coordinate;

public class Player extends RigidBody2D {


    private float moveVelocity = 1f;

    public Player(Vector2 position, Vector2 velocity, Vector2 force) {
        super(position, velocity, force);
    }

    public void handleInput(Character c) {

        switch (c.charValue()) {
            case 'd':
                velocity.setX(moveVelocity);
                break;
            case 'a':
                velocity.setX(-moveVelocity);
                break;
        }

    }

    @Override
    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '█', TextColor.ANSI.BLUE);
    }

}
