package UserActivityTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 class ApplicationUsageLogger {

    public static void logRunningProcesses() throws IOException {
        try {
            LoggerUtil.log("🖥️ Tracking running applications...");

            ProcessBuilder pb = new ProcessBuilder("tasklist");
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                LoggerUtil.log("🪟 Running Process: " + line);
            }
        } catch (Exception e) {
            LoggerUtil.log("❌ Error tracking apps: " + e.getMessage());
        }
    }
}
