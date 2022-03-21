package ui.demo;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import model.util.Coordinate;
import model.util.Vector2;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.object.World;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// A ball game demo that allows for a player to juggle balls around
public class JuggleDemo extends JPanel implements Runnable {

    public static final int defaultWidth = 1200;
    public static final int defaultHeight = 720;

    private World world;

    Thread panelThread;

    private Screen screen;
    private Terminal terminal;

    private boolean isActive;
    private int ticksPerSecond;

    private long previousTime;
    private long currentTime;

    private JsonWriter jsonWriter;
    public static final String juggleDemoPath = "./data/juggleWorld.json";

    // Constructor for JuggleDemo for phase 3 that showcases gravity and dynamics
    // EFFECTS: Constructs the demo, sets up the JPanel in which the simulation will be
    // played of a default width of 1200 px and height of 720 px, and creates a new JsonWriter object.
    public JuggleDemo() {
        isActive = true;
        previousTime = System.nanoTime();
        ticksPerSecond = 60;

        setDoubleBuffered(true); // double buffer to increase performance
        setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        setBackground(Color.BLACK);

        Vector2 worldSize = new Vector2(defaultWidth, defaultHeight);
        world = new World(worldSize);
        jsonWriter = new JsonWriter(juggleDemoPath);
    }



    // Begins the simulation
    // MODIFIES: this
    // EFFECTS: Default starting screen is shown, starts the simulation
    public void begin() {
        panelThread = new Thread(this);
        panelThread.start();
    }

    // MODIFIES: this
    // EFFECTS: A method of Runnable, a separate thread will call this function and call tick()
    @Override
    public void run() {
        startTicking();
    }

    // Starts time in the simulation
    // MODIFIES: this
    // EFFECTS: Every (1 second / ticksPerSecond), one tick occurs,
    // exits if the world is no longer active
    private void startTicking()  {

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
    private void tick(long deltaTime) {

        float deltaTimeInSeconds = World.convertNanoToSeconds(deltaTime);
        world.tick(deltaTimeInSeconds);

        // calls the paintComponent method
        repaint();
    }

    // Handles keyboard input
    // MODIFIES: this
    // EFFECTS: Handles quitting the program on a given key press, then forwards
    // the key pressed to check for functionality in the world.
    public void handleInput(int key) {

        if (key == 81) { // q
            isActive = false;
            return;
        } else if (key == 73) { // i
            saveWorld();
        } else if (key == 79) { // o
            loadWorld();
        }

        world.handleInput(key);
    }

    // Renders the current world.
    // Modifies: this
    // EFFECTS: Renders controls string, and objects in the world.
    private void render(Graphics g) {
        // drawControls();
        world.draw(g);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
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
        text.putString(1, 4, "        I/O = Save / Load");
    }

    // EFFECTS: Saves data about the world onto a JSON file
    public void saveWorld() {
        // this could become an abstract or higher-level method in the future

        try {
            jsonWriter.openWriter();
            jsonWriter.write(world);
            jsonWriter.closeWriter();
            System.out.println("Saved the world state to " + juggleDemoPath);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found for saving");
        }

    }

    // MODIFIES: this
    // EFFECTS: Loads world data from a JSON file, replaces old world with a new world
    public void loadWorld()  {

        try {
            world = JsonReader.readWorld(juggleDemoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
