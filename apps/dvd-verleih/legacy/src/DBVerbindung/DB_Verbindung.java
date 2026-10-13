package DBVerbindung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB_Verbindung {

	private static Connection conn = null;

	// Hostname
	private static String dbHost = "127.0.0.1";

	// Port -- Standard: 3306
	private static String dbPort = "3306";

	// Datenbankname
	private static String database = "gruppe1";

	// Datenbankuser
	private static String dbUser = "gruppe_eins";

	// Datenbankpasswort
	private static String dbPassword = "geheimesPW";

	private static Connection getInstance() {
		if (conn == null) {
			new DB_Verbindung();
		}
		return conn;
	}

	public DB_Verbindung() {
		try {
			// Datenbanktreiber für ODBC Schnittstellen laden.
			// Für verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank 'gruppe1' herstellen.
			// Es wird die JDBC-ODBC-Brücke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":"
					+ dbPort + "/" + database + "?" + "user=" + dbUser + "&"
					+ "password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Treiber nicht gefunden");
		} catch (SQLException e) {
			throw new IllegalArgumentException("Connect nicht moeglich");
		}
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new IllegalArgumentException(
					"Fehler beim Datenbankschliessen.");
		}
	}

	public boolean delete(String tabelle, ArrayList<String> spalte,
			ArrayList<String> inhalt) {
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
				String spalten = "";
				for (String i : spalte) {
					spalten += ",`" + i + "`";
				}
				String inhalte = "";
				for (String i : inhalt) {
					inhalte += ",'" + i + "'";
				}
				// Ergebnistabelle erzeugen und abholen.
				String sql = "DELETE FROM `" + tabelle + "` WHERE ("
						+ spalten.substring(1) + ")" + " = " + "("
						+ inhalte.substring(1) + ")";
				System.out.println(sql);

				query.executeUpdate(sql);
			} catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Loeschen in der Datenbank.");
			}

		}
		return true;

	}

	/**
	 * Fügt einen neuen Datensatz hinzu @
	 */
	public void insert(String tabelle, ArrayList<String> spalte,
			ArrayList<String> inhalt) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
				String spalten = "";
				for (String i : spalte) {
					spalten += ",`" + i + "`";
				}
				String inhalte = "";
				for (String i : inhalt) {
					inhalte += ",'" + i + "'";
				}
				// Ergebnistabelle erzeugen und abholen.
				String sql = "INSERT INTO `" + tabelle + "`("
						+ spalten.substring(1) + ")" + " VALUES " + "("
						+ inhalte.substring(1) + ")";
				System.out.println(sql);

				query.executeUpdate(sql);

				// Es wird der letzte Datensatz abgefragt
				ResultSet result = query
						.executeQuery("SELECT id, vorname, nachname FROM kunde ORDER BY id desc LIMIT 1");

				// Wenn ein Datensatz gefunden wurde, wird auf diesen
				// Zugegriffen
				if (result.next()) {
					System.out.println("(" + result.getInt(1) + ")"
							+ result.getString(2) + " " + result.getString(3));
				}
				result.close();
			} catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Schreiben in der Datenbank.");
			}
		}
	}

	public ArrayList<String> select(ArrayList<String> gesuchtes,
			String tabelle, ArrayList<String> spalte, ArrayList<String> inhalt) {
		ArrayList<String> rturn = new ArrayList<String>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
				String spalten = "";
				for (String i : spalte) {
					spalten += "," + "`" + i + "`";
				}
				String inhalte = "";
				for (String i : inhalt) {
					inhalte += ",'" + i + "'";
				}
				String gesuche = "";
				for (String i : gesuchtes) {
					gesuche += ",`" + i + "`";
				}
				// Ergebnistabelle erzeugen und abholen.
				String sql = "select " + gesuche.substring(1) + " from `"
						+ tabelle + "` where (" + spalten.substring(1)
						+ ") = (" + inhalte.substring(1) + ")";
				System.out.println(sql);
				ResultSet result = query.executeQuery(sql);
				while (result.next()) {
					for (String i : gesuchtes) {
						rturn.add(result.getString(i));
					}
				}
				result.close();
			} catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Auslesen der Datenbank.");
			}
		}

		return rturn;
	}

	/**
	 * Ausleihen eines Filmes @
	 */
	public String[][] selectFilm(ArrayList<String> spalte, String inhalt,
			ArrayList<String> genre, String format, int fskVon, int fskBis,
			int jahrVon, int jahrBis) {

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Parameter suchen (CheckBoxen)
				String parameter = "";
				if (spalte.isEmpty()) {
					parameter = "     ";
				} else {
					for (String i : spalte) {
						if (!i.equals("Darsteller") && !i.equals("Regie")) {
							parameter += " OR `" + i.toLowerCase()
									+ "` LIKE '%" + inhalt + "%'";
						}
					}
				}

				// Genres suchen
				String genres = "", gerne = "";
				if (genre.size() == 0) {
					gerne = "";
				} else {
					for (int i = 0; i < genre.size(); i++) {
						genres += "," + "'Film_" + String.valueOf(genre.get(i))
								+ "'";
					}
					gerne = " AND genre IN ( " + genres.substring(1) + " )";
				}

				// Darsteller suchen
				String darsteller = "";
				if (spalte.contains("Darsteller")) {
					darsteller = " OR `d_vorname` LIKE '%" + inhalt
							+ "%' OR `d_nachname` LIKE '%" + inhalt + "%'";
				} else {
					darsteller = "";
				}

				// Regisseur suchen
				String regisseur = "";
				if (spalte.contains("Regie")) {
					regisseur = " OR `r_vorname` LIKE '%" + inhalt
							+ "%' OR `r_nachname` LIKE '%" + inhalt + "%'";
				} else {
					regisseur = "";
				}

				// Formate suchen
				String formate = "";
				if (format.equals("alle_Formate")) {
					formate = "";
				} else {
					formate = " AND format = '" + format + "'";
				}

				// FSK suchen
				String fsk = "";
				int alterVon = 0, alterBis = 0;
				if ((fskVon == 0) && (fskBis == 4)) {
					fsk = "";
				} else {
					switch (fskVon) {
					case 0:
						alterVon = 0;
						break;
					case 1:
						alterVon = 6;
						break;
					case 2:
						alterVon = 12;
						break;
					case 3:
						alterVon = 16;
						break;
					case 4:
						alterVon = 18;
						break;
					}
					switch (fskBis) {
					case 0:
						alterBis = 0;
						break;
					case 1:
						alterBis = 6;
						break;
					case 2:
						alterBis = 12;
						break;
					case 3:
						alterBis = 16;
						break;
					case 4:
						alterBis = 18;
						break;
					}
					fsk = " AND (fsk BETWEEN " + alterVon + " AND " + alterBis
							+ ")";
				}

				// Jahr suchen
				String jahr = "";
				int jahrBeginn = 1920, jahrEnde = 2010;
				if ((jahrVon == 0) && (jahrBis == 9)) {
					jahr = "";
				} else {
					switch (jahrVon) {
					case 0:
						jahrBeginn = 1920;
						break;
					case 1:
						jahrBeginn = 1930;
						break;
					case 2:
						jahrBeginn = 1940;
						break;
					case 3:
						jahrBeginn = 1950;
						break;
					case 4:
						jahrBeginn = 1960;
						break;
					case 5:
						jahrBeginn = 1970;
						break;
					case 6:
						jahrBeginn = 1980;
						break;
					case 7:
						jahrBeginn = 1990;
						break;
					case 8:
						jahrBeginn = 2000;
						break;
					case 9:
						jahrBeginn = 2010;
						break;
					}
					switch (jahrBis) {
					case 0:
						jahrEnde = 1920;
						break;
					case 1:
						jahrEnde = 1930;
						break;
					case 2:
						jahrEnde = 1940;
						break;
					case 3:
						jahrEnde = 1950;
						break;
					case 4:
						jahrEnde = 1960;
						break;
					case 5:
						jahrEnde = 1970;
						break;
					case 6:
						jahrEnde = 1980;
						break;
					case 7:
						jahrEnde = 1990;
						break;
					case 8:
						jahrEnde = 2000;
						break;
					case 9:
						jahrEnde = 2010;
						break;
					}
					if ((jahrBeginn != 1920) && (jahrEnde == 2010)) {
						jahr = " AND (produktionsjahr >= " + jahrBeginn + ")";
					} else {
						jahr = " AND (produktionsjahr BETWEEN " + jahrBeginn
								+ " AND " + jahrEnde + ")";
					}
				}

				// SQL-Abfrage erstellen

				String sql = "SELECT DISTINCT genre,titel,originaltitel,fsk,produktionsjahr,format,objekt.id,r_vorname, r_nachname"
						+ " FROM regie"
						+ " INNER JOIN (film_regie"
						+ " INNER JOIN (darsteller"
						+ " INNER JOIN (objekt"
						+ " INNER JOIN objekt_darsteller"
						+ " ON objekt.id = objekt_darsteller.objekt_id)"
						+ " ON darsteller.id = objekt_darsteller.darsteller_id)"
						+ " ON  film_regie.objekt_id = objekt.id)"
						+ " ON regie.id = film_regie.regie_id "
						+ " WHERE ("
						+ parameter.substring(4)
						+ darsteller.substring(0)
						+ regisseur.substring(0)
						+ ")"
						+ gerne
						+ formate
						+ fsk
						+ jahr;
				System.out.println(sql);

				// Query abschicken
				ResultSet result = query.executeQuery(sql);
				int resultLaenge = 0;
				while (result.next()) {
					resultLaenge += 1;
				}// end while
				result.beforeFirst();
				String[][] rturn = new String[resultLaenge][9];
				int a = 0;
				while (result.next()) {
					for (int b = 0; b < 9; b++) {
						rturn[a][b] = result.getString(b + 1);
						System.out.println("" + a + " / " + b + ": "
								+ result.getString(b + 1));
					}// end for
					a += 1;
				}// end while
				result.close();
				return rturn;
			}// end try
			catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Filmsuchen in der Datenbank.");
			}// end catch
		}// end if
		return null;
	}// end class

	public ArrayList<String> selectObjekt(ArrayList<String> gesuchtes,
			ArrayList<String> spalte, ArrayList<String> inhalt) {
		ArrayList<String> rturn = new ArrayList<String>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
				String spalten = "";
				for (String i : spalte) {
					spalten += "," + "`" + i + "`";
				}
				String inhalte = "";
				for (String i : inhalt) {
					inhalte += ",'" + i + "'";
				}
				String gesuche = "";
				for (String i : gesuchtes) {
					gesuche += ",`" + i + "`";
				}
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT " + gesuche.substring(1)
						+ " FROM `objekt` where (" + spalten.substring(1)
						+ ") = (" + inhalte.substring(1) + ")";
				System.out.println(sql);
				ResultSet result = query.executeQuery(sql);
				while (result.next()) {
					for (String i : gesuchtes) {
						rturn.add(result.getString(i));
					}
				}
				result.close();
			} catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Objektsuchen in der Datenbank.");
			}
		}

		return rturn;
	}

	/**
	 * Ausleihen eines Spieles @
	 */
	public String[][] selectSpiel(ArrayList<String> spalte, String inhalt,
			ArrayList<String> genre, String format, int fskVon, int fskBis,
			int jahrVon, int jahrBis) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Parameter suchen (CheckBoxen)
				String parameter = "";
				if (spalte.isEmpty()) {
					parameter = "     ";
				} else {
					for (String i : spalte) {
						parameter += " OR `" + i.toLowerCase() + "` LIKE '%"
								+ inhalt + "%'";
					}
				}

				// Genres suchen
				String genres = "", gerne = "";
				if (genre.size() == 0) {
					gerne = "";
				} else {
					for (int i = 0; i < genre.size(); i++) {
						genres += "," + "'Spiel_"
								+ String.valueOf(genre.get(i)) + "'";
					}
					gerne = " AND genre IN ( " + genres.substring(1) + " )";
				}

				// Formate suchen
				String formate = "";
				if (format.equals("alle_Formate")) {
					formate = "";
				} else {
					formate = " AND format = '" + format + "'";
				}

				// FSK suchen
				String fsk = "";
				int alterVon = 0, alterBis = 0;
				if ((fskVon == 0) && (fskBis == 4)) {
					fsk = "";
				} else {
					switch (fskVon) {
					case 0:
						alterVon = 0;
						break;
					case 1:
						alterVon = 6;
						break;
					case 2:
						alterVon = 12;
						break;
					case 3:
						alterVon = 16;
						break;
					case 4:
						alterVon = 18;
						break;
					}
					switch (fskBis) {
					case 0:
						alterBis = 0;
						break;
					case 1:
						alterBis = 6;
						break;
					case 2:
						alterBis = 12;
						break;
					case 3:
						alterBis = 16;
						break;
					case 4:
						alterBis = 18;
						break;
					}
					fsk = " AND (fsk BETWEEN " + alterVon + " AND " + alterBis
							+ ")";
				}

				// Jahr suchen
				String jahr = "";
				int jahrBeginn = 1920, jahrEnde = 2010;
				if ((jahrVon == 0) && (jahrBis == 9)) {
					jahr = "";
				} else {
					switch (jahrVon) {
					case 0:
						jahrBeginn = 1920;
						break;
					case 1:
						jahrBeginn = 1930;
						break;
					case 2:
						jahrBeginn = 1940;
						break;
					case 3:
						jahrBeginn = 1950;
						break;
					case 4:
						jahrBeginn = 1960;
						break;
					case 5:
						jahrBeginn = 1970;
						break;
					case 6:
						jahrBeginn = 1980;
						break;
					case 7:
						jahrBeginn = 1990;
						break;
					case 8:
						jahrBeginn = 2000;
						break;
					case 9:
						jahrBeginn = 2010;
						break;
					}
					switch (jahrBis) {
					case 0:
						jahrEnde = 1920;
						break;
					case 1:
						jahrEnde = 1930;
						break;
					case 2:
						jahrEnde = 1940;
						break;
					case 3:
						jahrEnde = 1950;
						break;
					case 4:
						jahrEnde = 1960;
						break;
					case 5:
						jahrEnde = 1970;
						break;
					case 6:
						jahrEnde = 1980;
						break;
					case 7:
						jahrEnde = 1990;
						break;
					case 8:
						jahrEnde = 2000;
						break;
					case 9:
						jahrEnde = 2010;
						break;
					}
					if ((jahrBeginn != 1920) && (jahrEnde == 2010)) {
						jahr = " AND (produktionsjahr >= " + jahrBeginn + ")";
					} else {
						jahr = " AND (produktionsjahr BETWEEN " + jahrBeginn
								+ " AND " + jahrEnde + ")";
					}
				}

				// SQL-Abfrage erstellen

				String sql = "SELECT DISTINCT genre, titel, originaltitel,fsk, produktionsjahr, format,objekt.id"
						+ " FROM objekt"
						+ " WHERE art = 'Spiel'"
						+ " AND ("
						+ parameter.substring(4)
						+ ")"
						+ gerne
						+ formate
						+ fsk
						+ jahr;
				System.out.println(sql);

				// Query abschicken
				ResultSet result = query.executeQuery(sql);
				int resultLaenge = 0;
				while (result.next()) {
					resultLaenge += 1;
				}// end while
				result.beforeFirst();
				String[][] rturn = new String[resultLaenge][7];
				int a = 0;
				while (result.next()) {
					for (int b = 0; b < 7; b++) {
						rturn[a][b] = result.getString(b + 1);
						System.out.println("" + a + " / " + b + ": "
								+ result.getString(b + 1));
					}// end for
					a += 1;
				}// end while
				result.close();
				return rturn;
			}// end try
			catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Spielsuchen in der Datenbank.");
			}// end catch
		}// end
		return null;
	}

	/**
	 * Ausleihen von Zubehoer @
	 */
	public String[][] selectZubehoer(ArrayList<String> spalte, String inhalt,
			ArrayList<String> genre, String format) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Parameter suchen (CheckBoxen)
				String parameter = "";
				if (spalte.isEmpty()) {
					parameter = "     ";
				} else {
					for (String i : spalte) {
						parameter += " OR `" + i.toLowerCase() + "` LIKE '%"
								+ inhalt + "%'";
					}
				}

				// Genres suchen
				String genres = "", gerne = "";
				if (genre.size() == 0) {
					gerne = "";
				} else {
					for (int i = 0; i < genre.size(); i++) {
						genres += "," + "'Zubehoer_"
								+ String.valueOf(genre.get(i)) + "'";
					}
					gerne = " AND genre IN ( " + genres.substring(1) + " )";
				}

				// Formate suchen
				String formate = "";
				if (format.equals("alle")) {
					formate = "";
				} else {
					formate = " AND format = '" + format + "'";
				}

				// Alte Abfrage
				// // SQL-Abfrage erstellen
				// String sql =
				// "SELECT DISTINCT titel, objekt.id, genre, format, hersteller, preis, verfuegbareAnzahl"
				// + " FROM objekt"
				// + " WHERE art = 'Zubehoer'"
				// + " AND (" + parameter.substring(4) + ")"
				// + gerne
				// + formate;
				// System.out.println(sql);

				// SQL-Abfrage erstellen
				String sql = "SELECT DISTINCT art, titel, originaltitel, fsk, produktionsjahr, format, id"
						+ " FROM objekt"
						+ " WHERE art = 'Zubehoer'"
						+ " AND ("
						+ parameter.substring(4) + ")" + gerne + formate;
				System.out.println(sql);

				// Query abschicken
				ResultSet result = query.executeQuery(sql);
				int resultLaenge = 0;
				while (result.next()) {
					resultLaenge += 1;
				}// end while
				result.beforeFirst();
				String[][] rturn = new String[resultLaenge][7];
				int a = 0;
				while (result.next()) {
					for (int b = 0; b < 7; b++) {
						rturn[a][b] = result.getString(b + 1);
						System.out.println("" + a + " / " + b + ": "
								+ result.getString(b + 1));
					}// end for
					a += 1;
				}// end while
				result.close();
				return rturn;
			}// end try
			catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Zubehoersuchen in der Datenbank.");
			}// end catch
		}// end
		return null;
	}

	/**
	 * Aktualisiert den Datensatz mit der übergebenen actorId
	 */
	public boolean update(String tabelle, String spalte_neu, String inhalt_neu,
			String spalte_vergl, String inhalt_vergl) {

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();
				String sql = "UPDATE `" + tabelle + "` SET `" + spalte_neu
						+ "` = '" + inhalt_neu + "' WHERE `" + spalte_vergl
						+ "` = '" + inhalt_vergl + "'";
				System.out.println(sql);

				query.executeUpdate(sql);
			} catch (SQLException e) {
				throw new IllegalArgumentException(
						"Fehler beim Updaten in der Datenbank.");
			}

		}
		return true;
	}
}