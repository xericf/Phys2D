package ui.demo;

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
    public static final int controlsFontSize = 15;
    public static final Font controlsFont = new Font("Courier", Font.PLAIN, controlsFontSize);

    private World world;

    Thread panelThread;

    private boolean isActive;
    private static int ticksPerSecond = 120;

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

        setDoubleBuffered(true); // double buffer to increase performance
        setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        setBackground(Color.BLACK);

        Vector2 worldSize = new Vector2(defaultWidth, defaultHeight);
        world = new World(worldSize);
        jsonWriter = new JsonWriter(juggleDemoPath);

        addListeners();
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
        world.draw(g);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawControls(g);
        render(g);
    }

    // draws control instructions
    // MODIFIES: this
    // EFFECTS: renders controls information in the top-left corner of the screen
    private void drawControls(Graphics g) {
        g.setColor(Color.GREEN);
        drawText(g, 1, controlsFontSize, "Controls: Q = Quit", controlsFont);
        drawText(g, 1, controlsFontSize * 2, "          F = Remove All Balls", controlsFont);
        drawText(g, 1, controlsFontSize * 3, "          T = Add Ball", controlsFont);
        drawText(g, 1, controlsFontSize * 4, "        A/D = Move Player", controlsFont);
        drawText(g, 1, controlsFontSize * 5, "        I/O = Save / Load", controlsFont);

    }

    // Draws text
    // MODIFIES: this
    // EFFECTS: Draws text at a given position with a certain font.
    private void drawText(Graphics g, int x, int y, String text, Font font) {
        g.setFont(font);
        g.drawString(text, x, y);
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
    // EFFECTS: Adds necessary mouse and mouse motion listeners to the JPanel (this) object
    private void addListeners() {
        addMouseListener(world);
        addMouseMotionListener(world);
    }

    // MODIFIES: this
    // EFFECTS: Removes the mouse and mouse motion listeners of this object.
    private void removeListeners() {
        // This is probably necessary to keep performance on JSON world loading.
        removeMouseListener(world);
        removeMouseMotionListener(world);
    }

    // MODIFIES: this
    // EFFECTS: Removes old mouse listeners, loads world data from a JSON file, replaces old world with a new world
    // and adds the new mouse listeners.
    public void loadWorld()  {
        try {
            // Necessary to make sure there is a world before removing any listeners
            World newWorld = JsonReader.readWorld(juggleDemoPath);

            removeListeners();
            world = newWorld;
            addListeners();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
