package UserActivityTracker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerUtil {

    private static final String LOG_FILE = "userActivityLog.txt"; // Your main log file

    public static void log(String message) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(timestamp + " - " + message + "\n");
            writer.flush();
        }
    }
}
