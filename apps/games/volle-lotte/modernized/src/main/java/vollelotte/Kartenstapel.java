package vollelotte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Shuffled draw pile; reshuffles when empty. */
public class Kartenstapel {

    private final List<Karte> karten;
    private final Random zufall;
    private final List<Karte> stapel = new ArrayList<>();

    /** The deck of the legacy game: ten of each bonus card. The full deck was commented out there. */
    public Kartenstapel(Random zufall) {
        this(zufall, List.of(Karte.BONUS_200, Karte.BONUS_300, Karte.BONUS_400, Karte.BONUS_500, Karte.BONUS_600), 10);
    }

    public Kartenstapel(Random zufall, List<Karte> arten, int jeweils) {
        this.zufall = zufall;
        this.karten = arten.stream().flatMap(k -> Collections.nCopies(jeweils, k).stream()).toList();
    }

    public int anzahl() {
        return stapel.size();
    }

    public Karte ziehen() {
        if (stapel.isEmpty()) {
            stapel.addAll(karten);
            Collections.shuffle(stapel, zufall);
        }
        return stapel.removeLast();
    }
}
