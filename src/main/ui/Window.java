package ui;

import model.log.Logger;
import ui.demo.JuggleDemo;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// JFrame window that holds the actual game
public class Window extends JFrame {

    JuggleDemo juggleDemo;

    // Main method, used for starting the game
    public static void main(String[] args) {
        new Window();
    }

    // EFFECTS: Constructs a JFrame that allows for demos to be played in
    public Window() {
        super("Physics Engine");
        initializeWindowSettings();

        juggleDemo = new JuggleDemo();
        add(juggleDemo);
        addKeyListener(new KeyHandler());

        pack(); // sizes all components so they are at their preferred sizes

        juggleDemo.begin();
    }

    // MODIFIES: this
    // EFFECTS: initializes the current window.
    private void initializeWindowSettings() {

        setTitle("Physics Engine");
        //setResizable(false);
        setVisible(true);

        // Window close handler
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                close();
            }
        });
    }

    // EFFECTS: Code ran before the application is closed out of. Prints the event log on exit.
    private void close() {
        Logger.printLog();
        System.exit(0);
    }



    // KeyEvent handler, credit to SpaceInvadersRefactored
    private class KeyHandler extends KeyAdapter {
        // EFFECTS: On each key press within the JFrame, send the input down towards the demo
        @Override
        public void keyPressed(KeyEvent e) {
            juggleDemo.handleInput(e.getKeyCode());
        }
    }

}
