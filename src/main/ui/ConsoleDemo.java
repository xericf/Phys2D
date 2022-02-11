package ui;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.util.Coordinate;
import model.util.Vector2;

import java.io.IOException;

public class ConsoleDemo {

    private World world;

    private Screen screen;
    private Terminal terminal;

    private boolean isActive;
    private int ticksPerSecond;

    private long previousTime;
    private long currentTime;

    // Constructor for ConsoleDemo for phase 1 that showcases gravity and dynamics
    // EFFECTS: Constructs the demo, sets up the terminal window in which the simulation will be
    // played of a default width of 120 column and 40 rows.
    public ConsoleDemo() throws IOException {
        isActive = true;
        previousTime = System.nanoTime();
        ticksPerSecond = 60;

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(120, 40));

        screen = terminalFactory.createScreen();
        TerminalSize terminalSize = screen.getTerminalSize();
        Vector2 worldSize = new Vector2(terminalSize.getColumns(), // x
                                        terminalSize.getRows());   // y
        world = new World(worldSize);
    }

    // Begins the simulation
    // MODIFIES: this
    // EFFECTS: Default starting screen is shown, starts the simulation
    public void begin() throws IOException, InterruptedException {
        screen.startScreen();
        startTicking();
    }

    // Starts time in the simulation
    // MODIFIES: this
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
    // MODIFIES: this
    // EFFECTS: every tick, input is handled, the world is updated,
    // and the next screen frame is generated.
    private void tick(long deltaTime) throws IOException {
        handleInput();

        float deltaTimeInSeconds = World.convertNanoToSeconds(deltaTime);
        world.tick(deltaTimeInSeconds);

        // Rendering is done last for most up-to-date graphics
        screen.clear();
        render();
        screen.refresh();
    }

    // Handles keyboard input
    // MODIFIES: this
    // EFFECTS: Handles quitting the program on a given key press, then forwards
    // the key pressed to check for functionality in the world.
    private void handleInput() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key == null || key.getCharacter() == null) {
            return;
        }

        Character c = key.getCharacter();

        if (c.charValue() == 'q') {
            isActive = false;
            return;
        }

        world.handleInput(c);
    }

    // Renders the current world.
    // Modifies: this
    // EFFECTS: Renders controls string, and objects in the world.
    private void render() {
        drawControls();
        world.drawTerminal(screen);
    }

    // draws control instructions
    // MODIFIES: this
    // EFFECTS: renders controls information in the top-left corner of the screen
    private void drawControls() {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(TextColor.ANSI.WHITE);
        text.putString(1, 0, "Controls: Q = Quit");
        text.putString(1, 1, "          F = Remove All Balls");
        text.putString(1, 2, "          T = Add Ball");
        text.putString(1, 3, "        A/D = Move Player");
    }

    // Draws a character at a coordinate
    // MODIFIES: this
    // EFFECTS: Draws a given character with a given color on the
    // terminal screen at a specified coordinate.
    public static void drawAtCoordinate(Screen screen, Coordinate coordinate, char character, TextColor color) {
        TextGraphics text = screen.newTextGraphics();
        text.setForegroundColor(color);

        // puts the character at position x, y
        text.putString(coordinate.getX(), coordinate.getY(), String.valueOf(character));
    }

}
