package timer;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** Simple countdown timer: enter minutes, press Start. */
public class TimerApp {

    private final JFrame frame = new JFrame("Timer v. 1.1");
    private final JTextField txtMinutes = new JTextField(10);
    private final JButton btnStart = new JButton("Start");
    private final Timer ticker = new Timer(1000, e -> tick());
    private int minutes;
    private int secondsLeft;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimerApp().show());
    }

    /** Formats seconds as "hh : mm : ss" (seconds within the current minute only, as in v1.0). */
    static String formatDuration(int seconds) {
        return "%02d : %02d : %02d".formatted(seconds / 3600, seconds % 3600 / 60, seconds % 60);
    }

    private void show() {
        var panel = new JPanel(null);
        panel.setBounds(0, 0, 293, 147);

        var lblMinutes = new JLabel("Enter Minutes:");
        lblMinutes.setBounds(64, 39, 89, 22);
        txtMinutes.setBounds(165, 39, 71, 22);
        txtMinutes.setHorizontalAlignment(SwingConstants.CENTER);
        btnStart.setBounds(100, 74, 97, 25);
        btnStart.addActionListener(e -> toggle());

        panel.add(lblMinutes);
        panel.add(txtMinutes);
        panel.add(btnStart);

        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(panel);
        frame.setResizable(false);
        var screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screen.width / 2 - 150, screen.height / 2 - 200, 299, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void toggle() {
        if (ticker.isRunning()) {
            reset();
            return;
        }
        try {
            minutes = Integer.parseInt(txtMinutes.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a whole number of minutes.", "Invalid input",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (minutes < 1) {
            return;
        }
        secondsLeft = minutes * 60;
        btnStart.setText("Stop");
        txtMinutes.setEditable(false);
        ticker.setInitialDelay(0);
        ticker.start();
    }

    private void tick() {
        txtMinutes.setText(formatDuration(secondsLeft--));
        if (secondsLeft < 0) {
            reset();
            JOptionPane.showMessageDialog(frame,
                    "The " + minutes + (minutes == 1 ? " minute is" : " minutes are") + " over!",
                    "Time over!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void reset() {
        ticker.stop();
        btnStart.setText("Start");
        txtMinutes.setEditable(true);
        txtMinutes.setText("");
    }
}
