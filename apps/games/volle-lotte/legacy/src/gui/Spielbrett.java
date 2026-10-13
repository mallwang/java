package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import funktionen.Status;
import funktionen.StatusModel;

public class Spielbrett {

	public static JFrame frame;
	public static JPanel panel;
	public static JButton btnStart, btnWuerfeln, btnSchreiben, btnSichern1, btnSichern2, btnSichern3,
			btnSichern4, btnSichern5, btnSichern6;
	public static JLabel lblStapelVerdeckt, lblStapelOffen, lblWuerfel1,
			lblWuerfel2, lblWuerfel3, lblWuerfel4, lblWuerfel5, lblWuerfel6,
			lblPunkte;
	public static JTextArea txtInfo;
	public static JMenuBar menubar;
	public static JMenu menuDatei;
	public static JMenuItem itmSpeichern, itmSchliessen;
	public static JTable tblPlayer, tblStatus;
	public static DefaultTableModel model;
	public static StatusModel statusModel;
	public static JScrollPane scrStatus;
	public static String col[] = { "Spieler", "Punkte" };
	public static String colStatus[] = { "Status", "Uhrzeit" };
	public static String[] spieler;
	public static boolean wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5,
			wuerfel6;
	public static int anzahlSpieler, verfuegbareWuerfel, anzahlWuerfe, rundenzahl;

	public Spielbrett(String[] spieler, int anzahlSpieler) {
		this.spieler = spieler;
		this.anzahlSpieler = anzahlSpieler;
		this.verfuegbareWuerfel = 6;
		this.anzahlWuerfe = 0;
		this.rundenzahl = -1;
		this.wuerfel1 = true;
		this.wuerfel2 = true;
		this.wuerfel3 = true;
		this.wuerfel4 = true;
		this.wuerfel5 = true;
		this.wuerfel6 = true;
		initialize();
	}

