package arbeitszeit;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** Shows when to leave work and how much time is left, based on the arrival time. */
public final class ArbeitszeitApp {
    private static final String VERSION = "2.0";
    private static final String NONE = "--";
    private static final Color GREEN = new Color(0, 204, 0);
    private static final Path CONFIG =
            Path.of(System.getProperty("user.home"), ".Arbeitszeitrechner", "config.properties");

    private final JFrame frame = new JFrame("Arbeitszeitrechner v. " + VERSION);
    private final JComboBox<String> arrivalHour = new JComboBox<>(hours(6, 14));
    private final JComboBox<String> arrivalMinute = new JComboBox<>(minutes());
    private final JComboBox<String> plannedHour = new JComboBox<>(hours(11, 22));
    private final JComboBox<String> plannedMinute = new JComboBox<>(minutes());
    private final JLabel clock = new JLabel();
    private final JLabel leaveLabel = new JLabel("--:-- Uhr");
    private final JLabel remainingLabel = new JLabel("--:-- Std");
    private final JLabel plannedLabel = new JLabel("--:-- Std");
    private final Timer timer = new Timer(1000, e -> update());
    private Settings settings = Settings.load(CONFIG);
    private TrayIcon trayIcon;

    private ArbeitszeitApp() {
        remainingLabel.setFont(remainingLabel.getFont().deriveFont(Font.BOLD));
        clock.setFont(clock.getFont().deriveFont(9f));

        JPanel panel = new JPanel(new GridLayout(0, 3, 6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        panel.add(new JLabel("Uhrzeit kommen:"));
        panel.add(row(arrivalHour, arrivalMinute));
        panel.add(clock);
        panel.add(new JLabel("Feierabend:"));
        panel.add(leaveLabel);
        panel.add(row(plannedHour, plannedMinute));
        panel.add(new JLabel("Restzeit:"));
        panel.add(remainingLabel);
        panel.add(plannedLabel);
        JLabel info = new JLabel("Berechnung für eine Arbeitswoche von 5 Tagen. Geplante Zeit: rechts.");
        info.setFont(info.getFont().deriveFont(9f));
        frame.add(panel);
        frame.add(info, java.awt.BorderLayout.SOUTH);
        frame.setJMenuBar(menu());
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/evosoftLogo.png")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(430, 200);
        frame.setLocationByPlatform(true);

        for (JComboBox<String> box : java.util.List.of(arrivalHour, arrivalMinute, plannedHour, plannedMinute)) {
            box.addActionListener(e -> update());
        }
        timer.setInitialDelay(0);
        timer.setDelay(settings.refreshSeconds() * 1000);
        frame.setVisible(true);
        timer.start();
    }

    private JMenuBar menu() {
        JMenuItem refresh = new JMenuItem("Aktualisierung");
        refresh.addActionListener(e -> chooseRefresh());
        JMenuItem weekly = new JMenuItem("Arbeitszeit");
        weekly.addActionListener(e -> chooseWeeklyHours());
        JMenuItem tray = new JMenuItem("In Tray verstecken");
        tray.addActionListener(e -> hideInTray());
        JMenu settingsMenu = new JMenu("Einstellungen");
        settingsMenu.add(refresh);
        settingsMenu.add(weekly);
        settingsMenu.add(tray);
        JMenuBar bar = new JMenuBar();
        bar.add(settingsMenu);
        return bar;
    }

    private void chooseRefresh() {
        String[] options = {"60", "30", "20", "15", "10", "5", "2", "1"};
        Object choice = JOptionPane.showInputDialog(frame, "Bitte wählen Sie das\nAktualisierungsintervall (Sekunden):\n",
                "Aktualisierungszeit wählen.", JOptionPane.QUESTION_MESSAGE, null, options,
                String.valueOf(settings.refreshSeconds()));
        if (choice != null) {
            apply(new Settings(Integer.parseInt((String) choice), settings.weeklyHours()));
            timer.setDelay(settings.refreshSeconds() * 1000);
        }
    }

    private void chooseWeeklyHours() {
        String[] options = IntStream.rangeClosed(20, 60).mapToObj(String::valueOf).toArray(String[]::new);
        Object choice = JOptionPane.showInputDialog(frame, "Bitte wählen Sie Ihre Wochen-\narbeitszeit (5 Arbeitstage):\n",
                "Wochenarbeitszeit wählen.", JOptionPane.QUESTION_MESSAGE, null, options,
                String.valueOf(settings.weeklyHours()));
        if (choice != null) {
            apply(new Settings(settings.refreshSeconds(), Integer.parseInt((String) choice)));
            update();
        }
    }

    private void apply(Settings s) {
        settings = s;
        try {
            s.save(CONFIG);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Einstellungen konnten nicht gespeichert werden: " + e.getMessage());
        }
    }

    private void hideInTray() {
        if (!SystemTray.isSupported()) {
            JOptionPane.showMessageDialog(frame, "Dein System unterstützt leider kein Tray, sorry!");
            return;
        }
        PopupMenu popup = new PopupMenu();
        MenuItem restore = new MenuItem("Wiederherstellen");
        MenuItem exit = new MenuItem("Beenden");
        popup.add(restore);
        popup.add(exit);
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/evosoftLogoTray.png")));
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popup);
        restore.addActionListener(e -> restoreFromTray());
        exit.addActionListener(e -> System.exit(0));
        trayIcon.addActionListener(e -> restoreFromTray()); // double click
        try {
            SystemTray.getSystemTray().add(trayIcon);
            frame.setVisible(false);
            update();
        } catch (java.awt.AWTException e) {
            JOptionPane.showMessageDialog(frame, "Das Minimieren schlug fehl, sorry!");
        }
    }

    private void restoreFromTray() {
        SystemTray.getSystemTray().remove(trayIcon);
        trayIcon = null;
        frame.setVisible(true);
    }

    private void update() {
        LocalTime now = LocalTime.now();
        clock.setText(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        LocalTime arrival = time(arrivalHour, arrivalMinute);
        if (arrival == null) {
            leaveLabel.setText("--:-- Uhr");
            remainingLabel.setText("--:-- Std");
            remainingLabel.setForeground(Color.BLACK);
            frame.setTitle("Arbeitszeitrechner v. " + VERSION);
            setTooltip("Bitte zuerst Kommenzeit angeben!");
        } else {
            LocalTime leave = WorkTime.leaveTime(arrival, settings.weeklyHours());
            Duration left = WorkTime.remaining(now, leave);
            String text = WorkTime.format(left);
            boolean over = !left.isPositive();
            leaveLabel.setText(leave.format(DateTimeFormatter.ofPattern("HH:mm")) + " Uhr");
            remainingLabel.setText(text);
            remainingLabel.setForeground(over ? GREEN : Color.RED);
            frame.setTitle(text + " - Arbeitszeitrechner v. " + VERSION);
            setTooltip((over ? "Feierabend seit " : "Feierabend in ") + text);
        }
        LocalTime planned = time(plannedHour, plannedMinute);
        if (arrival == null || planned == null) {
            plannedLabel.setText("--:-- Std");
            plannedLabel.setForeground(Color.BLACK);
        } else {
            Duration d = WorkTime.remaining(now, planned);
            plannedLabel.setText(WorkTime.format(d));
            plannedLabel.setForeground(d.isPositive() ? Color.RED : GREEN);
        }
    }

    private void setTooltip(String text) {
        if (trayIcon != null) {
            trayIcon.setToolTip(text);
        }
    }

    private static LocalTime time(JComboBox<String> hour, JComboBox<String> minute) {
        String h = (String) hour.getSelectedItem();
        String m = (String) minute.getSelectedItem();
        return NONE.equals(h) || NONE.equals(m) ? null : LocalTime.of(Integer.parseInt(h), Integer.parseInt(m));
    }

    private static JPanel row(JComboBox<String> a, JComboBox<String> b) {
        JPanel p = new JPanel(new GridLayout(1, 2, 4, 0));
        p.add(a);
        p.add(b);
        return p;
    }

    private static String[] hours(int from, int to) {
        return Stream.concat(Stream.of(NONE), IntStream.rangeClosed(from, to).mapToObj("%02d"::formatted))
                .toArray(String[]::new);
    }

    private static String[] minutes() {
        return Stream.concat(Stream.of(NONE), IntStream.range(0, 60).mapToObj("%02d"::formatted))
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArbeitszeitApp::new);
    }
}
