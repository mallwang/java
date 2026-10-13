package vollelotte;

import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/** Volle Lotte: a dice game with cards. */
public final class VolleLotteApp {

    private static final String REGELN = """
            Würfeln mit 6 Würfeln. Wertung:
            1: einzeln 100, 5: einzeln 50.
            Drei gleiche: 1 = 1000, 5 = 500, sonst Augenzahl x 100; jeder weitere gleiche Würfel verdoppelt.
            Nach jedem Wurf mindestens einen wertenden Würfel sichern (anklicken), dann die übrigen neu würfeln.
            Ohne wertenden Würfel ist die Runde verloren.
            Schreiben ist ab 350 Punkten in der Runde möglich.
            Sind alle 6 Würfel gesichert, gibt es den Bonus der gezogenen Karte (200-600).
            Die anderen Karten haben noch keine Wirkung. Es gibt kein Spielende.""";

    private final JFrame frame = new JFrame("Volle Lotte");

    private VolleLotteApp() {
        var menu = new JMenuBar();
        var datei = new JMenu("Datei");
        var neu = new JMenuItem("Neues Spiel");
        neu.addActionListener(e -> neuesSpiel());
        var ende = new JMenuItem("Beenden");
        ende.addActionListener(e -> System.exit(0));
        datei.add(neu);
        datei.add(ende);
        var hilfe = new JMenu("Hilfe");
        var regeln = new JMenuItem("Regelwerk");
        regeln.addActionListener(e -> JOptionPane.showMessageDialog(frame, REGELN, "Regelwerk", JOptionPane.INFORMATION_MESSAGE));
        hilfe.add(regeln);
        menu.add(datei);
        menu.add(hilfe);
        frame.setJMenuBar(menu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        neuesSpiel();
    }

    private void neuesSpiel() {
        List<String> namen = NeuesSpielDialog.fragen(frame);
        if (!namen.isEmpty()) {
            frame.setContentPane(new Spielbrett(new Spiel(namen, new Random())));
            frame.revalidate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VolleLotteApp::new);
    }
}
