package ui;

import ui.demo.JuggleDemo;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// JFrame window that holds the actual game
public class Window extends JFrame {

    JuggleDemo juggleDemo;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Physics Engine");
        //setResizable(false);
        setVisible(true);

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
