package dvdverleih.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Pnl_Produkte extends JPanel {
	/**
	 * Dieses Panel gibt Informationen zu in der Suche aufgerufenen Objekten,
	 * mit bspw. Filme
	 */
	private static final long serialVersionUID = -4220341266093700034L;
	private JButton btnSchliessen;
	private JButton btnSuche;
	private JButton btnNeu;

	public Pnl_Produkte() {
		setBackground(Color.DARK_GRAY);
		init();
	}

	private void init() {
		setLayout(null);

		// Button
		btnSchliessen = new JButton("Schlie\u00DFen");
		btnSchliessen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSchliessen.setBounds(404, 275, 126, 25);

		btnSuche = new JButton("Suche");
		btnSuche.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSuche.setBounds(124, 105, 126, 97);

		btnNeu = new JButton("Neu");
		btnNeu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNeu.setBounds(326, 105, 126, 97);

		// Button
		add(btnSchliessen);
		add(btnNeu);
		add(btnSuche);
	}

	public void setListener_BtnNeu(MouseAdapter ma) {
		btnNeu.addMouseListener(ma);
	}

	public void setListener_BtnSchliessen(MouseAdapter ma) {
		btnSchliessen.addMouseListener(ma);
	}

	public void setListener_BtnSuche(MouseAdapter ma) {
		btnSuche.addMouseListener(ma);
	}
}
