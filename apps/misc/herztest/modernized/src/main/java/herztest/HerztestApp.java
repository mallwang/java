package herztest;

import java.util.List;
import javax.swing.JOptionPane;

/** Three yes/no questions; every "yes" earns a heart, the first "no" ends the quiz. */
public final class HerztestApp {
    static final List<String> QUESTIONS = List.of(
            "Findest du Markus toll? ( j / n )",
            "Genießt du die gemeinsame Zeit? ( j / n )",
            "Hast du Schmetterlinge im Bauch? ( j / n )");
    private static final String HEART = "♥";

    private HerztestApp() {}

    public static boolean isYes(String answer) {
        return answer != null && answer.strip().toLowerCase().startsWith("j");
    }

    /** Final message for the number of hearts collected (0..QUESTIONS.size()). */
    public static String result(int hearts) {
        return switch (hearts) {
            case 0 -> "Schade, du hast kein " + HEART + " gesammelt";
            case 1 -> "Schade, du hast nur " + HEART + " gesammelt";
            case 2 -> "Schade, aber du hast immerhin " + HEART + " " + HEART + " gesammelt";
            default -> "Du hast gewonnen und alle " + HEART + " " + HEART + " " + HEART + " gesammelt";
        };
    }

    public static void main(String[] args) {
        int hearts = 0;
        for (String question : QUESTIONS) {
            if (!isYes(JOptionPane.showInputDialog(question))) {
                break;
            }
            hearts++;
            String reward = switch (hearts) {
                case 1 -> "Super, du erhältst ein " + HEART;
                case 2 -> "Super, du erhältst ein weiteres " + HEART;
                default -> "Super, du erhältst das letzte " + HEART;
            };
            JOptionPane.showMessageDialog(null, reward);
        }
        JOptionPane.showMessageDialog(null, result(hearts));
    }
}
