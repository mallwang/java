package gleitzeit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;

/** Same file format as GZR v3.5: {@code netto x kommenH x kommenM x essenH x essenM}, e.g. {@code 1700x08x04x12x00}. */
public record Config(int netto, LocalTime arrival, LocalTime meal) {
    public static final Config DEFAULT = new Config(1700, LocalTime.of(8, 0), LocalTime.of(12, 0));

    public static Config load(Path file) {
        try {
            String[] p = Files.readString(file).trim().split("x");
            return new Config(Integer.parseInt(p[0]),
                    LocalTime.of(Integer.parseInt(p[1]), Integer.parseInt(p[2])),
                    LocalTime.of(Integer.parseInt(p[3]), Integer.parseInt(p[4])));
        } catch (IOException | RuntimeException e) {
            return DEFAULT;
        }
    }

    public void save(Path file) throws IOException {
        Files.createDirectories(file.getParent());
        Files.writeString(file, "%dx%02dx%02dx%02dx%02d".formatted(
                netto, arrival.getHour(), arrival.getMinute(), meal.getHour(), meal.getMinute()));
    }
}
