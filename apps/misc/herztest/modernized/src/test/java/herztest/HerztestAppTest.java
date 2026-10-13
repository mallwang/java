package herztest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HerztestAppTest {
    @Test
    void parsesYes() {
        assertTrue(HerztestApp.isYes("j"));
        assertTrue(HerztestApp.isYes(" Ja "));
        assertFalse(HerztestApp.isYes("n"));
        assertFalse(HerztestApp.isYes(""));
        assertFalse(HerztestApp.isYes(null));
    }

    @Test
    void resultsPerHeartCount() {
        assertEquals("Schade, du hast kein ♥ gesammelt", HerztestApp.result(0));
        assertTrue(HerztestApp.result(3).startsWith("Du hast gewonnen"));
        assertEquals(3, HerztestApp.QUESTIONS.size());
    }
}
