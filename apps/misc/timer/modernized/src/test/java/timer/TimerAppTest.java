package timer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimerAppTest {
    @Test
    void formatsDuration() {
        assertEquals("00 : 00 : 00", TimerApp.formatDuration(0));
        assertEquals("00 : 01 : 05", TimerApp.formatDuration(65));
        assertEquals("01 : 00 : 00", TimerApp.formatDuration(3600));
    }
}