	private static void initialize() {
		// Neues Frame erstellen
		frame = new JFrame();
		frame.setTitle("Volle Lotte Version 0.1");
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		// Fenstergröße abfragen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int screenWidth = (int) screen.getWidth();
		int screenHeight = (int) screen.getHeight();
		int gameWidth = 1000;
		int gameHeight = 700;

		frame.setBounds(screenWidth / 2 - gameWidth / 2, screenHeight / 2
				- gameHeight / 2 - 100, gameWidth, gameHeight);

		// Neues Panel erstellen
		panel = new JPanel();
		panel.setBounds(0, 0, 994, 667);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(null);

		// Menubar erstellen
		menubar = new JMenuBar();
		menubar.setBounds(0, 0, 1000, 30);

		Font font = new Font("Arial", Font.PLAIN, 15);
		Font font1 = new Font("Arial", Font.BOLD, 18);
		Font font2 = new Font("Arial", Font.PLAIN, 11);

		// Menüs erstellen
		menuDatei = new JMenu("Datei");
		menuDatei.setFont(font);

		// Menuitems erstellen
		itmSchliessen = new JMenuItem("Spiel beenden");
		itmSchliessen.setFont(font);
		itmSpeichern = new JMenuItem("Speichern");
		itmSpeichern.setFont(font);

		// Buttons erstellen
		btnStart = new JButton("Spiel starten");
		btnStart.setBounds(671, 350, 276, 50);
		btnWuerfeln = new JButton("W\u00FCrfeln");
		btnWuerfeln.setBounds(671, 350, 120, 50);
		btnSchreiben = new JButton("Schreiben");
		btnSchreiben.setBounds(827, 350, 120, 50);
		btnSichern1 = new JButton("sichern");
		btnSichern1.setBounds(100, 590, 80, 25);
		btnSichern1.setFont(font2);
		btnSichern1.setEnabled(false);
		btnSichern2 = new JButton("sichern");
		btnSichern2.setBounds(220, 590, 80, 25);
		btnSichern2.setFont(font2);
		btnSichern2.setEnabled(false);
		btnSichern3 = new JButton("sichern");
		btnSichern3.setBounds(340, 590, 80, 25);
		btnSichern3.setFont(font2);
		btnSichern3.setEnabled(false);
		btnSichern4 = new JButton("sichern");
		btnSichern4.setBounds(460, 590, 80, 25);
		btnSichern4.setFont(font2);
		btnSichern4.setEnabled(false);
		btnSichern5 = new JButton("sichern");
		btnSichern5.setBounds(580, 590, 80, 25);
		btnSichern5.setFont(font2);
		btnSichern5.setEnabled(false);
		btnSichern6 = new JButton("sichern");
		btnSichern6.setBounds(700, 590, 80, 25);
		btnSichern6.setFont(font2);
		btnSichern6.setEnabled(false);

		// Textfeld erstellen
		txtInfo = new JTextArea();
		txtInfo.setFont(font1);
		txtInfo.setBounds(175, 43, 590, 50);
		
		// Tabelle erstellen
		model = new DefaultTableModel(col, anzahlSpieler);
		statusModel = new StatusModel();
		
		tblPlayer = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblPlayer.setFont(font1);
		tblPlayer.setBounds(700, 100, 200, anzahlSpieler * 30);

		// Spielernamen setzen
		for (int i = 0; i < anzahlSpieler; i++) {
			tblPlayer.setValueAt(spieler[i], i, 0);
			tblPlayer.setValueAt("0", i, 1);
		}
		tblPlayer.setRowHeight(30);

		// ScrollPane erstellen
		/* 
		scrStatus = new JScrollPane(tblStatus);
		tblStatus = new JTable(statusModel);
		tblStatus.setFont(font);
		tblStatus.setBackground(Color.ORANGE);
		tblStatus.setBounds(175, 43, 590, 57);
		tblStatus.setRowHeight(25);
		statusModel.addStatus(new Status("Ein neues Spiel wurde gestartet", "Uhrzeit"));
		statusModel.addStatus(new Status("Ein neues Spiel wurde gestartet", "Uhrzeit"));
		statusModel.addStatus(new Status("Ein neues Spiel wurde gestartet", "Uhrzeit"));
		statusModel.addStatus(new Status("Ein neues Spiel wurde gestartet", "Uhrzeit"));
		*/
		
		// Labels erstellen
		lblStapelVerdeckt = new JLabel("VERDECKTE KARTEN");
		lblStapelVerdeckt.setHorizontalAlignment(SwingConstants.CENTER);
		lblStapelVerdeckt.setBounds(175, 100, 200, 300);
		lblStapelVerdeckt.setBackground(Color.YELLOW);
		lblStapelVerdeckt.setOpaque(true);
		lblStapelOffen = new JLabel("OFFENE KARTEN");
		lblStapelOffen.setHorizontalAlignment(SwingConstants.CENTER);
		lblStapelOffen.setBounds(425, 100, 200, 300);
		lblStapelOffen.setBackground(Color.ORANGE);
		lblStapelOffen.setOpaque(true);
		lblWuerfel1 = new JLabel("Würfel 1");
		lblWuerfel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel1.setBounds(100, 500, 80, 80);
		lblWuerfel1.setBackground(Color.CYAN);
		lblWuerfel1.setOpaque(true);
		lblWuerfel2 = new JLabel("Würfel 2");
		lblWuerfel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel2.setBounds(220, 500, 80, 80);
		lblWuerfel2.setBackground(Color.CYAN);
		lblWuerfel2.setOpaque(true);
		lblWuerfel3 = new JLabel("Würfel 3");
		lblWuerfel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel3.setBounds(340, 500, 80, 80);
		lblWuerfel3.setBackground(Color.CYAN);
		lblWuerfel3.setOpaque(true);
		lblWuerfel4 = new JLabel("Würfel 4");
		lblWuerfel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel4.setBounds(460, 500, 80, 80);
		lblWuerfel4.setBackground(Color.CYAN);
		lblWuerfel4.setOpaque(true);
		lblWuerfel5 = new JLabel("Würfel 5");
		lblWuerfel5.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel5.setBounds(580, 500, 80, 80);
		lblWuerfel5.setBackground(Color.CYAN);
		lblWuerfel5.setOpaque(true);
		lblWuerfel6 = new JLabel("Würfel 6");
		lblWuerfel6.setHorizontalAlignment(SwingConstants.CENTER);
		lblWuerfel6.setBounds(700, 500, 80, 80);
		lblWuerfel6.setBackground(Color.CYAN);
		lblWuerfel6.setOpaque(true);
		lblPunkte = new JLabel("Punkte: ");
		lblPunkte.setHorizontalAlignment(SwingConstants.LEFT);
		lblPunkte.setBounds(680, 462, 162, 25);
		lblPunkte.setFont(font1);

		
		menuDatei.add(itmSpeichern);
		menuDatei.add(itmSchliessen);
		menubar.add(menuDatei);
		panel.add(menubar);
		panel.add(btnStart);
		panel.add(btnWuerfeln);
		panel.add(btnSchreiben);
		panel.add(btnSichern1);
		panel.add(btnSichern2);
		panel.add(btnSichern3);
		panel.add(btnSichern4);
		panel.add(btnSichern5);
		panel.add(btnSichern6);
		panel.add(lblStapelVerdeckt);
		panel.add(lblStapelOffen);
		panel.add(lblWuerfel1);
		panel.add(lblWuerfel2);
		panel.add(lblWuerfel3);
		panel.add(lblWuerfel4);
		panel.add(lblWuerfel5);
		panel.add(lblWuerfel6);
		panel.add(lblPunkte);
		panel.add(tblPlayer);
		panel.add(txtInfo);
		//panel.add(tblStatus);
		//panel.add(scrStatus);
		frame.getContentPane().add(panel);
		frame.setVisible(true);

	}

