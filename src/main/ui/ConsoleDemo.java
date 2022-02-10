package ui;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Vector2;
import model.World;

import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class ConsoleDemo {

    private World world;

    private Screen screen;
    private Terminal terminal;

    private boolean isActive;
    private int ticksPerSecond;

    private long previousTime;
    private long currentTime;

    public ConsoleDemo() throws IOException {
        isActive = true;
        previousTime = System.nanoTime();
        ticksPerSecond = 60;

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(150, 50));

        screen = terminalFactory.createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();
        Vector2 worldSize = new Vector2(terminalSize.getColumns(), // x
                                        terminalSize.getRows());   // y
        System.out.println(worldSize.getX() + " " + worldSize.getY());
        world = new World(worldSize);
    }

    //
    public void begin() throws IOException, InterruptedException {

        screen.startScreen();

        startTicking();
    }

    // Begins the simulation
    // EFFECTS: Every (1 second / ticksPerSecond), one tick occurs,
    // exits if the world is no longer active
    private void startTicking() throws InterruptedException, IOException {
        while (isActive == true) {
            currentTime = System.nanoTime();
            long deltaTime = currentTime - previousTime;

            // 1000000000 nanoseconds in a second
            if (deltaTime > 1000000000 / ticksPerSecond) {
                tick(deltaTime);
                previousTime = System.nanoTime();
            }
        }

        // successful termination
        System.exit(0);
    }

    // forwards the world state by a certain amount of time
    private void tick(long deltaTime) throws IOException {
        handleInput();

        // Rendering is done last for most up-to-date graphics
        screen.clear();
        render();
        screen.refresh();
    }

    private void handleInput() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key == null || key.getCharacter() == null) {
            return;
        }
        Character c = key.getCharacter();

        world.handleInput(c);
        System.out.println(c);
    }


    //Renders the current world.
    // EFFECTS:
    private void render() {
        drawControls();
        world.drawTerminal(screen);
    }


    private void drawControls() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(1, 0, "Controls: F = Reset");

    }

    public static void drawAtCoordinate(Screen screen, Coordinate coordinate, char character, TextColor color) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);

        // puts the character at position x, y
        text.putString(coordinate.getX(), coordinate.getY(), String.valueOf(character));

    }

}
