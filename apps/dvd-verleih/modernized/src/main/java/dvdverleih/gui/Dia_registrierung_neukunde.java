package dvdverleih.gui;

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

public class Dia_registrierung_neukunde extends JDialog {

	private static final long serialVersionUID = 6499965275178292084L;
	private final JPanel Registrierung_Neukunde = new JPanel();
	private JTextField txtVname;
	private JTextField txtNname;
	private JTextField txtStr;
	private JTextField txtOrt;
	private JTextField txtGeb;
	private JTextField txtTele;
	private JTextField txtPnr;
	private JTextField txtHnr;
	private JTextField txtPlz;
	private JButton btnKK_anlegen;
	private JLabel lblNichtAlleFelder;

	/**
	 * Diese Maske dient der Registrierung neuer Kunden. Dabei werden Textfelder
	 * angeboten, in denen die Kundendaten eingegeben werden koennen.
	 */
	public Dia_registrierung_neukunde() {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		Registrierung_Neukunde.setBackground(Color.DARK_GRAY);
		Registrierung_Neukunde.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(Registrierung_Neukunde, BorderLayout.CENTER);
		Registrierung_Neukunde.setLayout(null);
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

		JLabel lblStr = new JLabel("Stra\u00DFe");
		lblStr.setForeground(new Color(245, 245, 245));
		lblStr.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblStr.setBounds(26, 124, 132, 16);

		JLabel lblOrt = new JLabel("Ort");
		lblOrt.setForeground(new Color(245, 245, 245));
		lblOrt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblOrt.setBounds(26, 153, 132, 16);

		JLabel lblGeb = new JLabel("Geburtsdatum");
		lblGeb.setForeground(new Color(245, 245, 245));
		lblGeb.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblGeb.setBounds(26, 182, 132, 16);

		JLabel lblTele = new JLabel("Telefon");
		lblTele.setForeground(new Color(245, 245, 245));
		lblTele.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblTele.setBounds(26, 222, 132, 16);

		JLabel lblPnr = new JLabel("Personalausweis-Nr.");
		lblPnr.setForeground(new Color(245, 245, 245));
		lblPnr.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPnr.setBounds(26, 263, 154, 16);

		JLabel lblHnr = new JLabel("Nr.");
		lblHnr.setForeground(new Color(245, 245, 245));
		lblHnr.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblHnr.setBounds(304, 114, 56, 16);

		JLabel lblPlz = new JLabel("PLZ");
		lblPlz.setForeground(new Color(245, 245, 245));
		lblPlz.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPlz.setBounds(304, 153, 56, 16);

		lblNichtAlleFelder = new JLabel("Bitte alle Felder ausf\u00FCllen!");
		lblNichtAlleFelder.setVisible(false);
		lblNichtAlleFelder.setForeground(Color.RED);
		lblNichtAlleFelder.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		// Textfelder
		txtVname = new JTextField();
		txtVname.setBounds(170, 32, 200, 22);
		txtVname.setColumns(10);

		txtNname = new JTextField();
		txtNname.setColumns(10);
		txtNname.setBounds(170, 67, 200, 22);

		txtStr = new JTextField();
		txtStr.setBounds(170, 112, 116, 22);
		txtStr.setColumns(10);

		txtOrt = new JTextField();
		txtOrt.setBounds(170, 147, 116, 22);
		txtOrt.setColumns(10);

		txtGeb = new JTextField();
		txtGeb.setBounds(170, 180, 116, 22);
		txtGeb.setColumns(10);

		txtTele = new JTextField();
		txtTele.setBounds(170, 220, 116, 22);
		txtTele.setColumns(10);

		txtPnr = new JTextField();
		txtPnr.setBounds(26, 290, 260, 22);
		txtPnr.setColumns(10);

		txtHnr = new JTextField();
		txtHnr.setBounds(343, 112, 62, 22);
		txtHnr.setColumns(10);

		txtPlz = new JTextField();
		txtPlz.setBounds(343, 151, 62, 22);
		txtPlz.setColumns(10);

		// Button
		btnKK_anlegen = new JButton("Kundenkonto anlegen");
		btnKK_anlegen.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnKK_anlegen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnKK_anlegen);

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Label
		Registrierung_Neukunde.add(lblVname);
		Registrierung_Neukunde.add(lblNname);
		Registrierung_Neukunde.add(lblStr);
		Registrierung_Neukunde.add(lblOrt);
		Registrierung_Neukunde.add(lblGeb);
		Registrierung_Neukunde.add(lblTele);
		Registrierung_Neukunde.add(lblPnr);
		Registrierung_Neukunde.add(lblHnr);
		Registrierung_Neukunde.add(lblPlz);
		buttonPane.add(lblNichtAlleFelder);

		// Textfelder
		Registrierung_Neukunde.add(txtVname);
		Registrierung_Neukunde.add(txtNname);
		Registrierung_Neukunde.add(txtStr);
		Registrierung_Neukunde.add(txtOrt);
		Registrierung_Neukunde.add(txtGeb);
		Registrierung_Neukunde.add(txtTele);
		Registrierung_Neukunde.add(txtPnr);
		Registrierung_Neukunde.add(txtHnr);
		Registrierung_Neukunde.add(txtPlz);

		// Button
		buttonPane.add(btnKK_anlegen);

	}

	public JPanel getRegistrierung_Neukunde() {
		return Registrierung_Neukunde;
	}

	public String getTxtGeb() {
		return txtGeb.getText();
	}

	public String getTxtHnr() {
		return txtHnr.getText();
	}

	public String getTxtNname() {
		return txtNname.getText();
	}

	public String getTxtOrt() {
		return txtOrt.getText();
	}

	public String getTxtPlz() {
		return txtPlz.getText();
	}

	public String getTxtPnr() {
		return txtPnr.getText();
	}

	public String getTxtStr() {
		return txtStr.getText();
	}

	public String getTxtTele() {
		return txtTele.getText();
	}

	public String getTxtVname() {
		return txtVname.getText();
	}

	public void setLblNichtAlleFelder(boolean b) {
		lblNichtAlleFelder.setVisible(b);
	}

	public void setListener_BtnKK_anlegen(MouseAdapter ma) {
		btnKK_anlegen.addMouseListener(ma);
	}

}
