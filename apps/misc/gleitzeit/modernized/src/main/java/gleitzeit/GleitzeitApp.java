package gleitzeit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** Flextime calculator (GZR): saldo, leave time, breaks and pay estimate from the arrival time. */
public final class GleitzeitApp {
    private static final Path CONFIG = Path.of(System.getProperty("user.home"), ".gleitzeit", "config_gzr_3_5.txt");
    private static final DateTimeFormatter HM = DateTimeFormatter.ofPattern("HH:mm");
    private static final Color BG = new Color(83, 83, 83);
    private static final Color FG = new Color(250, 225, 150);

    private final JFrame frame = new JFrame("GZR v3.6");
    private final JComboBox<String> arrivalH = combo(6, 10, 1);
    private final JComboBox<String> arrivalM = combo(0, 59, 1);
    private final JComboBox<String> mealH = combo(7, 18, 1);
    private final JComboBox<String> mealM = combo(0, 59, 1);
    private final JComboBox<String> netto = new JComboBox<>(
            IntStream.rangeClosed(40, 1000).mapToObj(i -> String.valueOf(i * 10)).toArray(String[]::new));
    private final JLabel saldo = label(), leave = label(), maxEnd = label(), pauses = label(),
            pay = label(), meal = label(), warning = label();
    private final JProgressBar target = new JProgressBar(0, 100), overtime = new JProgressBar(0, 100);

    private GleitzeitApp() {
        Config c = Config.load(CONFIG);
        arrivalH.setSelectedItem("%02d".formatted(c.arrival().getHour()));
        arrivalM.setSelectedItem("%02d".formatted(c.arrival().getMinute()));
        mealH.setSelectedItem("%02d".formatted(c.meal().getHour()));
        mealM.setSelectedItem("%02d".formatted(c.meal().getMinute()));
        netto.setSelectedItem(String.valueOf(c.netto()));
        target.setStringPainted(true);
        overtime.setStringPainted(true);

        JPanel p = new JPanel(new GridLayout(0, 2, 6, 4));
        p.setBackground(BG);
        p.add(label("Kommen:"));
        p.add(pair(arrivalH, arrivalM));
        p.add(label("Essenszeit:"));
        p.add(pair(mealH, mealM));
        p.add(label("Netto (€):"));
        p.add(netto);
        p.add(label("Saldo:"));
        p.add(saldo);
        p.add(label("Feierabend:"));
        p.add(leave);
        p.add(label("Max. Arbeitszeit bis:"));
        p.add(maxEnd);
        p.add(label("Pausen:"));
        p.add(pauses);
        p.add(label("% Soll:"));
        p.add(target);
        p.add(label("% Überstd.:"));
        p.add(overtime);
        p.add(label("Verdienst:"));
        p.add(pay);
        p.add(label("Bis Essen:"));
        p.add(meal);
        frame.add(p);
        warning.setForeground(Color.ORANGE);
        frame.add(warning, BorderLayout.SOUTH);
        frame.getContentPane().setBackground(BG);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/check.png")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(520, 440);
        frame.setLocationByPlatform(true);
        for (JComboBox<String> b : java.util.List.of(arrivalH, arrivalM, mealH, mealM, netto)) {
            b.addActionListener(e -> update());
        }
        Timer t = new Timer(1000, e -> update());
        t.setInitialDelay(0);
        frame.setVisible(true);
        t.start();
    }

    private void update() {
        LocalTime now = LocalTime.now();
        Config c = config();
        GleitzeitModel.Result r = GleitzeitModel.compute(c.arrival(), now);
        String s = GleitzeitModel.formatSaldo(r.saldo());
        saldo.setText(s);
        leave.setText(r.leave().format(HM));
        maxEnd.setText(r.maxEnd().format(HM));
        pauses.setText("%s-%s / %s".formatted(r.pause1().format(HM), r.pause1().plusMinutes(30).format(HM),
                r.pause2() == null ? "N/A" : r.pause2().format(HM) + "-" + r.pause2().plusMinutes(15).format(HM)));
        target.setValue(r.percentTarget());
        overtime.setValue(r.percentOvertime());
        pay.setText("%.2f €".formatted(GleitzeitModel.pay(c.netto(), r.worked())));
        Duration d = Duration.between(now, c.meal());
        meal.setText(d.isNegative() ? "Mahlzeit!" : "%02d:%02d h".formatted(d.toHours(), d.toMinutesPart()));
        warning.setText(String.join("  ", r.warnings()));
        frame.setTitle("%s %d%% - GZR v3.6".formatted(s, r.percentTarget()));
        try {
            c.save(CONFIG);
        } catch (IOException e) {
            warning.setText("Konfiguration nicht speicherbar: " + e.getMessage());
        }
    }

    private Config config() {
        return new Config(Integer.parseInt((String) netto.getSelectedItem()),
                time(arrivalH, arrivalM), time(mealH, mealM));
    }

    private static LocalTime time(JComboBox<String> h, JComboBox<String> m) {
        return LocalTime.of(Integer.parseInt((String) h.getSelectedItem()), Integer.parseInt((String) m.getSelectedItem()));
    }

    private static JComboBox<String> combo(int from, int to, int step) {
        return new JComboBox<>(IntStream.rangeClosed(from, to).filter(i -> (i - from) % step == 0)
                .mapToObj("%02d"::formatted).toArray(String[]::new));
    }

    private static JPanel pair(JComboBox<String> a, JComboBox<String> b) {
        JPanel p = new JPanel(new GridLayout(1, 2, 4, 0));
        p.setOpaque(false);
        p.add(a);
        p.add(b);
        return p;
    }

    private static JLabel label() {
        return label("");
    }

    private static JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(FG);
        return l;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GleitzeitApp::new);
    }
}
