package funktionen;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StatusModel extends AbstractTableModel {

	// Jede Spalte erhält einen Index
	private static final int COLUMN_STATUS = 0;
	private static final int COLUMN_UHRZEIT = 1;

	// Liste enthält Statusobjekte
	private List<Status> listStatusobjekte;

	// Namen für die Spaltenüberschriften
	private String[] spaltennamen;

	public StatusModel() {
		// Liste mit Statusobjekten initialisieren
		this.listStatusobjekte = new ArrayList<Status>();
		// Spaltenüberschriften erstellen
		spaltennamen = new String[] { "Ereignis", "Uhrzeit" };
	}

	// Gibt die Anzahl an Spalten in der Tabelle zurück
	public int getColumnCount() {
		return spaltennamen.length;
	}

	// Gibt einen speziellen Spaltennamen zurück
	public String getColumnName(int column) {
		return spaltennamen[column];
	}

	// Gibt die Anzahl der Reihen in der Tabelle zurück
	public int getRowCount() {
		return listStatusobjekte.size();
	}

	// Gibt einen Zellenwert zurück
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		Status status = listStatusobjekte.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_STATUS:
			value = status.getStatus();
			break;
		case COLUMN_UHRZEIT:
			value = status.getUhrzeit();
			break;
		}

		return value;
	}

	// Eine Zelle aktualisieren
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Status status = listStatusobjekte.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_STATUS:
			status.setStatus(value.toString());
			break;
		case COLUMN_UHRZEIT:
			status.setUhrzeit(value.toString());
			break;
		}
	}

	// Keine Zelle ist editierbar
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	// Einen Eintrag hinzufügen
	public void addStatus(Status status) {
		this.listStatusobjekte.add(status);
	}

	// Einen Eintrag entfernen
	public void removeStatus(int rowIndex) {
		this.listStatusobjekte.remove(rowIndex);
	}
}
