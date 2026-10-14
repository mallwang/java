package dvdverleih.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

/**
 * SQLite access. The schema is created on first start; values are always
 * bound as parameters, table and column names are checked against a pattern.
 */
public class DB_Verbindung {

	private static final Pattern NAME = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*");
	private static final int[] FSK = { 0, 6, 12, 16, 18 };

	private static final String[] SCHEMA = {
			"CREATE TABLE IF NOT EXISTS adresse (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " strasse TEXT, hausnummer TEXT, wohnort TEXT, plz TEXT)",
			"CREATE TABLE IF NOT EXISTS kunde (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " vorname TEXT, nachname TEXT, geburtsdatum TEXT, adresse INTEGER,"
					+ " telefon TEXT, personalausweisnummer TEXT)",
			"CREATE TABLE IF NOT EXISTS objekt (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " art TEXT, genre TEXT, format TEXT, titel TEXT, hersteller TEXT,"
					+ " produktionsjahr INTEGER, fsk INTEGER, originaltitel TEXT, preis REAL,"
					+ " gesamtAnzahl INTEGER, verfuegbareAnzahl INTEGER, beschreibung TEXT, tags TEXT)",
			"CREATE TABLE IF NOT EXISTS verleih (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " kunde_id INTEGER, objekt_id INTEGER, ausleihdatum TEXT, anzahl INTEGER, verliehen INTEGER)",
			"CREATE TABLE IF NOT EXISTS darsteller (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " d_vorname TEXT, d_nachname TEXT)",
			"CREATE TABLE IF NOT EXISTS regie (id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " r_vorname TEXT, r_nachname TEXT)",
			"CREATE TABLE IF NOT EXISTS objekt_darsteller (objekt_id INTEGER, darsteller_id INTEGER)",
			"CREATE TABLE IF NOT EXISTS film_regie (objekt_id INTEGER, regie_id INTEGER)" };

	private static final String FILM_FROM = "objekt"
			+ " LEFT JOIN objekt_darsteller ON objekt.id = objekt_darsteller.objekt_id"
			+ " LEFT JOIN darsteller ON darsteller.id = objekt_darsteller.darsteller_id"
			+ " LEFT JOIN film_regie ON film_regie.objekt_id = objekt.id"
			+ " LEFT JOIN regie ON regie.id = film_regie.regie_id";

	private final Connection conn;

	/** Opens the database file (property {@code dvdverleih.db}, default {@code ~/.dvd-verleih/dvd-verleih.db}). */
	public DB_Verbindung() {
		this("jdbc:sqlite:" + dateiPfad());
		if (Boolean.getBoolean("dvdverleih.beispieldaten")) {
			beispieldatenLaden();
		}
	}

	/** Opens the given JDBC URL, e.g. {@code jdbc:sqlite::memory:} for tests. */
	public DB_Verbindung(String url) {
		try {
			conn = DriverManager.getConnection(url);
			try (Statement st = conn.createStatement()) {
				for (String sql : SCHEMA) {
					st.executeUpdate(sql);
				}
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("Connect nicht moeglich", e);
		}
	}

	/** Loads the sample data of the legacy MySQL dump ({@code beispieldaten.sql}) unless customers already exist. */
	public void beispieldatenLaden() {
		try (var in = DB_Verbindung.class.getResourceAsStream("/beispieldaten.sql");
				Statement st = conn.createStatement()) {
			try (ResultSet r = st.executeQuery("SELECT COUNT(*) FROM kunde")) {
				if (r.getInt(1) > 0) {
					return;
				}
			}
			// statements end with ";" at the end of a line
			for (String sql : new String(in.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).split(";\\R")) {
				if (!sql.isBlank()) {
					st.executeUpdate(sql);
				}
			}
		} catch (IOException | SQLException e) {
			throw new IllegalArgumentException("Beispieldaten nicht ladbar", e);
		}
	}

	private static Path dateiPfad() {
		Path p = Path.of(System.getProperty("dvdverleih.db",
				System.getProperty("user.home") + "/.dvd-verleih/dvd-verleih.db"));
		try {
			if (p.getParent() != null) {
				Files.createDirectories(p.getParent());
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Datenbankordner nicht anlegbar", e);
		}
		return p;
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new IllegalArgumentException("Fehler beim Datenbankschliessen.", e);
		}
	}

	private static String name(String s) {
		if (!NAME.matcher(s).matches()) {
			throw new IllegalArgumentException("Ungueltiger Name: " + s);
		}
		return "`" + s + "`";
	}

	private static String spalten(List<String> namen) {
		if (namen.isEmpty()) {
			throw new IllegalArgumentException("Keine Spalten angegeben");
		}
		StringJoiner j = new StringJoiner(",");
		namen.forEach(n -> j.add(name(n)));
		return j.toString();
	}

	private static String where(List<String> spalte, List<String> inhalt) {
		if (spalte.size() != inhalt.size()) {
			throw new IllegalArgumentException("Spalten und Inhalte passen nicht zusammen");
		}
		spalten(spalte);
		StringJoiner j = new StringJoiner(" AND ", " WHERE ", "");
		spalte.forEach(s -> j.add(name(s) + " = ?"));
		return j.toString();
	}

	private PreparedStatement prep(String sql, List<?> werte) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < werte.size(); i++) {
			ps.setObject(i + 1, werte.get(i));
		}
		return ps;
	}

	private void update(String sql, List<?> werte, String fehler) {
		try (PreparedStatement ps = prep(sql, werte)) {
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new IllegalArgumentException(fehler, e);
		}
	}

	public boolean delete(String tabelle, ArrayList<String> spalte, ArrayList<String> inhalt) {
		update("DELETE FROM " + name(tabelle) + where(spalte, inhalt), inhalt,
				"Fehler beim Loeschen in der Datenbank.");
		return true;
	}

	public void insert(String tabelle, ArrayList<String> spalte, ArrayList<String> inhalt) {
		if (spalte.size() != inhalt.size()) {
			throw new IllegalArgumentException("Spalten und Inhalte passen nicht zusammen");
		}
		String fragezeichen = String.join(",", java.util.Collections.nCopies(spalte.size(), "?"));
		update("INSERT INTO " + name(tabelle) + "(" + spalten(spalte) + ") VALUES (" + fragezeichen + ")",
				inhalt, "Fehler beim Schreiben in der Datenbank.");
	}

	public ArrayList<String> select(ArrayList<String> gesuchtes, String tabelle,
			ArrayList<String> spalte, ArrayList<String> inhalt) {
		String sql = "SELECT " + spalten(gesuchtes) + " FROM " + name(tabelle) + where(spalte, inhalt);
		ArrayList<String> rturn = new ArrayList<>();
		try (PreparedStatement ps = prep(sql, inhalt); ResultSet result = ps.executeQuery()) {
			while (result.next()) {
				for (int i = 1; i <= gesuchtes.size(); i++) {
					rturn.add(result.getString(i));
				}
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("Fehler beim Auslesen der Datenbank.", e);
		}
		return rturn;
	}

	public ArrayList<String> selectObjekt(ArrayList<String> gesuchtes, ArrayList<String> spalte,
			ArrayList<String> inhalt) {
		return select(gesuchtes, "objekt", spalte, inhalt);
	}

	public boolean update(String tabelle, String spalte_neu, String inhalt_neu, String spalte_vergl,
			String inhalt_vergl) {
		update("UPDATE " + name(tabelle) + " SET " + name(spalte_neu) + " = ? WHERE " + name(spalte_vergl)
				+ " = ?", List.of(inhalt_neu, inhalt_vergl), "Fehler beim Updaten in der Datenbank.");
		return true;
	}

	public String[][] selectFilm(ArrayList<String> spalte, String inhalt, ArrayList<String> genre,
			String format, int fskVon, int fskBis, int jahrVon, int jahrBis) {
		return suche("SELECT DISTINCT genre,titel,originaltitel,fsk,produktionsjahr,format,objekt.id,r_vorname,r_nachname",
				FILM_FROM, "objekt.art = 'Film'", spalte, inhalt, "Film_", genre, format, "alle_Formate", fskVon, fskBis,
				jahrVon, jahrBis, 9, "Fehler beim Filmsuchen in der Datenbank.");
	}

	public String[][] selectSpiel(ArrayList<String> spalte, String inhalt, ArrayList<String> genre,
			String format, int fskVon, int fskBis, int jahrVon, int jahrBis) {
		return suche("SELECT DISTINCT genre,titel,originaltitel,fsk,produktionsjahr,format,objekt.id",
				"objekt", "art = 'Spiel'", spalte, inhalt, "Spiel_", genre, format, "alle_Formate", fskVon,
				fskBis, jahrVon, jahrBis, 7, "Fehler beim Spielsuchen in der Datenbank.");
	}

	public String[][] selectZubehoer(ArrayList<String> spalte, String inhalt, ArrayList<String> genre,
			String format) {
		return suche("SELECT DISTINCT art,titel,originaltitel,fsk,produktionsjahr,format,id", "objekt",
				"art = 'Zubehoer'", spalte, inhalt, "Zubehoer_", genre, format, "alle", 0, 4, 0, 9, 7,
				"Fehler beim Zubehoersuchen in der Datenbank.");
	}

	/** Shared search: LIKE over the chosen columns (OR), then genre, format, FSK and year filters (AND). */
	private String[][] suche(String select, String from, String basis, List<String> spalte, String inhalt,
			String genrePraefix, List<String> genre, String format, String alleFormate, int fskVon,
			int fskBis, int jahrVon, int jahrBis, int spaltenZahl, String fehler) {
		List<Object> p = new ArrayList<>();
		StringBuilder w = new StringBuilder(basis);
		String muster = "%" + inhalt + "%";

		List<String> like = new ArrayList<>();
		for (String s : spalte) {
			List<String> teile = switch (s) {
			case "Darsteller" -> List.of("d_vorname LIKE ?", "d_nachname LIKE ?");
			case "Regie" -> List.of("r_vorname LIKE ?", "r_nachname LIKE ?");
			default -> List.of(name(s.toLowerCase()) + " LIKE ?");
			};
			like.addAll(teile);
			teile.forEach(t -> p.add(muster));
		}
		if (!like.isEmpty()) {
			w.append(" AND (").append(String.join(" OR ", like)).append(")");
		}
		if (!genre.isEmpty()) {
			w.append(" AND genre IN (").append(String.join(",", java.util.Collections.nCopies(genre.size(), "?")))
					.append(")");
			genre.forEach(g -> p.add(genrePraefix + g));
		}
		if (!format.equals(alleFormate)) {
			w.append(" AND format = ?");
			p.add(format);
		}
		if (!(fskVon == 0 && fskBis == 4)) {
			w.append(" AND fsk BETWEEN ? AND ?");
			p.add(FSK[Math.clamp(fskVon, 0, 4)]);
			p.add(FSK[Math.clamp(fskBis, 0, 4)]);
		}
		if (!(jahrVon == 0 && jahrBis == 9)) {
			int von = 1920 + 10 * Math.clamp(jahrVon, 0, 9);
			int bis = 1920 + 10 * Math.clamp(jahrBis, 0, 9);
			if (von != 1920 && bis == 2010) {
				w.append(" AND produktionsjahr >= ?");
				p.add(von);
			} else {
				w.append(" AND produktionsjahr BETWEEN ? AND ?");
				p.add(von);
				p.add(bis);
			}
		}

		List<String[]> zeilen = new ArrayList<>();
		try (PreparedStatement ps = prep(select + " FROM " + from + " WHERE " + w, p);
				ResultSet r = ps.executeQuery()) {
			while (r.next()) {
				String[] z = new String[spaltenZahl];
				for (int i = 0; i < spaltenZahl; i++) {
					z[i] = r.getString(i + 1);
				}
				zeilen.add(z);
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException(fehler, e);
		}
		return zeilen.toArray(new String[0][]);
	}
}
