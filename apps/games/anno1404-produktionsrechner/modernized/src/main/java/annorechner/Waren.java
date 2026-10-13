package annorechner;

import static annorechner.Bevoelkerung.*;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** The goods of ANNO 1404 with the numbers of the legacy calculator. */
public final class Waren {

    /** Goods of the buildings that are only shown as a production chain. */
    public record Kette(String name, String bild, String kette) {
    }

    public static final List<Ware> WAREN = List.of(
            ware("Fisch", "Fisch", "Fischproduktion", BAUERN, 2000, r(7, 10, 4, 2.2, 1.6, 0, 0)),
            ware("Most", "Most", "Mostproduktion", BAUERN, 1500, r(3, 4.4, 4.4, 2.3, 1.3, 0, 0), BAUERN, 60,
                    EnumSet.of(BETTLER, BUERGER, PATRIZIER, ADELIGE)),
            ware("Gewürze", "Gewuerze", "Gewuerzproduktion", BUERGER, 2000, r(0, 0, 4, 2.2, 1.6, 0, 0)),
            ware("Leinenkutten", "Leinenkutten", "Leinenkuttenproduktion", BUERGER, 2000, r(0, 0, 4.2, 1.9, 0.8, 0, 0)),
            ware("Brot", "Brot", "Brotproduktion", PATRIZIER, 4000, r(0, 0, 0, 5.5, 3.9, 0, 0)),
            ware("Bier", "Bier", "Bierproduktion", PATRIZIER, 1500, r(0, 0, 0, 2.4, 1.4, 0, 0), PATRIZIER, 510,
                    EnumSet.of(ADELIGE)),
            ware("Lederwämser", "Lederwaemser", "Lederwaemseproduktion", PATRIZIER, 4000, r(0, 0, 0, 2.8, 1.6, 0, 0),
                    PATRIZIER, 690, EnumSet.of(ADELIGE)),
            ware("Bücher", "Buecher", "Buecherproduktion", PATRIZIER, 3000, r(0, 0, 0, 1.6, 0.9, 0, 0), PATRIZIER, 940,
                    EnumSet.of(ADELIGE)),
            ware("Fleisch", "Fleisch", "Fleischproduktion", ADELIGE, 2500, r(0, 0, 0, 0, 2.2, 0, 0)),
            ware("Wein", "Wein", "Weinproduktion", ADELIGE, 2000, r(0, 0, 0, 0, 2.0, 0, 0), ADELIGE, 1500,
                    EnumSet.noneOf(Bevoelkerung.class)),
            ware("Pelzmäntel", "Pelzmaentel", "Pelzmaentelproduktion", ADELIGE, 2500, r(0, 0, 0, 0, 1.6, 0, 0), ADELIGE,
                    950, EnumSet.noneOf(Bevoelkerung.class)),
            ware("Brokatgewänder", "Brokatgewaender", "Brokatgewaenderproduktion", ADELIGE, 3000,
                    r(0, 0, 0, 0, 1.42, 0, 0), ADELIGE, 4000, EnumSet.noneOf(Bevoelkerung.class)),
            // legacy: patricians only count once the nobles unlock it
            ware("Kerzenleuchter", "Kerzenleuchter", "Kerzenleuchterproduktion", ADELIGE, 2000,
                    r(0, 0, 0, 0.8, 0.6, 0, 0), ADELIGE, 3000, EnumSet.noneOf(Bevoelkerung.class)),
            ware("Brillen", "Brillen", "Brillenproduktion", ADELIGE, 2000, r(0, 0, 0, 0, 1.17, 0, 0), ADELIGE, 2200,
                    EnumSet.noneOf(Bevoelkerung.class)),
            ware("Datteln", "Datteln", "Dattelproduktion", NOMADEN, 3000, r(0, 0, 0, 0, 0, 6.66, 5.0)),
            ware("Milch", "Milch", "Milchproduktion", NOMADEN, 1500, r(0, 0, 0, 0, 0, 3.44, 2.25), NOMADEN, 145,
                    EnumSet.of(GESANDTE)),
            ware("Teppiche", "Teppiche", "Teppichproduktion", NOMADEN, 1500, r(0, 0, 0, 0, 0, 1.65, 1.0), NOMADEN, 295,
                    EnumSet.of(GESANDTE)),
            ware("Kaffee", "Kaffee", "Kaffeeproduktion", GESANDTE, 1000, r(0, 0, 0, 0, 0, 0, 1.0)),
            ware("Perlenketten", "Perlenketten", "Perlenkettenproduktion", GESANDTE, 1000, r(0, 0, 0, 0, 0, 0, 1.33),
                    GESANDTE, 1040, EnumSet.noneOf(Bevoelkerung.class)),
            ware("Duftwasser", "Duftwasser", "Duftwasserproduktion", GESANDTE, 1000, r(0, 0, 0, 0, 0, 0, 0.8), GESANDTE,
                    2600, EnumSet.noneOf(Bevoelkerung.class)),
            ware("Marzipan", "Marzipan", "Marzipanproduktion", GESANDTE, 4000, r(0, 0, 0, 0, 0, 0, 1.63), GESANDTE, 4360,
                    EnumSet.noneOf(Bevoelkerung.class)));

    public static final List<Kette> SONSTIGE = List.of(
            new Kette("Seile", "Seile", "Seileproduktion"),
            new Kette("Messing", "Messing", "Messingproduktion"),
            new Kette("Stein", "Stein", "Steinproduktion"),
            new Kette("Kanonen", "Kanonen", "Kanonenproduktion"),
            new Kette("Werkzeuge", "Werkzeuge", "Werkzeugeproduktion"),
            new Kette("Mosaik", "Mosaik", "Mosaikproduktion"),
            new Kette("Waffen", "Waffen", "Waffenproduktion"),
            new Kette("Glas", "Glas", "Glasproduktion"),
            new Kette("Salz", "Salz", "Salzproduktion"),
            new Kette("Eisen", "Eisen", "Eisenproduktion"),
            new Kette("Holz", "Holz", "Holzproduktion"),
            new Kette("Kriegsmaschinen", "Kriegsmaschinen", "Kriegsmaschinenproduktion"));

    private Waren() {
    }

    /** Parses a field: empty is 0, digits only and at most 5 characters, otherwise -1. */
    public static int parse(String text) {
        if (text.isEmpty()) {
            return 0;
        }
        return text.matches("[0-9]{1,5}") ? Integer.parseInt(text) : -1;
    }

    /** Consumption in the order of {@link Bevoelkerung}; zeros are dropped. */
    private static Map<Bevoelkerung, Double> r(double... werte) {
        var m = new EnumMap<Bevoelkerung, Double>(Bevoelkerung.class);
        for (var g : Bevoelkerung.values()) {
            if (werte[g.ordinal()] > 0) {
                m.put(g, werte[g.ordinal()]);
            }
        }
        return m;
    }

    private static Ware ware(String name, String bild, String kette, Bevoelkerung spalte, int prod,
            Map<Bevoelkerung, Double> rate) {
        return new Ware(name, bild + ".png", kette + ".png", spalte, prod, rate, null, 0, Set.of());
    }

    private static Ware ware(String name, String bild, String kette, Bevoelkerung spalte, int prod,
            Map<Bevoelkerung, Double> rate, Bevoelkerung schwelle, int ab, Set<Bevoelkerung> auch) {
        return new Ware(name, bild + ".png", kette + ".png", spalte, prod, rate, schwelle, ab, auch);
    }
}
