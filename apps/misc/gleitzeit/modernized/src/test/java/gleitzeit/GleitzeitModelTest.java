package gleitzeit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GleitzeitModelTest {
    @Test
    void feierabendAndBreaks() {
        var r = GleitzeitModel.compute(LocalTime.of(8, 0), LocalTime.of(8, 0));
        assertEquals(LocalTime.of(16, 18), r.leave());
        assertEquals(LocalTime.of(14, 0), r.pause1());
        assertEquals(LocalTime.of(17, 30), r.pause2());
        assertEquals(LocalTime.of(18, 45), r.maxEnd());
    }

    @Test
    void saldoIsZeroAtFeierabendAndPausesDontCount() {
        assertEquals(0, GleitzeitModel.compute(LocalTime.of(8, 0), LocalTime.of(16, 18)).saldo());
        // 14:05: 365 min elapsed, 5 min of pause 1 -> 360 worked
        assertEquals(360 - 468, GleitzeitModel.compute(LocalTime.of(8, 0), LocalTime.of(14, 5)).saldo());
    }

    @Test
    void futureArrivalAndEarlyStart() {
        var f = GleitzeitModel.compute(LocalTime.of(9, 0), LocalTime.of(8, 0));
        assertEquals(-468, f.saldo());
        assertTrue(f.warnings().stream().anyMatch(w -> w.contains("Zukunft")));
        assertEquals(LocalTime.of(6, 30), GleitzeitModel.compute(LocalTime.of(6, 0), LocalTime.of(7, 0)).arrival());
    }

    @Test
    void lateStartCapsMaxAndDropsPause2() {
        var r = GleitzeitModel.compute(LocalTime.of(10, 0), LocalTime.of(10, 0));
        assertEquals(GleitzeitModel.LATEST_END, r.maxEnd());
        assertNull(r.pause2());
    }

    @Test
    void configRoundTrip(@TempDir Path dir) throws Exception {
        Path f = dir.resolve("x/config_gzr_3_5.txt");
        assertEquals(Config.DEFAULT, Config.load(f));
        var c = new Config(1700, LocalTime.of(8, 4), LocalTime.of(12, 0));
        c.save(f);
        assertEquals("1700x08x04x12x00", java.nio.file.Files.readString(f));
        assertEquals(c, Config.load(f));
    }
}
