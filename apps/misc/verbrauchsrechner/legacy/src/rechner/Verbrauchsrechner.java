package rechner;

/**
 * Created by Markus on 09.04.2015.
 */
public class Verbrauchsrechner {
    // Dauer des Ticks in Sekunden
    double dauer_tick = 30.00000;

    // Einwohner
    int anzahl_bauern = 0;
    int anzahl_handwerker = 0;
    int anzahl_kaufleute = 0;
    int anzahl_adlige = 0;

    // Verbrauchswerte
    // Nahrung
    double verbr_fisch_bauern = 0.00042;
    double verbr_fisch_handwerker = 0.00030;
    double verbr_fisch_kaufleute = 0.00025;
    double verbr_fisch_adlige = 0.00022;
    double verbr_brot_handwerker = 0.00025;
    double verbr_brot_kaufleute = 0.00020;
    double verbr_brot_adlige = 0.00018;
    double verbr_gewuerze_kaufleute = 0.00012;
    double verbr_gewuerze_adlige = 0.00011;
    double verbr_fleisch_adlige = 0.00017;

    // Getränke
    double verbr_milch_bauern = 0.00050;
    double verbr_milch_handwerker = 0.00036;
    double verbr_milch_kaufleute = 0.00030;
    double verbr_milch_adlige = 0.00026;
    double verbr_most_handwerker = 0.00022;
    double verbr_most_kaufleute = 0.00018;
    double verbr_most_adlige = 0.00016;
    double verbr_bier_kaufleute = 0.00012;
    double verbr_bier_adlige = 0.00011;
    double verbr_wein_adlige = 0.0009;

    // Kleidung
    double verbr_wollgewaender_bauern = 0.00027;
    double verbr_wollgewaender_handwerker = 0.00020;
    double verbr_wollgewaender_kaufleute = 0.00016;
    double verbr_wollgewaender_adlige = 0.00014;
    double verbr_leinengewaender_handwerker = 0.00015;
    double verbr_leinengewaender_kaufleute = 0.00012;
    double verbr_leinengewaender_adlige = 0.00010;
    double verbr_lederwaemser_kaufleute = 0.00004;
    double verbr_lederwaemser_adlige = 0.00003;
    double verbr_pelzmaentel_kaufleute = 0.00004;
    double verbr_pelzmaentel_adlige = 0.00003;
    double verbr_brokatgewaender_adlige = 0.00002;

    // Besitz
    double verbr_buecher_kaufleute = 0.00012;
    double verbr_buecher_adlige = 0.00011;
    double verbr_kerzenleuchter_adlige = 0.00008;
    double verbr_brillen_adlige = 0.00003;

    // Gezahlte Steuern
    // Nahrung
    double steu_fisch_bauern = 0.00104;
    double steu_fisch_handwerker = 0.00104;
    double steu_fisch_kaufleute = 0.00104;
    double steu_fisch_adlige = 0.00104;
    double steu_brot_handwerker = 0.00438;
    double steu_brot_kaufleute = 0.00438;
    double steu_brot_adlige = 0.00438;
    double steu_gewuerze_kaufleute = 0.00500;
    double steu_gewuerze_adlige = 0.00500;
    double steu_fleisch_adlige = 0.02480;

    // Getränke
    double steu_milch_bauern = 0.00118;
    double steu_milch_handwerker = 0.00118;
    double steu_milch_kaufleute = 0.00118;
    double steu_milch_adlige = 0.00118;
    double steu_most_handwerker = 0.00419;
    double steu_most_kaufleute = 0.00419;
    double steu_most_adlige = 0.00419;
    double steu_bier_kaufleute = 0.01250;
    double steu_bier_adlige = 0.01250;
    double steu_wein_adlige = 0.06650;

    // Kleidung
    double steu_wollgewaender_bauern = 0.00234;
    double steu_wollgewaender_handwerker = 0.00234;
    double steu_wollgewaender_kaufleute = 0.00234;
    double steu_wollgewaender_adlige = 0.00234;
    double steu_leinengewaender_handwerker = 0.01000;
    double steu_leinengewaender_kaufleute = 0.01000;
    double steu_leinengewaender_adlige = 0.01000;
    double steu_lederwaemser_kaufleute = 0.01750;
    double steu_lederwaemser_adlige = 0.01750;
    double steu_pelzmaentel_kaufleute = 0.02000;
    double steu_pelzmaentel_adlige = 0.02000;
    double steu_brokatgewaender_adlige = 0.18800;

    // Besitz
    double steu_buecher_kaufleute = 0.01000;
    double steu_buecher_adlige = 0.01000;
    double steu_kerzenleuchter_adlige = 0.06200;
    double steu_brillen_adlige = 0.06000;

    // Produktionszeiten
    // Baumaterialien
    int prod_time_holz = 105;
    int prod_time_stein = 180;
    int prod_time_eisenerz = 210;
    int prod_time_kohle = 210;
    int prod_time_kohlemine = 105;
    int prod_time_eisen = 315;
    int prod_time_werkzeug = 420;
    int prod_time_pottasche = 840;
    int prod_time_quarz = 1260;
    int prod_time_glas = 1260;

