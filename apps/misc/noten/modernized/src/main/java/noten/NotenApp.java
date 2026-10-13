package noten;

import java.util.Locale;
import javax.swing.JOptionPane;

/** Average of German school grades (1-6) typed as one digit string, e.g. "233". */
public final class NotenApp {
    private NotenApp() {}

    /** @throws IllegalArgumentException if the input is empty or contains something other than 1-6 */
    public static double average(String grades) {
        if (grades.isEmpty()) {
            throw new IllegalArgumentException("Keine Noten eingegeben");
        }
        int sum = 0;
        for (char c : grades.toCharArray()) {
            if (c < '1' || c > '6') {
                throw new IllegalArgumentException(c + " ist keine Note");
            }
            sum += c - '0';
        }
        return (double) sum / grades.length();
    }

    public static void main(String[] args) {
        String prompt = "Bitte gib alle deine Noten hintereinander ohne Leerzeichen ein (z.B. 233), e = Ende";
        while (true) {
            String input = JOptionPane.showInputDialog(prompt);
            if (input == null || input.strip().equalsIgnoreCase("e")) {
                JOptionPane.showMessageDialog(null, "Ora et labora!");
                return;
            }
            String message;
            try {
                message = String.format(Locale.GERMANY, "Durchschnittsnote: %.2f", average(input.strip()));
            } catch (IllegalArgumentException e) {
                message = e.getMessage();
            }
            JOptionPane.showMessageDialog(null, message);
        }
    }
}
