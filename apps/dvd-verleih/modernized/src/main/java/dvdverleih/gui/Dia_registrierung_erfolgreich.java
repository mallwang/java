package dvdverleih.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class Dia_registrierung_erfolgreich extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4037575367527769486L;

	/**
	 * Die Neuregistrierung wird bestätigt.
	 */

	private final JPanel contentPanel = new JPanel();
	private JLabel lblText1;
	private JLabel lblText2;
	private JLabel knr;
	private JButton btnOk;

	/**
	 * Create the dialog.
	 */
	public Dia_registrierung_erfolgreich(final String id) {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 350, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		// Label
		lblText1 = new JLabel("Es wurde ein neues Kundenkonto angelegt.");
		lblText1.setForeground(new Color(245, 245, 245));
		lblText1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblText1, 26,
				SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblText1, 21,
				SpringLayout.WEST, contentPanel);

		lblText2 = new JLabel("Die Kundennummer lautet:");
		lblText2.setForeground(new Color(245, 245, 245));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, lblText2, 26,
				SpringLayout.SOUTH, lblText1);
		sl_contentPanel.putConstraint(SpringLayout.WEST, lblText2, 0,
				SpringLayout.WEST, lblText1);
		lblText2.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		knr = new JLabel();
		knr.setForeground(new Color(245, 245, 245));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, knr, 0,
				SpringLayout.NORTH, lblText2);
		sl_contentPanel.putConstraint(SpringLayout.WEST, knr, 34,
				SpringLayout.EAST, lblText2);
		sl_contentPanel.putConstraint(SpringLayout.EAST, knr, -27,
				SpringLayout.EAST, contentPanel);
		knr.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		// Button
		btnOk = new JButton("OK");
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, btnOk, -10,
				SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, btnOk, -10,
				SpringLayout.EAST, contentPanel);
		btnOk.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnOk.setActionCommand("OK");
		getRootPane().setDefaultButton(btnOk);

		// Hinzufuegen
		contentPanel.add(lblText1);
		contentPanel.add(lblText2);
		contentPanel.add(knr);
		contentPanel.add(btnOk);

	}

	public void setKnr(String knr) {
		this.knr.setText(knr);
	}

	public void setListener_BtnOk(MouseAdapter ma) {
		btnOk.addMouseListener(ma);
	}
}
