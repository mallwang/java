package kdet;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/** Calibration data export tool: two ZIP archives in, one CSV per section out. */
public final class KdetApp extends JFrame {

    private static final Path LOG = Path.of("Kalibrierungtool.log");

    private final JTextField xmlInput = field();
    private final JTextField xsltInput = field();
    private final JTextField saveInput = field();

    private KdetApp() {
        super("Calibration");
        setLayout(new GridBagLayout());
        row(0, "XML-ZIP-Archiv:", xmlInput, "XML-Archiv wählen", chooseZip(xmlInput));
        row(1, "XSLT-ZIP-Archiv:", xsltInput, "XSLT-Archiv wählen", chooseZip(xsltInput));
        row(2, "Speicherpfad:", saveInput, "Speicherpfad wählen", this::chooseDirectory);

        JButton work = new JButton("Verarbeiten");
        work.addActionListener(e -> process());
        JButton close = new JButton("Beenden");
        close.addActionListener(e -> dispose());
        add(work, at(1, 3, 0));
        add(close, at(2, 3, 0));
        getRootPane().setDefaultButton(work);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private static JTextField field() {
        JTextField f = new JTextField(40);
        f.setEditable(false);
        return f;
    }

    private static GridBagConstraints at(int x, int y, double weightx) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.weightx = weightx;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(4, 6, 4, 6);
        return c;
    }

    private void row(int y, String label, JTextField field, String button, Runnable action) {
        add(new JLabel(label), at(0, y, 0));
        add(field, at(1, y, 1));
        JButton b = new JButton(button);
        b.addActionListener(e -> action.run());
        add(b, at(2, y, 0));
    }

    private Runnable chooseZip(JTextField target) {
        return () -> {
            JFileChooser chooser = new JFileChooser(target.getText().isEmpty() ? null : Path.of(target.getText()).toFile());
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setFileFilter(new FileNameExtensionFilter("ZIP-Datei (*.zip)", "zip"));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                target.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        };
    }

    private void chooseDirectory() {
        JFileChooser chooser = new JFileChooser(saveInput.getText().isEmpty() ? null : Path.of(saveInput.getText()).toFile());
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveInput.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void process() {
        String xml = xmlInput.getText();
        String xslt = xsltInput.getText();
        String save = saveInput.getText();
        if (xml.isEmpty() || xslt.isEmpty() || save.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte XML-Archiv, XSLT-Archiv und Speicherpfad wählen.",
                    "Achtung - Quelldaten fehlen", JOptionPane.WARNING_MESSAGE);
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            String message = "Die Konvertierung wurde erfolgreich abgeschlossen.\n\n"
                    + KdetTransformer.run(Path.of(xml), Path.of(xslt), Path.of(save)).format();
            writeLog(message);
            JOptionPane.showMessageDialog(this, message, "Fertig", JOptionPane.INFORMATION_MESSAGE);
        } catch (KdetTransformer.KdetException e) {
            writeLog(e.toString());
            JOptionPane.showMessageDialog(this, e.getMessage() + "\n\nDetails stehen in " + LOG,
                    "Fehler", JOptionPane.WARNING_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private static void writeLog(String message) {
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        try {
            Files.writeString(LOG, stamp + "\n" + "-".repeat(60) + "\n" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KdetApp().setVisible(true));
    }
}
