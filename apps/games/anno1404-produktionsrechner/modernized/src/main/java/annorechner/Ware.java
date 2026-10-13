package annorechner;

import java.util.Map;
import java.util.Set;

/**
 * A good with the consumption per inhabitant and minute (x1000 would be per ton, as in the game) and the output of one
 * production building.
 *
 * @param bild   icon file below {@code img/}
 * @param kette  image of the production chain
 * @param spalte population group below which the good is shown
 * @param rate   consumption per inhabitant and minute, groups not listed consume nothing
 * @param schwelle group whose count unlocks the good, or null if always unlocked
 * @param ab     inhabitants of {@code schwelle} needed to unlock
 * @param auch   groups that unlock it with any count above 0
 */
public record Ware(String name, String bild, String kette, Bevoelkerung spalte, int prodProMinute,
        Map<Bevoelkerung, Double> rate, Bevoelkerung schwelle, int ab, Set<Bevoelkerung> auch) {

    /** Buildings needed and the consumption they cover, shown as "buildings (consumption)". */
    public record Ergebnis(int produktionen, int verbrauch) {
        @Override
        public String toString() {
            return produktionen + " (" + verbrauch + ")";
        }
    }

    public Ergebnis berechne(Map<Bevoelkerung, Integer> anzahl) {
        if (!freigeschaltet(anzahl)) {
            return new Ergebnis(0, 0);
        }
        int verbrauch = 0;
        for (var e : rate.entrySet()) {
            // legacy truncated every group separately
            verbrauch += (int) (anzahl.getOrDefault(e.getKey(), 0) * e.getValue());
        }
        return new Ergebnis((verbrauch + prodProMinute - 1) / prodProMinute, verbrauch);
    }

    private boolean freigeschaltet(Map<Bevoelkerung, Integer> anzahl) {
        return schwelle == null || anzahl.getOrDefault(schwelle, 0) >= ab
                || auch.stream().anyMatch(g -> anzahl.getOrDefault(g, 0) > 0);
    }

}
