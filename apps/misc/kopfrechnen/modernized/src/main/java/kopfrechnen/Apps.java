package kopfrechnen;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/** The apps that were shipped as separate JARs; starting the JAR shows a chooser. */
public enum Apps {
    HUNDERTER_ADDIEREN("Hunderter addieren", () -> quiz(Quiz.HUNDERTER_ADDIEREN)),
    ZEHNER_ADDIEREN("Zehner addieren", () -> quiz(Quiz.ZEHNER_ADDIEREN)),
    ZEHNER_MULTIPLIZIEREN("Zehner multiplizieren", () -> quiz(Quiz.ZEHNER_MULTIPLIZIEREN)),
    ZEHNER_SUBTRAHIEREN("Zehner subtrahieren", () -> quiz(Quiz.ZEHNER_SUBTRAHIEREN)),
    QUERSUMME("Quersummenrechner", () -> {
        var in = ask("Mit diesem Rechner können Sie die Quersumme einer beliebigen Zahl ausrechnen. Bitte geben Sie diese nun ein:");
        return in == null ? null : "Die Quersumme lautet: " + Arithmetic.digitSum(in.trim());
    }),
    ZAHLZERLEGUNG("Zahlzerlegung", () -> {
        var in = ask("Mit diesem Rechner können Sie Ihre Zahl in Worten vorwärts oder rückwärts ausgeben lassen. Geben Sie diese nun ein:");
        var dir = in == null ? null : ask("In welche Richtung? Vorwärts (v) oder Rückwärts (r)?");
        if (dir == null || dir.isEmpty() || "vr".indexOf(dir.charAt(0)) < 0) {
            return null;
        }
        boolean back = dir.charAt(0) == 'r';
        return (back ? "Rückwärts" : "Vorwärts") + " zerlegt: " + Arithmetic.spellDigits(in.trim(), back);
    }),
    SEKUNDENZERLEGER("Sekundenzerleger", () -> {
        var in = ask("Mit diesem Rechner können Sie Sekunden in Jahre, Tage usw. zerlegen. Geben Sie bitte die Sekunden ein:");
        return in == null ? null : Arithmetic.splitSeconds(Integer.parseInt(in.trim()));
    }),
    VIELFACHE("Vielfache berechnen", () -> {
        var in = ask("Mit diesem Rechner können Sie die Vielfachen Ihrer eingegebenen Zahl berechnen. Bitte geben Sie diese nun ein:");
        if (in == null) {
            return null;
        }
        int n = Integer.parseInt(in.trim());
        return "Die Vielfachen von " + n + " lauten: " + Arithmetic.multiples(n);
    }),
    TASCHENRECHNER("Taschenrechner", () -> {
        var in = ask("Mit diesem kleinen Taschenrechner können Sie einfache Rechenaufgaben lösen. Geben Sie bitte nun eine komplette Rechenaufgabe ein (z. B. 12*3):");
        return in == null ? null : "Ergebnis ist: " + Arithmetic.calculate(in);
    });

    private final String title;
    /** Runs the app; returns the result text to show, or null if there is nothing to show. */
    private final Supplier<String> body;

    Apps(String title, Supplier<String> body) {
        this.title = title;
        this.body = body;
    }

    void run() {
        try {
            var result = body.get();
            if (result != null) {
                JOptionPane.showMessageDialog(null, result);
            }
        } catch (IllegalArgumentException e) { // NumberFormatException is one, too
            JOptionPane.showMessageDialog(null, "Ungültige Eingabe: " + e.getMessage(), title, JOptionPane.WARNING_MESSAGE);
        }
    }

    private static String ask(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    /** Asks until the answer is wrong; then offers to continue (j/n). Cancel ends the quiz. */
    private static String quiz(Quiz quiz) {
        JOptionPane.showMessageDialog(null,
                "Mit diesem Rechner können Sie Ihr Gedächtnis auf Vordermann bringen, indem Sie einfache Rechenaufgaben lösen.");
        var rnd = ThreadLocalRandom.current();
        while (true) {
            var task = quiz.next(rnd);
            var in = ask("Wie lautet folgende Rechnung?: " + task);
            if (in == null) {
                return null;
            }
            try {
                if (Integer.parseInt(in.trim()) == task.answer()) {
                    JOptionPane.showMessageDialog(null, "Richtig!");
                    continue;
                }
            } catch (NumberFormatException ignored) {
                // counts as a wrong answer
            }
            JOptionPane.showMessageDialog(null, "Falsch! Richtig wäre " + task.answer() + ".");
            var again = ask("Weiter? (j/n)");
            if (again == null || !again.startsWith("j")) {
                return "Gut gemacht!";
            }
        }
    }

    /** Starts the app named by the first argument, or lets the user pick one. */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var chosen = args.length > 0
                    ? Arrays.stream(values()).filter(a -> a.name().equalsIgnoreCase(args[0])).findFirst().orElse(null)
                    : (Apps) JOptionPane.showInputDialog(null, "Welche Anwendung?", "Kopfrechnen",
                            JOptionPane.PLAIN_MESSAGE, null, values(), HUNDERTER_ADDIEREN);
            if (chosen != null) {
                chosen.run();
            }
            // modal dialogs only: the JVM exits once no window is left
        });
    }

    @Override
    public String toString() {
        return title;
    }
}
