package gui;

import gui.Enums.Formate_Film;
import gui.Enums.Formate_Spiel;
import gui.Enums.Formate_Zubehoer;
import gui.Enums.Genre_Film;
import gui.Enums.Genre_Spiel;
import gui.Enums.Genre_Zubehoer;
import gui.Enums.Kategorie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import slider.RangeSlider;

public class Dia_sucheMaske extends JDialog {
	/**
	 * Diese Maske ermoeglicht die Suche nach allen Objekten, die ausgeliehen
	 * werden koennen. Sie wird entweder im Verleihprozess oder ueber die
	 * Produktsuche aufgerufen.
	 */
	private static final long serialVersionUID = -6423764830651335438L;
	private final JTextField txtSuche;
	private final JCheckBox cbTitel;
	private final JCheckBox cbOriginaltitel;
	private final JCheckBox cbTags;
	private final JCheckBox cbDarsteller;
	private final JCheckBox cbRegie;
	private final JComboBox dropdownKategorie;
	private final JComboBox dropdownFormat;
	private final RangeSlider rangeSlider_fsk;
	private final RangeSlider rangeSlider_jahr;
	private final JList<String> list;
	private final JButton btnSuchen;
	private JLabel lblKeinSuchbegriff;
	private JLabel lblKeinSuchtreffer;

