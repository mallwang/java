package leet;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import leet.Leet.Mode;

/** Swing front end: converts the input text live and can copy the result to the clipboard. */
public final class LeetApp {
    private final JFrame frame = new JFrame();
    private final JTextArea input = new JTextArea(6, 28);
    private final JTextArea output = new JTextArea(6, 28);
    private final JRadioButton simple = new JRadioButton("", true);
    private final JRadioButton ultimate = new JRadioButton("/_/|_71/V\\471\\/35 |_337");
    private final JButton copy = new JButton();
    private final JLabel info = new JLabel(" ");
    private final JLabel version = new JLabel();
    private final JLabel clock = new JLabel();
    private final JMenu styleMenu = new JMenu();
    private final JMenu optionsMenu = new JMenu();
    private final JMenuItem normalItem = new JMenuItem();
    private final JMenuItem leetItem = new JMenuItem();
    private final JMenuItem closeItem = new JMenuItem();
    private boolean leetStyle;

    private LeetApp() {
        output.setEditable(false);
        output.setBackground(Color.LIGHT_GRAY);
        input.setBackground(Color.LIGHT_GRAY);
        simple.setText("31n74ch35 L337");
        ButtonGroup group = new ButtonGroup();
        group.add(simple);
        group.add(ultimate);

        JPanel texts = new JPanel(new GridLayout(1, 2, 6, 0));
        texts.add(new JScrollPane(input));
        texts.add(new JScrollPane(output));
        JPanel options = new JPanel();
        options.add(simple);
        options.add(ultimate);
        options.add(copy);
        JPanel status = new JPanel(new BorderLayout());
        status.add(version, BorderLayout.WEST);
        status.add(info, BorderLayout.CENTER);
        status.add(clock, BorderLayout.EAST);
        JPanel body = new JPanel(new BorderLayout(0, 6));
        body.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        body.add(texts, BorderLayout.NORTH);
        body.add(options, BorderLayout.CENTER);
        body.add(status, BorderLayout.SOUTH);

        styleMenu.add(normalItem);
        styleMenu.add(leetItem);
        optionsMenu.add(closeItem);
        JMenuBar bar = new JMenuBar();
        bar.add(styleMenu);
        bar.add(optionsMenu);

        input.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { convert(); }
            public void removeUpdate(DocumentEvent e) { convert(); }
            public void changedUpdate(DocumentEvent e) { convert(); }
        });
        simple.addActionListener(e -> convert());
        ultimate.addActionListener(e -> convert());
        copy.addActionListener(e -> copyOutput());
        normalItem.addActionListener(e -> setStyle(false));
        leetItem.addActionListener(e -> setStyle(true));
        closeItem.addActionListener(e -> System.exit(0));

        frame.setJMenuBar(bar);
        frame.add(body);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setStyle(false);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        Timer t = new Timer(1000, e -> clock.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        t.setInitialDelay(0);
        t.start();
    }

    private Mode mode() {
        return simple.isSelected() ? Mode.SIMPLE : Mode.ULTIMATE;
    }

    private void convert() {
        output.setText(Leet.convert(input.getText(), mode()));
        show(input.getText().isEmpty() ? "" : "Der Text befindet sich nicht in der Zwischenablage", Color.RED.darker());
    }

    private void copyOutput() {
        if (input.getText().isEmpty()) {
            show("Bitte geben Sie einen Text ein!", Color.RED.darker());
            return;
        }
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(output.getText()), null);
        show("Der Text befindet sich nun in der Zwischenablage!", new Color(0, 140, 0));
    }

    private void show(String text, Color color) {
        info.setText(leetStyle ? Leet.convert(text, Mode.SIMPLE) : text);
        info.setForeground(color);
    }

    /** L337 style shows the UI texts themselves in simple leetspeak. */
    private void setStyle(boolean leet) {
        leetStyle = leet;
        styleMenu.setText(tr("Style"));
        optionsMenu.setText(tr("Optionen"));
        normalItem.setText(tr("Normal"));
        leetItem.setText("L337");
        closeItem.setText(tr("Schließen"));
        copy.setText(tr("Text in die Zwischenablage kopieren"));
        version.setText(tr("Version: 2.0"));
        frame.setTitle(tr("L337 Converter") + "  v.2.0");
        convert();
    }

    private String tr(String s) {
        return leetStyle ? Leet.convert(s, Mode.SIMPLE) : s;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LeetApp::new);
    }
}
