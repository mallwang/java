package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Dia_sucheErgebnis_user extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8424202308277909453L;
	private final JPanel contentPanel = new JPanel();
	private JTable table_1;
	private JButton btnAusgewaehlte_anzeigen;
	private Object[][] inhalt;
	private JLabel lblKeineAuswahl;

	public Dia_sucheErgebnis_user() {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 633, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.DARK_GRAY);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(245, 245, 245));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(12, 11, 597, 273);

		// Label
		lblKeineAuswahl = new JLabel("Es wurde nichts ausgew\u00E4hlt!");
		lblKeineAuswahl.setVisible(false);
		lblKeineAuswahl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblKeineAuswahl.setForeground(Color.RED);

		// Table
		table_1 = new JTable();
		table_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table_1.setForeground(new Color(245, 245, 245));
		table_1.setBackground(Color.DARK_GRAY);

		// Button
		btnAusgewaehlte_anzeigen = new JButton("Ausgew\u00E4hlte anzeigen");
		btnAusgewaehlte_anzeigen.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAusgewaehlte_anzeigen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnAusgewaehlte_anzeigen);

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.add(lblKeineAuswahl);

		// ScrollPane
		contentPanel.add(scrollPane);

		// Table
		scrollPane.setViewportView(table_1);

		// Button
		buttonPane.add(btnAusgewaehlte_anzeigen);
	}

	public ArrayList<String> getAusgewaehlt() {
		int anzahl = table_1.getRowCount();
		ArrayList<String> auswahl = new ArrayList<String>();
		for (int i = 0; i < anzahl; i++) {
			if ((boolean) table_1.getModel().getValueAt(i, 3)) {

				auswahl.add((String) table_1.getModel().getValueAt(i, 0));
			}
		}
		return auswahl;
	}

	public void setInhalt(String[][] ergebnis) {
		System.out.println("" + ergebnis[0][0] + ergebnis[0][1]);
		inhalt = new Object[ergebnis.length][4];
		for (int i = 0; i < ergebnis.length; i++) {
			inhalt[i][0] = ergebnis[i][0]; // Kundennummer
			inhalt[i][1] = ergebnis[i][1]; // Nachname
			inhalt[i][2] = ergebnis[i][2]; // Vorname
			inhalt[i][3] = Boolean.FALSE; // Auswahl
		}
		System.out.println("inhalt wurde gesetzt");
		System.out.println("" + inhalt[0][0] + inhalt[0][1]);
	}

	public void setLblKeineAuswahl(boolean b) {
		lblKeineAuswahl.setVisible(b);
	}

	public void setListener_BtnAusgewaehlte_anzeigen(MouseAdapter ma) {
		btnAusgewaehlte_anzeigen.addMouseListener(ma);
	}

	public void updateTable() {
		table_1.setModel(new DefaultTableModel(inhalt, new String[] { "K-Nr.",
				"Nachname", "Vorname", "Auswahl" }) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 610139887354535476L;
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, Boolean.class };
			boolean[] columnEditables = new boolean[] { false, false, false,
					true };

			@Override
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(124);

	}

}
