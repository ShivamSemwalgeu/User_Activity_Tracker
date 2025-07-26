package UserActivityTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginLogoutLogger {

    public static void monitorLoginLogout() {
        try {
            ProcessBuilder builder = new ProcessBuilder("wevtutil", "qe", "Security", "/q:*[System[(EventID=4624)]]", "/f:text", "/c:5");
            Process process = builder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Event ID: 4624")) {
                        LoggerUtil.log("🔓 LOGIN event detected: Event ID 4624");
                    }
                }
            }
        } catch (Exception e) {
            try {
                LoggerUtil.log("❌ Error monitoring login/logout: " + e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
