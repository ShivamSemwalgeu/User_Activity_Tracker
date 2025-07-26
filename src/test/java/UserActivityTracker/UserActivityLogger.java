package UserActivityTracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserActivityLogger {

    private static final String LOG_FILE = "userActivitylog.txt";

    public static void log(String activity) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().toString();
            String logEntry = timestamp + " - " + activity;
            writer.write(logEntry + "\n");
            writer.flush();
            System.out.println("Logged to userActivitylog.txt: " + logEntry); // ✅ Confirm it works
        } catch (IOException e) {
            System.out.println("Failed to write log: " + e.getMessage());
            e.printStackTrace(); // ✅ Show full error
        }
    }

    public static void main(String[] args) {
        log("⚙️ Test log from main method."); // 🔁 Run this to check if file is written
    }
}
