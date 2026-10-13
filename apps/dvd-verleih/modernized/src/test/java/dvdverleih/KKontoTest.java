package dvdverleih;

import static org.junit.jupiter.api.Assertions.*;

import dvdverleih.funktionen.KKonto;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class KKontoTest {

	@Test
	void customerLendReturn(@TempDir Path dir) {
		System.setProperty("dvdverleih.db", dir.resolve("t.db").toString());
		KKonto kk = new KKonto();
		String kunde = kk.KKanlegen("Max", "Muster", "1990-01-31", "Weg", "1", "Berlin", "10115", "030123456",
				"9704631642920117614010280");
		assertNotNull(kunde);
		assertEquals("Max", kk.KKinfo(kunde).get(0));

		String objekt = kk.ObjektAnlegen("Film", "Action", "DVD", "Heat", "WB", "3", "2.5", "1995", "16",
				"Heat", "crime", "Cops", new ArrayList<>(List.of()), new ArrayList<>(List.of()));
		assertNotNull(objekt);
		kk.leihen(kunde, objekt, "1");
		String[][] leih = kk.ausleihobjekte(kunde);
		assertEquals(1, leih.length);
		assertEquals("Heat", leih[0][0]);
		assertEquals("1", leih[0][2]); // same day counts as one day

		assertTrue(kk.rueckgabe(leih[0][4]));
		assertNull(kk.ausleihobjekte(kunde)); // null = nothing lent
		assertEquals(1, kk.suche("heat", "Film", "alle_Formate", 0, 4, 0, 9, new ArrayList<>(), new ArrayList<>(List.of("Titel"))).length);
	}
}