	public Dia_sucheMaske(int def) {
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 13));
		setBackground(Color.DARK_GRAY);
		final JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(169, 169, 169));
		setBounds(100, 100, 550, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(169, 169, 169));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Text field
		txtSuche = new JTextField();
		txtSuche.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtSuche.setBounds(133, 63, 229, 20);

		// Checkbox
		cbTitel = new JCheckBox("Titel");
		cbTitel.setForeground(new Color(245, 245, 245));
		cbTitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbTitel.setBackground(new Color(169, 169, 169));
		cbTitel.setEnabled(false);
		cbTitel.setSelected(true);
		cbTitel.setBounds(25, 108, 97, 23);

		cbOriginaltitel = new JCheckBox("Originaltitel");
		cbOriginaltitel.setForeground(new Color(245, 245, 245));
		cbOriginaltitel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbOriginaltitel.setBackground(new Color(169, 169, 169));
		cbOriginaltitel.setBounds(25, 138, 97, 23);

		cbTags = new JCheckBox("Tags");
		cbTags.setForeground(new Color(245, 245, 245));
		cbTags.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbTags.setBackground(new Color(169, 169, 169));
		cbTags.setBounds(25, 220, 97, 23);

		cbDarsteller = new JCheckBox("Darsteller");
		cbDarsteller.setForeground(new Color(245, 245, 245));
		cbDarsteller.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbDarsteller.setBackground(new Color(169, 169, 169));
		cbDarsteller.setBounds(25, 164, 97, 23);

		cbRegie = new JCheckBox("Regie");
		cbRegie.setForeground(new Color(245, 245, 245));
		cbRegie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbRegie.setBackground(new Color(169, 169, 169));
		cbRegie.setBounds(25, 194, 97, 23);

		// ComboBox
		dropdownKategorie = new JComboBox();
		dropdownKategorie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		dropdownKategorie
				.setModel(new DefaultComboBoxModel(Kategorie.values()));
		dropdownKategorie.setBounds(132, 21, 88, 20);
		dropdownKategorie.setSelectedIndex(def);

		dropdownFormat = new JComboBox();
		dropdownFormat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		dropdownFormat.setBounds(241, 21, 121, 20);

		// Hash table
		Hashtable<Integer, JLabel> table_fsk = new Hashtable<Integer, JLabel>();
		table_fsk.put(0, new JLabel("0"));
		table_fsk.put(1, new JLabel("6"));
		table_fsk.put(2, new JLabel("12"));
		table_fsk.put(3, new JLabel("16"));
		table_fsk.put(4, new JLabel("18"));

		Hashtable<Integer, JLabel> table_jahr = new Hashtable<Integer, JLabel>();
		table_jahr.put(0, new JLabel("20er"));
		table_jahr.put(1, new JLabel("30er"));
		table_jahr.put(2, new JLabel("40er"));
		table_jahr.put(3, new JLabel("50er"));
		table_jahr.put(4, new JLabel("60er"));
		table_jahr.put(5, new JLabel("70er"));
		table_jahr.put(6, new JLabel("80er"));
		table_jahr.put(7, new JLabel("90er"));
		table_jahr.put(8, new JLabel("ab 2000"));
		table_jahr.put(9, new JLabel("ab 2010"));

		// Label
		JLabel lblFsk = new JLabel("FSK:");
		lblFsk.setForeground(new Color(245, 245, 245));
		lblFsk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblFsk.setBounds(174, 224, 46, 14);

		JLabel lblJahr = new JLabel("Jahr:");
		lblJahr.setForeground(new Color(245, 245, 245));
		lblJahr.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblJahr.setBounds(25, 316, 46, 14);

		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setForeground(new Color(245, 245, 245));
		lblGenre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblGenre.setHorizontalAlignment(SwingConstants.LEFT);
		lblGenre.setBounds(376, 92, 46, 14);

		JLabel lblKategorie = new JLabel("Kategorie");
		lblKategorie.setForeground(new Color(245, 245, 245));
		lblKategorie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblKategorie.setBounds(35, 24, 87, 14);

		JLabel lblSuchbegriff = new JLabel("Suchbegriff");
		lblSuchbegriff.setForeground(new Color(245, 245, 245));
		lblSuchbegriff.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblSuchbegriff.setBounds(35, 66, 88, 14);

		lblKeinSuchbegriff = new JLabel("Es wurde kein Suchbegriff eingegeben!");
		lblKeinSuchbegriff.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblKeinSuchbegriff.setForeground(Color.RED);
		lblKeinSuchbegriff.setVisible(false);

		lblKeinSuchtreffer = new JLabel("Die Suche hat keinen Treffer ergeben.");
		lblKeinSuchtreffer.setVisible(false);
		lblKeinSuchtreffer.setForeground(Color.RED);
		lblKeinSuchtreffer.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeinSuchtreffer.setBounds(133, 93, 229, 14);

		// Range Slider
		rangeSlider_fsk = new RangeSlider();
		rangeSlider_fsk.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		rangeSlider_fsk.setForeground(new Color(245, 245, 245));
		rangeSlider_fsk.setBackground(new Color(169, 169, 169));
		rangeSlider_fsk.setBounds(162, 235, 144, 60);
		rangeSlider_fsk.setPreferredSize(new Dimension(240, rangeSlider_fsk
				.getPreferredSize().height));
		rangeSlider_fsk.setMinimum(0);
		rangeSlider_fsk.setMaximum(4);
		rangeSlider_fsk.setPaintTicks(true);
		rangeSlider_fsk.setMinorTickSpacing(1);
		rangeSlider_fsk.setPaintLabels(true);
		rangeSlider_fsk.setLabelTable(table_fsk);
		rangeSlider_fsk.setValue(0);
		rangeSlider_fsk.setUpperValue(4);

		rangeSlider_jahr = new RangeSlider();
		rangeSlider_jahr.setForeground(new Color(245, 245, 245));
		rangeSlider_jahr.setBackground(new Color(169, 169, 169));
		rangeSlider_jahr.setValue(0);
		rangeSlider_jahr.setUpperValue(9);
		rangeSlider_jahr.setPreferredSize(new Dimension(240, 16));
		rangeSlider_jahr.setPaintTicks(true);
		rangeSlider_jahr.setPaintLabels(true);
		rangeSlider_jahr.setLabelTable(table_jahr);
		rangeSlider_jahr.setMinorTickSpacing(1);
		rangeSlider_jahr.setMinimum(0);
		rangeSlider_jahr.setMaximum(9);
		rangeSlider_jahr.setBounds(10, 334, 514, 60);

		rangeSlider_jahr.setValue(0);
		rangeSlider_jahr.setUpperValue(9);

		// Scroll Pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(364, 117, 160, 206);
		contentPanel.add(scrollPane);

		// List
		list = new JList<String>();
		list.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane.setViewportView(list);

		// Button
		btnSuchen = new JButton("Suchen");
		btnSuchen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSuchen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnSuchen);

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// Textfeld
		contentPanel.add(txtSuche);

		// Checkbox
		contentPanel.add(cbTitel);
		contentPanel.add(cbOriginaltitel);
		contentPanel.add(cbTags);
		contentPanel.add(cbDarsteller);
		contentPanel.add(cbRegie);

		// Dropdownmenues
		contentPanel.add(dropdownFormat);
		contentPanel.add(dropdownKategorie);

		// Label
		contentPanel.add(lblFsk);
		contentPanel.add(lblJahr);
		contentPanel.add(lblGenre);
		contentPanel.add(lblKategorie);
		contentPanel.add(lblSuchbegriff);
		contentPanel.add(lblKeinSuchtreffer);
		buttonPane.add(lblKeinSuchbegriff);

		// RangeSlider
		contentPanel.add(rangeSlider_fsk);
		contentPanel.add(rangeSlider_jahr);

		// Button
		buttonPane.add(btnSuchen);

	}

	public boolean getCbDarsteller() {
		return cbDarsteller.isSelected();
	}

	public boolean getCbOriginaltitel() {
		return cbOriginaltitel.isSelected();
	}

	public boolean getCbRegie() {
		return cbRegie.isSelected();
	}

	public boolean getCbTags() {
		return cbTags.isSelected();
	}

	public boolean getCbTitel() {
		return cbTitel.isSelected();
	}

	public String getDropdownFormat() {
		return String.valueOf(dropdownFormat.getSelectedItem());
	}

	public String getDropdownKategorie() {
		return String.valueOf(dropdownKategorie.getSelectedItem());
	}

	public ArrayList<String> getList() {
		ArrayList<String> alist = new ArrayList<String>();
		if (!list.getSelectedValuesList().isEmpty()) {
			alist = (ArrayList<String>) list.getSelectedValuesList();
		}
		return alist;
	}

	public int getRangeSlider_fsk_uppervalue() {
		return rangeSlider_fsk.getUpperValue();
	}

	public int getRangeSlider_fsk_value() {
		return rangeSlider_fsk.getValue();
	}

	public int getRangeSlider_jahr_uppervalue() {
		return rangeSlider_jahr.getUpperValue();
	}

	public int getRangeSlider_jahr_value() {
		return rangeSlider_jahr.getValue();
	}

	public String getTxtSuche() {
		if (txtSuche.getText().matches("[a-zA-Z0-9]*"))
			return txtSuche.getText();
		else
			return "";
	}

	public void setBtnSuchen(boolean b) {
		btnSuchen.setEnabled(b);
	}

	public void setCbDarsteller(boolean b) {
		cbDarsteller.setEnabled(b);
	}

	public void setCbDarsteller_value(boolean b) {
		cbDarsteller.setSelected(b);
	}

	public void setCbOriginaltitel(boolean b) {
		cbOriginaltitel.setEnabled(b);
	}

	public void setCbOriginaltitel_value(boolean b) {
		cbOriginaltitel.setSelected(b);
	}

	public void setCbRegie(boolean b) {
		cbRegie.setEnabled(b);
	}

	public void setCbRegie_value(boolean b) {
		cbRegie.setSelected(b);
	}

	public void setCbTags(boolean b) {
		cbTags.setEnabled(b);
	}

	public void setCbTags_value(boolean b) {
		cbTags.setSelected(b);
	}

	public void setCbTitel(boolean b) {
		cbTitel.setEnabled(b);
	}

	public void setCbTitel_value(boolean b) {
		cbTitel.setSelected(b);
	}

	public void setDropdownFormat(String kategorie) {
		if (kategorie.equals("Film")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(Formate_Film
					.values()));
		} else if (kategorie.equals("Spiel")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(Formate_Spiel
					.values()));
		} else if (kategorie.equals("Zubehör")) {
			dropdownFormat.setModel(new DefaultComboBoxModel(Formate_Zubehoer
					.values()));
		}
	}

	public void setGenre(boolean b) {
		list.setEnabled(b);
	}

	public void setGenre(String kategorie) {
		if (kategorie.equals("Film")) {
			list.setModel(new DefaultComboBoxModel(Genre_Film.values()));
		} else if (kategorie.equals("Spiel")) {
			list.setModel(new DefaultComboBoxModel(Genre_Spiel.values()));
		} else if (kategorie.equals("Zubehör")) {
			list.setModel(new DefaultComboBoxModel(Genre_Zubehoer.values()));
		}
	}

	public void setLblKeinSuchbegriff(boolean b) {
		lblKeinSuchbegriff.setVisible(b);
	}

	public void setLblKeinSuchtreffer(boolean b) {
		lblKeinSuchtreffer.setVisible(b);
	}

	public void setListener_BtnSuchen(MouseAdapter ma) {
		btnSuchen.addMouseListener(ma);
	}

	public void setListener_DropdownFormat(ActionListener al) {
		dropdownFormat.addActionListener(al);
	}

	public void setListener_DropdownKategorie(ActionListener al) {
		dropdownKategorie.addActionListener(al);
	}

	public void setListener_DropDownKategorie(MouseAdapter ma) {
		btnSuchen.addMouseListener(ma);
	}

}
