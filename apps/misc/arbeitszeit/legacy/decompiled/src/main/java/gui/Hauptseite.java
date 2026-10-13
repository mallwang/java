/*
 * Decompiled with CFR 0.152.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Hauptseite {
    public static JFrame frame;
    public static JPanel panel;
    public static JLabel lblUhrzeitKommen;
    public static JLabel lblUhrzeitFeierabend;
    public static JLabel lblUhrzeitFeierabendZeit;
    public static JLabel lblUhrzeitFeierabendRestzeit;
    public static JLabel lblUhrzeitFeierabendRestzeitZeit;
    public static JLabel lblUhrzeitFeierabendGeplant;
    public static JLabel lblUhrzeitGehen;
    public static JLabel lblInfo;
    public static JLabel lblAktUhrzeit;
    public static JComboBox cboUhrzeitKommenStd;
    public static JComboBox cboUhrzeitKommenMin;
    public static JComboBox cboUhrzeitGehenStd;
    public static JComboBox cboUhrzeitGehenMin;
    public static JMenuBar mbLeiste;
    public static JMenu menuEinstellungen;
    public static JMenuItem miAktualisierung;
    public static JMenuItem miArbeitszeit;
    public static JMenuItem miTray;
    public static String VERSION;

    static {
        VERSION = "1.5";
    }

    public Hauptseite() {
        Hauptseite.createHauptseite();
    }

    public static void createHauptseite() {
        frame = new JFrame();
        int frameWidth = 370;
        int frameHeigth = 190;
        frame.setSize(frameWidth, frameHeigth);
        frame.setTitle("Arbeitszeitrechner v. " + VERSION);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Hauptseite.class.getResource("/evosoftLogo.png")));
        Hauptseite.setFramePosition(frameWidth, frameHeigth);
        panel = new JPanel();
        panel.setBounds(0, 0, 370, 190);
        panel.setLayout(null);
        lblUhrzeitGehen = new JLabel("Uhrzeit gehen:");
        lblUhrzeitGehen.setBounds(240, 30, 100, 20);
        lblInfo = new JLabel("Hinweis: Die Berechnung der Arbeitszeit bezieht sich auf eine Arbeitswoche von 5 Tagen.");
        lblInfo.setBounds(10, 130, 320, 10);
        lblInfo.setFont(new Font("Segue UI", 0, 8));
        lblUhrzeitKommen = new JLabel("Uhrzeit kommen:");
        lblUhrzeitKommen.setBounds(10, 30, 100, 20);
        lblUhrzeitFeierabend = new JLabel("Feierabend:");
        lblUhrzeitFeierabend.setBounds(10, 60, 100, 20);
        lblUhrzeitFeierabendZeit = new JLabel("--:-- Uhr");
        lblUhrzeitFeierabendZeit.setBounds(145, 60, 100, 20);
        lblUhrzeitFeierabendRestzeit = new JLabel("Restzeit:");
        lblUhrzeitFeierabendRestzeit.setBounds(10, 90, 100, 20);
        lblUhrzeitFeierabendRestzeitZeit = new JLabel("--:-- Std");
        lblUhrzeitFeierabendRestzeitZeit.setBounds(145, 90, 100, 20);
        Font font = lblUhrzeitFeierabendRestzeitZeit.getFont();
        Font boldFont = new Font(font.getFontName(), 1, font.getSize());
        lblUhrzeitFeierabendRestzeitZeit.setFont(boldFont);
        lblUhrzeitFeierabendGeplant = new JLabel("--:-- Std");
        lblUhrzeitFeierabendGeplant.setBounds(270, 90, 100, 20);
        lblAktUhrzeit = new JLabel(Hauptseite.createCurrentTime());
        lblAktUhrzeit.setBounds(310, 2, 90, 20);
        Font fontAktUhrzeit = lblAktUhrzeit.getFont();
        lblAktUhrzeit.setFont(new Font(fontAktUhrzeit.getFontName(), 0, 9));
        String[] listKommenStd = new String[]{"--", "06", "07", "08", "09", "10", "11", "12", "13", "14"};
        String[] listGehenStd = new String[]{"--", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
        String[] listMin = new String[]{"--", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
        cboUhrzeitKommenStd = new JComboBox<String>(listKommenStd);
        cboUhrzeitKommenStd.setBounds(115, 30, 50, 20);
        cboUhrzeitGehenStd = new JComboBox<String>(listGehenStd);
        cboUhrzeitGehenStd.setBounds(240, 60, 50, 20);
        cboUhrzeitKommenMin = new JComboBox<String>(listMin);
        cboUhrzeitKommenMin.setBounds(170, 30, 50, 20);
        cboUhrzeitGehenMin = new JComboBox<String>(listMin);
        cboUhrzeitGehenMin.setBounds(295, 60, 50, 20);
        mbLeiste = new JMenuBar();
        mbLeiste.setBounds(0, 0, 290, 20);
        menuEinstellungen = new JMenu("Einstellungen");
        menuEinstellungen.setEnabled(true);
        miAktualisierung = new JMenuItem("Aktualisierung");
        miArbeitszeit = new JMenuItem("Arbeitszeit");
        miTray = new JMenuItem("In Tray verstecken");
        frame.getContentPane().add(panel);
        panel.add(cboUhrzeitKommenMin);
        panel.add(cboUhrzeitGehenMin);
        panel.add(cboUhrzeitKommenStd);
        panel.add(cboUhrzeitGehenStd);
        panel.add(lblUhrzeitFeierabendGeplant);
        panel.add(lblUhrzeitFeierabendRestzeitZeit);
        panel.add(lblUhrzeitFeierabendRestzeit);
        panel.add(lblUhrzeitFeierabendZeit);
        panel.add(lblUhrzeitFeierabend);
        panel.add(lblUhrzeitKommen);
        panel.add(lblInfo);
        panel.add(lblUhrzeitGehen);
        panel.add(lblAktUhrzeit);
        mbLeiste.add(menuEinstellungen);
        menuEinstellungen.add(miAktualisierung);
        menuEinstellungen.add(miArbeitszeit);
        menuEinstellungen.add(miTray);
        panel.add(mbLeiste);
        frame.setVisible(true);
    }

    public void setListenerCboUhrzeitKommenStd(ItemListener aListener) {
        cboUhrzeitKommenStd.addItemListener(aListener);
    }

    public void setListenerCboUhrzeitKommenMin(ItemListener aListener) {
        cboUhrzeitKommenMin.addItemListener(aListener);
    }

    public void setListenerCboUhrzeitGehenStd(ItemListener aListener) {
        cboUhrzeitGehenStd.addItemListener(aListener);
    }

    public void setListenerCboUhrzeitGehenMin(ItemListener aListener) {
        cboUhrzeitGehenMin.addItemListener(aListener);
    }

    public void setListenerMiAktualisierung(MouseAdapter ma) {
        miAktualisierung.addMouseListener(ma);
    }

    public void setListenerMiArbeitszeit(MouseAdapter ma) {
        miArbeitszeit.addMouseListener(ma);
    }

    public void setListenerMiTray(MouseAdapter ma) {
        miTray.addMouseListener(ma);
    }

    public String getUhrzeitKommenStd() {
        return cboUhrzeitKommenStd.getSelectedItem().toString();
    }

    public String getUhrzeitKommenMin() {
        return cboUhrzeitKommenMin.getSelectedItem().toString();
    }

    public String getUhrzeitGehenStd() {
        return cboUhrzeitGehenStd.getSelectedItem().toString();
    }

    public String getUhrzeitGehenMin() {
        return cboUhrzeitGehenMin.getSelectedItem().toString();
    }

    public void setLblUhrzeitFeierabendZeit(String uhrzeit) {
        lblUhrzeitFeierabendZeit.setText(uhrzeit);
    }

    public static void setLblAktUhrzeit(String uhrzeit) {
        lblAktUhrzeit.setText(uhrzeit);
    }

    public void setLblUhrzeitFeierabendRestzeitZeit(String uhrzeit, Color color) {
        lblUhrzeitFeierabendRestzeitZeit.setText(uhrzeit);
        lblUhrzeitFeierabendRestzeitZeit.setForeground(color);
    }

    public void setLblUhrzeitFeierabendGeplant(String uhrzeit, Color color) {
        lblUhrzeitFeierabendGeplant.setText(uhrzeit);
        lblUhrzeitFeierabendGeplant.setForeground(color);
    }

    public void setFrameTitle(String title) {
        frame.setTitle(String.valueOf(title) + VERSION);
    }

    private static String createCurrentTime() {
        Calendar calNow = Calendar.getInstance();
        String stdNow = String.valueOf(calNow.get(11));
        String minNow = String.valueOf(calNow.get(12));
        String secNow = String.valueOf(calNow.get(13));
        if (stdNow.length() == 1) {
            stdNow = "0" + stdNow;
        }
        if (minNow.length() == 1) {
            minNow = "0" + minNow;
        }
        if (secNow.length() == 1) {
            secNow = "0" + secNow;
        }
        return String.valueOf(stdNow) + ":" + minNow + ":" + secNow;
    }

    private static void setFramePosition(int frameWidth, int frameHeight) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = new Dimension(370, 190);
        int x = screenSize.width / 4 - frameWidth / 2;
        int y = screenSize.height / 4 - frameHeight / 2;
        frame.setBounds(x, y, frameWidth, frameHeight);
    }
}

