package  UserActivityTracker;


import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WindowLogger {

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("window_log.txt", true)) {
            while (true) {
                HWND hwnd = User32.INSTANCE.GetForegroundWindow();
                char[] windowText = new char[512];
                User32.INSTANCE.GetWindowText(hwnd, windowText, 512);

                String windowTitle = Native.toString(windowText);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                if (!windowTitle.isEmpty()) {
                    String log = timestamp + " - " + windowTitle + "\n";
                    writer.write(log);
                    writer.flush();
                    System.out.print(log); // Also print to console
                }

                Thread.sleep(2000); // Wait 2 seconds
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

