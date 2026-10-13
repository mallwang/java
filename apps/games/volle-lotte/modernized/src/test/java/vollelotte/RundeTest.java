package vollelotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

class RundeTest {

    private static Runde runde(Karte karte, Integer... wuerfe) {
        Iterator<Integer> it = List.of(wuerfe).iterator();
        return new Runde(karte, it::next);
    }

    @Test
    void writeAfterSecuringAllDiceGivesBonus() {
        Runde r = runde(Karte.BONUS_300, 1, 1, 1, 5, 5, 5, 5, 5, 5);
        r.werfen();
        for (int i = 0; i < 3; i++) {
            r.umschalten(i);
        }
        assertEquals(1000, r.punkte());
        r.werfen();
        for (int i = 3; i < 6; i++) {
            r.umschalten(i);
        }
        assertEquals(1500, r.punkte());
        assertTrue(r.kannSchreiben());
        assertEquals(1800, r.geschrieben());
    }

    @Test
    void noBonusWithFreeDice() {
        Runde r = runde(Karte.BONUS_600, 1, 1, 1, 2, 3, 4);
        r.werfen();
        for (int i = 0; i < 3; i++) {
            r.umschalten(i);
        }
        assertEquals(1000, r.geschrieben());
    }

    @Test
    void belowMinimumCannotWrite() {
        Runde r = runde(Karte.BONUS_200, 1, 2, 3, 4, 6, 6);
        r.werfen();
        r.umschalten(0);
        assertEquals(100, r.punkte());
        assertTrue(r.kannWerfen());
        assertFalse(r.kannSchreiben());
    }

    @Test
    void invalidSelectionBlocksThrow() {
        Runde r = runde(Karte.BONUS_200, 1, 2, 3, 4, 6, 6);
        r.werfen();
        assertFalse(r.kannWerfen());
        r.umschalten(1);
        assertFalse(r.kannWerfen());
        assertThrows(IllegalStateException.class, r::werfen);
    }

    @Test
    void bustLosesRound() {
        Runde r = runde(Karte.BONUS_200, 2, 3, 4, 6, 2, 4);
        r.werfen();
        assertTrue(r.verloren());
        assertFalse(r.kannWerfen());
    }
}
