package funktionen;

import gui.NeuesSpiel;
import gui.Oberflaeche;
import gui.Regelwerk;
import gui.Spielbrett;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class Hauptseite {
	
	public static Oberflaeche oberflaeche;
	public static NeuesSpiel neuesSpiel;
	public static Spielbrett spielbrett;
	public static Regelwerk regelwerk;
	public static Wurf wurf;
	private static Kartenspiel kartenspiel;
	public static int anzSpieler, sicher, aktuellePunkte, rundePunkte;
	public static int[] results, gesichert, aktWurf;
	public static boolean wuerfel1, wuerfel2, wuerfel3, wuerfel4, wuerfel5, wuerfel6;
	public static String aktuellerSpieler;
	public static Spieler spieler1, spieler2, spieler3, spieler4, spieler5;
	public static String karte;
	
	public static void main(String[] args) {
		oberflaeche = new Oberflaeche();
		
		// Neues Spielbrett erstellen
		oberflaeche.newGame();
		
		// MouseAdapter für Schließen
		MouseAdapter maSchliessen = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		};
		oberflaeche.setListenerSchliessen(maSchliessen);
		
		// MouseAdapter für NeuesSpiel
		MouseAdapter maNeuesSpiel = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				neuesSpiel();
			}
		};
		oberflaeche.setListenerNeuesSpiel(maNeuesSpiel);
		
		// MouseAdapter für Laden
		MouseAdapter maLaden = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Alle Daten aus einer Datei laden
			}
		};
		oberflaeche.setListenerLaden(maLaden);
		
		// MouseAdapter für Regelwerk anzeigen
		MouseAdapter maRegelwerk = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				zeigeRegelwerk();
			}
		};
		oberflaeche.setListenerRegelwerk(maRegelwerk);
	}

	// REGELN ANZEIGEN
	protected static void zeigeRegelwerk(){
		regelwerk = new Regelwerk();
		oberflaeche.setRules();
		System.out.println("Regelfenster anzeigen");
	}
	
	// NEUES SPIEL STARTEN
	protected static void neuesSpiel() {
		neuesSpiel = new NeuesSpiel();
		anzSpieler = neuesSpiel.getSpieler();
		// MouseAdapter für Spielerzahl erhöhen
		MouseAdapter maSpielerErhoehen = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (anzSpieler == 5){
					neuesSpiel.setErrortext("Die maximale Anzahl an Spielern ist erreicht!");
				}
				else{
					anzSpieler++;
					neuesSpiel.setErrortext("");
					neuesSpiel.setSpieler(anzSpieler);
					if (anzSpieler == 3){
						neuesSpiel.enableTxtSpieler3(true);
						neuesSpiel.setTxtSpieler3BGWhite();
					}
					else if (anzSpieler == 4){
						neuesSpiel.enableTxtSpieler4(true);
						neuesSpiel.setTxtSpieler4BGWhite();
					}
					else if (anzSpieler == 5){
						neuesSpiel.enableTxtSpieler5(true);
						neuesSpiel.setTxtSpieler5BGWhite();
					}
				}
			}
		};
		neuesSpiel.setListenerMehrSpieler(maSpielerErhoehen);
		
		// MouseAdapter für Spielerzahl verringern
		MouseAdapter maSpielerVerringern = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (anzSpieler == 2){
					neuesSpiel.setErrortext("Die minimale Anzahl an Spielern ist erreicht!");
				}
				else{
					anzSpieler--;
					neuesSpiel.setErrortext("");
					neuesSpiel.setSpieler(anzSpieler);
					if (anzSpieler == 2){
						neuesSpiel.enableTxtSpieler3(false);
						neuesSpiel.setTxtSpieler3BGGray();
					}
					else if (anzSpieler == 3){
						neuesSpiel.enableTxtSpieler4(false);
						neuesSpiel.setTxtSpieler4BGGray();
					}
					else if (anzSpieler == 4){
						neuesSpiel.enableTxtSpieler5(false);
						neuesSpiel.setTxtSpieler5BGGray();
					}
				}
			}
		};
		neuesSpiel.setListenerWenigerSpieler(maSpielerVerringern);
		
		// MouseAdapter für Abbrechen
		MouseAdapter maAbbrechen = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Fenster wird geschlossen!");
				neuesSpiel.frame.dispose();
			}
		};
		neuesSpiel.setListenerAbbrechen(maAbbrechen);
		
		// MouseAdapter für Neues Spiel
		MouseAdapter maNeuesSpiel = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (neuesSpiel.getSpieler() == 2){
					if (neuesSpiel.getTxtSpieler1().length() == 0 || neuesSpiel.getTxtSpieler2().length() == 0){
						neuesSpiel.setErrortextNamen("Bitte alle Felder ausfüllen!");
					}
					else{
						starteNeuesSpiel();
					}
				}
				if (neuesSpiel.getSpieler() == 3){
					if (neuesSpiel.getTxtSpieler1().length() == 0 || neuesSpiel.getTxtSpieler2().length() == 0 || neuesSpiel.getTxtSpieler3().length() == 0){
						neuesSpiel.setErrortextNamen("Bitte alle Felder ausfüllen!");
					}
					else{
						starteNeuesSpiel();
					}
				}
				if (neuesSpiel.getSpieler() == 4){
					if (neuesSpiel.getTxtSpieler1().length() == 0 || neuesSpiel.getTxtSpieler2().length() == 0 || neuesSpiel.getTxtSpieler3().length() == 0 || neuesSpiel.getTxtSpieler4().length() == 0){
						neuesSpiel.setErrortextNamen("Bitte alle Felder ausfüllen!");
					}
					else{
						starteNeuesSpiel();
					}
				}
				if (neuesSpiel.getSpieler() == 5){
					if (neuesSpiel.getTxtSpieler1().length() == 0 || neuesSpiel.getTxtSpieler2().length() == 0 || neuesSpiel.getTxtSpieler3().length() == 0 || neuesSpiel.getTxtSpieler4().length() == 0 || neuesSpiel.getTxtSpieler5().length() == 0){
						neuesSpiel.setErrortextNamen("Bitte alle Felder ausfüllen!");
					}
					else{
						starteNeuesSpiel();
					}
				}
			}

			private void starteNeuesSpiel() {
				neuesSpiel.frame.dispose();
				System.out.println("-------EIN NEUES SPIEL WIRD GESTARTET-------");
				neuesSpielbrett();
			}
		};
		neuesSpiel.setListenerNeuesSpiel(maNeuesSpiel);
	}
	
	// NEUES SPIELBRETT ERSTELLEN
	protected static void neuesSpielbrett() {
		
		// Spielernamen abfragen
		int anzahlSpieler = neuesSpiel.getSpieler();
		
		String[] spieler = new String[anzahlSpieler];
		
		spieler[0] = neuesSpiel.getTxtSpieler1();
		spieler1 = new Spieler(spieler[0], 0);
		spieler[1] = neuesSpiel.getTxtSpieler2();
		spieler2 = new Spieler(spieler[1], 0);
		
		if (anzahlSpieler >= 3){
			spieler[2] = neuesSpiel.getTxtSpieler3();
			spieler3 = new Spieler(spieler[2], 0);
			if (anzahlSpieler >= 4){
				spieler[3] = neuesSpiel.getTxtSpieler4();
				spieler4 = new Spieler(spieler[3], 0);
				if (anzahlSpieler == 5){
					spieler[4] = neuesSpiel.getTxtSpieler5();
					spieler5 = new Spieler(spieler[4], 0);
				}
			}
		}
		
		for (int i=0; i<anzahlSpieler; i++){
			System.out.println("Spieler " + (i*1+1) + ": " + spieler[i]);
		}
		
		wuerfel1 = true;
		wuerfel2 = true;
		wuerfel3 = true;
		wuerfel4 = true;
		wuerfel5 = true;
		wuerfel6 = true;
		
		spielbrett = new Spielbrett(spieler, anzahlSpieler);
		spielbrett.setTxtStatus(spieler[0] + " ist an der Reihe");
		
		// Buttons verstecken
		spielbrett.btnWuerfeln.setVisible(false);
		spielbrett.btnSchreiben.setVisible(false);
		
		kartenspiel = new Kartenspiel();
		System.out.println("");
		System.out.println("-------EIN NEUES KARTENSPIEL WURDE ERSTELLT-------");
		System.out.println("Kartenanzahl: " + kartenspiel.kartenAnzahl());
		
		aktuellePunkte = 0;
		rundePunkte = 0;
		
		/*
	    int kartenImSpiel = kartenspiel.kartenAnzahl();
	    for (int i=0; i<kartenImSpiel; i++){
	    	System.out.println("Karte" + (i*1+1) + ": " + kartenspiel.naechsteKarte().karteToString());
	    }
	    */
		
		// MouseAdapter für Spiel starten
	    MouseAdapter maStart = new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		// TODO: Bei Klick auf "Spiel starten" Aktion ausführen
	    		System.out.println("Spiel starten geklickt");
	    		spielstart();
	    	}
		};
		spielbrett.setListenerStart(maStart);
		
		// MouseAdapter für Schreiben
	    MouseAdapter maSchreiben = new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		// TODO: Bei Klick auf "Schreiben" Aktion ausführen
	    		System.out.println("Schreiben geklickt");
	    		schreiben();
	    	}
		};
		spielbrett.setListenerSchreiben(maSchreiben);
		
	    // MouseAdapter für Button "würfeln"
	    MouseAdapter maWuerfeln = new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		// TODO: Bei Klick auf Würfeln Aktion ausführen
	    		System.out.println("Würfeln geklickt");
	    		wuerfeln();
	    		wurfAnzeigen();
	    	}
		};
		spielbrett.setListenerWuerfeln(maWuerfeln);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel1Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(1, wuerfel1);
				System.out.println("Sichern1 geklickt");
			}
		};
		spielbrett.setListenerSichern1(maWuerfel1Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel1NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel2Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(2, wuerfel2);
				System.out.println("Sichern2 geklickt");
			}
		};
		spielbrett.setListenerSichern2(maWuerfel2Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel2NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel3Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(3, wuerfel3);
				System.out.println("Sichern3 geklickt");
			}
		};
		spielbrett.setListenerSichern3(maWuerfel3Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel3NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel4Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(4, wuerfel4);
				System.out.println("Sichern4 geklickt");
			}
		};
		spielbrett.setListenerSichern4(maWuerfel4Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel4NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel5Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(5, wuerfel5);
				System.out.println("Sichern5 geklickt");
			}
		};
		spielbrett.setListenerSichern5(maWuerfel5Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel5NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel6Behalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "sichern" wird würfel grau hinterlegt und gewertet
				sichereWuerfel(6, wuerfel6);
				System.out.println("Sichern6 geklickt");
			}
		};
		spielbrett.setListenerSichern6(maWuerfel6Behalten);
		
		// MouseAdapter für Buttons der Würfel (sichern, nicht sichern)
		MouseAdapter maWuerfel6NichtBehalten = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO: Bei Klick auf "nicht sichern" wird graue hinterlegung des würfels entfernt und nicht gewertet
				System.out.println("");
			}
		};
		
		// MouseAdapter für Speichern
		MouseAdapter maSpeichern = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO: Spiel in einer Datei speichern
				System.out.println("Spiel speichern geklickt!");
			}
		};
		
		spielbrett.setListenerSpeichern(maSpeichern);
		// MouseAdapter für Schließen
		MouseAdapter maSchliessen = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);
			}
		};
		spielbrett.setListenerSchliessen(maSchliessen);
	}
	
	private static void spielstart(){
		// Buttons erscheinen lassen
		spielbrett.btnStart.setVisible(false);
		spielbrett.btnWuerfeln.setVisible(true);
		spielbrett.btnSchreiben.setVisible(true);
		
		// Neue Spielrunde
		neueRunde();
	}
	
	private static void wuerfeln(){
		
		if (spielbrett.btnWuerfeln.isEnabled()){
			
			// Gesicherte Punkte des aktuellen Wurfes
			sicher = 0;
			// Button "Schreiben" ausgrauen
			spielbrett.btnSchreiben.setEnabled(false);
			// Buttons "sichern" ausgrauen
			spielbrett.btnSichern1.setVisible(false);
			spielbrett.btnSichern2.setVisible(false);
			spielbrett.btnSichern3.setVisible(false);
			spielbrett.btnSichern4.setVisible(false);
			spielbrett.btnSichern5.setVisible(false);
			spielbrett.btnSichern6.setVisible(false);
			// zur Verfügung stehende Würfel
			int verfuegbareWuerfel = spielbrett.getVerfuegbareWuerfel();
			// Wenn es der erste Wurf der aktuellen Runde ist, wird ein neues Array mit den bisherigen gesicherten Würfeln erstellt
			if (spielbrett.anzahlWuerfe == 0){
				gesichert = new int[6];
				gesichert[0] = 0;
				gesichert[1] = 0;
				gesichert[2] = 0;
				gesichert[3] = 0;
				gesichert[4] = 0;
				gesichert[5] = 0;
			}
			
			aktWurf = new int[6];
			aktWurf[0] = 0;
			aktWurf[1] = 0;
			aktWurf[2] = 0;
			aktWurf[3] = 0;
			aktWurf[4] = 0;
			aktWurf[5] = 0;
			
			System.out.println("Verfügbare Würfel: " + verfuegbareWuerfel);
			
			// Aktuelle Punkte zu Rundenpunkten addieren
			rundePunkte += aktuellePunkte;
			
			// Neuen Wurf mit der verfübaren Anzahl an Würfeln werfen
			wurf = new Wurf(verfuegbareWuerfel);
			// Ergebnisse in einem Array speichern
			results = wurf.getResults();
			/*
			for(int i=0; i<verfuegbareWuerfel; i++){
				System.out.println("Würfel " + (i+1) + ": " + results[i]);
			}
			*/
		}
	}
	
	protected static void wurfAnzeigen(){
		// Button "Würfeln" ausgrauen
		spielbrett.setBtnWuerfelnDisabled(false);
		// Hilfsinteger für die Zuweisung der aktuellen Würfel zu deren Feldern
		int position = 0;
		// Zuweisung der aktuellen Würfel zu den noch offenen Feldern
		if(spielbrett.isWuerfel1Frei()){
			spielbrett.setLblWuerfel1(String.valueOf(results[position]));
			spielbrett.enableBtnSichern1();
			position++;
		}
		if (spielbrett.isWuerfel2Frei()){
			spielbrett.setLblWuerfel2(String.valueOf(results[position]));
			spielbrett.enableBtnSichern2();
			position++;
		}
		if (spielbrett.isWuerfel3Frei()){
			spielbrett.setLblWuerfel3(String.valueOf(results[position]));
			spielbrett.enableBtnSichern3();
			position++;
		}
		if (spielbrett.isWuerfel4Frei()){
			spielbrett.setLblWuerfel4(String.valueOf(results[position]));
			spielbrett.enableBtnSichern4();
			position++;
		}
		if (spielbrett.isWuerfel5Frei()){
			spielbrett.setLblWuerfel5(String.valueOf(results[position]));
			spielbrett.enableBtnSichern5();
			position++;
		}
		if (spielbrett.isWuerfel6Frei()){
			spielbrett.setLblWuerfel6(String.valueOf(results[position]));
			spielbrett.enableBtnSichern6();
			position++;
		}
		// Anzahl der Würfe erhöhen
		spielbrett.anzahlWuerfe++;
		// Anzahl der unterschiedlichen Würfel
		int einser = 0;
		int zweier = 0;
		int dreier = 0;
		int vierer = 0;
		int fuenfer = 0;
		int sechser = 0;
		// Einser zählen
			for (int i=0; i<position; i++){
				if (results[i] == 1){
					einser++;
				}
			}
		// Zweier zählen
		for (int i=0; i<position; i++){
			if (results[i] == 2){
				zweier++;
			}
		}
		// Dreier zählen
		for (int i=0; i<position; i++){
			if (results[i] == 3){
				dreier++;
			}
		}
		// Vierer zählen
		for (int i=0; i<position; i++){
			if (results[i] == 4){
				vierer++;
			}
		}
		// Zweier zählen
			for (int i=0; i<position; i++){
				if (results[i] == 5){
					fuenfer++;
				}
			}
		// Sechser zählen
		for (int i=0; i<position; i++){
			if (results[i] == 6){
				sechser++;
			}
		}
		
		System.out.println("");
		System.out.println("Würfel 1: " + spielbrett.lblWuerfel1.getText());
		System.out.println("Würfel 2: " + spielbrett.lblWuerfel2.getText());
		System.out.println("Würfel 3: " + spielbrett.lblWuerfel3.getText());
		System.out.println("Würfel 4: " + spielbrett.lblWuerfel4.getText());
		System.out.println("Würfel 5: " + spielbrett.lblWuerfel5.getText());
		System.out.println("Würfel 6: " + spielbrett.lblWuerfel6.getText());
		
		System.out.println("");
		System.out.println("Zweier: " + zweier);
		System.out.println("Dreier: " + dreier);
		System.out.println("Vierer: " + vierer);
		System.out.println("Sechser: " + sechser);
		
		// Wenn aktueller Wurf keine Punkte zeigt, erscheint ein Hinweis
		if (einser == 0 && zweier < 3 && dreier < 3 && vierer < 4 && fuenfer == 0 && sechser < 3){
			System.out.println("ENDE! Keine zählbare Punkte!");
			neueRunde();
		}
		
		// Wenn die Anzahl an einzelnen Würfel mindestens drei ist, erscheint unter jedem einzelnen der Button "sichern"
		if (zweier >= 3){
			if (spielbrett.lblWuerfel1.getText().equals("2"))
				spielbrett.btnSichern1.setVisible(true);
			if (spielbrett.lblWuerfel2.getText().equals("2"))
				spielbrett.btnSichern2.setVisible(true);
			if (spielbrett.lblWuerfel3.getText().equals("2"))
				spielbrett.btnSichern3.setVisible(true);
			if (spielbrett.lblWuerfel4.getText().equals("2"))
				spielbrett.btnSichern4.setVisible(true);
			if (spielbrett.lblWuerfel5.getText().equals("2"))
				spielbrett.btnSichern5.setVisible(true);
			if (spielbrett.lblWuerfel6.getText().equals("2"))
				spielbrett.btnSichern6.setVisible(true);
		}
		if (dreier >= 3){
			if (spielbrett.lblWuerfel1.getText().equals("3"))
				spielbrett.btnSichern1.setVisible(true);
			if (spielbrett.lblWuerfel2.getText().equals("3"))
				spielbrett.btnSichern2.setVisible(true);
			if (spielbrett.lblWuerfel3.getText().equals("3"))
				spielbrett.btnSichern3.setVisible(true);
			if (spielbrett.lblWuerfel4.getText().equals("3"))
				spielbrett.btnSichern4.setVisible(true);
			if (spielbrett.lblWuerfel5.getText().equals("3"))
				spielbrett.btnSichern5.setVisible(true);
			if (spielbrett.lblWuerfel6.getText().equals("3"))
				spielbrett.btnSichern6.setVisible(true);
		}
		if (vierer >= 3){
			if (spielbrett.lblWuerfel1.getText().equals("4"))
				spielbrett.btnSichern1.setVisible(true);
			if (spielbrett.lblWuerfel2.getText().equals("4"))
				spielbrett.btnSichern2.setVisible(true);
			if (spielbrett.lblWuerfel3.getText().equals("4"))
				spielbrett.btnSichern3.setVisible(true);
			if (spielbrett.lblWuerfel4.getText().equals("4"))
				spielbrett.btnSichern4.setVisible(true);
			if (spielbrett.lblWuerfel5.getText().equals("4"))
				spielbrett.btnSichern5.setVisible(true);
			if (spielbrett.lblWuerfel6.getText().equals("4"))
				spielbrett.btnSichern6.setVisible(true);
		}
		if (sechser >= 3){
			if (spielbrett.lblWuerfel1.getText().equals("6"))
				spielbrett.btnSichern1.setVisible(true);
			if (spielbrett.lblWuerfel2.getText().equals("6"))
				spielbrett.btnSichern2.setVisible(true);
			if (spielbrett.lblWuerfel3.getText().equals("6"))
				spielbrett.btnSichern3.setVisible(true);
			if (spielbrett.lblWuerfel4.getText().equals("6"))
				spielbrett.btnSichern4.setVisible(true);
			if (spielbrett.lblWuerfel5.getText().equals("6"))
				spielbrett.btnSichern5.setVisible(true);
			if (spielbrett.lblWuerfel6.getText().equals("6"))
				spielbrett.btnSichern6.setVisible(true);
		}
		// Anzeige der Buttons "sichern" unter jeder eins und fünf
		if (spielbrett.lblWuerfel1.getText().equals("1"))
			spielbrett.btnSichern1.setVisible(true);
		if (spielbrett.lblWuerfel2.getText().equals("1"))
			spielbrett.btnSichern2.setVisible(true);
		if (spielbrett.lblWuerfel3.getText().equals("1"))
			spielbrett.btnSichern3.setVisible(true);
		if (spielbrett.lblWuerfel4.getText().equals("1"))
			spielbrett.btnSichern4.setVisible(true);
		if (spielbrett.lblWuerfel5.getText().equals("1"))
			spielbrett.btnSichern5.setVisible(true);
		if (spielbrett.lblWuerfel6.getText().equals("1"))
			spielbrett.btnSichern6.setVisible(true);
		if (spielbrett.lblWuerfel1.getText().equals("5"))
			spielbrett.btnSichern1.setVisible(true);
		if (spielbrett.lblWuerfel2.getText().equals("5"))
			spielbrett.btnSichern2.setVisible(true);
		if (spielbrett.lblWuerfel3.getText().equals("5"))
			spielbrett.btnSichern3.setVisible(true);
		if (spielbrett.lblWuerfel4.getText().equals("5"))
			spielbrett.btnSichern4.setVisible(true);
		if (spielbrett.lblWuerfel5.getText().equals("5"))
			spielbrett.btnSichern5.setVisible(true);
		if (spielbrett.lblWuerfel6.getText().equals("5"))
			spielbrett.btnSichern6.setVisible(true);
	}
	
	protected static void sichereWuerfel (int wuerfel, boolean frei){
		// Hilfsinteger mit den verfügbaren Würfel
		int verfuegbareWuerfel = spielbrett.getVerfuegbareWuerfel();
		// Nach Klick auf "sichern" erscheint als Text "gesichert" und der Würfel wird Weiß hinterlegt
		if (frei){
			if (wuerfel == 1){
				spielbrett.setBtnSichern1("gesichert");
				spielbrett.lblWuerfel1.setBackground(Color.WHITE);
				spielbrett.setWuerfel1Frei(false);
				wuerfel1 = false;
				gesichert[0] = Integer.valueOf(spielbrett.lblWuerfel1.getText());
				aktWurf[0] = Integer.valueOf(spielbrett.lblWuerfel1.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
			else if (wuerfel == 2){
				spielbrett.setBtnSichern2("gesichert");
				spielbrett.lblWuerfel2.setBackground(Color.WHITE);
				spielbrett.setWuerfel2Frei(false);
				wuerfel2 = false;
				gesichert[1] = Integer.valueOf(spielbrett.lblWuerfel2.getText());
				aktWurf[1] = Integer.valueOf(spielbrett.lblWuerfel2.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
			else if (wuerfel == 3){
				spielbrett.setBtnSichern3("gesichert");
				spielbrett.lblWuerfel3.setBackground(Color.WHITE);
				spielbrett.setWuerfel3Frei(false);
				wuerfel3 = false;
				gesichert[2] = Integer.valueOf(spielbrett.lblWuerfel3.getText());
				aktWurf[2] = Integer.valueOf(spielbrett.lblWuerfel3.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
			else if (wuerfel == 4){
				spielbrett.setBtnSichern4("gesichert");
				spielbrett.lblWuerfel4.setBackground(Color.WHITE);
				spielbrett.setWuerfel4Frei(false);
				wuerfel4 = false;
				gesichert[3] = Integer.valueOf(spielbrett.lblWuerfel4.getText());
				aktWurf[3] = Integer.valueOf(spielbrett.lblWuerfel4.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
			else if (wuerfel == 5){
				spielbrett.setBtnSichern5("gesichert");
				spielbrett.lblWuerfel5.setBackground(Color.WHITE);
				spielbrett.setWuerfel5Frei(false);
				wuerfel5 = false;
				gesichert[4] = Integer.valueOf(spielbrett.lblWuerfel5.getText());
				aktWurf[4] = Integer.valueOf(spielbrett.lblWuerfel5.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
			else if (wuerfel == 6){
				spielbrett.setBtnSichern6("gesichert");
				spielbrett.lblWuerfel6.setBackground(Color.WHITE);
				spielbrett.setWuerfel6Frei(false);
				wuerfel6 = false;
				gesichert[5] = Integer.valueOf(spielbrett.lblWuerfel6.getText());
				aktWurf[5] = Integer.valueOf(spielbrett.lblWuerfel6.getText());
				sicher++;
				verfuegbareWuerfel--;
			}
		}
		else{
			// Text auf "verwenden" ändern und Label grau hinterlegen
			if (wuerfel == 1){
				spielbrett.setBtnSichern1("sichern");
				spielbrett.lblWuerfel1.setBackground(Color.CYAN);
				spielbrett.setWuerfel1Frei(true);
				wuerfel1 = true;
				gesichert[0] = 0;
				aktWurf[0] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
			else if (wuerfel == 2){
				spielbrett.setBtnSichern2("sichern");
				spielbrett.lblWuerfel2.setBackground(Color.CYAN);
				spielbrett.setWuerfel2Frei(true);
				wuerfel2 = true;
				gesichert[1] = 0;
				aktWurf[1] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
			else if (wuerfel == 3){
				spielbrett.setBtnSichern3("sichern");
				spielbrett.lblWuerfel3.setBackground(Color.CYAN);
				spielbrett.setWuerfel3Frei(true);
				wuerfel3 = true;
				gesichert[2] = 0;
				aktWurf[2] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
			else if (wuerfel == 4){
				spielbrett.setBtnSichern4("sichern");
				spielbrett.lblWuerfel4.setBackground(Color.CYAN);
				spielbrett.setWuerfel4Frei(true);
				wuerfel4 = true;
				gesichert[3] = 0;
				aktWurf[3] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
			else if (wuerfel == 5){
				spielbrett.setBtnSichern5("sichern");
				spielbrett.lblWuerfel5.setBackground(Color.CYAN);
				spielbrett.setWuerfel5Frei(true);
				wuerfel5 = true;
				gesichert[4] = 0;
				aktWurf[4] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
			else if (wuerfel == 6){
				spielbrett.setBtnSichern6("sichern");
				spielbrett.lblWuerfel6.setBackground(Color.CYAN);
				spielbrett.setWuerfel6Frei(true);
				wuerfel6 = true;
				gesichert[5] = 0;
				aktWurf[5] = 0;
				sicher--;
				verfuegbareWuerfel++;
			}
		}
		// Verfügbare Würfel setzen
		spielbrett.setVerfuegbareWuerfel(verfuegbareWuerfel);
		// Wenn ein Würfel gesichert wurde, erscheint der Button "Würfeln" erneut
		if (sicher > 0)
			spielbrett.btnWuerfeln.setEnabled(true);
		else
			spielbrett.btnWuerfeln.setEnabled(false);
		// Die Punktzahl wird berechnet
		berechnePunkte();
	}
	
	protected static void berechnePunkte(){
		
		// Punkte
		int punkte = 0;
		int einser = 0;
		int zweier = 0;
		int dreier = 0;
		int vierer = 0;
		int fuenfer = 0;
		int sechser = 0;
		int pkt1er = 0;
		int pkt2er = 0;
		int pkt3er = 0;
		int pkt4er = 0;
		int pkt5er = 0;
		int pkt6er = 0;
		
		// Einser zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 1){
				einser++;
			}
		}
		// Zweier zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 2){
				zweier++;
			}
		}
		// Dreier zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 3){
				dreier++;
			}
		}
		// Vierer zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 4){
				vierer++;
			}
		}
		// Fünfer zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 5){
				fuenfer++;
			}
		}
		// Sechser zählen
		for (int i=0; i<6; i++){
			if (aktWurf[i] == 6){
				sechser++;
			}
		}
		
		// Punkte für Einser berechnen
		if (einser < 3)
			pkt1er = einser*100;
		else if (einser == 3)
			pkt1er = 1000;
		else if (einser == 4)
			pkt1er = 2000;
		else if (einser == 5)
			pkt1er = 4000;
		else if (einser == 6)
			pkt1er = 8000;
		
		// Punkte für Zweier berechnen
		if (zweier == 3)
			pkt2er = 200;
		else if (zweier == 4)
			pkt2er = 400;
		else if (zweier == 5)
			pkt2er = 800;
		else if (zweier == 6)
			pkt2er = 1600;
		
		// Punkte für Dreier berechnen
		if (dreier == 3)
			pkt3er = 300;
		else if (dreier == 4)
			pkt3er = 600;
		else if (dreier == 5)
			pkt3er = 1200;
		else if (dreier == 6)
			pkt3er = 2400;
		
		// Punkte für Vierer berechnen
		if (vierer == 3)
			pkt4er = 400;
		else if (vierer == 4)
			pkt4er = 800;
		else if (vierer == 5)
			pkt4er = 1600;
		else if (vierer == 6)
			pkt4er = 3200;
		
		// Punkte für Fünfer berechnen
		if (fuenfer < 3)
			pkt5er = fuenfer*50;
		else if (fuenfer == 3)
			pkt5er = 500;
		else if (fuenfer == 4)
			pkt5er = 1000;
		else if (fuenfer == 5)
			pkt5er = 2000;
		else if (fuenfer == 6)
			pkt5er = 4000;
		
		// Punkte für Zweier berechnen
		if (sechser == 3)
			pkt6er = 600;
		else if (sechser == 4)
			pkt6er = 1200;
		else if (sechser == 5)
			pkt6er = 2400;
		else if (sechser == 6)
			pkt6er = 4800;
		
		// Gesamtpunktzahl berechnen
		punkte = pkt1er + pkt2er + pkt3er + pkt4er + pkt5er + pkt6er;
		// Wenn die Punktzahl mindestens 350 beträgt, wird der Button "Schreiben" angezeigt
		if ((punkte + rundePunkte) >= 350)
			spielbrett.btnSchreiben.setEnabled(true);
		else
			spielbrett.btnSchreiben.setEnabled(false);
		// Punktzahl anzeigen
		spielbrett.setLblPunkte("Punkte: " + String.valueOf(punkte + rundePunkte));
		// Punktzahl merken
		aktuellePunkte = punkte;
	}
	
	// ZEIGT DIE NÄCHSTE KARTE AN
	protected static void zeigeKarte(){
		
		// TODO: Anhand der Bezeichnung der nächsten Karte Bild anzeigen
		String nachsteKarte = kartenspiel.naechsteKarte().karteToString();
		karte = nachsteKarte;
		ImageIcon img = null;
		if (nachsteKarte.equals("Bonus 200")){
			img = new ImageIcon("img/Bonus_200_Card_1.png");
		}
		if (nachsteKarte.equals("Bonus 300")){
			img = new ImageIcon("img/Bonus_300_Card.png");
		}
		if (nachsteKarte.equals("Bonus 400")){
			img = new ImageIcon("img/Bonus_400_Card.png");
		}
		if (nachsteKarte.equals("Bonus 500")){
			img = new ImageIcon("img/Bonus_500_Card.png");
		}
		if (nachsteKarte.equals("Bonus 600")){
			img = new ImageIcon("img/Bonus_600_Card.png");
		}
		if (nachsteKarte.equals("Doppelte Lotte")){
			img = new ImageIcon("img/Doppelte_Lotte_Card.png");
		}
		if (nachsteKarte.equals("Stern")){
			img = new ImageIcon("img/Star_Card.png");
		}
		if (nachsteKarte.equals("Aussetzen")){
			img = new ImageIcon("img/Forbidden_Card.png");
		}
		if (nachsteKarte.equals("Plus-Minus 1000")){
			img = new ImageIcon("img/1000-1000+_Card.png");
		}
		if (nachsteKarte.equals("Straße")){
			img = new ImageIcon("img/Street_2000_Card.png");
		}
		if (nachsteKarte.equals("Kleeblatt")){
			img = new ImageIcon("img/Lucky_Card02.png");
		}
		spielbrett.showNaechsteKarte(img);
	}
	
	protected static void neueRunde(){
		// Rundenzahl erhöhen
		spielbrett.setNeueRunde();
		// Aktuellen Spieler setzen
		aktuellerSpieler = spielbrett.nextSpieler();
		// Akuelle Punkte zurücksetzen
		aktuellePunkte = 0;
		
		spielbrett.setTxtStatus(aktuellerSpieler + " ist an der Reihe");
		
		// Neue Karte anzeigen
		zeigeKarte();
		// Alle Felder verfügbar machen
		spielbrett.setWuerfel1Frei(true);
		spielbrett.setWuerfel2Frei(true);
		spielbrett.setWuerfel3Frei(true);
		spielbrett.setWuerfel4Frei(true);
		spielbrett.setWuerfel5Frei(true);
		spielbrett.setWuerfel6Frei(true);
		// Hintergrund aller Würfel ändern
		spielbrett.lblWuerfel1.setBackground(Color.CYAN);
		spielbrett.lblWuerfel2.setBackground(Color.CYAN);
		spielbrett.lblWuerfel3.setBackground(Color.CYAN);
		spielbrett.lblWuerfel4.setBackground(Color.CYAN);
		spielbrett.lblWuerfel5.setBackground(Color.CYAN);
		spielbrett.lblWuerfel6.setBackground(Color.CYAN);
		// Alle "Sichern-Buttons" verstecken und Text zurücksetzen
		spielbrett.btnSichern1.setVisible(false);
		spielbrett.btnSichern2.setVisible(false);
		spielbrett.btnSichern3.setVisible(false);
		spielbrett.btnSichern4.setVisible(false);
		spielbrett.btnSichern5.setVisible(false);
		spielbrett.btnSichern6.setVisible(false);
		spielbrett.setBtnSichern1("sichern");
		spielbrett.setBtnSichern2("sichern");
		spielbrett.setBtnSichern3("sichern");
		spielbrett.setBtnSichern4("sichern");
		spielbrett.setBtnSichern5("sichern");
		spielbrett.setBtnSichern6("sichern");
		// Verfügbare Würfel zurücksetzen
		spielbrett.setVerfuegbareWuerfel(6);
		// Punkte zurücksetzen
		aktuellePunkte = 0;
		rundePunkte = 0;
		// 
		wuerfel1 = true;
		wuerfel2 = true;
		wuerfel3 = true;
		wuerfel4 = true;
		wuerfel5 = true;
		wuerfel6 = true;
		// Punktefeld zurücksetzen
		spielbrett.setLblPunkte("");
		// Button "Würfeln" sichtbar machen
		spielbrett.btnWuerfeln.setEnabled(true);
	}
	
	protected static void schreiben(){
		// Hilfsvariabeln
		int points = 0;
		int neuePunkte = 0;
		int bonus = 0;
		// Bonus berechnen
		if (gesichert[0] != 0 && gesichert[1] != 0 && gesichert[2] != 0 && gesichert[3] != 0 && gesichert[4] != 0 && gesichert[5] != 0){
			if (karte.equals("Bonus 200"))
				bonus = 200;
			if (karte.equals("Bonus 300"))
				bonus = 300;
			if (karte.equals("Bonus 400"))
				bonus = 400;
			if (karte.equals("Bonus 500"))
				bonus = 500;
			if (karte.equals("Bonus 600"))
				bonus = 600;
		}
		
		// Aktuelle Punktzahl schreiben
		if (aktuellerSpieler.equals(spieler1.getPlayerName())){
			points = spieler1.getPoints();
			neuePunkte = bonus + points + Integer.valueOf(spielbrett.getLblPunkte().substring(8));
			spieler1.setPoints(neuePunkte);
			spielbrett.tblPlayer.setValueAt(String.valueOf(neuePunkte), 0, 1);
		}
		else if (aktuellerSpieler.equals(spieler2.getPlayerName())){
			points = spieler2.getPoints();
			neuePunkte = bonus + points + Integer.valueOf(spielbrett.getLblPunkte().substring(8));
			spieler2.setPoints(neuePunkte);
			spielbrett.tblPlayer.setValueAt(String.valueOf(neuePunkte), 1, 1);
		}
		if (anzSpieler >= 3){
			if (aktuellerSpieler.equals(spieler3.getPlayerName())){
				points = spieler3.getPoints();
				neuePunkte = bonus + points + Integer.valueOf(spielbrett.getLblPunkte().substring(8));
				spieler3.setPoints(neuePunkte);
				spielbrett.tblPlayer.setValueAt(String.valueOf(neuePunkte), 2, 1);
			}
		}
		if (anzSpieler >= 4){
			if (aktuellerSpieler.equals(spieler4.getPlayerName())){
				points = spieler4.getPoints();
				neuePunkte = bonus + points + Integer.valueOf(spielbrett.getLblPunkte().substring(8));
				spieler4.setPoints(neuePunkte);
				spielbrett.tblPlayer.setValueAt(String.valueOf(neuePunkte), 3, 1);
			}
		}
		if (anzSpieler >= 5){
			if (aktuellerSpieler.equals(spieler5.getPlayerName())){
				points = spieler5.getPoints();
				neuePunkte = bonus + points + Integer.valueOf(spielbrett.getLblPunkte().substring(8));
				spieler5.setPoints(neuePunkte);
				spielbrett.tblPlayer.setValueAt(String.valueOf(neuePunkte), 4, 1);
			}
		}
		System.out.println("Neue Punktzahl von " + aktuellerSpieler + " beträgt: " + neuePunkte);
		neueRunde();
	}
}
