package vollelotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class PunkteTest {

    @Test
    void singles() {
        assertEquals(200, Punkte.bewerte(List.of(1, 5, 5)));
    }

    @Test
    void threeAndMoreOfAKind() {
        assertEquals(1000, Punkte.bewerte(List.of(1, 1, 1)));
        assertEquals(4000, Punkte.bewerte(List.of(1, 1, 1, 1, 1)));
        assertEquals(500, Punkte.bewerte(List.of(5, 5, 5)));
        assertEquals(400, Punkte.bewerte(List.of(4, 4, 4)));
        assertEquals(1200, Punkte.bewerte(List.of(6, 6, 6, 6)));
    }

    @Test
    void nonScoringDieIsInvalid() {
        assertEquals(Punkte.UNGUELTIG, Punkte.bewerte(List.of(1, 2)));
        assertEquals(Punkte.UNGUELTIG, Punkte.bewerte(List.of(3, 3)));
    }

    @Test
    void bust() {
        assertFalse(Punkte.hatPunkte(List.of(2, 3, 4, 6, 2, 4)));
        assertTrue(Punkte.hatPunkte(List.of(2, 2, 2, 3, 4, 6)));
        assertTrue(Punkte.hatPunkte(List.of(4, 4, 4)));
        assertTrue(Punkte.hatPunkte(List.of(2, 3, 4, 6, 2, 5)));
    }
}
