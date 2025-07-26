package UserActivityTracker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class UserActivityListener {

    public static void main(String[] args) {
        // Create a hidden JFrame to capture key and mouse events
        JFrame frame = new JFrame("Activity Tracker");

        // Add a KeyListener to capture key presses
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("Key Typed: " + e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        // Add a MouseMotionListener to capture mouse movements
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse Moved: X=" + e.getX() + ", Y=" + e.getY());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("Mouse Dragged: X=" + e.getX() + ", Y=" + e.getY());
            }
        });

        // Hide the frame and make it invisible while still listening
        frame.setUndecorated(true);
        frame.setOpacity(0); // make it invisible
        frame.setSize(1, 1); // set to minimal size
        frame.setLocation(-100, -100); // move off-screen
        frame.setVisible(true);

        // Keep the frame running so listeners can capture events
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
