package vollelotte;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class SpielTest {

    @Test
    void playersTakeTurns() {
        Spiel s = new Spiel(List.of("A", "B"), new Random(1));
        s.neueRunde();
        assertEquals(0, s.aktuell());
        s.neueRunde();
        assertEquals(1, s.aktuell());
        s.neueRunde();
        assertEquals(0, s.aktuell());
    }

    @Test
    void deckReshufflesWhenEmpty() {
        Kartenstapel k = new Kartenstapel(new Random(1));
        var gezogen = new HashSet<Karte>();
        for (int i = 0; i < 120; i++) {
            gezogen.add(k.ziehen());
        }
        assertEquals(5, gezogen.size());
        assertEquals(30, k.anzahl());
    }
}