	/* LISTENER */
	public void setListenerSchliessen(MouseAdapter ma) {
		itmSchliessen.addMouseListener(ma);
	}

	public void setListenerSpeichern(MouseAdapter ma) {
		itmSpeichern.addMouseListener(ma);
	}

	public void setListenerWuerfeln(MouseAdapter ma) {
		btnWuerfeln.addMouseListener(ma);
	}

	public void setListenerSchreiben(MouseAdapter ma) {
		btnSchreiben.addMouseListener(ma);
	}
	
	public void setListenerSichern1(MouseAdapter ma) {
		btnSichern1.addMouseListener(ma);
	}

	public void setListenerSichern2(MouseAdapter ma) {
		btnSichern2.addMouseListener(ma);
	}

	public void setListenerSichern3(MouseAdapter ma) {
		btnSichern3.addMouseListener(ma);
	}

	public void setListenerSichern4(MouseAdapter ma) {
		btnSichern4.addMouseListener(ma);
	}

	public void setListenerSichern5(MouseAdapter ma) {
		btnSichern5.addMouseListener(ma);
	}

	public void setListenerSichern6(MouseAdapter ma) {
		btnSichern6.addMouseListener(ma);
	}
	
	public void setListenerStart(MouseAdapter ma){
		btnStart.addMouseListener(ma);
	}

	/* SETTER */
	public void setVerfuegbareWuerfel(int verfuegbareWuerfel) {
		this.verfuegbareWuerfel = verfuegbareWuerfel;
	}

	public static void setBtnSichern1(String text) {
		btnSichern1.setText(text);
	}

	public static void setBtnSichern2(String text) {
		btnSichern2.setText(text);
	}

	public static void setBtnSichern3(String text) {
		btnSichern3.setText(text);
	}

	public static void setBtnSichern4(String text) {
		btnSichern4.setText(text);
	}

	public static void setBtnSichern5(String text) {
		btnSichern5.setText(text);
	}

