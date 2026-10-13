package bingo;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/** Click the button ten times to win: the progress bar fills up and the window turns red. */
public final class BingoApp extends JFrame {
    private static final int GOAL = 10;

    private final JProgressBar progress = new JProgressBar();
    private int clicks;

    private BingoApp() {
        super("Grünes Fenster");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.GREEN);
        setSize(200, 150);
        setResizable(false);
        setLocationRelativeTo(null);

        progress.setBounds(10, 75, 180, 25);
        progress.setStringPainted(true);
        progress.setString(percent(0));
        var button = new JButton("Klick Mich!");
        button.setBounds(25, 20, 150, 25);
        button.addActionListener(e -> click());
        add(progress);
        add(button);
    }

    static String percent(int clicks) {
        return clicks * 10 + "%";
    }

    private void click() {
        if (clicks < GOAL) {
            clicks++;
            progress.setValue(clicks * 10);
            progress.setString(percent(clicks));
        }
        if (clicks == GOAL) {
            getContentPane().setBackground(Color.RED);
            if (JOptionPane.showConfirmDialog(this, "BINGO!!", "Meldung", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BingoApp().setVisible(true));
    }
}
