package kopfrechnen;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Pure logic of the small mental-arithmetic apps (no UI, testable without a display). */
final class Arithmetic {
    private static final Pattern TERM = Pattern.compile("\\s*(-?\\d+)\\s*([-+*/])\\s*(-?\\d+)\\s*");
    private static final String[] DIGIT_WORDS =
            {"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun"};
    static final int SECONDS_PER_YEAR = 365 * 86_400;

    private Arithmetic() {}

    /** Evaluates "a op b" with integer operands; the result is a double like in v1.0 (so 7/2 = 3.5). */
    static double calculate(String term) {
        var m = TERM.matcher(term);
        if (!m.matches()) {
            throw new IllegalArgumentException("Erwartet: Zahl, Operator (+ - * /), Zahl");
        }
        double a = Integer.parseInt(m.group(1));
        double b = Integer.parseInt(m.group(3));
        return switch (m.group(2).charAt(0)) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> a / b;
        };
    }

    /** "3, 6, 9, ..." for the multiples 1..10 of n. */
    static String multiples(int n) {
        return IntStream.rangeClosed(1, 10).mapToObj(i -> String.valueOf(n * i)).collect(Collectors.joining(", "));
    }

    /** Splits seconds into years (365 days), days, hours, minutes and seconds. */
    static String splitSeconds(int sec) {
        return "%d Sekunden entsprechen: %d Jahre(n), %d Tage(n), %d Stunde(n), %d Minute(n), %d Sekunde(n)."
                .formatted(sec, sec / SECONDS_PER_YEAR, sec % SECONDS_PER_YEAR / 86_400,
                        sec % 86_400 / 3600, sec % 3600 / 60, sec % 60);
    }

    static int digitSum(String number) {
        return number.chars().map(Arithmetic::digit).sum();
    }

    /** Spells each digit as a German word, e.g. "102" -> "eins null zwei". */
    static String spellDigits(String number, boolean backwards) {
        var words = number.chars().mapToObj(c -> DIGIT_WORDS[digit(c)]).collect(Collectors.toList());
        if (backwards) {
            java.util.Collections.reverse(words);
        }
        return String.join(" ", words);
    }

    private static int digit(int c) {
        if (c < '0' || c > '9') {
            throw new IllegalArgumentException("Nur Ziffern erlaubt: " + (char) c);
        }
        return c - '0';
    }
}
