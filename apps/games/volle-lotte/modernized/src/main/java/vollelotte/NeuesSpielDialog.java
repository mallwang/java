package vollelotte;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Asks for 2-5 player names. */
final class NeuesSpielDialog {

    private NeuesSpielDialog() {
    }

    /** @return the names, or empty if cancelled */
    static List<String> fragen(JFrame parent) {
        var anzahl = new JComboBox<>(new Integer[] {2, 3, 4, 5});
        var felder = new JTextField[5];
        var panel = new JPanel(new GridLayout(0, 2, 4, 4));
        panel.add(new JLabel("Spieler:"));
        panel.add(anzahl);
        for (int i = 0; i < felder.length; i++) {
            felder[i] = new JTextField(12);
            panel.add(new JLabel("Name " + (i + 1) + ":"));
            panel.add(felder[i]);
        }
        while (JOptionPane.showConfirmDialog(parent, panel, "Neues Spiel", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            var namen = new ArrayList<String>();
            for (int i = 0; i < (Integer) anzahl.getSelectedItem(); i++) {
                namen.add(felder[i].getText().trim());
            }
            if (!namen.contains("")) {
                return namen;
            }
            JOptionPane.showMessageDialog(parent, "Bitte alle Namen eingeben.");
        }
        return List.of();
    }
}