    // Nahrung
    int prod_time_fisch = 110;
    int prod_time_weizen = 440;
    int prod_time_mehl = 220;
    int prod_time_brot = 220;
    int prod_time_vieh = 840;
    int prod_time_fleisch = 630;

    // Getränke
    int prod_time_milch = 235;
    int prod_time_aepfel = 380;
    int prod_time_most = 190;
    int prod_time_kraeuter = 660;
    int prod_time_bier = 330;
    int prod_time_trauben = 2205;
    int prod_time_faesser = 945;
    int prod_time_wein = 1260;

    // Kleidung
    int prod_time_woole = 200;
    int prod_time_wollgewaender = 200;
    int prod_time_hanf = 630;
    int prod_time_leinengewaender = 210;
    int prod_time_tierhaeute = 840;
    int prod_time_sole = 140;
    int prod_time_salz = 210;
    int prod_time_lederwaemser = 420;
    int prod_time_pelze = 600;
    int prod_time_pelzmaentel = 600;
    int prod_time_golderz = 1260;
    int prod_time_gold = 1050;
    int prod_time_brokatgewaender = 3150;

    // Besitz
    int prod_time_papier = 210;
    int prod_time_buecher = 630;
    int prod_time_bienenwachs = 840;
    int prod_time_kerzen = 1260;
    int prod_time_kerzenleuchter = 840;
    int prod_time_kupfererz = 280;
    int prod_time_messing = 420;
    int prod_time_brillen = 1260;

    // Produktionsketten
    int prk_fisch = 0;
    int prk_brot = 0;
    int prk_fleisch = 0;
    int prk_milch = 0;
    int prk_most = 0;
    int prk_bier = 0;
    int prk_wein = 0;
    int prk_wollgewaender = 0;
    int prk_leinengewaender = 0;
    int prk_lederwaemser = 0;
    int prk_pelzmaentel = 0;
    int prk_brokatgewaender = 0;
    int prk_buecher = 0;
    int prk_kerzenleuchter = 0;
    int prk_brillen = 0;

    public static void main(String [] args){
        Verbrauchsrechner verbrauchsrechner = new Verbrauchsrechner(1568, 2325, 1258, 2324);
    }

    // Konstruktor
    public Verbrauchsrechner(int anzahl_bauern, int anzahl_handwerker, int anzahl_kaufleute, int anzahl_adlige){
        this.anzahl_bauern = anzahl_bauern;
        this.anzahl_handwerker = anzahl_handwerker;
        this.anzahl_kaufleute = anzahl_kaufleute;
        this.anzahl_adlige = anzahl_adlige;
        // Berechnungsfunktion
        berechne();
    }

    private void berechne(){
        prk_fisch = berechneFisch();
        prk_brot = berechneBrot();
        prk_fleisch = berechneFleisch();
        prk_milch = berechneMilch();
        prk_most = berechneMost();
        prk_bier = berechneBier();
        prk_wein = berechneWein();
        prk_wollgewaender = berechneWollgewaender();
        prk_leinengewaender = berechneLeinengewaender();
        prk_lederwaemser = berechneLederwaemser();
        prk_pelzmaentel = berechnePelzmaentel();
        prk_brokatgewaender = berechneBrokatgewaender();
        prk_buecher = berechneBuecher();
        prk_kerzenleuchter = berechneKerzenleuchter();
        prk_brillen = berechneBrillen();
    }
    
    private int berechneFisch(){
        int anzahl_fischer = 0;
        double prod_per_tick = dauer_tick/prod_time_fisch;
        //System.out.println(prod_per_tick);
        double verbr_per_tick_bauern = anzahl_bauern*verbr_fisch_bauern;
        double verbr_per_tick_handwerker = anzahl_handwerker*verbr_fisch_handwerker;
        double verbr_per_tick_kaufleute = anzahl_kaufleute*verbr_fisch_kaufleute;
        double verbr_per_tick_adlige = anzahl_adlige*verbr_fisch_adlige;
        double verbr_summe = verbr_per_tick_bauern + verbr_per_tick_handwerker + verbr_per_tick_kaufleute + verbr_per_tick_adlige;
        //System.out.println(verbr_per_tick);
        // Anzahl Produktionsketten
        anzahl_fischer = (int) (verbr_summe/prod_per_tick);
        System.out.println(anzahl_fischer + 1);
        return anzahl_fischer;
    }
    
    private int berechneBrot() {
        return 0;
    }
    private int berechneFleisch() {
        return 0;
    }
    private int berechneMilch() {
        return 0;
    }
    private int berechneMost() {
        return 0;
    }
    private int berechneBier() {
        return 0;
    }
    private int berechneWein() {
        return 0;
    }
    private int berechneWollgewaender() {
        return 0;
    }
    private int berechneLeinengewaender() {
        return 0;
    }
    private int berechneLederwaemser() {
        return 0;
    }
    private int berechnePelzmaentel() {
        return 0;
    }
    private int berechneBrokatgewaender() {
        return 0;
    }
    private int berechneBuecher() {
        return 0;
    }
    private int berechneKerzenleuchter() {
        return 0;
    }
    private int berechneBrillen() {
        return 0;
    }
}
