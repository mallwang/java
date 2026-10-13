package leet;

import java.util.Map;

/** Text to leetspeak conversion. */
public final class Leet {
    public enum Mode { SIMPLE, ULTIMATE }

    private static final Map<Character, String> SIMPLE = Map.of(
            'a', "4", 'b', "8", 'e', "3", 'g', "9", 'i', "1", 'o', "0", 's', "5", 't', "7", 'z', "2");

    private static final Map<Character, String> ULTIMATE = Map.ofEntries(
            Map.entry('a', "4"), Map.entry('b', "l3"), Map.entry('c', "©"), Map.entry('d', "l)"),
            Map.entry('e', "3"), Map.entry('f', "|#"), Map.entry('g', "9"), Map.entry('h', "/-/"),
            Map.entry('i', "1"), Map.entry('j', "j"), Map.entry('k', "]{"), Map.entry('l', "|_"),
            Map.entry('m', "/V\\"), Map.entry('n', "/\\/"), Map.entry('o', "0"), Map.entry('p', "|>"),
            Map.entry('q', "<|"), Map.entry('r', "®"), Map.entry('s', "5"), Map.entry('t', "7"),
            Map.entry('u', "/_/"), Map.entry('v', "\\/"), Map.entry('w', "\\/\\/"), Map.entry('x', "><"),
            Map.entry('y', "¥"), Map.entry('z', "2"));

    private Leet() {}

    /** Lower-cases the input and replaces mapped letters; everything else is kept. */
    public static String convert(String text, Mode mode) {
        Map<Character, String> table = mode == Mode.SIMPLE ? SIMPLE : ULTIMATE;
        StringBuilder out = new StringBuilder();
        for (char c : text.toLowerCase().toCharArray()) {
            out.append(table.getOrDefault(c, String.valueOf(c)));
        }
        return out.toString();
    }
}
