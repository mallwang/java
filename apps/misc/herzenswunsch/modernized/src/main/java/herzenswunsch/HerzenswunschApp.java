package herzenswunsch;

import javax.swing.JOptionPane;

/** Asks for a name and a number of hearts (1-20) and hands them out. */
public final class HerzenswunschApp {
    static final int MAX_HEARTS = 20;

    private HerzenswunschApp() {}

    /** @throws IllegalArgumentException if count is outside 1..{@value #MAX_HEARTS} */
    public static String hearts(int count) {
        if (count < 1 || count > MAX_HEARTS) {
            throw new IllegalArgumentException("Zu viele oder zu wenige Herzen!");
        }
        return "♥ ".repeat(count).stripTrailing();
    }

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Bitte nenne mir deinen Namen:");
        if (name == null) {
            return;
        }
        String answer = JOptionPane.showInputDialog("Hallo " + name + ", wieviele ♥ möchtest du?");
        if (answer == null) {
            return;
        }
        String message;
        try {
            message = name + ", du erhältst: " + hearts(Integer.parseInt(answer.strip()));
        } catch (NumberFormatException e) {
            message = "Das ist keine Zahl!";
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        JOptionPane.showMessageDialog(null, message);
    }
}