	public static void setBtnSichern6(String text) {
		btnSichern6.setText(text);
	}

	public void setLblWuerfel1Transparancy() {
	}

	public static void setWuerfel1Frei(boolean wuerfel1) {
		Spielbrett.wuerfel1 = wuerfel1;
	}

	public static void setWuerfel2Frei(boolean wuerfel2) {
		Spielbrett.wuerfel2 = wuerfel2;
	}

	public static void setWuerfel3Frei(boolean wuerfel3) {
		Spielbrett.wuerfel3 = wuerfel3;
	}

	public static void setWuerfel4Frei(boolean wuerfel4) {
		Spielbrett.wuerfel4 = wuerfel4;
	}

	public static void setWuerfel5Frei(boolean wuerfel5) {
		Spielbrett.wuerfel5 = wuerfel5;
	}

	public static void setWuerfel6Frei(boolean wuerfel6) {
		Spielbrett.wuerfel6 = wuerfel6;
	}

	public void setLblWuerfel1(String text) {
		lblWuerfel1.setText(text);
	}

	public void setLblWuerfel2(String text) {
		lblWuerfel2.setText(text);
	}

	public void setLblWuerfel3(String text) {
		lblWuerfel3.setText(text);
	}

	public void setLblWuerfel4(String text) {
		lblWuerfel4.setText(text);
	}

	public void setLblWuerfel5(String text) {
		lblWuerfel5.setText(text);
	}

	public void setLblWuerfel6(String text) {
		lblWuerfel6.setText(text);
	}

	public void setBtnWuerfelnDisabled(boolean b) {
		btnWuerfeln.setEnabled(b);
	}
	
	public void setBtnSchreibenDisabled(boolean b) {
		btnSchreiben.setEnabled(b);
	}
	
	public void showNaechsteKarte(ImageIcon icon){
		lblStapelOffen.setIcon(icon);
	}

	public void enableBtnSichern1() {
		btnSichern1.setEnabled(true);
	}

	public void enableBtnSichern2() {
		btnSichern2.setEnabled(true);
	}

	public void enableBtnSichern3() {
		btnSichern3.setEnabled(true);
	}

	public void enableBtnSichern4() {
		btnSichern4.setEnabled(true);
	}

	public void enableBtnSichern5() {
		btnSichern5.setEnabled(true);
	}

	public void enableBtnSichern6() {
		btnSichern6.setEnabled(true);
	}

	public void disableBtnSichern1() {
		btnSichern1.setEnabled(false);
	}

	public void disableBtnSichern2() {
		btnSichern2.setEnabled(false);
	}

	public void disableBtnSichern3() {
		btnSichern3.setEnabled(false);
	}

	public void disableBtnSichern4() {
		btnSichern4.setEnabled(false);
	}

	public void disableBtnSichern5() {
		btnSichern5.setEnabled(false);
	}

	public void disableBtnSichern6() {
		btnSichern6.setEnabled(false);
	}

	public void setLblPunkte(String text) {
		lblPunkte.setText(text);
	}
	
	public void setTxtStatus(String text){
		txtInfo.setText(text);
	}
	
	public void setNeueRunde(){
		rundenzahl++;
	}

	/* GETTER */
	public String nextSpieler(){
		return spieler[rundenzahl%anzahlSpieler];
	}
	
	public int getVerfuegbareWuerfel() {
		return this.verfuegbareWuerfel;
	}

	public static boolean isWuerfel1Frei() {
		return wuerfel1;
	}

	public static boolean isWuerfel2Frei() {
		return wuerfel2;
	}

	public static boolean isWuerfel3Frei() {
		return wuerfel3;
	}

	public static boolean isWuerfel4Frei() {
		return wuerfel4;
	}

	public static boolean isWuerfel5Frei() {
		return wuerfel5;
	}

	public static boolean isWuerfel6Frei() {
		return wuerfel6;
	}
	
	public static String getLblPunkte(){
		return lblPunkte.getText().toString();
	}
}