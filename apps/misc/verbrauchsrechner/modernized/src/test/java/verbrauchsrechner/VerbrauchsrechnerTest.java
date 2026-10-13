package verbrauchsrechner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import verbrauchsrechner.Verbrauchsrechner.Good;
import verbrauchsrechner.Verbrauchsrechner.Population;

class VerbrauchsrechnerTest {

    @Test
    void fishForLegacyExamplePopulation() {
        // legacy: floor(8.00008) = 8 fishers, printed as 8 + 1
        var r = Verbrauchsrechner.calculate(Good.FISCH, new Population(1568, 2325, 1258, 2324));
        assertEquals(9, r.chains());
        assertEquals("9x (88,9%)", r.format());
    }

    @Test
    void noPopulationNeedsNoBuildings() {
        var r = Verbrauchsrechner.calculate(Good.FISCH, new Population(0, 0, 0, 0));
        assertEquals(0, r.chains());
    }

    @Test
    void wholeLoadHasNoDecimals() {
        assertEquals("1x (100%)", new Verbrauchsrechner.Result(Good.FISCH, 1, 100.0).format());
    }
}
