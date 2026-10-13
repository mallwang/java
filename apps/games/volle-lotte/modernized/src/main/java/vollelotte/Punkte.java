package vollelotte;

import java.util.Collection;

/** Scoring of secured dice. */
public final class Punkte {

    /** Returned when the dice contain one that does not score. */
    public static final int UNGUELTIG = -1;

    private Punkte() {
    }

    /** Points of a single face value that occurs {@code anzahl} times (0 if it does not score). */
    static int wert(int augen, int anzahl) {
        if (anzahl >= 3) {
            int basis = augen == 1 ? 1000 : augen == 5 ? 500 : augen * 100;
            return basis << (anzahl - 3);
        }
        return switch (augen) {
            case 1 -> anzahl * 100;
            case 5 -> anzahl * 50;
            default -> 0;
        };
    }

    /** Sum over all face values, or {@link #UNGUELTIG} if a die does not score (e.g. a lone 2). */
    public static int bewerte(Collection<Integer> wuerfel) {
        int[] anzahl = new int[7];
        wuerfel.forEach(w -> anzahl[w]++);
        int summe = 0;
        for (int augen = 1; augen <= 6; augen++) {
            int p = wert(augen, anzahl[augen]);
            if (p == 0 && anzahl[augen] > 0) {
                return UNGUELTIG;
            }
            summe += p;
        }
        return summe;
    }

    /** Whether a throw contains at least one scoring die. Otherwise the round is lost. */
    public static boolean hatPunkte(Collection<Integer> wurf) {
        return wurf.stream().anyMatch(w -> w == 1 || w == 5)
                || wurf.stream().anyMatch(w -> wurf.stream().filter(w::equals).count() >= 3);
    }
}
