package model.object;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import model.util.Vector2;
import ui.ConsoleDemo;
import ui.Coordinate;

public class Circle extends RigidBody2D {


    public Circle(Vector2 position, Vector2 velocity, Vector2 force) {
        super(position, velocity, force);
    }

    @Override
    public void drawTerminal(Screen screen) {
        Coordinate coordinate = new Coordinate((int) position.getX(), (int) position.getY());
        ConsoleDemo.drawAtCoordinate(screen, coordinate, '⬤', TextColor.ANSI.BLUE);
    }
}
