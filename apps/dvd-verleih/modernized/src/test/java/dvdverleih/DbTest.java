package dvdverleih;

import static org.junit.jupiter.api.Assertions.*;

import dvdverleih.db.DB_Verbindung;
import dvdverleih.funktionen.Pruefung;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DbTest {

	private static ArrayList<String> l(String... s) {
		return new ArrayList<>(List.of(s));
	}

	private static final ArrayList<String> OBJ = l("art", "genre", "format", "titel", "fsk", "produktionsjahr");

	private DB_Verbindung db() {
		DB_Verbindung db = new DB_Verbindung("jdbc:sqlite::memory:");
		db.insert("objekt", OBJ, l("Film", "Film_Komödie", "DVD", "O'Brien; DROP TABLE objekt", "12", "1995"));
		db.insert("objekt", OBJ, l("Spiel", "Spiel_Action", "PC", "Doom", "18", "2005"));
		db.insert("objekt", OBJ, l("Film", "Film_Action", "BluRay", "Heat", "16", "1999"));
		return db;
	}

	@Test
	void insertAndSelectUseParameters() {
		DB_Verbindung db = db();
		assertEquals(l("Doom"), db.select(l("titel"), "objekt", l("art", "format"), l("Spiel", "PC")));
		assertEquals(l("O'Brien; DROP TABLE objekt"), db.select(l("titel"), "objekt", l("fsk"), l("12")));
	}

	@Test
	void rejectsBadIdentifiers() {
		DB_Verbindung db = db();
		assertThrows(IllegalArgumentException.class,
				() -> db.select(l("titel"), "objekt; DROP TABLE objekt", l("id"), l("1")));
	}

	@Test
	void updateAndDelete() {
		DB_Verbindung db = db();
		db.update("objekt", "titel", "Doom 3", "titel", "Doom");
		assertEquals(l("Doom 3"), db.select(l("titel"), "objekt", l("art"), l("Spiel")));
		db.delete("objekt", l("art"), l("Spiel"));
		assertTrue(db.select(l("titel"), "objekt", l("art"), l("Spiel")).isEmpty());
	}

	@Test
	void searchFiltersGenreFormatFskYear() {
		DB_Verbindung db = db();
		// film without actors/directors is found, LIKE on title
		assertEquals(2, db.selectFilm(l("Titel"), "", l(), "alle_Formate", 0, 4, 0, 9).length);
		assertEquals(1, db.selectFilm(l("Titel"), "heat", l("Action"), "BluRay", 0, 4, 0, 9).length);
		// FSK index 3 = 16 .. 4 = 18; year index 7 = 1990 .. 9 = 2010
		assertEquals(1, db.selectFilm(l("Titel"), "", l(), "alle_Formate", 3, 4, 7, 9).length);
		assertEquals(1, db.selectSpiel(l("Titel"), "doom", l(), "alle_Formate", 0, 4, 0, 9).length);
		assertEquals(0, db.selectZubehoer(l("Titel"), "doom", l(), "alle").length);
	}

	@Test
	void personalausweis() {
		assertNotNull(Pruefung.perso("9704631642920117614010280"));
		assertNull(Pruefung.perso("9704631642920117614010281"));
		assertTrue(Pruefung.pDate("2000-02-29"));
		assertFalse(Pruefung.pDate("2001-02-29"));
	}
}
