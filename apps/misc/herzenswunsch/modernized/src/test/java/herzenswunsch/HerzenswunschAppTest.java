package herzenswunsch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HerzenswunschAppTest {
    @Test
    void repeatsHearts() {
        assertEquals("♥", HerzenswunschApp.hearts(1));
        assertEquals("♥ ♥ ♥", HerzenswunschApp.hearts(3));
    }

    @Test
    void enforcesRange() {
        assertEquals(39, HerzenswunschApp.hearts(20).length());
        assertThrows(IllegalArgumentException.class, () -> HerzenswunschApp.hearts(21));
        assertThrows(IllegalArgumentException.class, () -> HerzenswunschApp.hearts(0));
    }
}
