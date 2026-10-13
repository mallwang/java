package arbeitszeit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class WorkTimeTest {
    @Test
    void breaksByDailyWorkTime() {
        assertEquals(0, WorkTime.breakMinutes(300));
        assertEquals(30, WorkTime.breakMinutes(480));
        assertEquals(45, WorkTime.breakMinutes(540));
    }

    @Test
    void leaveTimeAddsWorkAndBreak() {
        // 40 h/week = 8 h/day + 30 min break
        assertEquals(LocalTime.of(16, 30), WorkTime.leaveTime(LocalTime.of(8, 0), 40));
        // 39 h/week = 7:48 + 30 min
        assertEquals(LocalTime.of(16, 18), WorkTime.leaveTime(LocalTime.of(8, 0), 39));
    }

    @Test
    void remainingAndFormat() {
        LocalTime leave = LocalTime.of(16, 30);
        assertEquals("01:05 Std", WorkTime.format(WorkTime.remaining(LocalTime.of(15, 25, 40), leave)));
        assertEquals(Duration.ofMinutes(-10), WorkTime.remaining(LocalTime.of(16, 40, 30), leave));
        assertEquals("00:10 Std", WorkTime.format(Duration.ofMinutes(-10)));
    }

    @Test
    void settingsRoundTripAndFallback(@TempDir Path dir) throws Exception {
        Path file = dir.resolve("sub/config.properties");
        assertEquals(Settings.DEFAULT, Settings.load(file));
        new Settings(10, 38).save(file);
        assertEquals(new Settings(10, 38), Settings.load(file));
    }
}
