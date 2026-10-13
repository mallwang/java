package vollelotte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/** Game board: dice, card, scores and buttons. Rules live in {@link Spiel} and {@link Runde}. */
class Spielbrett extends JPanel {

    private static final Color HINTERGRUND = new Color(255, 200, 100);
    private static final Color FREI = Color.CYAN;
    private static final Color GEWAEHLT = Color.WHITE;
    private static final Color FEST = Color.LIGHT_GRAY;

    private final Spiel spiel;
    private Runde runde;
    private final JButton[] wuerfel = new JButton[Runde.WUERFEL];
    private final JButton start = new JButton("Start");
    private final JButton werfen = new JButton("Würfeln");
    private final JButton schreiben = new JButton("Schreiben");
    private final JLabel karte = new JLabel("", SwingConstants.CENTER);
    private final JLabel status = new JLabel(" ", SwingConstants.CENTER);
    private final DefaultTableModel tabelle = new DefaultTableModel(new String[] {"Spieler", "Punkte"}, 0);

    Spielbrett(Spiel spiel) {
        super(new BorderLayout(8, 8));
        this.spiel = spiel;
        setBackground(HINTERGRUND);

        var tab = new JTable(tabelle);
        tab.setEnabled(false);
        spiel.namen().forEach(n -> tabelle.addRow(new Object[] {n, 0}));
        var links = new JPanel(new BorderLayout());
        links.setOpaque(false);
        links.add(tab.getTableHeader(), BorderLayout.NORTH);
        links.add(tab, BorderLayout.CENTER);
        add(links, BorderLayout.WEST);

        var mitte = new JPanel(new GridLayout(2, 3, 8, 8));
        mitte.setOpaque(false);
        for (int i = 0; i < wuerfel.length; i++) {
            int index = i;
            var b = new JButton(" ");
            b.setFont(b.getFont().deriveFont(Font.BOLD, 32f));
            b.setOpaque(true);
            b.setEnabled(false);
            b.addActionListener(e -> {
                runde.umschalten(index);
                aktualisieren();
            });
            wuerfel[i] = b;
            mitte.add(b);
        }
        add(mitte, BorderLayout.CENTER);

        karte.setPreferredSize(new java.awt.Dimension(180, 260));
        add(karte, BorderLayout.EAST);

        var unten = new JPanel(new BorderLayout());
        unten.setOpaque(false);
        var knoepfe = new JPanel(new FlowLayout());
        knoepfe.setOpaque(false);
        knoepfe.add(start);
        knoepfe.add(werfen);
        knoepfe.add(schreiben);
        unten.add(status, BorderLayout.NORTH);
        unten.add(knoepfe, BorderLayout.SOUTH);
        add(unten, BorderLayout.SOUTH);

        start.addActionListener(e -> neueRunde());
        werfen.addActionListener(e -> {
            runde.werfen();
            aktualisieren();
            if (runde.verloren()) {
                verloren();
            }
        });
        schreiben.addActionListener(e -> {
            spiel.schreiben(runde);
            tabelle.setValueAt(spiel.punkte(spiel.aktuell()), spiel.aktuell(), 1);
            runde = null;
            aktualisieren();
        });
        aktualisieren();
    }

    private void neueRunde() {
        runde = spiel.neueRunde();
        karte.setIcon(new ImageIcon(getClass().getResource("/img/" + runde.karte().bild())));
        aktualisieren();
    }

    private void verloren() {
        status.setText(spiel.namen().get(spiel.aktuell()) + ": nichts gewürfelt, Runde verloren!");
        var t = new Timer(1500, e -> {
            runde = null;
            aktualisieren();
        });
        t.setRepeats(false);
        t.start();
    }

    private void aktualisieren() {
        boolean laeuft = runde != null;
        boolean aus = laeuft && runde.verloren();
        start.setEnabled(!laeuft);
        werfen.setEnabled(laeuft && runde.kannWerfen());
        schreiben.setEnabled(laeuft && runde.kannSchreiben());
        for (int i = 0; i < wuerfel.length; i++) {
            var b = wuerfel[i];
            int augen = laeuft ? runde.augen(i) : 0;
            b.setText(augen == 0 ? " " : String.valueOf(augen));
            b.setBackground(runde != null && runde.istFest(i) ? FEST : runde != null && runde.istGewaehlt(i) ? GEWAEHLT : FREI);
            b.setEnabled(laeuft && !aus && augen != 0 && !runde.istFest(i));
        }
        if (!laeuft) {
            karte.setIcon(null);
            status.setText(spiel.aktuell() < 0 ? "Start drücken" : "Nächster Spieler: "
                    + spiel.namen().get((spiel.aktuell() + 1) % spiel.namen().size()));
        } else if (!aus) {
            status.setText(spiel.namen().get(spiel.aktuell()) + ": " + runde.punkte() + " Punkte in dieser Runde");
        }
    }
}
