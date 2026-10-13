package arbeitszeit;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Refresh interval (seconds) and weekly hours; persisted as a .properties file. */
public record Settings(int refreshSeconds, int weeklyHours) {
    public static final Settings DEFAULT = new Settings(1, 40);

    public static Settings load(Path file) {
        try (Reader in = Files.newBufferedReader(file)) {
            Properties p = new Properties();
            p.load(in);
            return new Settings(
                    Integer.parseInt(p.getProperty("refresh", "1")),
                    Integer.parseInt(p.getProperty("worktime", "40")));
        } catch (IOException | NumberFormatException e) {
            return DEFAULT;
        }
    }

    public void save(Path file) throws IOException {
        Files.createDirectories(file.getParent());
        Properties p = new Properties();
        p.setProperty("refresh", String.valueOf(refreshSeconds));
        p.setProperty("worktime", String.valueOf(weeklyHours));
        try (Writer out = Files.newBufferedWriter(file)) {
            p.store(out, "Arbeitszeitrechner");
        }
    }
}
