package UserActivityTracker;

import java.io.IOException;
import java.nio.file.*;

public class FileAccessLogger {

    public static void watchFolder(String folderPath) throws IOException {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(folderPath);

            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            LoggerUtil.log("📂 Watching folder: " + folderPath);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    String log = "📁 File Access Event: " + event.kind() + " - " + event.context();
                    LoggerUtil.log(log);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            LoggerUtil.log("❌ Error watching folder: " + e.getMessage());
        }
    }
}
