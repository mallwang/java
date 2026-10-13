package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;


public class Regelwerk{

	public static JPanel panel;
	public static JTextField txtRegeln;
	public static JTextArea	tarRegeln;
	
	public Regelwerk(){
		initialize();
	}

	private void initialize() {
		
		// Panel erstellen
		panel = new JPanel();
		panel.setLayout(null);
		
		// Textfeld erstellen
		txtRegeln = new JTextField();
		txtRegeln.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegeln.setBounds(10, 10, 428, 24);
		txtRegeln.setFont(new Font("Arial", Font.BOLD, 15));
		txtRegeln.setBackground(SystemColor.control);
		txtRegeln.setText("Regeln des Spiels \"Volle Lotte\"");
		
		// Textarea erstellen
		tarRegeln = new JTextArea();
		tarRegeln.setBackground(SystemColor.control);
		tarRegeln.setFont(new Font("Arial", Font.PLAIN, 12));
		tarRegeln.setText("Spielziel: Wer zuerst die vorher festgelegte Punktzahl erreicht, hat gewonnen.\r\n\r\nSpielverlauf: ...");
		tarRegeln.setBounds(10, 47, 428, 240);
		
		// Elemente hinzufügen
		panel.add(txtRegeln);
		panel.add(tarRegeln);
		
		// Panel sichtbar machen
		panel.setVisible(true);
	}
	
	public JPanel getPanel(){
		return this.panel;
	}
	
	
}
