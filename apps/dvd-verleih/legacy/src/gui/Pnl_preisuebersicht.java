package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Pnl_preisuebersicht extends JPanel {
	/**
	 * Dieses Panel gibt Preisinformationen wieder.
	 */
	private static final long serialVersionUID = 7292885057932093483L;
	private JButton btnSchliessen;

	public Pnl_preisuebersicht() {
		setBackground(Color.DARK_GRAY);
		init();
	}

	private void init() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(224, 5, 1, 1);
		panel.setLayout(null);

		// Button
		btnSchliessen = new JButton("Schlie\u00DFen");
		btnSchliessen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSchliessen.setBounds(404, 275, 126, 25);

		// TextPane
		JTextPane txtpnBeamerBluerayplayer = new JTextPane();
		txtpnBeamerBluerayplayer
				.setText("Beamer:\t\t6,00\u20AC\r\nBlueRay-Player:\t3,00\u20AC\r\nDVD-Player:\t1,50\u20AC");
		txtpnBeamerBluerayplayer.setForeground(new Color(245, 245, 245));
		txtpnBeamerBluerayplayer.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnBeamerBluerayplayer.setBackground(Color.DARK_GRAY);
		txtpnBeamerBluerayplayer.setBounds(36, 159, 203, 74);

		JTextPane txtpnAktuellbis = new JTextPane();
		txtpnAktuellbis.setBackground(Color.DARK_GRAY);
		txtpnAktuellbis.setForeground(new Color(245, 245, 245));
		txtpnAktuellbis.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtpnAktuellbis
				.setText("Aktuell (bis 2 Jahre):\t1,50\u20AC\r\nMittel (2 - 5 Jahre):\t1,00\u20AC\r\nAlt (ab 5 Jahren):\t0,50\u20AC");
		txtpnAktuellbis.setBounds(36, 50, 203, 74);

		// Label
		JLabel lblKategorieFilmspiel = new JLabel("Kategorie Film/Spiel");
		lblKategorieFilmspiel.setForeground(new Color(245, 245, 245));
		lblKategorieFilmspiel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblKategorieFilmspiel.setBounds(36, 24, 187, 25);

		JLabel lblKategorieZubehr = new JLabel("Kategorie Zubeh\u00F6r");
		lblKategorieZubehr.setForeground(new Color(245, 245, 245));
		lblKategorieZubehr.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblKategorieZubehr.setBounds(36, 132, 187, 25);

		// Hinzufuegen
		add(panel);

		// Button
		add(btnSchliessen);

		// TextPane
		add(txtpnBeamerBluerayplayer);
		add(txtpnAktuellbis);

		// Label
		add(lblKategorieFilmspiel);
		add(lblKategorieZubehr);

	}

	public void setListener_BtnSchliessen(MouseAdapter ma) {
		btnSchliessen.addMouseListener(ma);
	}

}
