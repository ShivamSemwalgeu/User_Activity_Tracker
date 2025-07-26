package UserActivityTracker;

import com.sun.jna.*;
import com.sun.jna.win32.*;

import java.io.IOException;

public class ActivityTracker {

    // Define the User32 interface to call functions from user32.dll
    public interface User32 extends Library {
        // Load the user32.dll library using JNA
        User32 INSTANCE = (User32) Native.load("user32", User32.class);

        // Declare the necessary Windows API functions
        int GetForegroundWindow(); // Get the handle of the active window
        int GetWindowTextA(int hwnd, byte[] lpString, int nMaxCount); // Get the title of the active window
    }

    // Method to log active window changes
    public static void logActiveWindow() throws IOException {  // Add throws IOException here
        // Add the search path for user32.dll, although it should already be in the system path
        NativeLibrary.addSearchPath("user32", "C:\\Windows\\System32");

        // Create a byte array to store the window title
        byte[] windowTitle = new byte[512]; // Buffer to hold window title (adjust size if necessary)

        while (true) {
            // Get the handle of the active window
            int hwnd = User32.INSTANCE.GetForegroundWindow();

            // Get the title of the active window using the handle
            User32.INSTANCE.GetWindowTextA(hwnd, windowTitle, windowTitle.length);

            String activeWindow = Native.toString(windowTitle);

            // If the window title is non-empty, log it
            if (!activeWindow.isEmpty()) {
                String logMessage = "Active Window: " + activeWindow;
                System.out.println(logMessage);  // Output to console

                // Log to the main log file
                LoggerUtil.log(logMessage); // This now needs to handle IOException
            }

            try {
                Thread.sleep(1000);  // Wait for 1 second before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {  // Add throws IOException here
        logActiveWindow(); // Start the activity tracking
    }
}
