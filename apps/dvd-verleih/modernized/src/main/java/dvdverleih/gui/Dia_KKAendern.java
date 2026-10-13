package dvdverleih.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Dia_KKAendern extends JDialog {

	/**
	 * Dieser Diolog erscheint, wenn ein Kundenkonto geaendert werden soll. Er
	 * bietet fuer die persoenlichen Daten Textfelder, die, je nachdem ob man
	 * sie aendern moechte, mittels Checkboxen aktiviert werden koennen.
	 */
	private static final long serialVersionUID = 3975553579765711446L;
	private JTextField txtVname;
	private JTextField txtNname;
	private JTextField txtStr;
	private JTextField txtOrt;
	private JTextField txtGeb;
	private JTextField txtTele;
	private JTextField txtPnr;
	private JTextField txtHnr;
	private JTextField txtPlz;
	private JLabel Knr;
	private JLabel lblNichtKorrekt;
	private JButton btnKKAendern;
	private final JCheckBox cbVname;
	private final JCheckBox cbNname;
	private final JCheckBox cbStr;
	private final JCheckBox cbOrt;
	private final JCheckBox cbGeb;
	private final JCheckBox cbTele;
	private final JCheckBox cbPnr;
	private final JCheckBox cbHnr;
	private final JCheckBox cbPlz;

	/**
	 * Create the dialog.
	 */
	public Dia_KKAendern() {
		setBackground(Color.DARK_GRAY);
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 468, 366);
		getContentPane().setLayout(null);
		new dvdverleih.funktionen.KKonto();

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setBounds(0, 281, 452, 40);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Textfelder
		txtVname = new JTextField();// info.get(0));
		txtVname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtVname.setForeground(new Color(245, 245, 245));
		txtVname.setBackground(Color.DARK_GRAY);
		txtVname.setBounds(131, 60, 169, 22);
		txtVname.setEnabled(false);
		txtVname.setColumns(10);

		txtNname = new JTextField();// info.get(1));
		txtNname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtNname.setForeground(new Color(245, 245, 245));
		txtNname.setBackground(Color.DARK_GRAY);
		txtNname.setBounds(131, 90, 169, 22);
		txtNname.setEnabled(false);
		txtNname.setColumns(10);

		txtGeb = new JTextField();// info.get(2));
		txtGeb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtGeb.setForeground(new Color(245, 245, 245));
		txtGeb.setBackground(Color.DARK_GRAY);
		txtGeb.setBounds(130, 181, 121, 22);
		txtGeb.setEnabled(false);
		txtGeb.setColumns(10);

		txtTele = new JTextField();// info.get(4));
		txtTele.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTele.setForeground(new Color(245, 245, 245));
		txtTele.setBackground(Color.DARK_GRAY);
		txtTele.setBounds(130, 211, 121, 22);
		txtTele.setEnabled(false);
		txtTele.setColumns(10);

		txtPnr = new JTextField();// info.get(5));
		txtPnr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPnr.setForeground(new Color(245, 245, 245));
		txtPnr.setBackground(Color.DARK_GRAY);
		txtPnr.setBounds(174, 240, 208, 22);
		txtPnr.setEnabled(false);
		txtPnr.setColumns(10);

		txtStr = new JTextField();// info.get(7));
		txtStr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtStr.setForeground(new Color(245, 245, 245));
		txtStr.setBackground(Color.DARK_GRAY);
		txtStr.setBounds(131, 120, 120, 22);
		txtStr.setEnabled(false);
		txtStr.setColumns(10);

		txtHnr = new JTextField();// info.get(8));
		txtHnr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtHnr.setForeground(new Color(245, 245, 245));
		txtHnr.setBackground(Color.DARK_GRAY);
		txtHnr.setBounds(329, 120, 62, 22);
		txtHnr.setEnabled(false);
		txtHnr.setColumns(10);

		txtOrt = new JTextField();// info.get(9));
		txtOrt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtOrt.setForeground(new Color(245, 245, 245));
		txtOrt.setBackground(Color.DARK_GRAY);
		txtOrt.setBounds(130, 151, 121, 22);
		txtOrt.setEnabled(false);
		txtOrt.setColumns(10);

		txtPlz = new JTextField();// info.get(10));
		txtPlz.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPlz.setForeground(new Color(245, 245, 245));
		txtPlz.setBackground(Color.DARK_GRAY);
		txtPlz.setBounds(329, 150, 62, 22);
		txtPlz.setEnabled(false);
		txtPlz.setColumns(10);

		// Label
		JLabel lblKnr = new JLabel("Kundennummer");
		lblKnr.setForeground(new Color(245, 245, 245));
		lblKnr.setBounds(10, 11, 125, 23);
		lblKnr.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		Knr = new JLabel();// id);
		Knr.setForeground(new Color(245, 245, 245));
		Knr.setBounds(147, 11, 116, 22);
		Knr.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		lblNichtKorrekt = new JLabel(
				"Bitte alle zu \u00E4ndernden Felder korrekt ausf\u00FCllen!");
		lblNichtKorrekt.setVisible(false);
		lblNichtKorrekt.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblNichtKorrekt.setForeground(Color.RED);

		// Checkboxen
		cbVname = new JCheckBox("Vorname");
		cbVname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbVname.setForeground(new Color(245, 245, 245));
		cbVname.setBackground(Color.DARK_GRAY);
		cbVname.setBounds(6, 60, 117, 23);

		cbNname = new JCheckBox("Nachname");
		cbNname.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbNname.setForeground(new Color(245, 245, 245));
		cbNname.setBackground(Color.DARK_GRAY);
		cbNname.setBounds(6, 90, 117, 23);

		cbStr = new JCheckBox("Stra\u00DFe");
		cbStr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbStr.setForeground(new Color(245, 245, 245));
		cbStr.setBackground(Color.DARK_GRAY);
		cbStr.setBounds(6, 120, 117, 23);

		cbOrt = new JCheckBox("Ort");
		cbOrt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbOrt.setForeground(new Color(245, 245, 245));
		cbOrt.setBackground(Color.DARK_GRAY);
		cbOrt.setBounds(6, 150, 116, 23);

		cbGeb = new JCheckBox("Geburtsdatum");
		cbGeb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbGeb.setForeground(new Color(245, 245, 245));
		cbGeb.setBackground(Color.DARK_GRAY);
		cbGeb.setBounds(6, 180, 116, 23);

		cbTele = new JCheckBox("Telefon");
		cbTele.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbTele.setForeground(new Color(245, 245, 245));
		cbTele.setBackground(Color.DARK_GRAY);
		cbTele.setBounds(6, 210, 116, 23);

		cbPnr = new JCheckBox("Personalausweis-Nr.");
		cbPnr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbPnr.setForeground(new Color(245, 245, 245));
		cbPnr.setBackground(Color.DARK_GRAY);
		cbPnr.setBounds(6, 240, 160, 23);

		cbHnr = new JCheckBox("Nr.");
		cbHnr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbHnr.setForeground(new Color(245, 245, 245));
		cbHnr.setBackground(Color.DARK_GRAY);
		cbHnr.setBounds(260, 120, 61, 23);

		cbPlz = new JCheckBox("PLZ");
		cbPlz.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbPlz.setForeground(new Color(245, 245, 245));
		cbPlz.setBackground(Color.DARK_GRAY);
		cbPlz.setBounds(260, 150, 61, 23);

		// Button

		btnKKAendern = new JButton("Kundenkonto \u00E4ndern");
		btnKKAendern.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnKKAendern.setActionCommand("OK");
		getRootPane().setDefaultButton(btnKKAendern);

		// Seperator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 43, 381, 2);

		// Hinzufuegen

		getContentPane().add(buttonPane);

		// Textfelder
		getContentPane().add(txtVname);
		getContentPane().add(txtNname);
		getContentPane().add(txtGeb);
		getContentPane().add(txtTele);
		getContentPane().add(txtPnr);
		getContentPane().add(txtStr);
		getContentPane().add(txtHnr);
		getContentPane().add(txtOrt);
		getContentPane().add(txtPlz);

		// Label
		getContentPane().add(lblKnr);
		getContentPane().add(Knr);
		buttonPane.add(lblNichtKorrekt);

		// Checkboxen
		getContentPane().add(cbVname);
		getContentPane().add(cbNname);
		getContentPane().add(cbStr);
		getContentPane().add(cbOrt);
		getContentPane().add(cbGeb);
		getContentPane().add(cbTele);
		getContentPane().add(cbPnr);
		getContentPane().add(cbHnr);
		getContentPane().add(cbPlz);

		// Button
		buttonPane.add(btnKKAendern);

		// Seperator
		getContentPane().add(separator_1);
	}

	public boolean getCbGeb() {
		return cbGeb.isSelected();
	}

	public boolean getCbHnr() {
		return cbHnr.isSelected();
	}

	public boolean getCbNname() {
		return cbNname.isSelected();
	}

	public boolean getCbOrt() {
		return cbOrt.isSelected();
	}

	public boolean getCbPlz() {
		return cbPlz.isSelected();
	}

	public boolean getCbPnr() {
		return cbPnr.isSelected();
	}

	public boolean getCbStr() {
		return cbStr.isSelected();
	}

	public boolean getCbTele() {
		return cbTele.isSelected();
	}

	public boolean getCbVname() {
		return cbVname.isSelected();
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

	public void setKnr(String knr) {
		Knr.setText(knr);
	}

	public void setLblNichtKorrekt(boolean b) {
		lblNichtKorrekt.setVisible(b);
	}

	public void setListener_BtnKKAendern(MouseAdapter ma) {
		btnKKAendern.addMouseListener(ma);
	}

	public void setListener_CbGeb(ItemListener il) {
		cbGeb.addItemListener(il);
	}

	public void setListener_CbHnr(ItemListener il) {
		cbHnr.addItemListener(il);
	}

	public void setListener_CbNname(ItemListener il) {
		cbNname.addItemListener(il);
	}

	public void setListener_CbOrt(ItemListener il) {
		cbOrt.addItemListener(il);
	}

	public void setListener_CbPlz(ItemListener il) {
		cbPlz.addItemListener(il);
	}

	public void setListener_CbPnr(ItemListener il) {
		cbPnr.addItemListener(il);
	}

	public void setListener_CbStr(ItemListener il) {
		cbStr.addItemListener(il);
	}

	public void setListener_CbTele(ItemListener il) {
		cbTele.addItemListener(il);
	}

	public void setListener_CbVname(ItemListener il) {
		cbVname.addItemListener(il);
	}

	public void setTxtGeb(boolean b) {
		txtGeb.setEnabled(b);
	}

	public void setTxtGeb(String txtGeb) {
		this.txtGeb.setText(txtGeb);
	}

	public void setTxtHnr(boolean b) {
		txtHnr.setEnabled(b);
	}

	public void setTxtHnr(String txtHnr) {
		this.txtHnr.setText(txtHnr);
	}

	public void setTxtNname(boolean b) {
		txtNname.setEnabled(b);
	}

	public void setTxtNname(String txtNname) {
		this.txtNname.setText(txtNname);
	}

	public void setTxtOrt(boolean b) {
		txtOrt.setEnabled(b);
	}

	public void setTxtOrt(String txtOrt) {
		this.txtOrt.setText(txtOrt);
	}

	public void setTxtPlz(boolean b) {
		txtPlz.setEnabled(b);
	}

	public void setTxtPlz(String txtPlz) {
		this.txtPlz.setText(txtPlz);
	}

	public void setTxtPnr(boolean b) {
		txtPnr.setEnabled(b);
	}

	public void setTxtPnr(String txtPnr) {
		this.txtPnr.setText(txtPnr);
	}

	public void setTxtStr(boolean b) {
		txtStr.setEnabled(b);
	}

	public void setTxtStr(String txtStr) {
		this.txtStr.setText(txtStr);
	}

	public void setTxtTele(boolean b) {
		txtTele.setEnabled(b);
	}

	public void setTxtTele(String txtTele) {
		this.txtTele.setText(txtTele);
	}

	public void setTxtVname(boolean b) {
		txtVname.setEnabled(b);
	}

	public void setTxtVname(String txtVname) {
		this.txtVname.setText(txtVname);
	}
}
