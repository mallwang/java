package dvdverleih.funktionen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class KKonto {

	private ArrayList<String> KUNDE = new ArrayList<String>();
	private ArrayList<String> ADRESSE = new ArrayList<String>();
	private ArrayList<String> VERLEIH = new ArrayList<String>();
	private ArrayList<String> RUECKGABE = new ArrayList<String>();
	private ArrayList<String> OBJEKT = new ArrayList<String>();
	private ArrayList<String> DARSTELLER = new ArrayList<String>();
	private ArrayList<String> REGIE = new ArrayList<String>();
	private ArrayList<String> OBJ_DARSTELLER = new ArrayList<String>();
	private ArrayList<String> FILM_REGIE = new ArrayList<String>();

	private String tblKunde = "kunde";
	private String tblAdresse = "adresse";
	private String tblverleih = "verleih";
	private String tblObjekte = "objekt";
	private String tblDarsteller = "darsteller";
	private String tblObjektDarsteller = "objekt_darsteller";
	private String tblRegie = "regie";
	private String tblFilmRegie = "film_regie";

	private dvdverleih.db.DB_Verbindung db = new dvdverleih.db.DB_Verbindung();

	public KKonto() {

		KUNDE.add("vorname");
		KUNDE.add("nachname");
		KUNDE.add("geburtsdatum");
		KUNDE.add("adresse");
		KUNDE.add("telefon");
		KUNDE.add("personalausweisnummer");

		ADRESSE.add("strasse");
		ADRESSE.add("hausnummer");
		ADRESSE.add("wohnort");
		ADRESSE.add("plz");

		RUECKGABE.add("id");
		RUECKGABE.add("objekt_id");
		RUECKGABE.add("anzahl");
		RUECKGABE.add("ausleihdatum");

		VERLEIH.add("kunde_id");
		VERLEIH.add("objekt_id");
		VERLEIH.add("ausleihdatum");
		VERLEIH.add("anzahl");
		VERLEIH.add("verliehen");

		OBJEKT.add("art");
		OBJEKT.add("genre");
		OBJEKT.add("format");
		OBJEKT.add("titel");
		OBJEKT.add("hersteller");
		OBJEKT.add("produktionsjahr");
		OBJEKT.add("fsk");
		OBJEKT.add("originaltitel");
		OBJEKT.add("preis");
		OBJEKT.add("gesamtAnzahl");
		OBJEKT.add("verfuegbareAnzahl");
		OBJEKT.add("beschreibung");

		DARSTELLER.add("d_vorname");
		DARSTELLER.add("d_nachname");

		REGIE.add("r_vorname");
		REGIE.add("r_nachname");

		OBJ_DARSTELLER.add("objekt_id");
		OBJ_DARSTELLER.add("darsteller_id");

		FILM_REGIE.add("objekt_id");
		FILM_REGIE.add("regie_id");
	}

	public String[][] ausleihobjekte(String id) {

		ArrayList<String> ergebnis = new ArrayList<String>();
		ArrayList<String> objekt = new ArrayList<String>();
		String[][] rturn = null;
		String[][] rueckgabe = null;
		Date heutigesDatum = new Date();
		SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String datumHeute = myDateFormat.format(heutigesDatum);

		if (Pruefung.pInt(id)) {
			ArrayList<String> idSpalte = new ArrayList<String>();
			idSpalte.add("kunde_id");
			idSpalte.add("verliehen");
			ArrayList<String> idInhalt = new ArrayList<String>();
			idInhalt.add(id);
			idInhalt.add("1");
			ergebnis = db.select(RUECKGABE, tblverleih, idSpalte, idInhalt);

			// VERLEIH.add("id");
			// VERLEIH.add("id_objekt");
			// VERLEIH.add("anzahl");
			// VERLEIH.add("ausleihdatum");

			// objekttitel+grundpreis hinzfügen
			ArrayList<String> gesuchtes = new ArrayList<String>();
			gesuchtes.add("titel");
			gesuchtes.add("preis");
			ArrayList<String> spalte = new ArrayList<String>();
			spalte.add("id");
			ArrayList<String> inhalt;

			// Titel, Anzahl, Tage, Grundpreis, VerleihID

			rturn = new String[ergebnis.size() / 4][4];
			rueckgabe = new String[ergebnis.size() / 4][5];
			for (int i = 0; i < (ergebnis.size() / 4); i++) {
				rturn[i][0] = ergebnis.get(4 * i);
				rturn[i][1] = ergebnis.get((4 * i) + 1);
				rturn[i][2] = ergebnis.get((4 * i) + 2);
				rturn[i][3] = ergebnis.get((4 * i) + 3);
				rueckgabe[i][1] = rturn[i][2];
				rueckgabe[i][4] = rturn[i][0];

				inhalt = new ArrayList<String>();
				inhalt.add(rturn[i][1]);

				objekt = db.select(gesuchtes, tblObjekte, spalte, inhalt);
				rueckgabe[i][0] = objekt.get(0);
				rueckgabe[i][3] = objekt.get(1);

				long tage = java.time.temporal.ChronoUnit.DAYS.between(
						java.time.LocalDate.parse(rturn[i][3]), java.time.LocalDate.now());
				if (tage == 0) {
					tage = 1;
				}

				rueckgabe[i][2] = String.valueOf(tage);

			}
		} else
			return null;

		if (ergebnis.size() > 0)
			return rueckgabe;
		else
			return null;
	}

	public boolean Darstelleranlegen(ArrayList<String> darsteller) {
		// Pruefung ob Datenfelder korrekt ausgefuellt worden sind
		if (!(Pruefung.pStr(darsteller.get(0)) || Pruefung.pStr(darsteller
				.get(1))))
			return false;
		else {
			db.insert(tblDarsteller, DARSTELLER, darsteller);
			return true;
		}
	}

	public void DB_schliessen() {
		db.close();
	}

	public String id2name(String id) {
		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("nachname");
		gesuch.add("vorname");
		ArrayList<String> idSpalte = new ArrayList<String>();
		idSpalte.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> ergebnis = db.select(gesuch, tblKunde, idSpalte,
				idInhalt);
		String rturn = ergebnis.get(1);
		rturn += " ";
		rturn += ergebnis.get(0);
		return rturn;
	}

	public String id2ObjektName(String id) {
		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("titel");
		ArrayList<String> idSpalte = new ArrayList<String>();
		idSpalte.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> ergebnis = db.select(gesuch, tblObjekte, idSpalte,
				idInhalt);
		String rturn = ergebnis.get(0);
		if (rturn.length() <= 13)
			return rturn;
		else {
			rturn = rturn.substring(0, 10);
			rturn += "...";
			return rturn;
		}
	}

	public boolean KKaendern(ArrayList<String> name, ArrayList<String> inhalt,
			String id) {
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("adresse");
		for (String i : inhalt) {
			if (!Pruefung.pStr(i))
				return false;
		}
		for (int i = 0; i < name.size(); i++) {
			if (name.get(i).equals("personalausweisnummer")) {
				if (Pruefung.perso(inhalt.get(i)) == null)
					return false;
			}
			if (name.get(i).equals("telefon")) {
				if (!Pruefung.pTele(inhalt.get(i)))
					return false;
			}
			if (name.get(i).equals("geburtsdatum")) {
				if (!Pruefung.pDate(inhalt.get(i)))
					return false;
			}
			if (name.get(i).equals("plz")) {
				if (!Pruefung.pInt(inhalt.get(i)))
					return false;
			}

		}
		String adress_id = db.select(gesuch, tblKunde, idList, idInhalt).get(0);
		for (int i = 0; i < name.size(); i++) {
			if (KUNDE.contains(name.get(i))) {
				db.update(tblKunde, name.get(i), inhalt.get(i), "id", id);
			} else if (ADRESSE.contains(name.get(i))) {
				db.update(tblAdresse, name.get(i), inhalt.get(i), "id",
						adress_id);
			}
		}

		return true;
	}

	public String KKanlegen(String vname, String nname, String geb,
			String strasse, String hnr, String ort, String plz, String tele,
			String pnr) {
		// Pruefung ob Datenfelder korrekt ausgefuellt worden sind
		if (!(Pruefung.pStr(vname) || Pruefung.pStr(nname)
				|| Pruefung.pStr(strasse) || Pruefung.pStr(hnr) || Pruefung
					.pStr(ort)))
			return null;
		else if (!(Pruefung.pDate(geb) || Pruefung.pInt(plz) || Pruefung
				.pTele(tele)))
			return null;
		pnr = Pruefung.perso(pnr);
		if (pnr == null)
			return null;

		// Eintragen der Adresse
		ArrayList<String> adresse = new ArrayList<String>();
		adresse.add(strasse);
		adresse.add(hnr);
		adresse.add(ort);
		adresse.add(plz);

		db.insert(tblAdresse, ADRESSE, adresse);
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("id");
		ArrayList<String> id = db.select(idList, tblAdresse, ADRESSE, adresse);

		// Eintragen der weiteren Kundendaten
		ArrayList<String> kdaten = new ArrayList<String>();
		kdaten.add(vname);
		kdaten.add(nname);
		kdaten.add(geb);
		kdaten.add(id.get(0));
		kdaten.add(tele);
		kdaten.add(pnr);
		db.insert(tblKunde, KUNDE, kdaten);
		ArrayList<String> id_neu = db.select(idList, tblKunde, KUNDE, kdaten);
		return id_neu.get(0);
	}

	public ArrayList<String> KKinfo(String id) {
		dvdverleih.datenhaltung.Kunde kunde = new dvdverleih.datenhaltung.Kunde();
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> info = db.select(KUNDE, tblKunde, idList, idInhalt);
		info.add(id);
		idInhalt.set(0, info.get(3));
		ArrayList<String> adr = db
				.select(ADRESSE, tblAdresse, idList, idInhalt);
		for (String i : adr) {
			info.add(i);
		}
		kunde.setVname(info.get(0));
		return info;
		/*
		 * info_INHALT: KUNDE.add("vorname"); KUNDE.add("nachname");
		 * KUNDE.add("geburtsdatum"); KUNDE.add("adresse");
		 * KUNDE.add("telefon"); KUNDE.add("personalausweisnummer");
		 * kundennummer ADRESSE.add("strasse"); ADRESSE.add("hausnummer");
		 * ADRESSE.add("wohnort"); ADRESSE.add("plz");
		 */
	}

	public void KKloeschen(String id) {
		// Pruefung
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> idSpalte = new ArrayList<String>();
		idSpalte.add("id");
		ArrayList<String> adresse = new ArrayList<String>();
		adresse.add("adresse");
		ArrayList<String> adresse_id = db.select(adresse, tblKunde, idSpalte,
				idInhalt);
		db.delete(tblAdresse, idSpalte, adresse_id);
		db.delete(tblKunde, idSpalte, idInhalt);

	}

	public void leihen(String id_kunde, String id_objekt, String anzahl) {
		Date heutigesDatum = new Date();
		SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String datumHeute = myDateFormat.format(heutigesDatum);

		ArrayList<String> inhalt = new ArrayList<String>();
		inhalt.add(id_kunde);
		inhalt.add(id_objekt);
		inhalt.add(datumHeute);
		inhalt.add(anzahl);
		inhalt.add("1");

		// VERLEIH.add("kunde_id");
		// VERLEIH.add("objekt_id");
		// VERLEIH.add("ausleihdatum");
		// VERLEIH.add("anzahl");
		// VERLEIH.add("verliehen");

		db.insert(tblverleih, VERLEIH, inhalt);
	}

	public String name2id(String name) {
		ArrayList<String> vergleichSpalte = new ArrayList<String>();
		vergleichSpalte.add("vorname");
		vergleichSpalte.add("nachname");
		ArrayList<String> vergleichInhalt = new ArrayList<String>();
		int leerzeichen = name.indexOf(" ");
		vergleichInhalt.add(name.substring(0, leerzeichen));
		vergleichInhalt.add(name.substring(leerzeichen + 1));
		vergleichSpalte.add("nachname");
		vergleichSpalte.add("vorname");
		ArrayList<String> idSpalte = new ArrayList<String>();
		idSpalte.add("id");
		ArrayList<String> ergebnis = db.select(idSpalte, tblKunde,
				vergleichSpalte, vergleichInhalt);
		String rturn = ergebnis.get(0);
		return rturn;
	}

	public void Objektaendern(ArrayList<String> name, ArrayList<String> inhalt,
			String id) {
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		for (int i = 0; i < name.size(); i++) {
			db.update(tblObjekte, name.get(i), inhalt.get(i), "id", id);
		}
	}

	public String ObjektAnlegen(String kategorie, String genre, String format,
			String titel, String hersteller, String anzahl, String preis,
			String jahr, String fsk, String originaltitel, String tags,
			String beschreibung, ArrayList<String> darsteller,
			ArrayList<String> regie) {

		// Pruefung ob Datenfelder korrekt ausgefuellt worden sind
		if (!(Pruefung.pStr(kategorie) || (Pruefung.pStr(genre))
				|| (Pruefung.pStr(format)) || (Pruefung.pStr(preis))
				|| (Pruefung.pStr(originaltitel)) || (Pruefung.pStr(tags)) || (Pruefung
					.pStr(beschreibung))))
			return null;
		else if (!(Pruefung.pInt(jahr) || Pruefung.pInt(fsk)))
			return null;
		else {
			// Eintragen des neuen Objektes
			ArrayList<String> objektdaten = new ArrayList<String>();
			objektdaten.add(kategorie);
			if (kategorie.equals("Zubehoer")) {
				objektdaten.add(kategorie + "_" + format);
				objektdaten.add(genre);
			} else {
				objektdaten.add(kategorie + "_" + genre);
				objektdaten.add(format);
			}

			objektdaten.add(titel);
			objektdaten.add(hersteller);
			objektdaten.add(jahr);
			objektdaten.add(fsk);
			objektdaten.add(originaltitel);
			objektdaten.add(preis);
			objektdaten.add(anzahl);
			objektdaten.add(anzahl);
			objektdaten.add(beschreibung);
			objektdaten.add(tags);
			ArrayList<String> objekt_neu = new ArrayList<String>();
			objekt_neu.addAll(OBJEKT);
			objekt_neu.add("tags");
			// neues Objekt eintragen

			db.insert(tblObjekte, objekt_neu, objektdaten);
			ArrayList<String> idList = new ArrayList<String>();
			idList.add("id");
			// Id des aktuellen Objektes abfragen (für tabelle
			// objekt-darsteller)
			ArrayList<String> objektID = db.select(idList, tblObjekte,
					objekt_neu, objektdaten);
			// Eintragen der Darsteller
			ArrayList<String> objektDarsteller;
			for (String i : darsteller) {
				objektDarsteller = new ArrayList<String>();
				objektDarsteller.add(objektID.get(0));
				objektDarsteller.add(i);
				db.insert(tblObjektDarsteller, OBJ_DARSTELLER, objektDarsteller);
			}
			// Eintragen der Regie
			ArrayList<String> filmRegie;
			for (String i : regie) {
				filmRegie = new ArrayList<String>();
				filmRegie.add(objektID.get(0));
				filmRegie.add(i);
				db.insert(tblFilmRegie, FILM_REGIE, filmRegie);
			}
			return objektID.get(0);
		}
	}

	public ArrayList<String> Objektinfo(String id) {
		new dvdverleih.datenhaltung.Objekt();
		ArrayList<String> idList = new ArrayList<String>();
		idList.add("id");
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> info = db.selectObjekt(OBJEKT, idList, idInhalt);
		info.add(id);

		return info;

		// OBJEKT.add("art");
		// OBJEKT.add("genre");
		// OBJEKT.add("format");
		// OBJEKT.add("titel");
		// OBJEKT.add("produktionsjahr");
		// OBJEKT.add("fsk");
		// OBJEKT.add("originaltitel");
		// OBJEKT.add("preis");
		// OBJEKT.add("gesamtAnzahl");
		// OBJEKT.add("verfuegbareAnzahl");

	}

	public void Objektloeschen(String id) {
		// Pruefung
		ArrayList<String> idInhalt = new ArrayList<String>();
		idInhalt.add(id);
		ArrayList<String> idSpalte = new ArrayList<String>();
		idSpalte.add("id");
		db.delete(tblObjekte, idSpalte, idInhalt);
	}

	public boolean Regieanlegen(ArrayList<String> regie) {
		// Pruefung ob Datenfelder korrekt ausgefuellt worden sind
		if (!(Pruefung.pStr(regie.get(0)) || Pruefung.pStr(regie.get(1))))
			return false;
		else {
			db.insert(tblRegie, REGIE, regie);
			return true;
		}
	}

	/*
	 * Funktion: Rückgabe eines Objektes. Übergabe: id der Tabelle 'verleih'
	 * Update: Spalte 'verliehen' erhält Status '0'
	 */
	public boolean rueckgabe(String id) {

		if (Pruefung.pInt(id)) {
			boolean rueckgabe = db.update(tblverleih, "verliehen", "0", "id",
					id);
			if (rueckgabe)
				return true;
			else
				return false;
		} else
			return false;
	}

	public String[][] suche(String suchbegriff, String kategorie,
			String format, int fskVon, int fskBis, int jahrVon, int jahrBis,
			ArrayList<String> genre, ArrayList<String> parameter) {
		if (kategorie.equals("Film")) {
			String[][] info = db.selectFilm(parameter, suchbegriff, genre,
					format, fskVon, fskBis, jahrVon, jahrBis);
			return info;
		} else if (kategorie.equals("Spiel")) {
			String[][] info = db.selectSpiel(parameter, suchbegriff, genre,
					format, fskVon, fskBis, jahrVon, jahrBis);
			return info;
		} else {
			String[][] info = db.selectZubehoer(parameter, suchbegriff, genre,
					format);
			return info;
		}

	}

	public String[][] suche_Darsteller(String such) {
		// Pruefung, ob es sich um eine Kundennummer handelt:

		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("id");
		gesuch.add("d_nachname");
		gesuch.add("d_vorname");
		ArrayList<String> ergebnis = new ArrayList<String>();
		String[][] rturn = null;

		if (Pruefung.pInt(such)) {
			ArrayList<String> idSpalte = new ArrayList<String>();
			idSpalte.add("id");
			ArrayList<String> idInhalt = new ArrayList<String>();
			idInhalt.add(such);
			ergebnis = db.select(gesuch, tblDarsteller, idSpalte, idInhalt);

			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		} else if (Pruefung.pStr(such)) {
			ArrayList<String> textSpalte1 = new ArrayList<String>();
			textSpalte1.add("d_nachname");
			ArrayList<String> textSpalte2 = new ArrayList<String>();
			textSpalte2.add("d_vorname");
			ArrayList<String> textInhalt = new ArrayList<String>();
			textInhalt.add(such);
			ergebnis = db
					.select(gesuch, tblDarsteller, textSpalte1, textInhalt);
			ArrayList<String> ergebnis2 = db.select(gesuch, tblDarsteller,
					textSpalte2, textInhalt);

			for (String i : ergebnis2) {
				ergebnis.add(i);
			}
			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		}

		if (ergebnis.size() > 0)
			return rturn;
		else
			return null;
	}

	public String[][] suche_Hauptseite(String such) {
		// Pruefung, ob es sich um eine Kundennummer handelt:

		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("id");
		gesuch.add("nachname");
		gesuch.add("vorname");
		ArrayList<String> ergebnis = new ArrayList<String>();
		String[][] rturn = null;

		if (Pruefung.pInt(such)) {
			ArrayList<String> idSpalte = new ArrayList<String>();
			idSpalte.add("id");
			ArrayList<String> idInhalt = new ArrayList<String>();
			idInhalt.add(such);
			ergebnis = db.select(gesuch, tblKunde, idSpalte, idInhalt);

			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		} else if (Pruefung.pStr(such)) {
			ArrayList<String> textSpalte1 = new ArrayList<String>();
			textSpalte1.add("nachname");
			ArrayList<String> textSpalte2 = new ArrayList<String>();
			textSpalte2.add("vorname");
			ArrayList<String> textInhalt = new ArrayList<String>();
			textInhalt.add(such);
			ergebnis = db.select(gesuch, tblKunde, textSpalte1, textInhalt);
			ArrayList<String> ergebnis2 = db.select(gesuch, tblKunde,
					textSpalte2, textInhalt);

			for (String i : ergebnis2) {
				ergebnis.add(i);
			}
			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		}

		if (ergebnis.size() > 0)
			return rturn;
		else
			return null;
	}

	public String[][] suche_Regie(String such) {
		// Pruefung, ob es sich um eine Kundennummer handelt:

		ArrayList<String> gesuch = new ArrayList<String>();
		gesuch.add("id");
		gesuch.add("r_nachname");
		gesuch.add("r_vorname");
		ArrayList<String> ergebnis = new ArrayList<String>();
		String[][] rturn = null;

		if (Pruefung.pInt(such)) {
			ArrayList<String> idSpalte = new ArrayList<String>();
			idSpalte.add("id");
			ArrayList<String> idInhalt = new ArrayList<String>();
			idInhalt.add(such);
			ergebnis = db.select(gesuch, tblRegie, idSpalte, idInhalt);

			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		} else if (Pruefung.pStr(such)) {
			ArrayList<String> textSpalte1 = new ArrayList<String>();
			textSpalte1.add("r_nachname");
			ArrayList<String> textSpalte2 = new ArrayList<String>();
			textSpalte2.add("r_vorname");
			ArrayList<String> textInhalt = new ArrayList<String>();
			textInhalt.add(such);
			ergebnis = db.select(gesuch, tblRegie, textSpalte1, textInhalt);
			ArrayList<String> ergebnis2 = db.select(gesuch, tblRegie,
					textSpalte2, textInhalt);

			for (String i : ergebnis2) {
				ergebnis.add(i);
			}
			rturn = new String[ergebnis.size() / 3][3];
			for (int i = 0; i < (ergebnis.size() / 3); i++) {
				rturn[i][0] = ergebnis.get(3 * i);
				rturn[i][1] = ergebnis.get((3 * i) + 1);
				rturn[i][2] = ergebnis.get((3 * i) + 2);
			}
		}

		if (ergebnis.size() > 0)
			return rturn;
		else
			return null;
	}

}