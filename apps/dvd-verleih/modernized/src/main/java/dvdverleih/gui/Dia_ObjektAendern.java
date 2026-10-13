package dvdverleih.gui;

import dvdverleih.gui.Enums.Formate_Film_aendern;
import dvdverleih.gui.Enums.Formate_Spiel_aendern;
import dvdverleih.gui.Enums.Formate_Zubehoer_aendern;
import dvdverleih.gui.Enums.Genre_Film;
import dvdverleih.gui.Enums.Genre_Spiel;
import dvdverleih.gui.Enums.Genre_Zubehoer;
import dvdverleih.gui.Enums.Kategorie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Dia_ObjektAendern extends JDialog {

	private static final long serialVersionUID = 1543618429369432088L;
	private JTextField txtTitel;
	private JTextField txtOriginaltitel;
	private JTextField txtJahr;
	private JTextField txtFsk;
	private JTextField txtPreis;
	private JLabel titel;
	private JButton btnObjektAendern;
	private JCheckBox cbKategorie;
	private JCheckBox cbGenre;
	private JCheckBox cbFormat;
	private JCheckBox cbTitel;
	private JCheckBox cbOriginaltitel;
	private JCheckBox cbJahr;
	private JCheckBox cbFsk;
	private JCheckBox cbPreis;
	private JComboBox dropdownKategorie;
	private JComboBox dropdownGenre;
	private JComboBox dropdownFormat;

	/**
	 * Wenn Objekte in der Datenbank fehlerhaft eingetragen worden sind, so
	 * koennen die einzelnen Daten ueber diese Maske geaendert werden.
	 */
	public Dia_ObjektAendern() {
		setBackground(Color.DARK_GRAY);
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 526, 337);
		getContentPane().setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setBounds(0, 244, 508, 48);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Textfelder
		txtJahr = new JTextField();
		txtJahr.setText("2000");
		txtJahr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtJahr.setForeground(new Color(245, 245, 245));
		txtJahr.setBackground(Color.DARK_GRAY);
		txtJahr.setBounds(110, 180, 69, 22);
		txtJahr.setEnabled(false);
		txtJahr.setColumns(10);

		txtFsk = new JTextField();
		txtFsk.setText("6");
		txtFsk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtFsk.setForeground(new Color(245, 245, 245));
		txtFsk.setBackground(Color.DARK_GRAY);
		txtFsk.setBounds(273, 180, 35, 22);
		txtFsk.setEnabled(false);
		txtFsk.setColumns(10);

		txtPreis = new JTextField();
		txtPreis.setText("1,00\u20AC");
		txtPreis.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPreis.setForeground(new Color(245, 245, 245));
		txtPreis.setBackground(Color.DARK_GRAY);
		txtPreis.setBounds(110, 209, 69, 22);
		txtPreis.setEnabled(false);
		txtPreis.setColumns(10);

		txtTitel = new JTextField();
		txtTitel.setText("Meine Braut, ihr Vater und ich");
		txtTitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtTitel.setForeground(new Color(245, 245, 245));
		txtTitel.setBackground(Color.DARK_GRAY);
		txtTitel.setBounds(110, 121, 385, 22);
		txtTitel.setEnabled(false);
		txtTitel.setColumns(10);

		txtOriginaltitel = new JTextField();
		txtOriginaltitel.setText("Wedding`s hot");
		txtOriginaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtOriginaltitel.setForeground(new Color(245, 245, 245));
		txtOriginaltitel.setBackground(Color.DARK_GRAY);
		txtOriginaltitel.setBounds(110, 151, 385, 22);
		txtOriginaltitel.setEnabled(false);
		txtOriginaltitel.setColumns(10);

		// Label
		JLabel lblTitel = new JLabel("Titel");
		lblTitel.setForeground(new Color(245, 245, 245));
		lblTitel.setBounds(10, 11, 125, 23);
		lblTitel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		titel = new JLabel();
		titel.setText("Meine Braut, ihr Vater und ich");
		titel.setForeground(new Color(245, 245, 245));
		titel.setBounds(110, 11, 396, 22);
		titel.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		// Seperator
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 43, 483, 2);

		// Checkboxen
		cbKategorie = new JCheckBox("Art");
		cbKategorie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbKategorie.setForeground(new Color(245, 245, 245));
		cbKategorie.setBackground(Color.DARK_GRAY);
		cbKategorie.setBounds(6, 60, 88, 23);

		cbGenre = new JCheckBox("Genre");
		cbGenre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbGenre.setForeground(new Color(245, 245, 245));
		cbGenre.setBackground(Color.DARK_GRAY);
		cbGenre.setBounds(6, 90, 88, 23);

		cbFormat = new JCheckBox("Format");
		cbFormat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbFormat.setForeground(new Color(245, 245, 245));
		cbFormat.setBackground(Color.DARK_GRAY);
		cbFormat.setBounds(302, 90, 69, 23);

		cbTitel = new JCheckBox("Titel");
		cbTitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbTitel.setForeground(new Color(245, 245, 245));
		cbTitel.setBackground(Color.DARK_GRAY);
		cbTitel.setBounds(6, 121, 88, 23);

		cbOriginaltitel = new JCheckBox("Originaltitel");
		cbOriginaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbOriginaltitel.setForeground(new Color(245, 245, 245));
		cbOriginaltitel.setBackground(Color.DARK_GRAY);
		cbOriginaltitel.setBounds(6, 152, 93, 23);

		cbJahr = new JCheckBox("Jahr");
		cbJahr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbJahr.setForeground(new Color(245, 245, 245));
		cbJahr.setBackground(Color.DARK_GRAY);
		cbJahr.setBounds(6, 180, 88, 23);

		cbFsk = new JCheckBox("FSK");
		cbFsk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbFsk.setForeground(new Color(245, 245, 245));
		cbFsk.setBackground(Color.DARK_GRAY);
		cbFsk.setBounds(196, 180, 61, 23);

		cbPreis = new JCheckBox("Preis");
		cbPreis.setForeground(new Color(245, 245, 245));
		cbPreis.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbPreis.setBackground(Color.DARK_GRAY);
		cbPreis.setBounds(6, 208, 69, 23);

		// Button
		btnObjektAendern = new JButton("Objekt \u00E4ndern");
		btnObjektAendern.setPreferredSize(new Dimension(135, 38));
		btnObjektAendern.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnObjektAendern.setActionCommand("OK");
		getRootPane().setDefaultButton(btnObjektAendern);

		// Dropdownmenues
		dropdownKategorie = new JComboBox();
		dropdownKategorie.setEnabled(false);
		dropdownKategorie
				.setModel(new DefaultComboBoxModel(Kategorie.values()));
		dropdownKategorie.setBounds(110, 58, 155, 22);

		dropdownGenre = new JComboBox();
		dropdownGenre.setEnabled(false);
		dropdownGenre.setBounds(110, 86, 155, 22);

		dropdownFormat = new JComboBox();
		dropdownFormat.setEnabled(false);
		dropdownFormat.setBounds(377, 92, 118, 20);

		// Hinzufuegen
		getContentPane().add(buttonPane);

		// Textfelder
		getContentPane().add(txtJahr);
		getContentPane().add(txtFsk);
		getContentPane().add(txtPreis);
		getContentPane().add(txtTitel);
		getContentPane().add(txtOriginaltitel);

		// Label
		getContentPane().add(lblTitel);
		getContentPane().add(titel);

		// Seperator
		getContentPane().add(separator_1);

		// Checkboxen
		getContentPane().add(cbKategorie);
		getContentPane().add(cbGenre);
		getContentPane().add(cbFormat);
		getContentPane().add(cbTitel);
		getContentPane().add(cbOriginaltitel);
		getContentPane().add(cbJahr);
		getContentPane().add(cbFsk);
		getContentPane().add(cbPreis);

		// Button
		buttonPane.add(btnObjektAendern);

		// Dropdownmenues
		getContentPane().add(dropdownKategorie);
		getContentPane().add(dropdownGenre);
		getContentPane().add(dropdownFormat);

	}

	public boolean getCbFormat() {
		return cbFormat.isSelected();
	}

	public boolean getCbFsk() {
		return cbFsk.isSelected();
	}

	public boolean getCbGenre() {
		return cbGenre.isSelected();
	}

	public boolean getCbJahr() {
		return cbJahr.isSelected();
	}

	public boolean getCbKategorie() {
		return cbKategorie.isSelected();
	}

	public boolean getCbOriginaltitel() {
		return cbOriginaltitel.isSelected();
	}

	public boolean getCbPreis() {
		return cbPreis.isSelected();
	}

	public boolean getCbTitel() {
		return cbTitel.isSelected();
	}

	public String getDropdownFormat() {
		return String.valueOf(dropdownFormat.getSelectedItem());
	}

	public String getDropdownGenre() {
		return String.valueOf(dropdownGenre.getSelectedItem());
	}

	public String getDropdownKategorie() {
		return String.valueOf(dropdownKategorie.getSelectedItem());
	}

	public String getTitel() {
		return titel.getText();
	}

	public String getTxtFsk() {
		return txtFsk.getText();
	}

	public String getTxtJahr() {
		return txtJahr.getText();
	}

	public String getTxtOriginaltitel() {
		return txtOriginaltitel.getText();
	}

	public String getTxtPreis() {
		return txtPreis.getText();
	}

	public String getTxtTitel() {
		return txtTitel.getText();
	}

	public void setCbFormat(boolean b) {
		cbFormat.setEnabled(b);
	}

	public void setCbFormat_value(boolean b) {
		cbFormat.setSelected(b);
	}

	public void setCbGenre(boolean b) {
		cbGenre.setEnabled(b);
	}

	public void setCbGenre_value(boolean b) {
		cbGenre.setSelected(b);
	}

	public void setDropdownFormat(boolean b) {
		dropdownFormat.setEnabled(b);
	}

	public void setDropdownFormat(String kategorie) {
		if (kategorie.equals("Film")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Film_aendern.values()));
		} else if (kategorie.equals("Spiel")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Spiel_aendern.values()));
		} else if (kategorie.equals("Zubehör")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(
					Formate_Zubehoer_aendern.values()));
		}
	}

	public void setDropdownFormat_focus(String name) {
		System.out.println("format fokus " + name);
		// dropdownFormat.setEditable(true);
		dropdownFormat.setSelectedItem(name);
	}

	public void setDropdownGenre(boolean b) {
		dropdownGenre.setEnabled(b);
	}

	public void setDropdownGenre(String kategorie) {
		if (kategorie.equals("Film")) {
			dropdownGenre
					.setModel(new DefaultComboBoxModel(Genre_Film.values()));
		} else if (kategorie.equals("Spiel")) {
			dropdownGenre.setModel(new DefaultComboBoxModel(Genre_Spiel
					.values()));
		} else if (kategorie.equals("Zubehör")) {
			dropdownGenre.setModel(new DefaultComboBoxModel(Genre_Zubehoer
					.values()));
		}
	}

	public void setDropdownGenre_focus(String name) {
		System.out.println("genre fokus " + name.substring(5));
		if (name.startsWith("Film_")) {
			dropdownGenre.setSelectedItem(name.substring(5));
		} else if (name.startsWith("Spiel_")) {
			dropdownGenre.setSelectedItem(name.substring(6));
		} else if (name.startsWith("Zubehoer_")) {
			dropdownGenre.setSelectedItem(name.substring(9));
		}
	}

	public void setDropdownKategorie(boolean b) {
		dropdownKategorie.setEnabled(b);
	}

	public void setDropdownKategorie(String kategorie) {
		dropdownKategorie
				.setModel(new DefaultComboBoxModel(Kategorie.values()));
	}

	public void setDropdownKategorie_focus(String name) {
		// dropdownKategorie.setEditable(true);
		dropdownKategorie.setSelectedItem(name);
	}

	public void setListener_BtnKKAendern(MouseAdapter ma) {
		btnObjektAendern.addMouseListener(ma);
	}

	public void setListener_CbArt(ItemListener il) {
		cbKategorie.addItemListener(il);
	}

	public void setListener_CbFormat(ItemListener il) {
		cbFormat.addItemListener(il);
	}

	public void setListener_CbFsk(ItemListener il) {
		cbFsk.addItemListener(il);
	}

	public void setListener_CbGenre(ItemListener il) {
		cbGenre.addItemListener(il);
	}

	public void setListener_CbJahr(ItemListener il) {
		cbJahr.addItemListener(il);
	}

	public void setListener_CbOriginaltitel(ItemListener il) {
		cbOriginaltitel.addItemListener(il);
	}

	public void setListener_CbPreis(ItemListener il) {
		cbPreis.addItemListener(il);
	}

	public void setListener_CbTitel(ItemListener il) {
		cbTitel.addItemListener(il);
	}

	public void setListener_DropdownFormat(ActionListener al) {
		dropdownFormat.addActionListener(al);
	}

	public void setListener_DropdownKategorie(ActionListener al) {
		dropdownKategorie.addActionListener(al);
	}

	public void setTitel(String titel) {
		this.titel.setText(titel);
	}

	public void setTxtFsk(boolean b) {
		txtFsk.setEnabled(b);
	}

	public void setTxtFsk(String txtFsk) {
		this.txtFsk.setText(txtFsk);
	}

	public void setTxtJahr(boolean b) {
		txtJahr.setEnabled(b);
	}

	public void setTxtJahr(String txtJahr) {
		this.txtJahr.setText(txtJahr);
	}

	public void setTxtOriginaltitel(boolean b) {
		txtOriginaltitel.setEnabled(b);
	}

	public void setTxtOriginaltitel(String txtOrginaltitel) {
		txtOriginaltitel.setText(txtOrginaltitel);
	}

	public void setTxtPreis(boolean b) {
		txtPreis.setEnabled(b);
	}

	public void setTxtPreis(String txtPreis) {
		this.txtPreis.setText(txtPreis);
	}

	public void setTxtTitel(boolean b) {
		txtTitel.setEnabled(b);
	}

	public void setTxtTitel(String txtTitel) {
		this.txtTitel.setText(txtTitel);
	}
}
