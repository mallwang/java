package vollelotte;

import java.util.List;
import java.util.Random;

/** Players, scores and whose turn it is. No win condition (the legacy game had none). */
public class Spiel {

    private final List<String> namen;
    private final int[] punkte;
    private final Kartenstapel stapel;
    private final Random zufall;
    private int aktuell = -1;

    public Spiel(List<String> namen, Random zufall) {
        this.namen = List.copyOf(namen);
        this.punkte = new int[namen.size()];
        this.zufall = zufall;
        this.stapel = new Kartenstapel(zufall);
    }

    public List<String> namen() {
        return namen;
    }

    public int punkte(int spieler) {
        return punkte[spieler];
    }

    public int aktuell() {
        return aktuell;
    }

    /** Next player draws a card and starts a round. */
    public Runde neueRunde() {
        aktuell = (aktuell + 1) % namen.size();
        return new Runde(stapel.ziehen(), () -> zufall.nextInt(6) + 1);
    }

    /** Adds the written points of the round to the current player. */
    public void schreiben(Runde runde) {
        punkte[aktuell] += runde.geschrieben();
    }
}
