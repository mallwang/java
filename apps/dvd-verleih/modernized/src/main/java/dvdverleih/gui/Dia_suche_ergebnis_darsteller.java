package dvdverleih.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Dia_suche_ergebnis_darsteller extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8368104712826953598L;
	private final JPanel contentPanel = new JPanel();
	private JTable table_1;
	private JButton btnAusgewaehlte_hinzufuegen;
	private Object[][] inhalt;

	/**
	 * Beim Neuanlegen eines Objektes kann nach schon in der Datenbank
	 * angelegten Darstellern gesucht werden, um sie dem neuen Film zuzuordnen.
	 */
	public Dia_suche_ergebnis_darsteller() {
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

		// SrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(245, 245, 245));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(12, 11, 597, 273);

		// Table
		table_1 = new JTable();
		table_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table_1.setForeground(new Color(245, 245, 245));
		table_1.setBackground(Color.DARK_GRAY);

		// Button
		btnAusgewaehlte_hinzufuegen = new JButton(
				"Ausgew\u00E4hlte hinzuf\u00FCgen");
		btnAusgewaehlte_hinzufuegen
				.setFont(new Font("Segoe UI", Font.BOLD, 13));

		btnAusgewaehlte_hinzufuegen.setActionCommand("OK");
		getRootPane().setDefaultButton(btnAusgewaehlte_hinzufuegen);

		// Hinzufuegen
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// ScrollPane
		contentPanel.add(scrollPane);

		// Table
		scrollPane.setViewportView(table_1);

		// Button
		buttonPane.add(btnAusgewaehlte_hinzufuegen);

	}

	public ArrayList<ArrayList<String>> getAusgewaehlt() {
		int anzahl = table_1.getRowCount();
		ArrayList<ArrayList<String>> auswahl = new ArrayList<ArrayList<String>>();
		ArrayList<String> zw;
		for (int i = 0; i < anzahl; i++) {

			if ((boolean) table_1.getModel().getValueAt(i, 3)) {
				zw = new ArrayList<String>();
				zw.add(String.valueOf(table_1.getModel().getValueAt(i, 2)));
				zw.add(String.valueOf(table_1.getModel().getValueAt(i, 1)));
				zw.add(String.valueOf(table_1.getModel().getValueAt(i, 0)));
				auswahl.add(zw);
			}
		}
		return auswahl;
	}

	public void setInhalt(String[][] ergebnis) {
		System.out.println("" + ergebnis[0][0] + ergebnis[0][1]);
		inhalt = new Object[ergebnis.length][4];
		for (int i = 0; i < ergebnis.length; i++) {
			inhalt[i][0] = ergebnis[i][0]; // ID
			inhalt[i][1] = ergebnis[i][1]; // Nachname
			inhalt[i][2] = ergebnis[i][2]; // Vorname
			inhalt[i][3] = Boolean.FALSE; // Auswahl
		}
		System.out.println("inhalt wurde gesetzt");
		System.out.println("" + inhalt[0][0] + inhalt[0][1]);
	}

	public void setListener_BtnAusgewaehlte_anzeigen(MouseAdapter ma) {
		btnAusgewaehlte_hinzufuegen.addMouseListener(ma);
	}

	public void updateTable() {
		table_1.setModel(new DefaultTableModel(inhalt, new String[] { "ID",
				"Nachname", "Vorname", "Auswahl" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = -9139395526838120836L;
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