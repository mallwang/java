package annorechner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/** Swing front end: one column per population group, goods below the group that consumes them first. */
public class AnnoApp {

    private final Map<Bevoelkerung, JTextField> felder = new EnumMap<>(Bevoelkerung.class);
    private final Map<Bevoelkerung, JLabel> status = new EnumMap<>(Bevoelkerung.class);
    private final Map<Ware, JLabel> ergebnisse = new HashMap<>();
    private final JLabel kette = new JLabel();

    private static ImageIcon icon(String datei, int groesse) {
        URL url = AnnoApp.class.getResource("/img/" + datei);
        if (url == null) {
            return new ImageIcon();
        }
        var bild = new ImageIcon(url);
        return groesse > 0
                ? new ImageIcon(bild.getImage().getScaledInstance(groesse, groesse, java.awt.Image.SCALE_SMOOTH))
                : bild;
    }

    private JPanel spalte(Bevoelkerung g) {
        var p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        var kopf = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var feld = new JTextField(5);
        var st = new JLabel("OK");
        st.setForeground(new Color(0, 140, 0));
        felder.put(g, feld);
        status.put(g, st);
        feld.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { berechne(); }
            public void removeUpdate(DocumentEvent e) { berechne(); }
            public void changedUpdate(DocumentEvent e) { berechne(); }
        });
        kopf.add(new JLabel(icon(g.bild(), 30)));
        kopf.add(feld);
        kopf.add(st);
        p.add(kopf);
        for (var w : Waren.WAREN) {
            if (w.spalte() == g) {
                p.add(zeile(w.name(), w.bild(), w.kette(), w));
            }
        }
        return p;
    }

    private JPanel zeile(String name, String bild, String kettenBild, Ware ware) {
        var z = new JPanel(new FlowLayout(FlowLayout.LEFT));
        var knopf = new JButton(icon(bild, 30));
        knopf.setToolTipText(name);
        knopf.setPreferredSize(new Dimension(40, 40));
        knopf.addActionListener(e -> kette.setIcon(icon(kettenBild, 0)));
        z.add(knopf);
        if (ware != null) {
            var l = new JLabel("0 (0)");
            ergebnisse.put(ware, l);
            z.add(l);
        }
        return z;
    }

    private void berechne() {
        var anzahl = new EnumMap<Bevoelkerung, Integer>(Bevoelkerung.class);
        for (var g : Bevoelkerung.values()) {
            int n = Waren.parse(felder.get(g).getText());
            var st = status.get(g);
            st.setText(n < 0 ? "Fehler!" : "OK");
            st.setForeground(n < 0 ? Color.RED : new Color(0, 140, 0));
            anzahl.put(g, Math.max(n, 0));
        }
        ergebnisse.forEach((w, l) -> l.setText(w.berechne(anzahl).toString()));
    }

    private void zuruecksetzen() {
        felder.values().forEach(f -> f.setText(""));
        berechne();
    }

    private void zeigen() {
        var rahmen = new JFrame("ANNO 1404 Produktionsrechner © by Markus Allwang");
        var oben = new JPanel(new java.awt.GridLayout(1, 0));
        for (var g : Bevoelkerung.values()) {
            oben.add(spalte(g));
        }
        var sonstige = new JPanel();
        sonstige.setLayout(new BoxLayout(sonstige, BoxLayout.Y_AXIS));
        sonstige.add(new JLabel("Sonstige"));
        var raster = new JPanel(new java.awt.GridLayout(0, 3));
        for (var k : Waren.SONSTIGE) {
            raster.add(zeile(k.name(), k.bild() + ".png", k.kette() + ".png", null));
        }
        sonstige.add(raster);
        var reset = new JButton("Felder zurücksetzen");
        reset.setToolTipText("Alle Felder zurücksetzen");
        reset.addActionListener(e -> zuruecksetzen());
        sonstige.add(Box.createVerticalStrut(5));
        sonstige.add(reset);
        oben.add(sonstige);
        rahmen.add(oben, BorderLayout.NORTH);
        rahmen.add(new JScrollPane(kette), BorderLayout.CENTER);
        rahmen.setSize(1250, 700);
        rahmen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rahmen.setLocationRelativeTo(null);
        rahmen.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (Exception e) {
                // keep the default look and feel
            }
            new AnnoApp().zeigen();
        });
    }
}
