package annorechner;

import static annorechner.Bevoelkerung.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.Test;

class RechnerTest {

    private static Ware ware(String name) {
        return Waren.WAREN.stream().filter(w -> w.name().equals(name)).findFirst().orElseThrow();
    }

    @Test
    void fischAddsGroupsAndRoundsBuildingsUp() {
        var e = ware("Fisch").berechne(Map.of(BETTLER, 9, BAUERN, 1000));
        assertEquals(63 + 10000, e.verbrauch());
        assertEquals(6, e.produktionen());
        assertEquals("6 (10063)", e.toString());
    }

    @Test
    void truncatesPerGroup() {
        // 3 * 2.2 = 6.6 -> 6, 3 * 1.6 = 4.8 -> 4
        assertEquals(10, ware("Gewürze").berechne(Map.of(PATRIZIER, 3, ADELIGE, 3)).verbrauch());
    }

    @Test
    void lockedGoodsShowZero() {
        assertEquals("0 (0)", ware("Wein").berechne(Map.of(ADELIGE, 1499)).toString());
        assertEquals(2, ware("Wein").berechne(Map.of(ADELIGE, 1500)).produktionen());
        assertEquals("0 (0)", ware("Bier").berechne(Map.of(PATRIZIER, 509)).toString());
        assertEquals(1, ware("Bier").berechne(Map.of(ADELIGE, 1)).produktionen());
    }

    @Test
    void emptyInputGivesNothing() {
        assertEquals("0 (0)", ware("Fisch").berechne(Map.of()).toString());
    }

    @Test
    void parsesFields() {
        assertEquals(0, Waren.parse(""));
        assertEquals(99999, Waren.parse("99999"));
        assertEquals(-1, Waren.parse("100000"));
        assertEquals(-1, Waren.parse("12a"));
        assertEquals(-1, Waren.parse("-1"));
    }

    @Test
    void allImagesExist() {
        for (var w : Waren.WAREN) {
            assertNotNull(getClass().getResource("/img/" + w.bild()), w.bild());
            assertNotNull(getClass().getResource("/img/" + w.kette()), w.kette());
        }
        for (var k : Waren.SONSTIGE) {
            assertNotNull(getClass().getResource("/img/" + k.bild() + ".png"), k.bild());
            assertNotNull(getClass().getResource("/img/" + k.kette() + ".png"), k.kette());
        }
        for (var g : Bevoelkerung.values()) {
            assertNotNull(getClass().getResource("/img/" + g.bild()), g.bild());
        }
    }
}
