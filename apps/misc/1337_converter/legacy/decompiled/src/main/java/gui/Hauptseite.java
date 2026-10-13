/*
 * Decompiled with CFR 0.152.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
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
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Hauptseite {
    public static JPanel panel;
    public static JFrame frame;
    public static JMenuBar menueLeiste;
    public static JMenu style;
    public static JMenu beenden;
    public static JMenuItem normal;
    public static JMenuItem L337;
    public static JMenuItem schliessen;
    public static ButtonGroup btnGroup;
    public static JRadioButton rb1;
    public static JRadioButton rb2;
    public static Font f;
    public static JTextArea TAeingabe;
    public static JTextArea TAausgabe;
    public static JButton btn;
    public static JLabel info;
    public static JLabel version;
    public static JLabel uhrzeit;
    public static Dimension dimension;
    public static boolean L337Style;

    static {
        L337Style = false;
    }

    public Hauptseite() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        Hauptseite.initialize();
    }

    private static void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.LookAndFeelInfo[] lookAndFeelInfoArray = UIManager.getInstalledLookAndFeels();
        int n = lookAndFeelInfoArray.length;
        int n2 = 0;
        while (n2 < n) {
            UIManager.LookAndFeelInfo info = lookAndFeelInfoArray[n2];
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
            ++n2;
        }
        f = new Font("Segue UI", 1, 10);
        dimension = new Dimension();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (Hauptseite.dimension.width - 622) / 2;
        int y = (Hauptseite.dimension.height - 500) / 2;
        frame = new JFrame("L337 Converter  v.1.2");
        frame.setDefaultCloseOperation(3);
        frame.setBounds(x, y, 622, 255);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setVisible(true);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(350, 325, 622, 255);
        menueLeiste = new JMenuBar();
        menueLeiste.setBounds(-2, 0, 625, 20);
        style = new JMenu("Style");
        beenden = new JMenu("Optionen");
        normal = new JMenuItem("Normal");
        L337 = new JMenuItem("L337");
        schliessen = new JMenuItem("Schlie\u00dfen");
        btnGroup = new ButtonGroup();
        rb1 = new JRadioButton("31n74ch35 L337");
        rb1.setBounds(125, 135, 150, 15);
        rb1.setFont(f);
        rb2 = new JRadioButton("/_/|_71/V\\471\\/35 |_337");
        rb2.setBounds(125, 155, 150, 15);
        rb2.setFont(f);
        rb1.setSelected(true);
        TAeingabe = new JTextArea();
        TAeingabe.setBounds(5, 30, 300, 100);
        TAeingabe.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        TAeingabe.setBackground(Color.LIGHT_GRAY);
        TAausgabe = new JTextArea();
        TAausgabe.setBounds(310, 30, 300, 100);
        TAausgabe.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
        TAausgabe.setEditable(false);
        TAausgabe.setBackground(Color.LIGHT_GRAY);
        btn = new JButton("Text in die Zwischenablage kopieren");
        btn.setBounds(334, 135, 243, 25);
        btn.setFont(f);
        info = new JLabel();
        info.setBounds(325, 180, 330, 15);
        info.setFont(f);
        version = new JLabel();
        version.setBounds(10, 210, 250, 15);
        version.setFont(f);
        version.setText("Version: 1.2");
        uhrzeit = new JLabel();
        uhrzeit.setBounds(565, 210, 50, 15);
        uhrzeit.setFont(f);
        style.add(normal);
        style.add(L337);
        beenden.add(schliessen);
        menueLeiste.add(style);
        menueLeiste.add(beenden);
        btnGroup.add(rb1);
        btnGroup.add(rb2);
        panel.add(menueLeiste);
        panel.add(TAeingabe);
        panel.add(TAausgabe);
        panel.add(btn);
        panel.add(info);
        panel.add(version);
        panel.add(uhrzeit);
        panel.add(rb1);
        panel.add(rb2);
        frame.add(panel);
    }

    public void setBtnText(String text) {
        btn.setText(text);
    }

    public void setNormalText(String text) {
        normal.setText(text);
    }

    public void setSchliessenText(String text) {
        schliessen.setText(text);
    }

    public void setStyleText(String text) {
        style.setText(text);
    }

    public void setBeendenText(String text) {
        beenden.setText(text);
    }

    public void setInfoText(String text) {
        info.setText(text);
    }

    public void setVersionText(String text) {
        version.setText(text);
    }

    public void setBtnListener(MouseAdapter ma) {
        btn.addMouseListener(ma);
    }

    public void setTAeingabeListener(KeyAdapter ka) {
        TAeingabe.addKeyListener(ka);
    }

    public void setMnItmL337Listener(MouseAdapter ma) {
        L337.addMouseListener(ma);
    }

    public void setMnItmSchliessenListener(MouseAdapter ma) {
        schliessen.addMouseListener(ma);
    }

    public void setMnItmNormalListener(MouseAdapter ma) {
        normal.addMouseListener(ma);
    }

    public void setBnt1Listener(MouseAdapter ma) {
        rb1.addMouseListener(ma);
    }

    public void setBnt2Listener(MouseAdapter ma) {
        rb2.addMouseListener(ma);
    }

    public void setStyle(boolean style) {
        L337Style = style;
    }

    public void setLblUhrzeitText(String text) {
        uhrzeit.setText(text);
    }
}

