package vollelotte;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

/**
 * One turn of one player: throw the free dice, secure scoring dice, throw the rest again, then write the points
 * (at least {@link #MINDESTPUNKTE}) or lose everything when a throw has no scoring die.
 */
public class Runde {

    public static final int WUERFEL = 6;
    public static final int MINDESTPUNKTE = 350;

    private final Karte karte;
    private final IntSupplier wuerfel;
    /** Face values, 0 = not thrown yet. */
    private final int[] augen = new int[WUERFEL];
    /** Secured in an earlier throw, final. */
    private final boolean[] fest = new boolean[WUERFEL];
    /** Secured after the latest throw, can still be given back. */
    private final boolean[] gewaehlt = new boolean[WUERFEL];
    private int festePunkte;
    private boolean verloren;

    /** @param wuerfel supplies die values 1..6 */
    public Runde(Karte karte, IntSupplier wuerfel) {
        this.karte = karte;
        this.wuerfel = wuerfel;
    }

    public Karte karte() {
        return karte;
    }

    public int augen(int i) {
        return augen[i];
    }

    public boolean istFest(int i) {
        return fest[i];
    }

    public boolean istGewaehlt(int i) {
        return gewaehlt[i];
    }

    public boolean verloren() {
        return verloren;
    }

    private boolean geworfen() {
        return augen[0] != 0;
    }

    /** Dice neither final nor secured now. */
    private int freie() {
        int n = 0;
        for (int i = 0; i < WUERFEL; i++) {
            if (!fest[i] && !gewaehlt[i]) {
                n++;
            }
        }
        return n;
    }

    /** Points of the dice secured after the latest throw, or {@link Punkte#UNGUELTIG}. */
    private int auswahlPunkte() {
        List<Integer> werte = new ArrayList<>();
        for (int i = 0; i < WUERFEL; i++) {
            if (gewaehlt[i]) {
                werte.add(augen[i]);
            }
        }
        return werte.isEmpty() ? 0 : Punkte.bewerte(werte);
    }

    /** Points of the round so far, shown to the player; 0 for an invalid selection. */
    public int punkte() {
        return Math.max(auswahlPunkte(), 0) + festePunkte;
    }

    private boolean auswahlGueltig() {
        boolean leer = true;
        for (boolean g : gewaehlt) {
            leer &= !g;
        }
        return !leer && auswahlPunkte() != Punkte.UNGUELTIG;
    }

    /** Secure or give back die {@code i} of the latest throw. */
    public void umschalten(int i) {
        if (!verloren && geworfen() && !fest[i]) {
            gewaehlt[i] = !gewaehlt[i];
        }
    }

    public boolean kannWerfen() {
        return !verloren && (!geworfen() || (auswahlGueltig() && freie() > 0));
    }

    public boolean kannSchreiben() {
        return !verloren && geworfen() && auswahlGueltig() && punkte() >= MINDESTPUNKTE;
    }

    /** Throws all dice (first throw) or the free ones. Sets {@link #verloren()} if nothing scores. */
    public void werfen() {
        if (!kannWerfen()) {
            throw new IllegalStateException("Cannot throw now");
        }
        festePunkte += Math.max(auswahlPunkte(), 0);
        List<Integer> wurf = new ArrayList<>();
        for (int i = 0; i < WUERFEL; i++) {
            if (gewaehlt[i]) {
                fest[i] = true;
                gewaehlt[i] = false;
            }
            if (!fest[i]) {
                augen[i] = wuerfel.getAsInt();
                wurf.add(augen[i]);
            }
        }
        verloren = !Punkte.hatPunkte(wurf);
    }

    /** Points to write for this round including the card bonus when all six dice are secured. */
    public int geschrieben() {
        if (!kannSchreiben()) {
            throw new IllegalStateException("Cannot write now");
        }
        boolean alle = true;
        for (int i = 0; i < WUERFEL; i++) {
            alle &= fest[i] || gewaehlt[i];
        }
        return punkte() + (alle ? karte.bonus() : 0);
    }
}
