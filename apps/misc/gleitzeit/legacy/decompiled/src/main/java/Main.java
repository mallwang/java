/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.accessibility.Accessible;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicComboPopup;

public class Main
extends JFrame
implements ActionListener {
    String[] stunde = new String[]{"06", "07", "08", "09", "10"};
    String[] minute = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"};
    String[] stunde_happa = new String[]{"07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18"};
    String[] minute_happa = this.minute;
    String[] netto_auswahl = new String[0];
    JComboBox kommen_stunde = new JComboBox<String>(this.stunde);
    JComboBox kommen_minute = new JComboBox<String>(this.minute);
    JComboBox happa_stunde = new JComboBox<String>(this.stunde_happa);
    JComboBox happa_minute = new JComboBox<String>(this.minute_happa);
    JComboBox netto = new JComboBox<String>(this.netto_auswahl);
    JButton refresh_button = new JButton("Refresh");
    JButton hilfe_button = new JButton("Hilfe/About");
    JButton exit_button = new JButton("Exit");
    JRadioButton full = new JRadioButton("Warnungen einblenden");
    JRadioButton small = new JRadioButton("ausblenden");
    JRadioButton normal = new JRadioButton("normal");
    JRadioButton mini = new JRadioButton("minimal");
    JLabel warning_red_p1_t = new JLabel("1.Pause l\u00e4uft, Saldo wird pausiert!");
    JLabel warning_red_p2_t = new JLabel("2.Pause l\u00e4uft, Saldo wird pausiert!");
    JLabel warning_red_p1bald_t = new JLabel("1.Pause beginnt in K\u00fcrze!");
    JLabel warning_red_p2bald_t = new JLabel("2.Pause beginnt in K\u00fcrze!");
    JLabel warning_red_maxarbeitbald_t = new JLabel("Sie haben gleich / jetzt die maximale Arbeitszeit erreicht!");
    JLabel warning_red_zufrueh_t = new JLabel("Kommen ist erst ab 06:30h m\u00f6glich (Arbeitszeit 06:30-19:30)!");
    JLabel warning_yellow_zuspaet_t = new JLabel("Sie haben nach 9Uhr eingestochen (Kernarbeitszeit 09:00-15:00).");
    JLabel warning_yellow_zukunft_t = new JLabel("Ihr Saldo l\u00e4uft sobald die Kommenzeit erreicht ist (Liegt in der Zukunft).");
    JLabel warning_yellow_maxarbeit_t = new JLabel("Die max. Arbeitszeit wurde auf 19:30 gesetzt (Arbeitszeit 06:30-19:30).");
    JLabel warning_yellow_keinep2_t = new JLabel("Heute kann die 2.Pause nicht mehr erreicht werden (nach 19:30).");
    JLabel check_p1_t = new JLabel("1. Pause");
    JLabel check_feierabend_t = new JLabel("Feierabend");
    JLabel check_p2_t = new JLabel("2. Pause");
    JLabel check_maxarbeit_t = new JLabel("Max.Arbeitszeit");
    JLabel warning_red_info_t = new JLabel("wichtig");
    JLabel warning_yellow_info_t = new JLabel("informativ");
    JLabel kommen_text;
    JLabel maxarbeit_text;
    JLabel pause1_text;
    JLabel pause2_text;
    JLabel feierabend_text;
    JLabel uhr_text;
    JLabel saldo_text;
    JLabel warning_red_p1;
    JLabel warning_red_p2;
    JLabel warning_red_p1bald;
    JLabel warning_red_p2bald;
    JLabel warning_red_maxarbeitbald;
    JLabel warning_red_zufrueh;
    JLabel warning_yellow_zuspaet;
    JLabel warning_yellow_zukunft;
    JLabel warning_yellow_maxarbeit;
    JLabel warning_yellow_keinep2;
    JLabel check_p1;
    JLabel check_feierabend;
    JLabel check_p2;
    JLabel check_maxarbeit;
    JLabel warning_red_info;
    JLabel warning_yellow_info;
    JLabel warning_red_avaible;
    JLabel warning_yellow_avaible;
    JLabel saldo_plus;
    JLabel saldo_minus;
    JLabel feierabend_flag;
    JLabel essen_pic;
    JLabel kommen_pic;
    JLabel pause1_pic;
    JLabel pause2_pic;
    JLabel check_bar1;
    JLabel check_bar2;
    JLabel check_bar3;
    JLabel check_bar4;
    Date zeit_uhrzeit = new Date();
    Date zeit_feierabend = new Date();
    Date zeit_saldo = new Date();
    Date zeit_kommen = new Date();
    Date zeit_pause1 = new Date();
    Date zeit_pause2 = new Date();
    Date zeit_pause1ende = new Date();
    Date zeit_pause2ende = new Date();
    Date zeit_anwesend = new Date();
    Date zeit_maxarbeit = new Date();
    Date zeit_happa = new Date();
    Date zeit_bisessen = new Date();
    Date zeit_dummy = new Date();
    JLabel uhr = new JLabel();
    JLabel feierabend = new JLabel();
    JLabel maxarbeit = new JLabel();
    JLabel saldo = new JLabel();
    JLabel pause1 = new JLabel();
    JLabel pause2 = new JLabel();
    JLabel kommen = new JLabel();
    JLabel pause1_ende = new JLabel();
    JLabel pause2_ende = new JLabel();
    JLabel prozent_feierabend = new JLabel();
    JLabel prozent_ueberstunden = new JLabel();
    JLabel prozent_pause1 = new JLabel();
    JLabel prozent_pause2 = new JLabel();
    JLabel bisessen = new JLabel();
    JLabel netto_text = new JLabel();
    SimpleDateFormat sdfAktuell = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sdfFeierabend = new SimpleDateFormat("HH:mm");
    DecimalFormat dfProzent = new DecimalFormat("0.00");
    DecimalFormat dfPause = new DecimalFormat("0.0");
    JProgressBar bar_feierabend = new JProgressBar(0, 100);
    JProgressBar bar_ueberstunden = new JProgressBar(0, 100);
    JProgressBar bar_pause1 = new JProgressBar(0, 100);
    JProgressBar bar_pause2 = new JProgressBar(0, 100);
    Color bg = new Color(83, 83, 83);
    Color standardfarbe = new Color(250, 225, 150);
    Color standardfarbe2 = new Color(0, 200, 255);

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main main = new Main();
    }

    public Main() throws IOException, URISyntaxException {
        this.setSize(600, 227);
        this.setResizable(false);
        this.setTitle(String.valueOf(this.saldo.getText()) + " GZR v3.5");
        this.setDefaultCloseOperation(3);
        this.getContentPane().setBackground(this.bg);
        this.setLayout(null);
        String gehalt_config = "";
        String stundekommen_config = "";
        String minutekommen_config = "";
        String stundeessen_config = "";
        String minuteessen_config = "";
        String full_config = this.configDatei_lesen();
        if (full_config.length() >= 12) {
            gehalt_config = full_config.substring(0, full_config.length() - 12);
            stundekommen_config = full_config.substring(full_config.length() - 11, full_config.length() - 9);
            minutekommen_config = full_config.substring(full_config.length() - 8, full_config.length() - 6);
            stundeessen_config = full_config.substring(full_config.length() - 5, full_config.length() - 3);
            minuteessen_config = full_config.substring(full_config.length() - 2, full_config.length() - 0);
        }
        String[] netto_auswahl = new String[961];
        int i = 0;
        while (i <= 960) {
            String dummy;
            netto_auswahl[i] = dummy = i * 10 + 400 + "\u20ac";
            ++i;
        }
        this.netto = new JComboBox<String>(netto_auswahl);
        this.full.setBackground(this.bg);
        this.full.setForeground(this.standardfarbe);
        this.small.setBackground(this.bg);
        this.small.setForeground(this.standardfarbe);
        this.normal.setBackground(this.bg);
        this.normal.setForeground(this.standardfarbe);
        this.mini.setBackground(this.bg);
        this.mini.setForeground(this.standardfarbe);
        this.kommen_stunde.setBackground(this.bg);
        this.kommen_stunde.setForeground(this.standardfarbe);
        this.kommen_minute.setBackground(this.bg);
        this.kommen_minute.setForeground(this.standardfarbe);
        this.happa_stunde.setBackground(this.bg);
        this.happa_stunde.setForeground(this.standardfarbe);
        this.happa_minute.setBackground(this.bg);
        this.happa_minute.setForeground(this.standardfarbe);
        this.netto.setBackground(this.bg);
        this.netto.setForeground(this.standardfarbe);
        Accessible child_stunde = this.kommen_stunde.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup_stunde = (BasicComboPopup)child_stunde;
        JList<Object> list_stunde = popup_stunde.getList();
        list_stunde.setSelectionBackground(Color.black);
        list_stunde.setSelectionForeground(this.standardfarbe);
        Accessible child_minute = this.kommen_minute.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup_minute = (BasicComboPopup)child_minute;
        JList<Object> list_minute = popup_minute.getList();
        list_minute.setSelectionBackground(Color.black);
        list_minute.setSelectionForeground(this.standardfarbe);
        Accessible child_stunde2 = this.happa_stunde.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup_stunde2 = (BasicComboPopup)child_stunde2;
        JList<Object> list_stunde2 = popup_stunde2.getList();
        list_stunde2.setSelectionBackground(Color.black);
        list_stunde2.setSelectionForeground(this.standardfarbe);
        Accessible child_minute2 = this.happa_minute.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup_minute2 = (BasicComboPopup)child_minute2;
        JList<Object> list_minute2 = popup_minute2.getList();
        list_minute2.setSelectionBackground(Color.black);
        list_minute2.setSelectionForeground(this.standardfarbe);
        Accessible child_netto = this.netto.getAccessibleContext().getAccessibleChild(0);
        BasicComboPopup popup_netto = (BasicComboPopup)child_netto;
        JList<Object> list_netto = popup_netto.getList();
        list_netto.setSelectionBackground(Color.black);
        list_netto.setSelectionForeground(this.standardfarbe);
        this.warning_red_p1_t.setForeground(this.standardfarbe);
        this.warning_red_p2_t.setForeground(this.standardfarbe);
        this.warning_red_p1bald_t.setForeground(this.standardfarbe);
        this.warning_red_p2bald_t.setForeground(this.standardfarbe);
        this.warning_red_maxarbeitbald_t.setForeground(this.standardfarbe);
        this.warning_red_zufrueh_t.setForeground(this.standardfarbe);
        this.warning_yellow_zuspaet_t.setForeground(this.standardfarbe);
        this.warning_yellow_zukunft_t.setForeground(this.standardfarbe);
        this.warning_yellow_maxarbeit_t.setForeground(this.standardfarbe);
        this.warning_yellow_keinep2_t.setForeground(this.standardfarbe);
        this.check_p1_t.setForeground(new Color(140, 140, 140));
        this.check_feierabend_t.setForeground(new Color(140, 140, 140));
        this.check_p2_t.setForeground(new Color(140, 140, 140));
        this.check_maxarbeit_t.setForeground(new Color(140, 140, 140));
        this.warning_red_info_t.setForeground(this.standardfarbe);
        this.warning_yellow_info_t.setForeground(this.standardfarbe);
        this.uhr.setForeground(this.standardfarbe);
        this.feierabend.setForeground(this.standardfarbe);
        this.maxarbeit.setForeground(this.standardfarbe);
        this.saldo.setForeground(this.standardfarbe);
        this.pause1.setForeground(this.standardfarbe);
        this.pause2.setForeground(this.standardfarbe);
        this.kommen.setForeground(this.standardfarbe);
        this.pause1_ende.setForeground(this.standardfarbe);
        this.pause2_ende.setForeground(this.standardfarbe);
        this.prozent_feierabend.setForeground(this.standardfarbe);
        this.prozent_ueberstunden.setForeground(this.standardfarbe);
        this.prozent_pause1.setForeground(this.standardfarbe);
        this.prozent_pause2.setForeground(this.standardfarbe);
        this.bar_feierabend.setBackground(new Color(170, 240, 255));
        this.bar_ueberstunden.setBackground(new Color(170, 240, 255));
        this.bar_pause1.setBackground(new Color(170, 240, 255));
        this.bar_pause2.setBackground(new Color(170, 240, 255));
        this.kommen_stunde.setSelectedItem(stundekommen_config);
        this.kommen_minute.setSelectedItem(minutekommen_config);
        this.kommen_stunde.setBounds(10, 35, 50, 20);
        this.kommen_minute.setBounds(65, 35, 50, 20);
        this.happa_stunde.setSelectedItem(stundeessen_config);
        this.happa_minute.setSelectedItem(minuteessen_config);
        this.happa_stunde.setBounds(450, 50, 50, 20);
        this.happa_minute.setBounds(505, 50, 50, 20);
        this.netto.setSelectedItem(String.valueOf(gehalt_config) + "\u20ac");
        this.netto.setBounds(460, 280, 70, 20);
        this.add(this.netto);
        this.kommen_text = new JLabel("<HTML><U>Kommen:</U></HTML>");
        this.kommen_text.setBounds(10, 10, 80, 20);
        this.kommen_text.setForeground(this.standardfarbe);
        this.uhr_text = new JLabel("<HTML><U>Uhrzeit:</U></HTML>");
        this.uhr_text.setBounds(120, 10, 80, 20);
        this.uhr_text.setForeground(this.standardfarbe);
        this.feierabend_text = new JLabel("<HTML><U>Feierabend:</U></HTML>");
        this.feierabend_text.setBounds(180, 10, 80, 20);
        this.feierabend_text.setForeground(this.standardfarbe);
        this.maxarbeit_text = new JLabel("<HTML><U>Max. Arbeitszeit:</U></HTML>");
        this.maxarbeit_text.setBounds(260, 10, 100, 20);
        this.maxarbeit_text.setForeground(this.standardfarbe);
        this.saldo_text = new JLabel("<HTML><U>Saldo:</HTML></U>");
        this.saldo_text.setBounds(380, 10, 80, 20);
        this.saldo_text.setForeground(this.standardfarbe);
        this.pause1_text = new JLabel("<HTML><U>1.Pause:</HTML></U>");
        this.pause1_text.setBounds(440, 10, 150, 20);
        this.pause1_text.setForeground(this.standardfarbe);
        this.pause2_text = new JLabel("<HTML><U>2.Pause:</HTML></U>");
        this.pause2_text.setBounds(515, 10, 150, 20);
        this.pause2_text.setForeground(this.standardfarbe);
        this.uhr.setBounds(120, 30, 80, 20);
        this.feierabend.setBounds(210, 30, 80, 20);
        this.maxarbeit.setBounds(260, 30, 80, 20);
        this.saldo.setBounds(375, 30, 80, 20);
        this.pause1.setBounds(440, 30, 40, 20);
        this.pause2.setBounds(515, 30, 40, 20);
        this.pause1_ende.setBounds(474, 30, 40, 20);
        this.pause2_ende.setBounds(549, 30, 40, 20);
        this.kommen.setBounds(65, 11, 80, 20);
        this.prozent_feierabend.setBounds(425, 79, 200, 20);
        this.prozent_ueberstunden.setBounds(425, 114, 200, 20);
        this.prozent_pause1.setBounds(425, 138, 200, 20);
        this.prozent_pause2.setBounds(425, 155, 200, 20);
        this.bisessen.setBounds(560, 50, 40, 20);
        this.netto_text.setBounds(530, 280, 70, 20);
        this.netto_text.setForeground(this.standardfarbe);
        this.bar_feierabend.setBounds(10, 75, 410, 25);
        this.bar_ueberstunden.setBounds(10, 110, 410, 25);
        this.bar_pause1.setBounds(10, 145, 200, 25);
        this.bar_pause2.setBounds(220, 145, 200, 25);
        this.refresh_button.setBounds(10, 180, 100, 20);
        this.hilfe_button.setBounds(120, 180, 100, 20);
        this.exit_button.setBounds(230, 180, 100, 20);
        this.refresh_button.addActionListener(this);
        this.hilfe_button.addActionListener(this);
        this.exit_button.addActionListener(this);
        this.full.setSelected(false);
        this.small.setSelected(true);
        this.full.addActionListener(this);
        this.small.addActionListener(this);
        ButtonGroup group1 = new ButtonGroup();
        group1.add(this.full);
        group1.add(this.small);
        this.full.setBounds(340, 180, 160, 18);
        this.small.setBounds(500, 180, 100, 18);
        this.normal.setSelected(true);
        this.mini.setSelected(true);
        this.normal.addActionListener(this);
        this.mini.addActionListener(this);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(this.normal);
        group2.add(this.mini);
        this.normal.setBounds(10, 56, 70, 18);
        this.mini.setBounds(80, 56, 70, 18);
        ImageIcon warning_red = ImageImport.createImageIcon(this, "/img/warning_red.png");
        ImageIcon warning_yellow = ImageImport.createImageIcon(this, "/img/warning_yellow.png");
        ImageIcon check = ImageImport.createImageIcon(this, "/img/check.png");
        ImageIcon plus = ImageImport.createImageIcon(this, "/img/plus.png");
        ImageIcon minus = ImageImport.createImageIcon(this, "/img/minus.png");
        ImageIcon flag = ImageImport.createImageIcon(this, "/img/flag.png");
        ImageIcon essen = ImageImport.createImageIcon(this, "/img/essen.png");
        ImageIcon bank = ImageImport.createImageIcon(this, "/img/bank.png");
        ImageIcon coffee = ImageImport.createImageIcon(this, "/img/coffee.png");
        this.warning_red_p1 = new JLabel(warning_red);
        this.warning_red_p2 = new JLabel(warning_red);
        this.warning_red_p1bald = new JLabel(warning_red);
        this.warning_red_p2bald = new JLabel(warning_red);
        this.warning_red_maxarbeitbald = new JLabel(warning_red);
        this.warning_red_zufrueh = new JLabel(warning_red);
        this.warning_yellow_zuspaet = new JLabel(warning_yellow);
        this.warning_yellow_zukunft = new JLabel(warning_yellow);
        this.warning_yellow_maxarbeit = new JLabel(warning_yellow);
        this.warning_yellow_keinep2 = new JLabel(warning_yellow);
        this.check_p1 = new JLabel(check);
        this.check_feierabend = new JLabel(check);
        this.check_p2 = new JLabel(check);
        this.check_maxarbeit = new JLabel(check);
        this.warning_red_info = new JLabel(warning_red);
        this.warning_yellow_info = new JLabel(warning_yellow);
        this.warning_red_avaible = new JLabel(warning_red);
        this.warning_yellow_avaible = new JLabel(warning_yellow);
        this.saldo_plus = new JLabel(plus);
        this.saldo_minus = new JLabel(minus);
        this.feierabend_flag = new JLabel(flag);
        this.essen_pic = new JLabel(essen);
        this.kommen_pic = new JLabel(bank);
        this.pause1_pic = new JLabel(coffee);
        this.pause2_pic = new JLabel(coffee);
        this.check_bar1 = new JLabel(check);
        this.check_bar2 = new JLabel(check);
        this.check_bar3 = new JLabel(check);
        this.check_bar4 = new JLabel(check);
        this.warning_red_p1.setBounds(10, 200, 20, 20);
        this.warning_red_p2.setBounds(10, 220, 20, 20);
        this.warning_red_p1bald.setBounds(10, 200, 20, 20);
        this.warning_red_p2bald.setBounds(10, 220, 20, 20);
        this.warning_red_maxarbeitbald.setBounds(10, 240, 20, 20);
        this.warning_red_zufrueh.setBounds(10, 260, 20, 20);
        this.warning_yellow_zuspaet.setBounds(10, 280, 20, 20);
        this.warning_yellow_zukunft.setBounds(10, 300, 20, 20);
        this.warning_yellow_maxarbeit.setBounds(10, 320, 20, 20);
        this.warning_yellow_keinep2.setBounds(10, 340, 20, 20);
        this.check_p1.setBounds(570, 200, 20, 20);
        this.check_feierabend.setBounds(570, 220, 20, 20);
        this.check_p2.setBounds(570, 240, 20, 20);
        this.check_maxarbeit.setBounds(570, 260, 20, 20);
        this.warning_red_info.setBounds(570, 300, 20, 20);
        this.warning_yellow_info.setBounds(570, 320, 20, 20);
        this.warning_red_avaible.setBounds(570, 140, 20, 20);
        this.warning_yellow_avaible.setBounds(570, 160, 20, 20);
        this.saldo_plus.setBounds(390, 50, 20, 20);
        this.saldo_minus.setBounds(390, 50, 20, 20);
        this.feierabend_flag.setBounds(245, 35, 24, 24);
        this.essen_pic.setBounds(425, 50, 20, 20);
        this.kommen_pic.setBounds(98, 10, 20, 20);
        this.pause1_pic.setBounds(493, 10, 20, 20);
        this.pause2_pic.setBounds(568, 10, 20, 20);
        this.warning_red_p1_t.setBounds(30, 200, 450, 20);
        this.warning_red_p2_t.setBounds(30, 220, 450, 20);
        this.warning_red_p1bald_t.setBounds(30, 200, 450, 20);
        this.warning_red_p2bald_t.setBounds(30, 220, 450, 20);
        this.warning_red_maxarbeitbald_t.setBounds(30, 240, 450, 20);
        this.warning_red_zufrueh_t.setBounds(30, 260, 450, 20);
        this.warning_yellow_zuspaet_t.setBounds(30, 280, 450, 20);
        this.warning_yellow_zukunft_t.setBounds(30, 300, 450, 20);
        this.warning_yellow_maxarbeit_t.setBounds(30, 320, 450, 20);
        this.warning_yellow_keinep2_t.setBounds(30, 340, 450, 20);
        this.check_p1_t.setBounds(499, 200, 70, 20);
        this.check_feierabend_t.setBounds(494, 220, 70, 20);
        this.check_p2_t.setBounds(499, 240, 70, 20);
        this.check_maxarbeit_t.setBounds(480, 260, 90, 20);
        this.warning_red_info_t.setBounds(500, 300, 90, 20);
        this.warning_yellow_info_t.setBounds(500, 320, 90, 20);
        this.check_bar1.setBounds(520, 80, 20, 20);
        this.check_bar2.setBounds(520, 115, 20, 20);
        this.check_bar3.setBounds(520, 140, 20, 20);
        this.check_bar4.setBounds(520, 155, 20, 20);
        this.addAll();
        this.setVisible(true);
        int change1 = -1;
        int change2 = -1;
        int change3 = -1;
        int change4 = -1;
        this.allwarningsinvisible();
        boolean dummy = false;
        boolean neue_benutzereingabe = false;
        while (true) {
            long vorher = System.nanoTime();
            if (change1 != this.kommen_stunde.getSelectedIndex() || change2 != this.kommen_minute.getSelectedIndex() || change3 != this.happa_stunde.getSelectedIndex() || change4 != this.happa_minute.getSelectedIndex()) {
                this.allwarningsinvisible();
                change1 = this.kommen_stunde.getSelectedIndex();
                change2 = this.kommen_minute.getSelectedIndex();
                change3 = this.happa_stunde.getSelectedIndex();
                change4 = this.happa_minute.getSelectedIndex();
                neue_benutzereingabe = true;
            }
            if (this.kommen_stunde.getSelectedIndex() == 0 && this.kommen_minute.getSelectedIndex() < 30) {
                this.kommen_minute.setVisible(false);
                this.kommen_minute.setSelectedIndex(30);
                this.kommen_minute.setVisible(true);
                this.warning_red_zufrueh.setVisible(true);
                this.warning_red_zufrueh_t.setVisible(true);
                change1 = this.kommen_stunde.getSelectedIndex();
                change2 = this.kommen_minute.getSelectedIndex();
            }
            if (this.kommen_stunde.getSelectedIndex() > 3 || this.kommen_stunde.getSelectedIndex() == 3 && this.kommen_minute.getSelectedIndex() > 0) {
                this.warning_yellow_zuspaet.setVisible(true);
                this.warning_yellow_zuspaet_t.setVisible(true);
            }
            this.zeit_uhrzeit.setTime(System.currentTimeMillis());
            this.uhr.setText(this.sdfAktuell.format(this.zeit_uhrzeit));
            this.uhr.setFont(new Font("Dialog", 1, 15));
            if (neue_benutzereingabe) {
                this.zeit_kommen.setSeconds(0);
                this.zeit_kommen.setHours(this.kommen_stunde.getSelectedIndex() + 6);
                this.zeit_kommen.setMinutes(this.kommen_minute.getSelectedIndex());
                this.kommen.setText(this.sdfFeierabend.format(this.zeit_kommen));
                this.zeit_maxarbeit.setSeconds(0);
                this.zeit_maxarbeit.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 10);
                this.zeit_maxarbeit.setMinutes(this.kommen_minute.getSelectedIndex() + 45);
                if (this.zeit_maxarbeit.getHours() >= 20 || this.zeit_maxarbeit.getHours() >= 19 && this.zeit_maxarbeit.getMinutes() > 30) {
                    this.zeit_maxarbeit.setHours(19);
                    this.zeit_maxarbeit.setMinutes(30);
                    this.warning_yellow_maxarbeit.setVisible(true);
                    this.warning_yellow_maxarbeit_t.setVisible(true);
                }
                this.maxarbeit.setText(this.sdfFeierabend.format(this.zeit_maxarbeit));
                this.maxarbeit.setFont(new Font("Dialog", 1, 15));
                this.zeit_feierabend.setSeconds(0);
                this.zeit_feierabend.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 8);
                this.zeit_feierabend.setMinutes(this.kommen_minute.getSelectedIndex() + 18);
                this.feierabend.setText(this.sdfFeierabend.format(this.zeit_feierabend));
                this.feierabend.setFont(new Font("Dialog", 1, 15));
                this.zeit_pause1.setSeconds(0);
                this.zeit_pause1.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 6);
                this.zeit_pause1.setMinutes(this.kommen_minute.getSelectedIndex());
                this.pause1.setText(String.valueOf(this.sdfFeierabend.format(this.zeit_pause1)) + "-");
                this.zeit_pause1ende.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 6);
                this.zeit_pause1ende.setMinutes(this.kommen_minute.getSelectedIndex() + 30);
                this.pause1_ende.setText(this.sdfFeierabend.format(this.zeit_pause1ende));
                this.zeit_pause2.setSeconds(0);
                this.zeit_pause2.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 9);
                this.zeit_pause2.setMinutes(this.kommen_minute.getSelectedIndex() + 30);
                this.pause2.setText(String.valueOf(this.sdfFeierabend.format(this.zeit_pause2)) + "-");
                this.zeit_pause2ende.setHours(this.kommen_stunde.getSelectedIndex() + 6 + 9);
                this.zeit_pause2ende.setMinutes(this.kommen_minute.getSelectedIndex() + 45);
                this.pause2_ende.setText(this.sdfFeierabend.format(this.zeit_pause2ende));
                if (this.zeit_pause2ende.getHours() >= 20 || this.zeit_pause2ende.getHours() >= 19 && this.zeit_pause2ende.getMinutes() >= 45) {
                    this.pause2.setText("N/A");
                    this.pause2_ende.setText("");
                    this.warning_yellow_keinep2.setVisible(true);
                    this.warning_yellow_keinep2_t.setVisible(true);
                }
                this.zeit_happa.setHours(this.happa_stunde.getSelectedIndex() + 7);
                this.zeit_happa.setMinutes(this.happa_minute.getSelectedIndex());
                this.zeit_happa.setSeconds(0);
                if (this.zeit_happa.getTime() > this.zeit_uhrzeit.getTime()) {
                    this.zeit_bisessen.setHours(this.zeit_happa.getHours() - this.zeit_uhrzeit.getHours());
                    this.zeit_bisessen.setMinutes(this.zeit_happa.getMinutes() - this.zeit_uhrzeit.getMinutes());
                    this.zeit_bisessen.setSeconds(0);
                } else {
                    this.zeit_bisessen.setHours(0);
                    this.zeit_bisessen.setMinutes(0);
                    this.zeit_bisessen.setSeconds(0);
                    this.bisessen.setForeground(new Color(0, 200, 0));
                }
                this.bisessen.setText(this.sdfFeierabend.format(this.zeit_bisessen));
            }
            this.zeit_anwesend.setSeconds(0);
            this.zeit_anwesend.setHours(this.zeit_uhrzeit.getHours() - this.zeit_kommen.getHours());
            this.zeit_anwesend.setMinutes(this.zeit_uhrzeit.getMinutes() - this.zeit_kommen.getMinutes());
            this.zeit_saldo.setSeconds(0);
            if (this.zeit_feierabend.getTime() >= this.zeit_uhrzeit.getTime()) {
                this.zeit_saldo.setHours(this.zeit_feierabend.getHours() - this.zeit_uhrzeit.getHours());
                this.zeit_saldo.setMinutes(this.zeit_feierabend.getMinutes() - this.zeit_uhrzeit.getMinutes() - 30);
                if (this.zeit_uhrzeit.getTime() > this.zeit_pause1ende.getTime()) {
                    this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes() + 30);
                }
                if (this.zeit_uhrzeit.getTime() > this.zeit_pause1.getTime() && this.zeit_uhrzeit.getTime() < this.zeit_pause1ende.getTime()) {
                    Date differenz = new Date();
                    differenz.setHours(this.zeit_uhrzeit.getHours() - this.zeit_pause1.getHours());
                    differenz.setMinutes(this.zeit_uhrzeit.getMinutes() - this.zeit_pause1.getMinutes());
                    this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes() + differenz.getMinutes());
                }
                this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes());
                this.saldo.setText("-" + this.sdfFeierabend.format(this.zeit_saldo));
            } else {
                this.zeit_saldo.setHours(this.zeit_uhrzeit.getHours() - this.zeit_feierabend.getHours());
                this.zeit_saldo.setMinutes(this.zeit_uhrzeit.getMinutes() - this.zeit_feierabend.getMinutes());
                if (this.zeit_uhrzeit.getTime() > this.zeit_pause2ende.getTime()) {
                    this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes() - 15);
                }
                if (this.zeit_uhrzeit.getTime() > this.zeit_pause2.getTime() && this.zeit_uhrzeit.getTime() < this.zeit_pause2ende.getTime()) {
                    Date differenz = new Date();
                    differenz.setHours(this.zeit_uhrzeit.getHours() - this.zeit_pause2.getHours());
                    differenz.setMinutes(this.zeit_uhrzeit.getMinutes() - this.zeit_pause2.getMinutes());
                    this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes() - differenz.getMinutes());
                }
                this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes());
                this.saldo.setText("+" + this.sdfFeierabend.format(this.zeit_saldo));
            }
            if (this.zeit_kommen.getTime() > this.zeit_uhrzeit.getTime()) {
                this.zeit_saldo.setHours(7);
                this.zeit_saldo.setMinutes(48);
                this.saldo.setText("-" + this.sdfFeierabend.format(this.zeit_saldo));
                this.warning_yellow_zukunft.setVisible(true);
                this.warning_yellow_zukunft_t.setVisible(true);
            } else {
                this.warning_yellow_zukunft.setVisible(false);
                this.warning_yellow_zukunft_t.setVisible(false);
            }
            if (this.zeit_uhrzeit.getTime() > this.zeit_maxarbeit.getTime()) {
                this.zeit_dummy.setHours(this.zeit_uhrzeit.getHours() - this.zeit_maxarbeit.getHours());
                this.zeit_dummy.setMinutes(this.zeit_uhrzeit.getMinutes() - this.zeit_maxarbeit.getMinutes());
                this.zeit_saldo.setHours(this.zeit_saldo.getHours() - this.zeit_dummy.getHours());
                this.zeit_saldo.setMinutes(this.zeit_saldo.getMinutes() - this.zeit_dummy.getMinutes());
            }
            this.saldo.setFont(new Font("Dialog", 1, 15));
            double feierabend_double = 100.0 - ((double)this.zeit_feierabend.getTime() - (double)this.zeit_uhrzeit.getTime()) / (((double)this.zeit_feierabend.getTime() - (double)this.zeit_kommen.getTime()) / 100.0);
            double ueberstunden_double = 100.0 - ((double)this.zeit_maxarbeit.getTime() - (double)this.zeit_uhrzeit.getTime()) / (((double)this.zeit_maxarbeit.getTime() - (double)this.zeit_feierabend.getTime()) / 100.0);
            double pause1_double = 100.0 - ((double)this.zeit_pause1ende.getTime() - (double)this.zeit_uhrzeit.getTime()) / (((double)this.zeit_pause1ende.getTime() - (double)this.zeit_pause1.getTime()) / 100.0);
            double pause2_double = 100.0 - ((double)this.zeit_pause2ende.getTime() - (double)this.zeit_uhrzeit.getTime()) / (((double)this.zeit_pause2ende.getTime() - (double)this.zeit_pause2.getTime()) / 100.0);
            double anwesend_in_sekunden = (double)this.zeit_uhrzeit.getSeconds() + (double)this.zeit_anwesend.getMinutes() * 60.0 + (double)this.zeit_anwesend.getHours() * 60.0 * 60.0;
            double money = (double)(this.netto.getSelectedIndex() * 10 + 400) / 168.48 / 60.0 / 60.0 * anwesend_in_sekunden;
            this.netto_text.setText(this.dfProzent.format(money) + " \u20ac");
            if (this.zeit_uhrzeit.getTime() < this.zeit_kommen.getTime()) {
                this.netto_text.setText("0.00 \u20ac");
            }
            if (feierabend_double >= 100.0) {
                feierabend_double = 100.0;
                this.check_bar1.setVisible(true);
                this.check_feierabend.setVisible(true);
                this.check_feierabend_t.setForeground(this.standardfarbe);
                this.saldo_plus.setVisible(true);
            }
            if (ueberstunden_double >= 100.0) {
                ueberstunden_double = 100.0;
                this.check_bar2.setVisible(true);
                this.check_maxarbeit.setVisible(true);
                this.check_maxarbeit_t.setForeground(this.standardfarbe);
            }
            if (feierabend_double != 100.0) {
                ueberstunden_double = 0.0;
            }
            if (feierabend_double <= 0.0) {
                feierabend_double = 0.0;
            }
            if (ueberstunden_double <= 0.0) {
                ueberstunden_double = 0.0;
                this.saldo_minus.setVisible(true);
            }
            if (pause1_double >= 100.0) {
                pause1_double = 100.0;
                this.check_bar3.setVisible(true);
                this.check_p1.setVisible(true);
                this.check_p1_t.setForeground(this.standardfarbe);
            }
            if (pause2_double >= 100.0) {
                pause2_double = 100.0;
                this.check_bar4.setVisible(true);
                this.check_p2.setVisible(true);
                this.check_p2_t.setForeground(this.standardfarbe);
            }
            if (pause1_double <= 0.0) {
                pause1_double = 0.0;
            }
            if (pause2_double <= 0.0) {
                pause2_double = 0.0;
            }
            this.bar_feierabend.setValue((int)feierabend_double);
            this.bar_ueberstunden.setValue((int)ueberstunden_double);
            this.bar_pause1.setValue((int)pause1_double);
            this.bar_pause2.setValue((int)pause2_double);
            this.bar_feierabend.setForeground(new Color(this.gibRot((int)feierabend_double), this.gibGruen((int)feierabend_double), 0));
            this.bar_ueberstunden.setForeground(new Color(this.gibRot((int)ueberstunden_double), 255, 0));
            this.bar_pause1.setForeground(new Color(this.gibRot((int)pause1_double), this.gibGruen((int)pause1_double), 0));
            this.bar_pause2.setForeground(new Color(this.gibRot((int)pause2_double), this.gibGruen((int)pause2_double), 0));
            this.prozent_feierabend.setText(String.valueOf(this.dfProzent.format(feierabend_double)) + "% Soll");
            this.prozent_feierabend.setForeground(new Color(this.gibRot((int)feierabend_double), this.gibGruen((int)feierabend_double), 0));
            if (feierabend_double < 100.0) {
                this.saldo.setForeground(new Color(255, 0, 0));
            }
            if (feierabend_double == 100.0) {
                this.saldo.setForeground(new Color(this.gibRot((int)feierabend_double), this.gibGruen((int)feierabend_double), 0));
            }
            this.saldo_text.setForeground(this.standardfarbe);
            this.setTitle(String.valueOf(this.saldo.getText()) + " " + this.dfProzent.format(feierabend_double) + "% - GZR v3.5");
            this.prozent_ueberstunden.setText(String.valueOf(this.dfProzent.format(ueberstunden_double)) + "% \u00dcberstd.");
            this.prozent_ueberstunden.setForeground(new Color(this.gibRot((int)ueberstunden_double), 255, 0));
            this.prozent_pause1.setText(String.valueOf(this.dfPause.format(pause1_double)) + "% 1.Pause");
            this.prozent_pause1.setForeground(this.standardfarbe2);
            this.prozent_pause2.setText(String.valueOf(this.dfPause.format(pause2_double)) + "% 2.Pause");
            this.prozent_pause2.setForeground(this.standardfarbe2);
            this.feierabend.setForeground(this.standardfarbe2);
            this.feierabend_text.setForeground(this.standardfarbe2);
            this.maxarbeit.setForeground(this.standardfarbe2);
            this.maxarbeit_text.setForeground(this.standardfarbe2);
            this.bisessen.setForeground(this.standardfarbe2);
            if (pause1_double != 0.0 && pause1_double != 100.0) {
                this.warning_red_p1.setVisible(true);
                this.warning_red_p1_t.setVisible(true);
                this.prozent_pause1.setForeground(Color.orange);
            } else {
                this.warning_red_p1.setVisible(false);
                this.warning_red_p1_t.setVisible(false);
                this.prozent_pause1.setForeground(this.standardfarbe2);
            }
            if (pause2_double != 0.0 && pause2_double != 100.0) {
                this.warning_red_p2.setVisible(true);
                this.warning_red_p2_t.setVisible(true);
                this.prozent_pause2.setForeground(Color.orange);
            } else {
                this.warning_red_p2.setVisible(false);
                this.warning_red_p2_t.setVisible(false);
                this.prozent_pause2.setForeground(this.standardfarbe2);
            }
            this.zeit_uhrzeit.setMinutes(this.zeit_uhrzeit.getMinutes() + 15);
            if (this.zeit_uhrzeit.getTime() >= this.zeit_maxarbeit.getTime()) {
                this.warning_red_maxarbeitbald.setVisible(true);
                this.warning_red_maxarbeitbald_t.setVisible(true);
            }
            if (this.zeit_uhrzeit.getTime() >= this.zeit_pause1.getTime() && !this.warning_red_p1.isVisible() && this.zeit_uhrzeit.getTime() <= this.zeit_pause1ende.getTime()) {
                this.warning_red_p1bald.setVisible(true);
                this.warning_red_p1bald_t.setVisible(true);
                this.pause1.setForeground(Color.red);
            } else {
                this.warning_red_p1bald.setVisible(false);
                this.warning_red_p1bald_t.setVisible(false);
                this.pause1.setForeground(this.standardfarbe);
            }
            if (this.zeit_uhrzeit.getTime() >= this.zeit_pause2.getTime() && !this.warning_red_p2.isVisible() && this.zeit_uhrzeit.getTime() <= this.zeit_pause2ende.getTime()) {
                this.warning_red_p2bald.setVisible(true);
                this.warning_red_p2bald_t.setVisible(true);
                this.pause2.setForeground(Color.red);
            } else {
                this.warning_red_p2bald.setVisible(false);
                this.warning_red_p2bald_t.setVisible(false);
                this.pause2.setForeground(this.standardfarbe);
            }
            if (this.warning_red_avaible()) {
                this.warning_red_avaible.setVisible(true);
            } else {
                this.warning_red_avaible.setVisible(false);
            }
            if (this.warning_yellow_avaible()) {
                this.warning_yellow_avaible.setVisible(true);
            } else {
                this.warning_yellow_avaible.setVisible(false);
            }
            if (!dummy) {
                this.setVisible(false);
                this.setVisible(true);
                dummy = true;
            }
            try {
                this.configDatei_schreiben(this.netto.getSelectedIndex() * 10 + 400 + "x" + this.kommen_stunde.getSelectedItem() + "x" + this.kommen_minute.getSelectedItem() + "x" + this.happa_stunde.getSelectedItem() + "x" + this.happa_minute.getSelectedItem());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            long nachher = System.nanoTime();
            long differenz = nachher - vorher;
            double differenz2 = (double)differenz / 1000000.0;
            System.out.println(String.valueOf(differenz2) + "ms - Rechenzeit f\u00fcr vergangene Sekunde.");
            if (!(differenz2 < 999.0)) continue;
            try {
                Thread.sleep((long)(999.0 - differenz2));
            }
            catch (Exception exception) {
                continue;
            }
            break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == this.refresh_button) {
            this.setVisible(false);
            this.setVisible(true);
        }
        if (arg0.getSource() == this.hilfe_button) {
            JOptionPane.showMessageDialog(null, "<html>(3.0) Der Gleitzeitrechner stellt das Saldo-Konto und die \u00dcberstunden des aktuellen Arbeitstages optisch dar.<br>(3.0) Der obere Teil besteht aus Informationsanzeigen, welche abh\u00e4ngig von Ihrer Kommen Buchung sind.<br>(3.0) Diese Anzeigen werden durch Prozentbalken optisch versch\u00f6nert.<br>(3.0) Au\u00dferhalb der offiziellen Arbeitszeit (06:30 - 19:30) kann nicht gearbeitet werden. (zeigt Warning)<br>(3.0) Bitte beachten Sie die aktuelle Kernarbeitszeit zwischen 09:00-15:00. (zeigt Warning)<br>(3.0) Die benutzten Werte sind abgestimmt f\u00fcr Angestellte im \u00f6ffentlichen Dienst (Mo-Fr, 39Stunden).<br>(3.0) Nach 6:00 Stunden Anwesenheit werden 30 min. Pflichtpause berechnet. Das Gleitzeitsaldo-Konto pausiert bis die Pause vor\u00fcber ist.<br>(3.0) Nach 9:30 Stunden Anwesenheit werden 15 min. Pflichtpause berechnet. Das Gleitzeitsaldo-Konto pausiert bis die Pause vor\u00fcber ist.<br>(3.0) Der Gleitzeitrechner l\u00e4sst sich in 3 verschiedene Gr\u00f6\u00dfen mit unterschiedlichem Informationsgehalt einstellen.<br>(3.0) Im unteren Teil werden Warnings sichtbar, falls es einen Anlass f\u00fcr diese gibt.<br>(3.0) Falls Warnings ausgeblendet wurden, wird in der 'normalen' Ansicht dennoch optisch sichbar sobald welche Vorhanden sind.<br>(3.0) Durch den Refresh Button wird die Grafische Oberfl\u00e4che(GUI) resettet (bei eventuellen Bugs (aktuell keine bekannt)).<br>(3.1) Einige Bugfixes<br>(3.2) Durch Eingabe einer Essenszeit kann zus\u00e4tzlich beobachtet werden wie lange es noch bis zur n\u00e4chsten Mahlzeit dauert.<br>(3.2) Der Gehalt-Rechner rechnet mit 21,6 Arbeitstagen pro Monat(Wert aus Buchhaltung). Bei 7,80Stunden/Tag sind dies 168,48 Stunden/Monat im Durchschnitt.<br>(3.2) Angegeben werden soll der Netto-Monats-Verdienst!<br>(3.3) Zus\u00e4tzliche Anzeige des Saldos und der Prozente in der Taskleiste<br>(3.4) Die zuletzt eingegebenen Daten (Gehalt, Kommenzeit, Essenszeit) werden gepspeichert und hinterlegt. (z.b. f\u00fcr den Fall eines Rechnerneustarts)<br>(3.5) Auch unter Windows7 werden nun die zuletzt eingegebenen Werte gespeichert.<br>uvm.<br>Nat\u00fcrlich keine Gew\u00e4hrleistung auf Richtigkeit.<br>(!)Der GZR unterst\u00fctzt keine flexible Kernzeit, Fr\u00fchschicht, Sp\u00e4tschicht oder 41-Stunden Woche.<br><br>v3.5 - A.Lohr, Dezember 2011-August 2013");
        }
        if (arg0.getSource() == this.exit_button) {
            System.exit(-1);
        }
        if (arg0.getSource() == this.full) {
            this.setSize(600, 385);
        }
        if (arg0.getSource() == this.small) {
            this.setSize(600, 227);
        }
        if (arg0.getSource() == this.normal) {
            this.setSize(600, 227);
            this.exit_button.setBounds(230, 180, 100, 20);
            this.small.setSelected(true);
        }
        if (arg0.getSource() == this.mini) {
            this.setSize(430, 100);
            this.exit_button.setBounds(318, 51, 100, 20);
        }
    }

    public int gibRot(int prozent) {
        if (prozent <= 70) {
            return 255;
        }
        return (int)(255.0 - (double)(prozent - 70) * 8.5);
    }

    public int gibGruen(int prozent) {
        if (prozent <= 70) {
            return (int)((double)prozent * 3.6);
        }
        return 250;
    }

    public boolean warning_red_avaible() {
        return this.warning_red_p1.isVisible() || this.warning_red_p2.isVisible() || this.warning_red_maxarbeitbald.isVisible() || this.warning_red_zufrueh.isVisible() || this.warning_red_p1bald.isVisible() || this.warning_red_p2bald.isVisible();
    }

    public boolean warning_yellow_avaible() {
        return this.warning_yellow_zuspaet.isVisible() || this.warning_yellow_zukunft.isVisible() || this.warning_yellow_maxarbeit.isVisible() || this.warning_yellow_keinep2.isVisible();
    }

    public void allwarningsinvisible() {
        this.warning_red_p1.setVisible(false);
        this.warning_red_p2.setVisible(false);
        this.warning_red_p1bald.setVisible(false);
        this.warning_red_p2bald.setVisible(false);
        this.warning_red_maxarbeitbald.setVisible(false);
        this.warning_red_zufrueh.setVisible(false);
        this.warning_yellow_zuspaet.setVisible(false);
        this.warning_yellow_zukunft.setVisible(false);
        this.warning_yellow_maxarbeit.setVisible(false);
        this.warning_yellow_keinep2.setVisible(false);
        this.check_p1.setVisible(false);
        this.check_feierabend.setVisible(false);
        this.check_p2.setVisible(false);
        this.check_maxarbeit.setVisible(false);
        this.warning_red_avaible.setVisible(false);
        this.warning_yellow_avaible.setVisible(false);
        this.saldo_plus.setVisible(false);
        this.saldo_minus.setVisible(false);
        this.warning_red_p1_t.setVisible(false);
        this.warning_red_p2_t.setVisible(false);
        this.warning_red_p1bald_t.setVisible(false);
        this.warning_red_p2bald_t.setVisible(false);
        this.warning_red_maxarbeitbald_t.setVisible(false);
        this.warning_red_zufrueh_t.setVisible(false);
        this.warning_yellow_zuspaet_t.setVisible(false);
        this.warning_yellow_zukunft_t.setVisible(false);
        this.warning_yellow_maxarbeit_t.setVisible(false);
        this.warning_yellow_keinep2_t.setVisible(false);
        this.check_bar1.setVisible(false);
        this.check_bar2.setVisible(false);
        this.check_bar3.setVisible(false);
        this.check_bar4.setVisible(false);
        this.check_p1_t.setForeground(new Color(140, 140, 140));
        this.check_feierabend_t.setForeground(new Color(140, 140, 140));
        this.check_p2_t.setForeground(new Color(140, 140, 140));
        this.check_maxarbeit_t.setForeground(new Color(140, 140, 140));
    }

    public void configDatei_schreiben(String text) throws IOException, URISyntaxException {
        FileOutputStream schreibeStrom = new FileOutputStream("config_gzr_3_5.txt");
        int i = 0;
        while (i < text.length()) {
            schreibeStrom.write((byte)text.charAt(i));
            ++i;
        }
        schreibeStrom.close();
    }

    public String configDatei_lesen() throws IOException {
        String text = "";
        boolean ist_vorhanden = false;
        File f = new File("config_gzr_3_5.txt");
        if (f.exists()) {
            ist_vorhanden = true;
        }
        if (ist_vorhanden) {
            byte zeichen;
            FileInputStream leseStrom = new FileInputStream("config_gzr_3_5.txt");
            do {
                zeichen = (byte)leseStrom.read();
                text = String.valueOf(text) + (char)zeichen;
            } while (zeichen != -1);
            leseStrom.close();
            return text.substring(0, text.length() - 1);
        }
        return "";
    }

    public void addAll() {
        this.add(this.kommen_stunde);
        this.add(this.kommen_minute);
        this.add(this.happa_stunde);
        this.add(this.happa_minute);
        this.add(this.kommen_text);
        this.add(this.uhr_text);
        this.add(this.feierabend_text);
        this.add(this.maxarbeit_text);
        this.add(this.saldo_text);
        this.add(this.pause1_text);
        this.add(this.pause2_text);
        this.add(this.refresh_button);
        this.add(this.hilfe_button);
        this.add(this.exit_button);
        this.add(this.full);
        this.add(this.small);
        this.add(this.normal);
        this.add(this.mini);
        this.add(this.warning_red_info);
        this.add(this.warning_yellow_info);
        this.add(this.warning_red_info_t);
        this.add(this.warning_yellow_info_t);
        this.add(this.feierabend_flag);
        this.add(this.essen_pic);
        this.add(this.kommen_pic);
        this.add(this.pause1_pic);
        this.add(this.pause2_pic);
        this.add(this.netto_text);
        this.add(this.uhr);
        this.add(this.kommen);
        this.add(this.maxarbeit);
        this.add(this.feierabend);
        this.add(this.pause1);
        this.add(this.pause1_ende);
        this.add(this.pause2);
        this.add(this.pause2_ende);
        this.add(this.saldo);
        this.add(this.bisessen);
        this.add(this.prozent_feierabend);
        this.add(this.prozent_ueberstunden);
        this.add(this.prozent_pause1);
        this.add(this.prozent_pause2);
        this.add(this.bar_feierabend);
        this.add(this.bar_ueberstunden);
        this.add(this.bar_pause1);
        this.add(this.bar_pause2);
        this.add(this.warning_red_p1);
        this.add(this.warning_red_p2);
        this.add(this.warning_red_p1bald);
        this.add(this.warning_red_p2bald);
        this.add(this.warning_red_maxarbeitbald);
        this.add(this.warning_red_zufrueh);
        this.add(this.warning_yellow_zuspaet);
        this.add(this.warning_yellow_zukunft);
        this.add(this.warning_yellow_maxarbeit);
        this.add(this.warning_yellow_keinep2);
        this.add(this.check_p1);
        this.add(this.check_feierabend);
        this.add(this.check_p2);
        this.add(this.check_maxarbeit);
        this.add(this.warning_red_avaible);
        this.add(this.warning_yellow_avaible);
        this.add(this.saldo_plus);
        this.add(this.saldo_minus);
        this.add(this.warning_red_p1_t);
        this.add(this.warning_red_p2_t);
        this.add(this.warning_red_p1bald_t);
        this.add(this.warning_red_p2bald_t);
        this.add(this.warning_red_maxarbeitbald_t);
        this.add(this.warning_red_zufrueh_t);
        this.add(this.warning_yellow_zuspaet_t);
        this.add(this.warning_yellow_zukunft_t);
        this.add(this.warning_yellow_maxarbeit_t);
        this.add(this.warning_yellow_keinep2_t);
        this.add(this.check_p1_t);
        this.add(this.check_feierabend_t);
        this.add(this.check_p2_t);
        this.add(this.check_maxarbeit_t);
        this.add(this.check_bar1);
        this.add(this.check_bar2);
        this.add(this.check_bar3);
        this.add(this.check_bar4);
    }
}

