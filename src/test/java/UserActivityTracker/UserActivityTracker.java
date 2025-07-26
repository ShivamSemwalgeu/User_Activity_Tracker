package UserActivityTracker;

import java.io.IOException;

public class UserActivityTracker {

    public static void main(String[] args) throws IOException {
        LoggerUtil.log("🚀 Starting User Activity Tracker...");

        Thread loginLogoutThread = new Thread(LoginLogoutLogger::monitorLoginLogout);

        Thread fileAccessThread = new Thread(() -> {
            try {
                FileAccessLogger.watchFolder("C:\\Users\\hp\\OneDrive\\Desktop\\dummy");
            } catch (IOException e) {
                try {
                    LoggerUtil.log("❌ Error in FileAccessLogger: " + e.getMessage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        Thread appUsageThread = new Thread(() -> {
            try {
                ApplicationUsageLogger.logRunningProcesses();
            } catch (IOException e) {
                try {
                    LoggerUtil.log("❌ Error in ApplicationUsageLogger: " + e.getMessage());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loginLogoutThread.start();
        fileAccessThread.start();
        appUsageThread.start();

        try {
            loginLogoutThread.join();
            fileAccessThread.join();
            appUsageThread.join();
        } catch (InterruptedException e) {
            try {
                LoggerUtil.log("❌ Tracker interrupted: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }

        LoggerUtil.log("✅ User Activity Tracking completed.");
    }
}
