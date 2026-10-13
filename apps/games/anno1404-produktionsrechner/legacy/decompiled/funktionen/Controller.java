/*
 * Decompiled with CFR 0.152.
 */
package funktionen;

import gui.Hauptseite;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    private static Hauptseite hauptseite;

    public static void main(String[] args) {
        try {
            Controller.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run() {
        Controller.erzeugeHauptseite();
    }

    public static boolean erzeugeHauptseite() {
        try {
            hauptseite = new Hauptseite();
            Hauptseite.txtBuerger.requestFocus();
            Hauptseite.frame.setVisible(true);
            class Zuruecksetzen
            extends MouseAdapter {
                Zuruecksetzen() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Controller.textfelderZuruecksetzen();
                }
            }
            Zuruecksetzen zuruecksetzen = new Zuruecksetzen();
            class Produkionskette
            extends MouseAdapter {
                private String name = "";

                public Produkionskette(String string) {
                    this.name = string;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Hauptseite.pnlProduktionsketten.removeAll();
                    Controller.oeffneProduktionskette(this.name);
                }
            }
            Produkionskette produktionFisch = new Produkionskette("Fisch");
            Produkionskette produktionMost = new Produkionskette("Most");
            Produkionskette produktionGewuerze = new Produkionskette("Gew\u00fcrze");
            Produkionskette produktionLeinenkutten = new Produkionskette("Leinenkutten");
            Produkionskette produktionBier = new Produkionskette("Bier");
            Produkionskette produktionBrot = new Produkionskette("Brot");
            Produkionskette produktionLederwaemser = new Produkionskette("Lederw\u00e4mser");
            Produkionskette produktionBuecher = new Produkionskette("B\u00fccher");
            Produkionskette produktionFleisch = new Produkionskette("Fleisch");
            Produkionskette produktionWein = new Produkionskette("Wein");
            Produkionskette produktionPelzmaentel = new Produkionskette("Pelzm\u00e4ntel");
            Produkionskette produktionBrokatgewaender = new Produkionskette("Brokatgew\u00e4nder");
            Produkionskette produktionKerzenleuchter = new Produkionskette("Kerzenleuchter");
            Produkionskette produktionBrillen = new Produkionskette("Brillen");
            Produkionskette produktionDatteln = new Produkionskette("Datteln");
            Produkionskette produktionMilch = new Produkionskette("Milch");
            Produkionskette produktionTeppiche = new Produkionskette("Teppiche");
            Produkionskette produktionKaffee = new Produkionskette("Kaffee");
            Produkionskette produktionPerlenketten = new Produkionskette("Perlenketten");
            Produkionskette produktionDuftwasser = new Produkionskette("Duftwasser");
            Produkionskette produktionMarzipan = new Produkionskette("Marzipan");
            Produkionskette produktionSeile = new Produkionskette("Seile");
            Produkionskette produktionMessing = new Produkionskette("Messing");
            Produkionskette produktionStein = new Produkionskette("Stein");
            Produkionskette produktionKanonen = new Produkionskette("Kanonen");
            Produkionskette produktionWerkzeuge = new Produkionskette("Werkzeuge");
            Produkionskette produktionMosaik = new Produkionskette("Mosaik");
            Produkionskette produktionWaffen = new Produkionskette("Waffen");
            Produkionskette produktionGlas = new Produkionskette("Glas");
            Produkionskette produktionSalz = new Produkionskette("Salz");
            Produkionskette produktionEisen = new Produkionskette("Eisen");
            Produkionskette produktionHolz = new Produkionskette("Holz");
            Produkionskette produktionKriegsmaschinen = new Produkionskette("Kriegsmaschinen");
            class Eingabe
            extends KeyAdapter {
                private String name = "";

                public Eingabe(String name) {
                    this.name = name;
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    Controller.pruefeEingabe(this.name);
                    Controller.berechneVerbrauch();
                }
            }
            Eingabe pruefeBettler = new Eingabe("Bettler");
            Eingabe pruefeBauern = new Eingabe("Bauern");
            Eingabe pruefeBuerger = new Eingabe("Buerger");
            Eingabe pruefePatrizier = new Eingabe("Patrizier");
            Eingabe pruefeAdelige = new Eingabe("Adelige");
            Eingabe pruefeNomaden = new Eingabe("Nomaden");
            Eingabe pruefeGesandte = new Eingabe("Gesandte");
            hauptseite.setZuruecksetzenListener(zuruecksetzen);
            hauptseite.setTxtBettlerListener(pruefeBettler);
            hauptseite.setTxtBauernListener(pruefeBauern);
            hauptseite.setTxtBuergerListener(pruefeBuerger);
            hauptseite.setTxtPatrizierListener(pruefePatrizier);
            hauptseite.setTxtAdeligeListener(pruefeAdelige);
            hauptseite.setTxtNomadenListener(pruefeNomaden);
            hauptseite.setTxtGesandteListener(pruefeGesandte);
            hauptseite.setBtnFischListener(produktionFisch);
            hauptseite.setBtnMostListener(produktionMost);
            hauptseite.setBtnGewuerzeListener(produktionGewuerze);
            hauptseite.setBtnLeinenkuttenListener(produktionLeinenkutten);
            hauptseite.setBtnBierListener(produktionBier);
            hauptseite.setBtnBrotListener(produktionBrot);
            hauptseite.setBtnLederwaemserListener(produktionLederwaemser);
            hauptseite.setBtnBuecherListener(produktionBuecher);
            hauptseite.setBtnFleischListener(produktionFleisch);
            hauptseite.setBtnWeinListener(produktionWein);
            hauptseite.setBtnPelzmaentelListener(produktionPelzmaentel);
            hauptseite.setBtnBrokatgewaenderListener(produktionBrokatgewaender);
            hauptseite.setBtnKerzenleuchterListener(produktionKerzenleuchter);
            hauptseite.setBtnBrillenListener(produktionBrillen);
            hauptseite.setBtnDattelnListener(produktionDatteln);
            hauptseite.setBtnMilchListener(produktionMilch);
            hauptseite.setBtnTeppicheListener(produktionTeppiche);
            hauptseite.setBtnKaffeeListener(produktionKaffee);
            hauptseite.setBtnPerlenkettenListener(produktionPerlenketten);
            hauptseite.setBtnDuftwasserListener(produktionDuftwasser);
            hauptseite.setBtnMarzipanListener(produktionMarzipan);
            hauptseite.setBtnSeileListener(produktionSeile);
            hauptseite.setBtnMessingListener(produktionMessing);
            hauptseite.setBtnSteinListener(produktionStein);
            hauptseite.setBtnKanonenListener(produktionKanonen);
            hauptseite.setBtnWerkzeugeListener(produktionWerkzeuge);
            hauptseite.setBtnMosaikListener(produktionMosaik);
            hauptseite.setBtnWaffenListener(produktionWaffen);
            hauptseite.setBtnGlasListener(produktionGlas);
            hauptseite.setBtnSalzListener(produktionSalz);
            hauptseite.setBtnEisenListener(produktionEisen);
            hauptseite.setBtnHolzListener(produktionHolz);
            hauptseite.setBtnKriegsmaschinenListener(produktionKriegsmaschinen);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void oeffneProduktionskette(String name) {
        try {
            hauptseite.setProduktion(name);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void textfelderZuruecksetzen() {
        hauptseite.settxtAnzahlBettler("");
        hauptseite.settxtAnzahlBauern("");
        hauptseite.settxtAnzahlBuerger("");
        hauptseite.settxtAnzahlPatrizier("");
        hauptseite.settxtAnzahlAdelige("");
        hauptseite.settxtAnzahlNomaden("");
        hauptseite.settxtAnzahlGesandte("");
        hauptseite.resetlblEingabeFehler();
        Hauptseite.pnlProduktionsketten.removeAll();
        Hauptseite.pnlProduktionsketten.repaint();
        Controller.berechneVerbrauch();
    }

    public static void pruefeEingabe(String name) {
        String eingabe = hauptseite.getAnzahl(name);
        if (eingabe.matches("[0-9]+") && eingabe.length() < 6) {
            hauptseite.setlblFehler(name, 2);
            hauptseite.setlblEingabeOK(name, 1);
        } else {
            hauptseite.setlblEingabeOK(name, 2);
            hauptseite.setlblFehler(name, 1);
            hauptseite.resetTxt(name);
        }
    }

    public static void berechneVerbrauch() {
        int Bettler = Integer.valueOf(hauptseite.getAnzahlBettler());
        int Bauern = Integer.valueOf(hauptseite.getAnzahlBauern());
        int Buerger = Integer.valueOf(hauptseite.getAnzahlBuerger());
        int Patrizier = Integer.valueOf(hauptseite.getAnzahlPatrizier());
        int Adelige = Integer.valueOf(hauptseite.getAnzahlAdelige());
        int Nomaden = Integer.valueOf(hauptseite.getAnzahlNomaden());
        int Gesandte = Integer.valueOf(hauptseite.getAnzahlGesandte());
        String fischer = Controller.berechneProduktionOrient(Bettler, 7.0, Bauern, 10.0, Buerger, 4.0, Patrizier, 2.2, Adelige, 1.6, 2000);
        String most = "0 (0)";
        if (Bauern >= 60 || Bettler > 0 || Buerger > 0 || Patrizier > 0 || Adelige > 0) {
            most = Controller.berechneProduktionOrient(Bettler, 3.0, Bauern, 4.4, Buerger, 4.4, Patrizier, 2.3, Adelige, 1.3, 1500);
        }
        String gewuerze = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 4.0, Patrizier, 2.2, Adelige, 1.6, 2000);
        String leinenkutten = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 4.2, Patrizier, 1.9, Adelige, 0.8, 2000);
        String brot = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 5.5, Adelige, 3.9, 4000);
        String bier = "0 (0)";
        if (Patrizier >= 510 || Adelige > 0) {
            bier = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 2.4, Adelige, 1.4, 1500);
        }
        String lederwaemser = "0 (0)";
        if (Patrizier >= 690 || Adelige > 0) {
            lederwaemser = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 2.8, Adelige, 1.6, 4000);
        }
        String buecher = "0 (0)";
        if (Patrizier >= 940 || Adelige > 0) {
            buecher = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 1.6, Adelige, 0.9, 3000);
        }
        String fleisch = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.0, Adelige, 2.2, 2500);
        String wein = "0 (0)";
        if (Adelige >= 1500) {
            wein = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.0, Adelige, 2.0, 2000);
        }
        String pelzmaentel = "0 (0)";
        if (Adelige >= 950) {
            pelzmaentel = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.0, Adelige, 1.6, 2500);
        }
        String brokatgewaender = "0 (0)";
        if (Adelige >= 4000) {
            brokatgewaender = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.0, Adelige, 1.42, 3000);
        }
        String brillen = "0 (0)";
        if (Adelige >= 2200) {
            brillen = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.0, Adelige, 1.17, 2000);
        }
        String kerzenhalter = "0 (0)";
        if (Adelige >= 3000) {
            kerzenhalter = Controller.berechneProduktionOrient(Bettler, 0.0, Bauern, 0.0, Buerger, 0.0, Patrizier, 0.8, Adelige, 0.6, 2000);
        }
        String datteln = Controller.berechneProduktionOkzident(Nomaden, 6.66, Gesandte, 5.0, 3000);
        String milch = "0 (0)";
        if (Nomaden >= 145 || Gesandte > 0) {
            milch = Controller.berechneProduktionOkzident(Nomaden, 3.44, Gesandte, 2.25, 1500);
        }
        String teppiche = "0 (0)";
        if (Nomaden >= 295 || Gesandte > 0) {
            teppiche = Controller.berechneProduktionOkzident(Nomaden, 1.65, Gesandte, 1.0, 1500);
        }
        String kaffee = Controller.berechneProduktionOkzident(Nomaden, 0.0, Gesandte, 1.0, 1000);
        String perlenketten = "0 (0)";
        if (Gesandte >= 1040) {
            perlenketten = Controller.berechneProduktionOkzident(Nomaden, 0.0, Gesandte, 1.33, 1000);
        }
        String duftwasser = "0 (0)";
        if (Gesandte >= 2600) {
            duftwasser = Controller.berechneProduktionOkzident(Nomaden, 0.0, Gesandte, 0.8, 1000);
        }
        String marzipan = "0 (0)";
        if (Gesandte >= 4360) {
            marzipan = Controller.berechneProduktionOkzident(Nomaden, 0.0, Gesandte, 1.63, 4000);
        }
        hauptseite.setlblAnzahlFisch(fischer);
        hauptseite.setlblAnzahlMost(most);
        hauptseite.setlblAnzahlGewuerze(gewuerze);
        hauptseite.setlblAnzahlLeinenkutten(leinenkutten);
        hauptseite.setlblAnzahlBrot(brot);
        hauptseite.setlblAnzahlBier(bier);
        hauptseite.setlblAnzahlLederwaemser(lederwaemser);
        hauptseite.setlblAnzahlBuecher(buecher);
        hauptseite.setlblAnzahlFleisch(fleisch);
        hauptseite.setlblAnzahlWein(wein);
        hauptseite.setlblAnzahlPelzmaentel(pelzmaentel);
        hauptseite.setlblAnzahlBrokatgewaender(brokatgewaender);
        hauptseite.setlblAnzahlBrillen(brillen);
        hauptseite.setlblAnzahlKerzenleuchter(kerzenhalter);
        hauptseite.setlblAnzahlDatteln(datteln);
        hauptseite.setlblAnzahlMilch(milch);
        hauptseite.setlblAnzahlTeppiche(teppiche);
        hauptseite.setlblAnzahlKaffee(kaffee);
        hauptseite.setlblAnzahlPerlenketten(perlenketten);
        hauptseite.setlblAnzahlDuftwasser(duftwasser);
        hauptseite.setlblAnzahlMarzipan(marzipan);
    }

    public static String berechneProduktionOrient(int Bettler, double verbrBettler, int Bauern, double verbrBauern, int Buerger, double verbrBuerger, int Patrizier, double verbrPatrizier, int Adelige, double verbrAdelige, int prodProMinute) {
        String rueckgabe = "";
        int verbrauch = 0;
        verbrauch = (int)((double)verbrauch + (double)Bettler * verbrBettler);
        verbrauch = (int)((double)verbrauch + (double)Buerger * verbrBuerger);
        verbrauch = (int)((double)verbrauch + (double)Patrizier * verbrPatrizier);
        verbrauch = (int)((double)verbrauch + (double)Adelige * verbrAdelige);
        verbrauch = (int)((double)verbrauch + (double)Bauern * verbrBauern);
        int aufrund = 0;
        if (verbrauch > 0) {
            aufrund = 1;
        }
        if (verbrauch % prodProMinute == 0) {
            aufrund = 0;
        }
        int produktionen = verbrauch / prodProMinute + aufrund;
        rueckgabe = String.valueOf(produktionen) + " (" + verbrauch + ")";
        return rueckgabe;
    }

    public static String berechneProduktionOkzident(int Nomaden, double verbrNomaden, int Gesandte, double verbrGesandte, int prodProMinute) {
        String rueckgabe = "";
        int verbrauch = 0;
        verbrauch = (int)((double)verbrauch + (double)Nomaden * verbrNomaden);
        verbrauch = (int)((double)verbrauch + (double)Gesandte * verbrGesandte);
        int aufrund = 0;
        if (verbrauch > 0) {
            aufrund = 1;
        }
        if (verbrauch % prodProMinute == 0) {
            aufrund = 0;
        }
        int produktionen = verbrauch / prodProMinute + aufrund;
        rueckgabe = String.valueOf(produktionen) + " (" + verbrauch + ")";
        return rueckgabe;
    }

    static /* synthetic */ Hauptseite access$0() {
        return hauptseite;
    }
}

