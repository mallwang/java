package dvdverleih.funktionen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Controller {

	private static dvdverleih.gui.Hauptseite window;
	private static dvdverleih.funktionen.KKonto kk;

	public static boolean erzeugHauptseite() {
		try {
			window = new dvdverleih.gui.Hauptseite();
			class SucheUser extends MouseAdapter {
				@Override
				public void mouseClicked(MouseEvent e) {
					String begriff = window.getTxtSuche();
					window.setLblKeinSuchtreffer(false);
					window.setLblKeinSuchbegriff(false);
					if (begriff.length() > 0) {
						String[][] ergebnis;

						ergebnis = kk.suche_Hauptseite(begriff);

						if (ergebnis != null) {
							erzeugSuchergebnis_user(ergebnis);
						} else {
							window.setLblKeinSuchtreffer(true);
						}
					} else {
						window.setLblKeinSuchbegriff(true);
					}
				}
			}
			class Registrieren extends MouseAdapter {
				@Override
				public void mouseClicked(MouseEvent e) {
					erzeugRegistrieren();
				}
			}
			class Preise extends MouseAdapter {
				@Override
				public void mouseClicked(MouseEvent e) {
					erzeugPnlPreisuebersicht();
				}
			}
			class Schliessen extends WindowAdapter {

				@Override
				public void windowClosing(WindowEvent arg0) {

					kk.DB_schliessen();
				}
			}

			class Produkt extends MouseAdapter {
				@Override
				public void mouseClicked(MouseEvent e) {
					erzeugPnlProdukte();
				}
			}

			window.frame.setVisible(true);
			MouseAdapter su = new SucheUser();
			MouseAdapter reg = new Registrieren();
			MouseAdapter ps = new Produkt();
			MouseAdapter pr = new Preise();
			WindowAdapter schliessen = new Schliessen();

			window.setListener_Schliessen(schliessen);
			window.setListener_BtnSuche(su);
			window.setListener_BtnRegistrieren(reg);
			window.setListener_BtnProdukte(ps);
			window.setListener_BtnPreise(pr);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static boolean erzeugKKAendern(final String id) {

		final Class<dvdverleih.gui.Dia_KKAendern> KKaendernclass;
		final ArrayList<String> info = kk.KKinfo(id);
		try {
			Class<?> kkae = Class.forName("dvdverleih.gui.Dia_KKAendern");
			KKaendernclass = (Class<dvdverleih.gui.Dia_KKAendern>) kkae;
			final dvdverleih.gui.Dia_KKAendern kkaend = KKaendernclass.newInstance();

			class Check implements ItemListener {
				private String cb;
				private String txt;
				private int index;

				public Check(String name, int index) {

					cb = "getCb" + name;
					txt = "setTxt" + name;
					this.index = index;
				}

				@Override
				public void itemStateChanged(ItemEvent e) {
					textfeld_setzen();
				}

				private void textfeld_setzen() {

					try {
						if ((boolean) KKaendernclass.getMethod(cb).invoke(
								kkaend)) {
							KKaendernclass.getMethod(txt,
									java.lang.Boolean.TYPE)
									.invoke(kkaend, true);
						} else {
							KKaendernclass.getMethod(txt, String.class).invoke(
									kkaend, info.get(index));
							KKaendernclass.getMethod(txt,
									java.lang.Boolean.TYPE).invoke(kkaend,
									false);
						}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			class Aendern extends MouseAdapter {
				private ArrayList<String>[] check_klick() {
					ArrayList<String> spalte_neu = new ArrayList<String>();
					ArrayList<String> inhalt_neu = new ArrayList<String>();
					if (kkaend.getCbVname()) {
						spalte_neu.add("vorname");
						inhalt_neu.add(kkaend.getTxtVname());
					}
					if (kkaend.getCbNname()) {
						spalte_neu.add("nachname");
						inhalt_neu.add(kkaend.getTxtNname());
					}
					if (kkaend.getCbGeb()) {
						spalte_neu.add("geburtsdatum");
						inhalt_neu.add(kkaend.getTxtGeb());
					}
					if (kkaend.getCbTele()) {
						spalte_neu.add("telefon");
						inhalt_neu.add(kkaend.getTxtTele());
					}
					if (kkaend.getCbPnr()) {
						spalte_neu.add("personalausweisnummer");
						inhalt_neu.add(kkaend.getTxtPnr());
					}
					if (kkaend.getCbStr()) {
						spalte_neu.add("strasse");
						inhalt_neu.add(kkaend.getTxtStr());
					}
					if (kkaend.getCbHnr()) {
						spalte_neu.add("hausnummer");
						inhalt_neu.add(kkaend.getTxtHnr());
					}
					if (kkaend.getCbOrt()) {
						spalte_neu.add("wohnort");
						inhalt_neu.add(kkaend.getTxtOrt());
					}
					if (kkaend.getCbPlz()) {
						spalte_neu.add("plz");
						inhalt_neu.add(kkaend.getTxtPlz());
					}
					ArrayList<String>[] rturn = new ArrayList[2];
					rturn[0] = spalte_neu;
					rturn[1] = inhalt_neu;
					return rturn;

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					kkaend.setLblNichtKorrekt(false);
					// Geklickte Checkoxen mit Inhalt sammeln
					ArrayList<String>[] check_klick = check_klick();
					// Weitergabe an kk
					window.TabLoeschen(kk.id2name(id));
					if (kk.KKaendern(check_klick[0], check_klick[1], id)) {
						// Tab setzen
						erzeugPnlKundenkonto(id);
						kkaend.dispose();
					} else {
						erzeugPnlKundenkonto(id);
						kkaend.setLblNichtKorrekt(true);
					}

				}
			}

			ItemListener cb;
			cb = new Check("Vname", 0);
			kkaend.setListener_CbVname(cb);
			cb = new Check("Nname", 1);
			kkaend.setListener_CbNname(cb);
			cb = new Check("Geb", 2);
			kkaend.setListener_CbGeb(cb);
			cb = new Check("Tele", 4);
			kkaend.setListener_CbTele(cb);
			cb = new Check("Pnr", 5);
			kkaend.setListener_CbPnr(cb);
			cb = new Check("Str", 7);
			kkaend.setListener_CbStr(cb);
			cb = new Check("Hnr", 8);
			kkaend.setListener_CbHnr(cb);
			cb = new Check("Ort", 9);
			kkaend.setListener_CbOrt(cb);
			cb = new Check("Plz", 10);
			kkaend.setListener_CbPlz(cb);

			MouseAdapter aendern = new Aendern();
			kkaend.setListener_BtnKKAendern(aendern);

			// Textfelder befuellen
			kkaend.setTxtVname(info.get(0));
			kkaend.setTxtNname(info.get(1));
			kkaend.setTxtGeb(info.get(2));
			kkaend.setTxtTele(info.get(4));
			kkaend.setTxtPnr(info.get(5));
			kkaend.setTxtStr(info.get(7));
			kkaend.setTxtHnr(info.get(8));
			kkaend.setTxtOrt(info.get(9));
			kkaend.setTxtPlz(info.get(10));
			kkaend.setKnr(id);

			kkaend.setVisible(true);

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			return false;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static boolean erzeugLeihen(final String id) {
		final dvdverleih.gui.Dia_leihen l = new dvdverleih.gui.Dia_leihen(id);
		new ArrayList<String>();
		class Leihen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				l.setLblKeineAuswahl(false);

				ArrayList<ArrayList<String>> auswahl = l.auswahl();
				if (auswahl.size() > 0) {
					for (int i = 0; i < auswahl.size(); i++) {
						kk.leihen(id, auswahl.get(i).get(0), auswahl.get(i)
								.get(1));
					}
					l.dispose();
				} else {
					l.setLblKeineAuswahl(true);
				}
			}
		}
		class Suchen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugSuchmaske(l);
			}

		}

		MouseAdapter leihen = new Leihen();
		l.setListener_BtnLeihen(leihen);
		MouseAdapter suchen = new Suchen();
		l.setListener_BtnSuche(suchen);
		l.updateTable();
		l.setVisible(true);

		return true;
	}

	public static boolean erzeugNeuerDarsteller(dvdverleih.gui.Dia_neues_Objekt no) {
		final dvdverleih.gui.Dia_neuerDarsteller neuerDarsteller = new dvdverleih.gui.Dia_neuerDarsteller();
		neuerDarsteller.setVisible(true);
		neuerDarsteller.setTitle("Neuer Darsteller");
		class DarstellerAnlegen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neuerDarsteller.setLblFelderKorrekt(false);
				ArrayList<String> neuDarst = new ArrayList<String>();
				String vname = neuerDarsteller.getTxtVname();
				String nname = neuerDarsteller.getTxtNname();
				if ((vname.length() > 0) && (nname.length() > 0)) {
					neuDarst.add(vname);
					neuDarst.add(nname);
					kk.Darstelleranlegen(neuDarst);
					neuerDarsteller.dispose();
				} else {
					neuerDarsteller.setLblFelderKorrekt(true);
				}

			}
		}
		MouseAdapter darstellerAnlegen = new DarstellerAnlegen();
		neuerDarsteller.setListener_BtnDarsteller_anlegen(darstellerAnlegen);

		return true;
	}

	public static boolean erzeugNeueRegie(dvdverleih.gui.Dia_neues_Objekt no) {
		final dvdverleih.gui.Dia_neueRegie neueRegie = new dvdverleih.gui.Dia_neueRegie();
		neueRegie.setVisible(true);
		neueRegie.setTitle("Neue Regie");
		class RegieAnlegen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neueRegie.setLblFelderKorrekt(false);
				ArrayList<String> neuRegie = new ArrayList<String>();
				String vname = neueRegie.getTxtVname();
				String nname = neueRegie.getTxtNname();
				if ((vname.length() > 0) && (nname.length() > 0)) {
					neuRegie.add(vname);
					neuRegie.add(nname);
					kk.Regieanlegen(neuRegie);
					neueRegie.dispose();
				} else {
					neueRegie.setLblFelderKorrekt(true);
				}

			}
		}
		MouseAdapter regieAnlegen = new RegieAnlegen();
		neueRegie.setListener_BtnRegie_anlegen(regieAnlegen);

		return true;
	}

	public static boolean erzeugNeuesObjekt() {
		final dvdverleih.gui.Dia_neues_Objekt neuesObjekt = new dvdverleih.gui.Dia_neues_Objekt();
		neuesObjekt.setTitle("Neues Objekt");
		neuesObjekt.setVisible(true);

		class DropdownKategorie implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				neuesObjekt.setFilmDaten(true);
				neuesObjekt.setDropdownGenre(true);
				String kat = neuesObjekt.getDropdownKategorie();
				neuesObjekt.setDropdownGenre(kat);
				neuesObjekt.setDropdownFormat(kat);
				if (!kat.equals("Film")) {
					neuesObjekt.setFilmDaten(false);
					neuesObjekt.clearTable(true);
					neuesObjekt.clearTable(false);
					neuesObjekt.updateInhalt(true);
					neuesObjekt.updateInhalt(false);
					neuesObjekt.updateTable(true);
					neuesObjekt.updateTable(false);
				}
			}
		}
		class DropdownFormat implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kat = neuesObjekt.getcbFormat();
				if (kat.equals("Beamer")) {
					neuesObjekt.setDropdownGenre(false);
				} else {
					neuesObjekt.setDropdownGenre(true);
				}
			}
		}

		class DarstellerSuche extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neuesObjekt.setLblKeinSuchbegriffDarsteller(false);
				neuesObjekt.setLblKeinSuchtrefferDarsteller(false);
				String suchbegriff = neuesObjekt.getTxtDarsteller();
				if (suchbegriff.length() > 0) {
					String[][] ergebnis = kk.suche_Darsteller(suchbegriff);

					if (ergebnis != null) {
						erzeugSuchergebnis_darsteller_regie(ergebnis,
								neuesObjekt, true);
					} else {
						neuesObjekt.setLblKeinSuchtrefferDarsteller(true);
					}
				} else {
					neuesObjekt.setLblKeinSuchbegriffDarsteller(true);
				}
			}
		}
		class DarstellerNeu extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugNeuerDarsteller(neuesObjekt);
			}
		}
		class RegieNeu extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugNeueRegie(neuesObjekt);
			}
		}
		class RegieSuche extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neuesObjekt.setLblKeinSuchtrefferRegie(false);
				neuesObjekt.setLblKeinSuchbegriffRegie(false);
				String suchbegriff = neuesObjekt.getTxtRegie();
				if (suchbegriff.length() > 0) {
					String[][] ergebnis = kk.suche_Regie(suchbegriff);

					if (ergebnis != null) {
						erzeugSuchergebnis_darsteller_regie(ergebnis,
								neuesObjekt, false);
					} else {
						neuesObjekt.setLblKeinSuchtrefferRegie(true);
					}
				} else {
					neuesObjekt.setLblKeinSuchbegriffRegie(true);
				}

			}
		}
		class Zuruecksetzen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neuesObjekt.setTxtAnzahl("");
				neuesObjekt.setTxtPreis("");
				neuesObjekt.setTxtTitel("");
				neuesObjekt.setTxtHersteller("");
				neuesObjekt.setTxtJahr("");
				neuesObjekt.setTxtFsk("");
				neuesObjekt.setTxtOriginaltitel("");
				neuesObjekt.setTxtTags("");
				neuesObjekt.setTxtBeschreibung("");
			}
		}
		class Objekt_anlegen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				neuesObjekt.setLblNichtKorrekt(true);
				String id;
				String kategorie = neuesObjekt.getDropdownKategorie();
				String genre = neuesObjekt.getDropdownGenre();
				String format = neuesObjekt.getDropdownFormat();
				String titel = neuesObjekt.getTxtTitel();
				String hersteller = neuesObjekt.getTxtHersteller();
				String anzahl = neuesObjekt.getTxtAnzahl();
				String preis = neuesObjekt.getTxtPreis();
				String jahr = neuesObjekt.getTxtJahr();
				String fsk = neuesObjekt.getTxtFsk();
				String originaltitel = neuesObjekt.getTxtOriginaltitel();
				String tags = neuesObjekt.getTxtTags();
				String beschreibung = neuesObjekt.getTxtBeschreibung();
				ArrayList<String> darsteller = neuesObjekt.getDarsteller();
				ArrayList<String> regie = neuesObjekt.getRegie();
				if ((neuesObjekt.getDropdownKategorie().equals("Film")
						&& (kategorie.length() > 0) && (genre.length() > 0)
						&& (format.length() > 0) && (titel.length() > 0)
						&& (hersteller.length() > 0) && (anzahl.length() > 0)
						&& (preis.length() > 0) && (jahr.length() > 0)
						&& (fsk.length() > 0) && (originaltitel.length() > 0)
						&& (tags.length() > 0) && (beschreibung.length() > 0)
						&& (darsteller.size() > 0) && (regie.size() > 0))
						|| ((neuesObjekt.getDropdownKategorie().equals("Spiel") || (neuesObjekt
								.getDropdownKategorie().equals("Zubehör")))
								&& (kategorie.length() > 0)
								&& (genre.length() > 0)
								&& (format.length() > 0)
								&& (titel.length() > 0)
								&& (hersteller.length() > 0)
								&& (anzahl.length() > 0)
								&& (preis.length() > 0)
								&& (jahr.length() > 0)
								&& (fsk.length() > 0)
								&& (originaltitel.length() > 0)
								&& (tags.length() > 0) && (beschreibung
								.length() > 0))) { // Entweder alle Kriterien
													// fuer FILM oder fuer SPIEl
													// bzw. ZUBEHOER sind
													// erfuellt
					if (format.equals("Beamer")) {
						genre = "Beamer";
					}
					if (kategorie.equals("Zubehör")) {
						kategorie = "Zubehoer";
					}

					id = kk.ObjektAnlegen(kategorie, genre, format, titel,
							hersteller, anzahl, preis, jahr, fsk,
							originaltitel, tags, beschreibung, darsteller,
							regie);
					if (id != null) {
						erzeugPnlObjekt(id);
						neuesObjekt.dispose();
					}
				} else {
					neuesObjekt.setLblNichtKorrekt(true);
				}

			}
		}

		neuesObjekt.setDropdownFormat("Film");
		neuesObjekt.setDropdownGenre("Film");
		neuesObjekt.setDropdownKategorie("Film");

		MouseAdapter an = new Objekt_anlegen();
		MouseAdapter zu = new Zuruecksetzen();
		MouseAdapter ds = new DarstellerSuche();
		MouseAdapter rs = new RegieSuche();
		MouseAdapter dn = new DarstellerNeu();
		MouseAdapter rn = new RegieNeu();

		ActionListener DropdownKat = new DropdownKategorie();
		ActionListener DropdownFor = new DropdownFormat();
		neuesObjekt.setListener_BtnObjektAnlegen(an);
		neuesObjekt.setListener_BtnZuruecksetzen(zu);
		neuesObjekt.setListener_BtnSucheDarsteller(ds);
		neuesObjekt.setListener_BtnSucheRegie(rs);
		neuesObjekt.setListener_BtnNeuDarsteller(dn);
		neuesObjekt.setListener_BtnNeuRegie(rn);
		neuesObjekt.setListener_CbArt(DropdownKat);
		neuesObjekt.setListener_CbFormat(DropdownFor);

		return true;
	}

	public static boolean erzeugObjektAendern(final String id) {

		final Class<dvdverleih.gui.Dia_ObjektAendern> Objektaendernclass;
		final ArrayList<String> info = kk.Objektinfo(id);

		try {
			Class<?> objae = Class.forName("dvdverleih.gui.Dia_ObjektAendern");
			Objektaendernclass = (Class<dvdverleih.gui.Dia_ObjektAendern>) objae;
			final dvdverleih.gui.Dia_ObjektAendern objaend = Objektaendernclass
					.newInstance();

			class Check implements ItemListener {
				private String cb;
				private String txt;
				private int index;
				private boolean b;

				public Check(String name, int index, boolean b) {
					this.b = b;
					if (b) {
						txt = "setDropdown" + name;
					} else {
						txt = "setTxt" + name;
					}
					cb = "getCb" + name;

					this.index = index;
				}

				@Override
				public void itemStateChanged(ItemEvent e) {
					textfeld_setzen();
				}

				private void textfeld_setzen() {

					try {
						if ((boolean) Objektaendernclass.getMethod(cb).invoke(
								objaend)) {
							Objektaendernclass.getMethod(txt,
									java.lang.Boolean.TYPE).invoke(objaend,
									true);
						} else {
							if (!b) {

								Objektaendernclass.getMethod(txt, String.class)
										.invoke(objaend, info.get(index));

							} else {
								Objektaendernclass.getMethod(txt, String.class)
										.invoke(objaend, info.get(0));
								Objektaendernclass.getMethod(txt + "_focus",
										String.class).invoke(objaend,
										info.get(index));
							}
							Objektaendernclass.getMethod(txt,
									java.lang.Boolean.TYPE).invoke(objaend,
									false);
						}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			class Aendern extends MouseAdapter {
				private ArrayList<String>[] check_klick() {
					ArrayList<String> spalte_neu = new ArrayList<String>();
					ArrayList<String> inhalt_neu = new ArrayList<String>();

					if (objaend.getCbOriginaltitel()) {
						spalte_neu.add("originaltitel");
						inhalt_neu.add(objaend.getTxtOriginaltitel());
					}
					if (objaend.getCbFsk()) {
						spalte_neu.add("fsk");
						inhalt_neu.add(objaend.getTxtFsk());
					}
					if (objaend.getCbJahr()) {
						spalte_neu.add("produktionsjahr");
						inhalt_neu.add(objaend.getTxtJahr());
					}
					if (objaend.getCbPreis()) {
						spalte_neu.add("preis");
						inhalt_neu.add(objaend.getTxtPreis());
					}
					if (objaend.getCbTitel()) {
						spalte_neu.add("titel");
						inhalt_neu.add(objaend.getTxtTitel());
					}
					ArrayList<String>[] rturn = new ArrayList[2];
					rturn[0] = spalte_neu;
					rturn[1] = inhalt_neu;
					return rturn;

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// Geklickte Checkoxen mit Inhalt sammeln
					ArrayList<String>[] check_klick = check_klick();
					// Weitergabe an kk
					window.TabLoeschen(kk.id2ObjektName(id));
					kk.Objektaendern(check_klick[0], check_klick[1], id);
					// Tab setzen
					erzeugPnlObjekt(id);
					objaend.dispose();
				}
			}

			class CheckKategorie implements ItemListener {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (objaend.getCbKategorie()) {
						objaend.setDropdownKategorie(true);
						objaend.setDropdownFormat(true);
						objaend.setCbFormat_value(true);
						objaend.setCbFormat(false);
						objaend.setDropdownGenre(true);
						objaend.setCbGenre_value(true);
						objaend.setCbGenre(false);
					} else {
						objaend.setDropdownKategorie_focus(info.get(0));
						objaend.setDropdownKategorie(false);
						objaend.setDropdownFormat(false);
						objaend.setCbFormat_value(false);
						objaend.setCbFormat(true);
						objaend.setDropdownGenre(false);
						objaend.setCbGenre_value(false);
						objaend.setCbGenre(true);
					}
				}
			}

			class DropdownKategorie implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					String kat = objaend.getDropdownKategorie();
					objaend.setDropdownFormat(kat);
					objaend.setDropdownGenre(kat);
				}
			}
			class DropdownFormat implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					String kat = objaend.getDropdownFormat();
					if (kat.equals("Beamer")) {
						objaend.setDropdownGenre(false);
						objaend.setCbGenre(false);
					} else {
						objaend.setDropdownGenre(true);
					}
				}
			}

			ActionListener dkat = new DropdownKategorie();
			ActionListener dfor = new DropdownFormat();
			objaend.setListener_DropdownKategorie(dkat);
			objaend.setListener_DropdownFormat(dfor);
			ItemListener cb;

			cb = new CheckKategorie();
			objaend.setListener_CbArt(cb);
			cb = new Check("Format", 2, true);
			objaend.setListener_CbFormat(cb);
			cb = new Check("Genre", 1, true);
			objaend.setListener_CbGenre(cb);
			cb = new Check("Titel", 3, false);
			objaend.setListener_CbTitel(cb);
			cb = new Check("Originaltitel", 7, false);
			objaend.setListener_CbOriginaltitel(cb);
			cb = new Check("Jahr", 5, false);
			objaend.setListener_CbJahr(cb);
			cb = new Check("Fsk", 6, false);
			objaend.setListener_CbFsk(cb);
			cb = new Check("Preis", 8, false);
			objaend.setListener_CbPreis(cb);

			MouseAdapter aendern = new Aendern();
			objaend.setListener_BtnKKAendern(aendern);

			
			// Textfelder befuellen
			objaend.setTxtTitel(info.get(3));
			objaend.setTxtOriginaltitel(info.get(7));
			objaend.setTxtJahr(info.get(5));
			objaend.setTxtFsk(info.get(6));
			objaend.setTxtPreis(info.get(8));
			objaend.setDropdownFormat(info.get(0));
			objaend.setDropdownFormat_focus(info.get(2));
			objaend.setDropdownGenre(info.get(0));
			objaend.setDropdownGenre_focus(info.get(1));
			objaend.setDropdownKategorie(info.get(0));
			objaend.setDropdownKategorie_focus(info.get(0));

			objaend.setTitel(info.get(3));
			objaend.setVisible(true);

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			return false;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public static boolean erzeugObjektAnlegen_erfolgreich(final String id) {
		final dvdverleih.gui.Dia_registrierung_erfolgreich ObjektAnlegen_erfolgreich = new dvdverleih.gui.Dia_registrierung_erfolgreich(
				id);
		ObjektAnlegen_erfolgreich.setVisible(true);

		class OK extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				new dvdverleih.gui.Pnl_kundenkonto(id);
				// TODO
				window.TabSetzen(null, kk.id2ObjektName(id), false);
				ObjektAnlegen_erfolgreich.dispose();
			}
		}
		MouseAdapter ok = new OK();
		ObjektAnlegen_erfolgreich.setListener_BtnOk(ok);

		return true;
	}

	public static boolean erzeugPnlKundenkonto(final String id) {
		dvdverleih.gui.Pnl_kundenkonto k = new dvdverleih.gui.Pnl_kundenkonto(id);
		class Schliessen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.TabLoeschen(kk.id2name(id));
			}
		}
		class Zurueckgeben extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!erzeugRueckgabe(id)) {
					JOptionPane.showMessageDialog(null,
							"Der Kunde hat nichts ausgeliehen.", "",
							JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		class Loeschen extends MouseAdapter {
			private boolean abfrage(String id) {
				String message = "M\u00F6chten Sie das Kundenkonto mit der "
						+ id + "\n" + "wirklich l\u00F6schen?";

				Object[] options = { "Ja, unwiderruflich l\u00F6schen!", "Nein" };
				int n = JOptionPane
						.showOptionDialog(null, message,
								id + " L\u00F6schen\n",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0)
					return true;
				else
					return false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (abfrage(id)) {
					window.TabLoeschen(kk.id2name(id));
					JOptionPane.showMessageDialog(null,
							"Das Kundenkonto mit der ID " + id
									+ " wurde gel\u00F6scht!", "",
							JOptionPane.PLAIN_MESSAGE);
					kk.KKloeschen(id);
				}
			}
		}
		class Aendern extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugKKAendern(id);
			}
		}
		class Leihen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugLeihen(id);
			}
		}

		MouseAdapter schliessen = new Schliessen();
		MouseAdapter Loeschen = new Loeschen();
		MouseAdapter Aendern = new Aendern();
		MouseAdapter Leihen = new Leihen();
		MouseAdapter zurueckgeben = new Zurueckgeben();

		k.setListener_BtnSchliessen(schliessen);
		k.setListener_BtnLoeschen(Loeschen);
		k.setListener_BtnAendern(Aendern);
		k.setListener_BtnLeihen(Leihen);
		k.setListener_BtnZurueckgeben(zurueckgeben);

		ArrayList<String> info = kk.KKinfo(id);

		// dvdverleih.datenhaltung.Kunde kunde=kk.KKinfo(id);
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getNname());
		// k.setVname(kunde.getGeb());
		// k.setVname(kunde.getTele());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());

		k.setVname(info.get(0));
		k.setNname(info.get(1));
		k.setGeb(info.get(2));
		k.setTele(info.get(4));
		k.setPnr(info.get(5));
		k.setKnr(info.get(6));
		k.setStr(info.get(7));
		k.setHnr(info.get(8));
		k.setOrt(info.get(9));
		k.setPlz(info.get(10));

		window.TabSetzen(k, id);
		return true;
	}

	public static boolean erzeugPnlObjekt(final String id) {
		dvdverleih.gui.Pnl_Objekt k = new dvdverleih.gui.Pnl_Objekt(id);
		class Schliessen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.TabLoeschen(kk.id2ObjektName(id));
			}
		}
		class Loeschen extends MouseAdapter {
			private boolean abfrage(String id) {
				String message = "M\u00F6chten Sie das Objekt mit der " + id
						+ "wirklich l\u00F6schen?";

				Object[] options = { "Ja, unwiderruflich l\u00F6schen!", "Nein" };
				int n = JOptionPane
						.showOptionDialog(null, message,
								id + " L\u00F6schen\n",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0)
					return true;
				else
					return false;

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (abfrage(id)) {

					window.TabLoeschen(kk.id2ObjektName(id));
					kk.Objektloeschen(id);
					JOptionPane.showMessageDialog(null,
							"Das Objekt mit der ID  " + id
									+ " wurde gel\u00F6scht!",
							"Objekt gelöscht", JOptionPane.PLAIN_MESSAGE);

				}
			}
		}
		class Aendern extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugObjektAendern(id);
			}
		}

		MouseAdapter schliessen = new Schliessen();
		MouseAdapter Loeschen = new Loeschen();
		MouseAdapter Aendern = new Aendern();

		k.setListener_BtnSchliessen(schliessen);
		k.setListener_BtnLoeschen(Loeschen);
		k.setListener_BtnAendern(Aendern);

		ArrayList<String> info = kk.Objektinfo(id);

		// dvdverleih.datenhaltung.Kunde kunde=kk.KKinfo(id);
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getNname());
		// k.setVname(kunde.getGeb());
		// k.setVname(kunde.getTele());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());
		// k.setVname(kunde.getVname());

		k.setArt(info.get(0));
		k.setGenre(info.get(1));
		k.setFormat(info.get(2));
		k.setTitel(info.get(3));
		k.setFsk(info.get(6));
		k.setJahr(info.get(5));
		k.setPreis(info.get(8));
		k.setVerfuegbar(info.get(9));
		k.setGesAnzahl(info.get(10));
		k.setOriginaltitel(info.get(7));
		k.setBeschreibung(info.get(11));

		window.TabSetzen(k, kk.id2ObjektName(id), false);
		return true;
	}

	public static boolean erzeugPnlPreisuebersicht() {
		dvdverleih.gui.Pnl_preisuebersicht pr = new dvdverleih.gui.Pnl_preisuebersicht();
		class Schliessen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.TabLoeschen("Preisübersicht");
			}
		}

		MouseAdapter schliessen = new Schliessen();
		pr.setListener_BtnSchliessen(schliessen);

		window.TabSetzen(pr, "Preisübersicht", false);
		return true;
	}

	public static boolean erzeugPnlProdukte() {
		dvdverleih.gui.Pnl_Produkte pro = new dvdverleih.gui.Pnl_Produkte();
		class Schliessen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				window.TabLoeschen("Produkte");
			}
		}
		class Suche extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugSuchmaske(null);
			}
		}
		class Neu extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugNeuesObjekt();
			}
		}
		MouseAdapter schliessen = new Schliessen();
		MouseAdapter suche = new Suche();
		MouseAdapter neu = new Neu();

		pro.setListener_BtnSchliessen(schliessen);
		pro.setListener_BtnSuche(suche);
		pro.setListener_BtnNeu(neu);

		window.TabSetzen(pro, "Produkte", false);
		return true;
	}

	public static boolean erzeugRegistrieren() {
		final dvdverleih.gui.Dia_registrierung_neukunde Registrierung_Neukunde = new dvdverleih.gui.Dia_registrierung_neukunde();
		Registrierung_Neukunde.setTitle("Registrieren");
		Registrierung_Neukunde.setVisible(true);

		class Neukunde_anlegen extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id;
				dvdverleih.funktionen.KKonto kk = new dvdverleih.funktionen.KKonto();
				String vname = Registrierung_Neukunde.getTxtVname();
				String nname = Registrierung_Neukunde.getTxtNname();
				String geb = Registrierung_Neukunde.getTxtGeb();
				String str = Registrierung_Neukunde.getTxtStr();
				String hnr = Registrierung_Neukunde.getTxtHnr();
				String ort = Registrierung_Neukunde.getTxtOrt();
				String plz = Registrierung_Neukunde.getTxtPlz();
				String tele = Registrierung_Neukunde.getTxtTele();
				String pnr = Registrierung_Neukunde.getTxtPnr();
				Registrierung_Neukunde.setLblNichtAlleFelder(false);
				if ((vname.length() > 0) && (nname.length() > 0)
						&& (geb.length() > 0) && (str.length() > 0)
						&& (hnr.length() > 0) && (ort.length() > 0)
						&& (plz.length() > 0) && (tele.length() > 0)
						&& (pnr.length() > 0)) {
					id = kk.KKanlegen(vname, nname, geb, str, hnr, ort, plz,
							tele, pnr);
					if (id != null) {
						erzeugRegistrierung_erfolgreich(id);

						Registrierung_Neukunde.dispose();
					}

				} else {
					Registrierung_Neukunde.setLblNichtAlleFelder(true);
				}

			}
		}
		MouseAdapter na = new Neukunde_anlegen();
		Registrierung_Neukunde.setListener_BtnKK_anlegen(na);

		return true;
	}

	public static boolean erzeugRegistrierung_erfolgreich(final String id) {
		final dvdverleih.gui.Dia_registrierung_erfolgreich Registrierung_erfolgreich = new dvdverleih.gui.Dia_registrierung_erfolgreich(
				id);
		Registrierung_erfolgreich.setVisible(true);

		class OK extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				erzeugPnlKundenkonto(id);
				Registrierung_erfolgreich.dispose();
			}
		}
		MouseAdapter ok = new OK();
		Registrierung_erfolgreich.setListener_BtnOk(ok);
		Registrierung_erfolgreich.setKnr(id);

		return true;
	}

	public static boolean erzeugRueckgabe(String id) {
		final dvdverleih.gui.Dia_rueckgabe rueck = new dvdverleih.gui.Dia_rueckgabe();

		class Bezahlen extends MouseAdapter {
			private boolean abfrage() {
				String message = "Hat der Kunde bezahlt?";

				Object[] options = { "Ja", "Nein" };
				int n = JOptionPane
						.showOptionDialog(null, message, "Bezahlen",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0)
					return true;
				else
					return false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				rueck.setLblKeineAuswahl(false);
				ArrayList<ArrayList<String>> auswahl = rueck.auswahl();
				if (auswahl.size() > 0) {
					if (abfrage()) {

						// aus Datenbank austragen
						System.out
								.println("Die ID aus der Datenbank austragen");
						for (int i = 0; i < auswahl.size(); i++) {

							String austragenId = auswahl.get(i).get(0);
							boolean rueckgabeErfolgreich = kk
									.rueckgabe(austragenId);
							if (rueckgabeErfolgreich) {

							} else {
								System.out
										.println("Die ID "
												+ austragenId
												+ " wurde nicht erfolgreich ausgetragen");
							}
						}// end for

						rueck.dispose();

						rueck.getSumme();

					}// end if (abfrage(id))

				}// end if (auswahl.size()>0)
				else {
					rueck.setLblKeineAuswahl(true);
				}// end else
			}
		}
		class CheckTable implements TableModelListener {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				Float summe = (float) 0.00;
				ArrayList<ArrayList<String>> auswahl = rueck.auswahl();
				for (ArrayList<String> a : auswahl) {
					summe += Float.valueOf(a.get(1));
				}
				rueck.setSumme(String.valueOf(summe));
			}
		}

		rueck.setKnr(id);
		rueck.setSumme("0,00");
		String[][] ergebnis = kk.ausleihobjekte(id);
		if (ergebnis != null) {
			rueck.setInhalt(ergebnis);
			rueck.updateTable();
			TableModelListener ct = new CheckTable();
			rueck.setListener_table(ct);
			MouseAdapter bezahlen = new Bezahlen();
			rueck.setListener_BtnAnders_bezahlen(bezahlen);

			rueck.setVisible(true);
			return true;
		} else {
			rueck.dispose();
			return false;
		}

	}

	public static String[][] erzeugSuchergebnis_darsteller_regie(
			String[][] ergebnis, final dvdverleih.gui.Dia_neues_Objekt no,
			final boolean darsteller) {
		final dvdverleih.gui.Dia_suche_ergebnis_darsteller such_erg = new dvdverleih.gui.Dia_suche_ergebnis_darsteller();

		class Auswahl extends MouseAdapter {
			private void gewaehltes_anzeigen() {
				if (darsteller) {
					ArrayList<ArrayList<String>> ergebnis = such_erg
							.getAusgewaehlt();
					no.setNewRows(ergebnis, true);
					no.updateInhalt(true);
					no.updateTable(true);
				} else {
					ArrayList<ArrayList<String>> ergebnis = such_erg
							.getAusgewaehlt();
					no.setNewRows(ergebnis, false);
					no.updateInhalt(false);
					no.updateTable(false);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				gewaehltes_anzeigen();
				such_erg.dispose();
			}
		}

		MouseAdapter ausw = new Auswahl();
		such_erg.setListener_BtnAusgewaehlte_anzeigen(ausw);

		// Ergebnistabelle fuellen
		such_erg.setInhalt(ergebnis);
		such_erg.updateTable();
		such_erg.setVisible(true);
		return null;
	}

	public static String[][] erzeugSuchergebnis_objekt(String[][] ergebnis,
			final dvdverleih.gui.Dia_leihen l, String kategorie) {
		final dvdverleih.gui.Dia_sucheErgebnis_objekt such_erg = new dvdverleih.gui.Dia_sucheErgebnis_objekt();
		such_erg.setVisible(true);
		class Auswahl extends MouseAdapter {
			private boolean gewaehltes_anzeigen() {
				// ausgewaehlte nehmen
				ArrayList<ArrayList<String>> erg = such_erg.auswahl();

				such_erg.setBtnAusgewaehlte_anzeigen(true);

				MouseAdapter ausw = new Auswahl();
				such_erg.setListener_BtnAusgewaehlte_anzeigen(ausw);

				if (l == null) {
					// Panel generieren (fuer jede auswahl)
					if (erg.size() > 0) {
						for (ArrayList<String> id : erg) {
							erzeugPnlObjekt(id.get(2));
						}
					} else
						return false;
					such_erg.dispose();
				} else {
					if (erg.size() > 0) {
						l.setNewRows(erg);
						l.updateInhalt();
						l.updateTable();
						l.repaint();
						such_erg.dispose();
					} else
						return false;
				}
				return true;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				such_erg.setLblKeineAuswahl(false);
				if (!gewaehltes_anzeigen()) {
					such_erg.setLblKeineAuswahl(true);
				}
				;
			}
		}

		MouseAdapter auswahl = new Auswahl();

		such_erg.setListener_BtnAusgewaehlte_anzeigen(auswahl);

		such_erg.setInhalt(ergebnis);
		if (kategorie.equals("Spiel")) {
			such_erg.updateTable("Hersteller");
		} else if (kategorie.equals("Film")) {
			such_erg.updateTable("Regie");
		} else {
			such_erg.updateTable("");
		}
		such_erg.setVisible(true);

		return null;
	}

	public static String[][] erzeugSuchergebnis_user(String[][] ergebnis) {
		final dvdverleih.gui.Dia_sucheErgebnis_user such_erg = new dvdverleih.gui.Dia_sucheErgebnis_user();

		class Auswahl extends MouseAdapter {
			private boolean gewaehltes_anzeigen() {

				ArrayList<String> ergebnis = such_erg.getAusgewaehlt();
				if (ergebnis.size() > 0) {
					for (String id : ergebnis) {
						erzeugPnlKundenkonto(id);
					}
				} else
					return false;
				return true;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				such_erg.setLblKeineAuswahl(false);
				if (!gewaehltes_anzeigen()) {
					such_erg.setLblKeineAuswahl(true);
				} else {
					such_erg.dispose();
				}
			}
		}

		MouseAdapter ausw = new Auswahl();
		such_erg.setListener_BtnAusgewaehlte_anzeigen(ausw);

		// Ergebnistabelle fuellen
		such_erg.setInhalt(ergebnis);
		such_erg.updateTable();
		such_erg.setVisible(true);
		return null;
	}

	public static String[][] erzeugSuchmaske(final dvdverleih.gui.Dia_leihen l) {

		final dvdverleih.gui.Dia_sucheMaske suchmaske = new dvdverleih.gui.Dia_sucheMaske(0);
		final String[][][] rturn = new String[1][][];

		suchmaske.setVisible(true);
		class SucheObjekt extends MouseAdapter {
			@Override
			public void mouseClicked(MouseEvent e) {
				suchmaske.setLblKeinSuchbegriff(false);
				suchmaske.setLblKeinSuchtreffer(false);
				// Suchbegriff auslesen
				String suchbegriff = suchmaske.getTxtSuche();
				if (suchbegriff.length() == 0) {
					suchmaske.setLblKeinSuchbegriff(true);
				} else {
					// Kategorie auslesen
					String kategorie = suchmaske.getDropdownKategorie();
					// Format auslesen
					String format = suchmaske.getDropdownFormat();
					// FSK auslesen
					int fskVon = suchmaske.getRangeSlider_fsk_value();
					int fskBis = suchmaske.getRangeSlider_fsk_uppervalue();
					// Jahr auslesen
					int jahrVon = suchmaske.getRangeSlider_jahr_value();
					int jahrBis = suchmaske.getRangeSlider_jahr_uppervalue();
					// Genre auslesen
					ArrayList<String> genre = suchmaske.getList();

					// Suchparameter auslesen
					ArrayList<String> parameter = new ArrayList<String>();
					if (suchmaske.getCbTitel()) {
						parameter.add("Titel");
					}
					if (suchmaske.getCbOriginaltitel()) {
						parameter.add("Originaltitel");
					}
					if (suchmaske.getCbDarsteller()) {
						parameter.add("Darsteller");
					}
					if (suchmaske.getCbRegie()) {
						parameter.add("Regie");
					}
					if (suchmaske.getCbTags()) {
						parameter.add("Tags");
						// Werte an die Suchfunktion uebergeben
					}

					rturn[0] = kk.suche(suchbegriff, kategorie, format, fskVon,
							fskBis, jahrVon, jahrBis, genre, parameter);
					// Rueckgabe in suche_ergebnis darstellen
					if (rturn[0].length > 0) {
						erzeugSuchergebnis_objekt(rturn[0], l, kategorie);
						suchmaske.dispose();
					} else {
						suchmaske.setLblKeinSuchtreffer(true);
					}
				}
			}
		}
		class DropdownKategorie implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				suchmaske.setGenre(true);
				String kat = suchmaske.getDropdownKategorie();
				suchmaske.setDropdownFormat(kat);
				suchmaske.setGenre(kat);
				if (kat.equals("Spiel")) {
					suchmaske.setCbDarsteller(false);
					suchmaske.setCbDarsteller_value(false);
					suchmaske.setCbRegie(false);
					suchmaske.setCbRegie_value(false);
					suchmaske.setCbOriginaltitel(true);
				} else if (kat.equals("Zubehör")) {
					suchmaske.setCbDarsteller(false);
					suchmaske.setCbDarsteller_value(false);
					suchmaske.setCbRegie(false);
					suchmaske.setCbRegie_value(false);
					suchmaske.setCbOriginaltitel(false);
					suchmaske.setCbOriginaltitel_value(false);
				} else {
					suchmaske.setCbDarsteller(true);
					suchmaske.setCbRegie(true);
					suchmaske.setCbOriginaltitel(true);
				}
			}
		}
		class DropdownFormat implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kat = suchmaske.getDropdownFormat();
				if (kat.equals("Beamer")) {
					suchmaske.setGenre(false);
				} else {
					suchmaske.setGenre(true);
				}
			}
		}

		ActionListener dkat = new DropdownKategorie();
		ActionListener dfor = new DropdownFormat();
		suchmaske.setListener_DropdownKategorie(dkat);
		suchmaske.setListener_DropdownFormat(dfor);
		MouseAdapter so = new SucheObjekt();
		suchmaske.setListener_BtnSuchen(so);

		suchmaske.setDropdownFormat("Film");
		suchmaske.setGenre("Film");

		return rturn[0];
	}

	public static void fehlerbehandlung(Exception e) {

		JOptionPane.showMessageDialog(null,
				"Es ist ein unerwarteter Fehler aufgetreten." + e.getMessage(),
				"", JOptionPane.PLAIN_MESSAGE);
	}

	public static void main(String[] args) {
		try {
			kk = new dvdverleih.funktionen.KKonto();
			if (true) {
				run();
			}
		} catch (Exception e) {
			fehlerbehandlung(e);
		}

	}

	public static void run() {
		erzeugHauptseite();

	}
}
