package  UserActivityTracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUsageLogger {

    public static void main(String[] args) {
        logRunningApps();
    }

    public static void logRunningApps() {
        try {
            // Execute Windows command to get running tasks
            Process process = Runtime.getRuntime().exec("tasklist");
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream())
            );

            String line;
            BufferedWriter writer = new BufferedWriter(new FileWriter("app_usage_log.txt", true));

            // Add timestamp
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("\n--- Running Applications at " + time + " ---\n");

            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }

            writer.close();
            reader.close();

            System.out.println("App usage logged successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
