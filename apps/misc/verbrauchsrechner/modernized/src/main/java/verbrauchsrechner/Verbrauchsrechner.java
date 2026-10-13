package verbrauchsrechner;

import java.util.Arrays;
import java.util.Locale;

/**
 * Consumption calculator for Anno 1404: how many production buildings are needed so that
 * the given population is supplied with each good.
 */
public final class Verbrauchsrechner {

    /** Seconds per consumption tick. */
    static final double TICK_SECONDS = 30.0;

    public record Population(int bauern, int handwerker, int kaufleute, int adlige) {}

    /**
     * A good with its consumption per resident and tick (bauern, handwerker, kaufleute, adlige)
     * and the production time of its final production step in seconds.
     */
    public enum Good {
        FISCH(110, 0.00042, 0.00030, 0.00025, 0.00022),
        BROT(220, 0, 0.00025, 0.00020, 0.00018),
        FLEISCH(630, 0, 0, 0, 0.00017),
        MILCH(235, 0.00050, 0.00036, 0.00030, 0.00026),
        MOST(190, 0, 0.00022, 0.00018, 0.00016),
        BIER(330, 0, 0, 0.00012, 0.00011),
        WEIN(1260, 0, 0, 0, 0.0009),
        WOLLGEWAENDER(200, 0.00027, 0.00020, 0.00016, 0.00014),
        LEINENGEWAENDER(210, 0, 0.00015, 0.00012, 0.00010),
        LEDERWAEMSER(420, 0, 0, 0.00004, 0.00003),
        PELZMAENTEL(600, 0, 0, 0.00004, 0.00003),
        BROKATGEWAENDER(3150, 0, 0, 0, 0.00002),
        BUECHER(630, 0, 0, 0.00012, 0.00011),
        KERZENLEUCHTER(840, 0, 0, 0, 0.00008),
        BRILLEN(1260, 0, 0, 0, 0.00003);

        private final int productionSeconds;
        private final double[] perResident;

        Good(int productionSeconds, double... perResident) {
            this.productionSeconds = productionSeconds;
            this.perResident = perResident;
        }

        /** Consumption of the whole population per tick. */
        double consumption(Population p) {
            return p.bauern() * perResident[0] + p.handwerker() * perResident[1]
                    + p.kaufleute() * perResident[2] + p.adlige() * perResident[3];
        }

        /** Production of one building per tick. */
        double productionPerBuilding() {
            return TICK_SECONDS / productionSeconds;
        }
    }

    /** @param chains buildings needed (rounded up); @param utilization average load per building in percent */
    public record Result(Good good, int chains, double utilization) {
        /** e.g. {@code 9x (88,9%)}; a whole-number load is shown without decimals. */
        public String format() {
            var load = String.format(Locale.GERMAN, "%.1f", utilization);
            if (load.endsWith(",0")) {
                load = load.substring(0, load.length() - 2);
            }
            return chains + "x (" + load + "%)";
        }
    }

    public static Result calculate(Good good, Population population) {
        var needed = good.consumption(population) / good.productionPerBuilding();
        // tiny epsilon: 8.0000000001 must not demand a 9th building through floating point noise
        var chains = (int) Math.ceil(needed - 1e-9);
        return new Result(good, chains, chains == 0 ? 0 : needed / chains * 100);
    }

    public static void main(String[] args) {
        var p = args.length == 4
                ? new Population(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]), Integer.parseInt(args[3]))
                : new Population(1568, 2325, 1258, 2324);
        System.out.println(p);
        Arrays.stream(Good.values()).map(g -> calculate(g, p))
                .forEach(r -> System.out.printf("%-16s %s%n", r.good(), r.format()));
    }

    private Verbrauchsrechner() {}
}
