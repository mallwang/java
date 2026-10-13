package noten;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NotenAppTest {
    @Test
    void averagesDigits() {
        assertEquals(8.0 / 3, NotenApp.average("233"), 1e-9);
        assertEquals(1.0, NotenApp.average("1"), 1e-9);
    }

    @Test
    void rejectsInvalidInput() {
        assertEquals("7 ist keine Note",
                assertThrows(IllegalArgumentException.class, () -> NotenApp.average("237")).getMessage());
        assertThrows(IllegalArgumentException.class, () -> NotenApp.average("0"));
        assertThrows(IllegalArgumentException.class, () -> NotenApp.average(""));
    }
}
