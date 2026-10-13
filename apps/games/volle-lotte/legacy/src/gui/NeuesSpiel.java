package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NeuesSpiel {

	public static JFrame frame;
	public static JPanel panel;
	public static JButton btnAbbrechen, btnNeuesSpiel, btnMinus, btnPlus;
	public static JLabel lblSpieler, lblAnzahlSpieler, lblSchwierigkeitsgrad, errSpieler, errNamen;
	public static JTextField txtSpieler1, txtSpieler2, txtSpieler3, txtSpieler4, txtSpieler5;
	public int Spieler;
	
	public NeuesSpiel() {
		initialize();
		setSpieler(2);
	}

	private void initialize() {

		// Neues Frame erstellen
		frame = new JFrame();
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Neues Spiel starten");
		frame.setLayout(null);
		frame.setResizable(false);

		// Fenstergröße abfragen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int screenWidth = (int) screen.getWidth();
		int screenHeight = (int) screen.getHeight();
		int gameWidth = 650;
		int gameHeight = 400;

		frame.setBounds(screenWidth / 2 - gameWidth / 2, screenHeight / 2
				- gameHeight / 2 - 100, gameWidth, gameHeight);

		// Neues Panel erstellen
		panel = new JPanel();
		panel.setBounds(0, 0, gameWidth, gameHeight);
		panel.setBackground(Color.ORANGE);
		panel.setLayout(null);
		
		Font font = new Font("Arial", Font.PLAIN, 15);
		Font font1 = new Font("Arial", Font.BOLD, 18);
		
		// Buttons erstellen
		btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.setBounds(175, 275, 120, 40);
		btnAbbrechen.setFont(font);
		btnNeuesSpiel = new JButton("Spiel starten");
		btnNeuesSpiel.setBounds(375, 275, 120, 40);
		btnNeuesSpiel.setFont(font);
		btnMinus = new JButton("-");
		btnMinus.setBounds(180, 50, 40, 25);
		btnMinus.setFont(font1);
		btnPlus = new JButton("+");
		btnPlus.setBounds(260, 50, 45, 25);
		btnPlus.setFont(font1);
		
		// Labels erstellen
		lblAnzahlSpieler = new JLabel("Anzahl Spieler:");
		lblAnzahlSpieler.setBounds(50, 50, 120, 25);
		lblAnzahlSpieler.setFont(font);
		lblSchwierigkeitsgrad = new JLabel("Schwierigkeitsgrad:    Einfach");
		lblSchwierigkeitsgrad.setBounds(50, 100, 240, 25);
		lblSchwierigkeitsgrad.setFont(font);
		lblSpieler = new JLabel("2");
		lblSpieler.setBounds(235, 50, 25, 25);
		lblSpieler.setFont(font);
		errSpieler = new JLabel();
		errSpieler.setBounds(50, 75, 400, 25);
		errSpieler.setForeground(Color.RED);
		errNamen = new JLabel();
		errNamen.setBounds(350, 240, 150, 25);
		errNamen.setForeground(Color.RED);
		
		// Textfelder erstellen
		txtSpieler1 = new JTextField();
		txtSpieler1.setBounds(350, 50, 250, 25);
		txtSpieler1.setFont(font);
		txtSpieler2 = new JTextField();
		txtSpieler2.setBounds(350, 90, 250, 25);
		txtSpieler2.setFont(font);
		txtSpieler3 = new JTextField();
		txtSpieler3.setBounds(350, 130, 250, 25);
		txtSpieler3.setFont(font);
		txtSpieler3.setEnabled(false);
		txtSpieler3.setBackground(Color.LIGHT_GRAY);
		txtSpieler4 = new JTextField();
		txtSpieler4.setBounds(350, 170, 250, 25);
		txtSpieler4.setFont(font);
		txtSpieler4.setEnabled(false);
		txtSpieler4.setBackground(Color.LIGHT_GRAY);
		txtSpieler5 = new JTextField();
		txtSpieler5.setBounds(350, 210, 250, 25);
		txtSpieler5.setFont(font);
		txtSpieler5.setEnabled(false);
		txtSpieler5.setBackground(Color.LIGHT_GRAY);
		
		// Hinzufügen
		panel.add(lblAnzahlSpieler);
		panel.add(lblSchwierigkeitsgrad);
		panel.add(lblSpieler);
		panel.add(btnAbbrechen);
		panel.add(btnNeuesSpiel);
		panel.add(btnMinus);
		panel.add(btnPlus);
		panel.add(errSpieler);
		panel.add(errNamen);
		panel.add(txtSpieler1);
		panel.add(txtSpieler2);
		panel.add(txtSpieler3);
		panel.add(txtSpieler4);
		panel.add(txtSpieler5);
		frame.add(panel);
		frame.setVisible(true);
	}

	/* SETTER */
	public void setListenerMehrSpieler(MouseAdapter ma) {
		btnPlus.addMouseListener(ma);
	}
	public void setListenerWenigerSpieler(MouseAdapter ma) {
		btnMinus.addMouseListener(ma);
	}
	public void setListenerAbbrechen(MouseAdapter ma){
		btnAbbrechen.addMouseListener(ma);
	}
	public void setListenerNeuesSpiel(MouseAdapter ma){
		btnNeuesSpiel.addMouseListener(ma);
	}
	public void setSpieler(int spieler) {
		Spieler = spieler;
		lblSpieler.setText("" + Spieler);
	}
	public void setErrortext(String text){
		errSpieler.setText(text);
	}
	public void setErrortextNamen(String text){
		errNamen.setText(text);
	}
	public void enableTxtSpieler3(boolean b){
		txtSpieler3.setEnabled(b);
	}
	public void enableTxtSpieler4(boolean b){
		txtSpieler4.setEnabled(b);
	}
	public void enableTxtSpieler5(boolean b){
		txtSpieler5.setEnabled(b);
	}
	public void setTxtSpieler3BGWhite(){
		txtSpieler3.setBackground(Color.WHITE);
	}
	public void setTxtSpieler4BGWhite(){
		txtSpieler4.setBackground(Color.WHITE);
	}
	public void setTxtSpieler5BGWhite(){
		txtSpieler5.setBackground(Color.WHITE);
	}
	public void setTxtSpieler3BGGray(){
		txtSpieler3.setBackground(Color.LIGHT_GRAY);
	}
	public void setTxtSpieler4BGGray(){
		txtSpieler4.setBackground(Color.LIGHT_GRAY);
	}
	public void setTxtSpieler5BGGray(){
		txtSpieler5.setBackground(Color.LIGHT_GRAY);
	}
	
	/* GETTER */
	public int getSpieler() {
		return Spieler;
	}
	public String getTxtSpieler1(){
		return txtSpieler1.getText();
	}
	public String getTxtSpieler2(){
		return txtSpieler2.getText();
	}
	public String getTxtSpieler3(){
		return txtSpieler3.getText();
	}
	public String getTxtSpieler4(){
		return txtSpieler4.getText();
	}
	public String getTxtSpieler5(){
		return txtSpieler5.getText();
	}
}
