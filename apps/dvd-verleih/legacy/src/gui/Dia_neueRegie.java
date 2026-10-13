package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Dia_neueRegie extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651482874090978415L;

	private JTextField txtVname;

	private JTextField txtNname;

	private JButton btnRegie_anlegen;
	private JLabel lblFelderKorrekt;

	/**
	 * Wenn ein neues Objekt (Film) angelegt werden soll, so koennen neue
	 * "Regisseure" der Datenbank hinzugefuegt werden.
	 */
	public Dia_neueRegie() {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 198);
		getContentPane().setLayout(new BorderLayout());
		final JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Label
		JLabel lblVname = new JLabel("Vorname");
		lblVname.setForeground(new Color(245, 245, 245));
		lblVname.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVname.setBounds(26, 34, 132, 16);

		JLabel lblNname = new JLabel("Nachname");
		lblNname.setForeground(new Color(245, 245, 245));
		lblNname.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNname.setBounds(26, 63, 132, 16);

		lblFelderKorrekt = new JLabel(
				"Bitte alle Felder korrekt ausf\u00FCllen!");
		lblFelderKorrekt.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblFelderKorrekt.setVisible(false);
		lblFelderKorrekt.setForeground(Color.RED);

		// Textfelder
		txtVname = new JTextField();
		txtVname.setBounds(170, 32, 200, 22);
		txtVname.setColumns(10);

		txtNname = new JTextField();
		txtNname.setColumns(10);
		txtNname.setBounds(170, 67, 200, 22);

		// Button
		btnRegie_anlegen = new JButton("Regie anlegen");
		btnRegie_anlegen.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnRegie_anlegen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnRegie_anlegen);

		// Hinzufuegen
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Label
		contentPanel.add(lblVname);
		contentPanel.add(lblNname);
		buttonPane.add(lblFelderKorrekt);

		// Textfelder
		contentPanel.add(txtVname);
		contentPanel.add(txtNname);

		// Button
		buttonPane.add(btnRegie_anlegen);

	}

	public String getTxtNname() {
		return txtNname.getText();
	}

	public String getTxtVname() {
		return txtVname.getText();
	}

	public void setLblFelderKorrekt(boolean b) {
		lblFelderKorrekt.setVisible(b);
	}

	public void setListener_BtnRegie_anlegen(MouseAdapter ma) {
		btnRegie_anlegen.addMouseListener(ma);
	}
}
