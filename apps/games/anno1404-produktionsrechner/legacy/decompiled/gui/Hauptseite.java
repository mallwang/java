/*
 * Decompiled with CFR 0.152.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Hauptseite {
    public static Font f;
    public static Font font;
    public static JFrame frame;
    public static JPanel pnlBedarf;
    public static JPanel pnlProduktionsketten;
    public static JLabel lblBettler;
    public static JLabel lblBauern;
    public static JLabel lblBuerger;
    public static JLabel lblPatrizier;
    public static JLabel lblAdelige;
    public static JLabel lblNomaden;
    public static JLabel lblGesandte;
    public static JLabel lblFisch;
    public static JLabel lblMost;
    public static JLabel lblGewuerze;
    public static JLabel lblLeinenkutten;
    public static JLabel lblBrot;
    public static JLabel lblBier;
    public static JLabel lblLederwaemser;
    public static JLabel lblBuecher;
    public static JLabel lblFleisch;
    public static JLabel lblWein;
    public static JLabel lblPelzmaentel;
    public static JLabel lblBrokatgewaender;
    public static JLabel lblKerzenleuchter;
    public static JLabel lblBrillen;
    public static JLabel lblDatteln;
    public static JLabel lblMilch;
    public static JLabel lblTeppiche;
    public static JLabel lblKaffee;
    public static JLabel lblPerlenketten;
    public static JLabel lblDuftwasser;
    public static JLabel lblMarzipan;
    public static JLabel lblAnzahlFisch;
    public static JLabel lblAnzahlMost;
    public static JLabel lblAnzahlGewuerze;
    public static JLabel lblAnzahlLeinenkutten;
    public static JLabel lblAnzahlBrot;
    public static JLabel lblAnzahlBier;
    public static JLabel lblAnzahlLederwaemser;
    public static JLabel lblAnzahlBuecher;
    public static JLabel lblAnzahlFleisch;
    public static JLabel lblAnzahlWein;
    public static JLabel lblAnzahlPelzmaentel;
    public static JLabel lblAnzahlBrokatgewaender;
    public static JLabel lblAnzahlKerzenleuchter;
    public static JLabel lblAnzahlBrillen;
    public static JLabel lblAnzahlDatteln;
    public static JLabel lblAnzahlMilch;
    public static JLabel lblAnzahlTeppiche;
    public static JLabel lblAnzahlKaffee;
    public static JLabel lblAnzahlPerlenketten;
    public static JLabel lblAnzahlDuftwasser;
    public static JLabel lblAnzahlMarzipan;
    public static JLabel lblFehlerBettler;
    public static JLabel lblFehlerBauern;
    public static JLabel lblFehlerBuerger;
    public static JLabel lblFehlerPatrizier;
    public static JLabel lblFehlerAdelige;
    public static JLabel lblFehlerNomaden;
    public static JLabel lblFehlerGesandte;
    public static JLabel lblEingabeOKBettler;
    public static JLabel lblEingabeOKBauern;
    public static JLabel lblEingabeOKBuerger;
    public static JLabel lblEingabeOKPatrizier;
    public static JLabel lblEingabeOKAdelige;
    public static JLabel lblEingabeOKNomaden;
    public static JLabel lblEingabeOKGesandte;
    public static JLabel lblFischproduktion;
    public static JLabel lblMostproduktion;
    public static JLabel lblGewuerzproduktion;
    public static JLabel lblLeinenkuttenproduktion;
    public static JLabel lblBrotproduktion;
    public static JLabel lblBierproduktion;
    public static JLabel lblLederwaemserproduktion;
    public static JLabel lblBuecherproduktion;
    public static JLabel lblFleischproduktion;
    public static JLabel lblWeinproduktion;
    public static JLabel lblPelzmaentelproduktion;
    public static JLabel lblKerzenleuchterproduktion;
    public static JLabel lblBrillenproduktion;
    public static JLabel lblBrokatgewaenderproduktion;
    public static JLabel lblDattelproduktion;
    public static JLabel lblMilchproduktion;
    public static JLabel lblTeppichproduktion;
    public static JLabel lblKaffeeproduktion;
    public static JLabel lblPerlenkettenproduktion;
    public static JLabel lblDuftwasserproduktion;
    public static JLabel lblMarzipanproduktion;
    public static JLabel lblSonstige;
    public static JLabel lblSeileproduktion;
    public static JLabel lblMessingproduktion;
    public static JLabel lblSteinproduktion;
    public static JLabel lblKanonenproduktionen;
    public static JLabel lblWerkzeugproduktion;
    public static JLabel lblMosaikproduktion;
    public static JLabel lblWaffenproduktion;
    public static JLabel lblGlasproduktion;
    public static JLabel lblSalzproduktion;
    public static JLabel lblEisenproduktion;
    public static JLabel lblHolzproduktion;
    public static JLabel lblKriegsmaschinenproduktion;
    public static JTextField txtBettler;
    public static JTextField txtBauern;
    public static JTextField txtBuerger;
    public static JTextField txtPatrizier;
    public static JTextField txtAdelige;
    public static JTextField txtNomaden;
    public static JTextField txtGesandte;
    public static JButton btnZuruecksetzen;
    public static JButton btnFisch;
    public static JButton btnMost;
    public static JButton btnGewuerze;
    public static JButton btnLeinenkutten;
    public static JButton btnBier;
    public static JButton btnBrot;
    public static JButton btnLederwaemser;
    public static JButton btnBuecher;
    public static JButton btnFleisch;
    public static JButton btnWein;
    public static JButton btnPelzmaentel;
    public static JButton btnBrokatgewaender;
    public static JButton btnKerzenleuchter;
    public static JButton btnBrillen;
    public static JButton btnDatteln;
    public static JButton btnMilch;
    public static JButton btnTeppiche;
    public static JButton btnKaffee;
    public static JButton btnPerlenketten;
    public static JButton btnDuftwasser;
    public static JButton btnMarzipan;
    public static JButton btnSeile;
    public static JButton btnMessing;
    public static JButton btnStein;
    public static JButton btnKanonen;
    public static JButton btnWerkzeuge;
    public static JButton btnMosaik;
    public static JButton btnWaffen;
    public static JButton btnGlas;
    public static JButton btnSalz;
    public static JButton btnEisen;
    public static JButton btnHolz;
    public static JButton btnKriegsmaschinen;
    public static Dimension dimension;

    public Hauptseite() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        Hauptseite.initialize();
    }

    private static void initialize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.LookAndFeelInfo[] lookAndFeelInfoArray = UIManager.getInstalledLookAndFeels();
        int n = lookAndFeelInfoArray.length;
        int n2 = 0;
        while (n2 < n) {
            UIManager.LookAndFeelInfo info = lookAndFeelInfoArray[n2];
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
            ++n2;
        }
        f = new Font("Segue UI", 1, 12);
        font = new Font("Segue UI", 1, 12);
        dimension = new Dimension();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (Hauptseite.dimension.width - 1150) / 2;
        int y = (Hauptseite.dimension.height - 600) / 2;
        frame = new JFrame("ANNO 1404 Produktionsrechner \u00a9 by Markus Allwang");
        frame.setDefaultCloseOperation(3);
        frame.setLayout(null);
        frame.setBounds(x, y, 1150, 600);
        frame.setResizable(false);
        pnlBedarf = new JPanel();
        pnlBedarf.setLayout(null);
        pnlBedarf.setBounds(0, 0, 1150, 330);
        pnlProduktionsketten = new JPanel();
        pnlProduktionsketten.setLayout(null);
        pnlProduktionsketten.setBounds(0, 341, 1150, 231);
        lblBettler = new JLabel();
        lblBettler.setBounds(15, 5, 50, 50);
        lblBettler.setFont(f);
        lblBettler.setIcon(new ImageIcon("images/Bettler.png"));
        lblBettler.setToolTipText("Bettler");
        lblBauern = new JLabel();
        lblBauern.setBounds(110, 5, 50, 50);
        lblBauern.setFont(f);
        lblBauern.setIcon(new ImageIcon("images/Bauer.png"));
        lblBauern.setToolTipText("Bauer");
        lblBuerger = new JLabel();
        lblBuerger.setBounds(250, 5, 50, 50);
        lblBuerger.setFont(f);
        lblBuerger.setIcon(new ImageIcon("images/Buerger.png"));
        lblBuerger.setToolTipText("B\u00fcrger");
        lblPatrizier = new JLabel();
        lblPatrizier.setBounds(390, 5, 50, 50);
        lblPatrizier.setFont(f);
        lblPatrizier.setIcon(new ImageIcon("images/Patrizier.png"));
        lblPatrizier.setToolTipText("Patrizier");
        lblAdelige = new JLabel();
        lblAdelige.setBounds(530, 5, 50, 50);
        lblAdelige.setFont(f);
        lblAdelige.setIcon(new ImageIcon("images/Adeliger.png"));
        lblAdelige.setToolTipText("Adeliger");
        lblNomaden = new JLabel();
        lblNomaden.setBounds(690, 5, 50, 50);
        lblNomaden.setFont(f);
        lblNomaden.setIcon(new ImageIcon("images/Nomade.png"));
        lblNomaden.setToolTipText("Nomade");
        lblGesandte = new JLabel();
        lblGesandte.setBounds(830, 5, 50, 50);
        lblGesandte.setFont(f);
        lblGesandte.setIcon(new ImageIcon("images/Gesandte.png"));
        lblGesandte.setToolTipText("Gesandter");
        btnFisch = new JButton();
        btnFisch.setBounds(110, 85, 30, 30);
        btnFisch.setIcon(new ImageIcon("images/Fisch.png"));
        btnFisch.setToolTipText("Fisch");
        lblAnzahlFisch = new JLabel("0 (0)");
        lblAnzahlFisch.setBounds(145, 90, 100, 20);
        lblFischproduktion = new JLabel();
        lblFischproduktion.setBounds(480, 20, 191, 167);
        btnMost = new JButton();
        btnMost.setBounds(110, 125, 30, 30);
        btnMost.setIcon(new ImageIcon("images/Most.png"));
        btnMost.setToolTipText("Most");
        lblAnzahlMost = new JLabel("0 (0)");
        lblAnzahlMost.setBounds(145, 130, 100, 20);
        lblMostproduktion = new JLabel();
        lblMostproduktion.setBounds(495, 20, 160, 167);
        lblAnzahlGewuerze = new JLabel("0 (0)");
        lblAnzahlGewuerze.setBounds(285, 90, 100, 20);
        lblGewuerzproduktion = new JLabel();
        lblGewuerzproduktion.setBounds(474, 20, 203, 167);
        btnGewuerze = new JButton();
        btnGewuerze.setBounds(250, 85, 30, 30);
        btnGewuerze.setIcon(new ImageIcon("images/Gewuerze.png"));
        btnGewuerze.setToolTipText("Gew\u00fcrze");
        lblAnzahlLeinenkutten = new JLabel("0 (0)");
        lblAnzahlLeinenkutten.setBounds(285, 130, 100, 20);
        lblLeinenkuttenproduktion = new JLabel();
        lblLeinenkuttenproduktion.setBounds(410, 20, 330, 167);
        btnLeinenkutten = new JButton();
        btnLeinenkutten.setBounds(250, 125, 30, 30);
        btnLeinenkutten.setIcon(new ImageIcon("images/Leinenkutten.png"));
        btnLeinenkutten.setToolTipText("Leinenkutten");
        lblAnzahlBrot = new JLabel("0 (0)");
        lblAnzahlBrot.setBounds(425, 90, 100, 20);
        lblBrotproduktion = new JLabel();
        lblBrotproduktion.setBounds(383, 20, 384, 167);
        btnBrot = new JButton();
        btnBrot.setBounds(390, 85, 30, 30);
        btnBrot.setIcon(new ImageIcon("images/Brot.png"));
        btnBrot.setToolTipText("Brot");
        lblAnzahlBier = new JLabel("0 (0)");
        lblAnzahlBier.setBounds(425, 130, 100, 20);
        lblBierproduktion = new JLabel();
        lblBierproduktion.setBounds(375, 20, 451, 167);
        btnBier = new JButton();
        btnBier.setBounds(390, 125, 30, 30);
        btnBier.setIcon(new ImageIcon("images/Bier.png"));
        btnBier.setToolTipText("Bier");
        lblAnzahlLederwaemser = new JLabel("0 (0)");
        lblAnzahlLederwaemser.setBounds(425, 170, 100, 20);
        lblLederwaemserproduktion = new JLabel();
        lblLederwaemserproduktion.setBounds(260, 20, 629, 167);
        btnLederwaemser = new JButton();
        btnLederwaemser.setBounds(390, 165, 30, 30);
        btnLederwaemser.setIcon(new ImageIcon("images/Lederwaemser.png"));
        btnLederwaemser.setToolTipText("Lederw\u00e4mser");
        lblAnzahlBuecher = new JLabel("0 (0)");
        lblAnzahlBuecher.setBounds(425, 210, 100, 20);
        lblBuecherproduktion = new JLabel();
        lblBuecherproduktion.setBounds(304, 20, 543, 167);
        btnBuecher = new JButton();
        btnBuecher.setBounds(390, 205, 30, 30);
        btnBuecher.setIcon(new ImageIcon("images/Buecher.png"));
        btnBuecher.setToolTipText("B\u00fccher");
        lblAnzahlFleisch = new JLabel("0 (0)");
        lblAnzahlFleisch.setBounds(565, 90, 100, 20);
        lblFleischproduktion = new JLabel();
        lblFleischproduktion.setBounds(286, 20, 577, 167);
        btnFleisch = new JButton();
        btnFleisch.setBounds(530, 85, 30, 30);
        btnFleisch.setIcon(new ImageIcon("images/Fleisch.png"));
        btnFleisch.setToolTipText("Fleisch");
        lblAnzahlWein = new JLabel("0 (0)");
        lblAnzahlWein.setBounds(565, 130, 100, 20);
        lblWeinproduktion = new JLabel();
        lblWeinproduktion.setBounds(155, 20, 840, 167);
        btnWein = new JButton();
        btnWein.setBounds(530, 125, 30, 30);
        btnWein.setIcon(new ImageIcon("images/Wein.png"));
        btnWein.setToolTipText("Wein");
        lblAnzahlPelzmaentel = new JLabel("0 (0)");
        lblAnzahlPelzmaentel.setBounds(565, 170, 100, 20);
        lblPelzmaentelproduktion = new JLabel();
        lblPelzmaentelproduktion.setBounds(258, 20, 633, 167);
        btnPelzmaentel = new JButton();
        btnPelzmaentel.setBounds(530, 165, 30, 30);
        btnPelzmaentel.setIcon(new ImageIcon("images/Pelzmaentel.png"));
        btnPelzmaentel.setToolTipText("Pelzm\u00e4ntel");
        lblAnzahlBrokatgewaender = new JLabel("0 (0)");
        lblAnzahlBrokatgewaender.setBounds(565, 210, 100, 20);
        lblBrokatgewaenderproduktion = new JLabel();
        lblBrokatgewaenderproduktion.setBounds(205, 20, 741, 167);
        btnBrokatgewaender = new JButton();
        btnBrokatgewaender.setBounds(530, 205, 30, 30);
        btnBrokatgewaender.setIcon(new ImageIcon("images/Brokatgewaender.png"));
        btnBrokatgewaender.setToolTipText("Brokatgew\u00e4nder");
        lblAnzahlKerzenleuchter = new JLabel("0 (0)");
        lblAnzahlKerzenleuchter.setBounds(565, 250, 100, 20);
        lblKerzenleuchterproduktion = new JLabel();
        lblKerzenleuchterproduktion.setBounds(107, 20, 936, 167);
        btnKerzenleuchter = new JButton();
        btnKerzenleuchter.setBounds(530, 245, 30, 30);
        btnKerzenleuchter.setIcon(new ImageIcon("images/Kerzenleuchter.png"));
        btnKerzenleuchter.setToolTipText("Kerzenleuchter");
        lblAnzahlBrillen = new JLabel("0 (0)");
        lblAnzahlBrillen.setBounds(565, 290, 100, 20);
        lblBrillenproduktion = new JLabel();
        lblBrillenproduktion.setBounds(240, 20, 670, 167);
        btnBrillen = new JButton();
        btnBrillen.setBounds(530, 285, 30, 30);
        btnBrillen.setIcon(new ImageIcon("images/Brillen.png"));
        btnBrillen.setToolTipText("Brillen");
        lblAnzahlDatteln = new JLabel("0 (0)");
        lblAnzahlDatteln.setBounds(725, 90, 100, 20);
        lblDattelproduktion = new JLabel();
        lblDattelproduktion.setBounds(470, 20, 210, 167);
        btnDatteln = new JButton();
        btnDatteln.setBounds(690, 85, 30, 30);
        btnDatteln.setIcon(new ImageIcon("images/Datteln.png"));
        btnDatteln.setToolTipText("Datteln");
        lblAnzahlMilch = new JLabel("0 (0)");
        lblAnzahlMilch.setBounds(725, 130, 100, 20);
        lblMilchproduktion = new JLabel();
        lblMilchproduktion.setBounds(483, 20, 184, 167);
        btnMilch = new JButton();
        btnMilch.setBounds(690, 125, 30, 30);
        btnMilch.setIcon(new ImageIcon("images/Milch.png"));
        btnMilch.setToolTipText("Milch");
        lblAnzahlTeppiche = new JLabel("0 (0)");
        lblAnzahlTeppiche.setBounds(725, 170, 100, 20);
        lblTeppichproduktion = new JLabel();
        lblTeppichproduktion.setBounds(322, 20, 506, 167);
        btnTeppiche = new JButton();
        btnTeppiche.setBounds(690, 165, 30, 30);
        btnTeppiche.setIcon(new ImageIcon("images/Teppiche.png"));
        btnTeppiche.setToolTipText("Teppiche");
        lblAnzahlKaffee = new JLabel("0 (0)");
        lblAnzahlKaffee.setBounds(865, 90, 100, 20);
        lblKaffeeproduktion = new JLabel();
        lblKaffeeproduktion.setBounds(426, 20, 297, 167);
        btnKaffee = new JButton();
        btnKaffee.setBounds(830, 85, 30, 30);
        btnKaffee.setIcon(new ImageIcon("images/Kaffee.png"));
        btnKaffee.setToolTipText("Kaffee");
        lblAnzahlPerlenketten = new JLabel("0 (0)");
        lblAnzahlPerlenketten.setBounds(865, 130, 100, 20);
        lblPerlenkettenproduktion = new JLabel();
        lblPerlenkettenproduktion.setBounds(392, 20, 365, 167);
        btnPerlenketten = new JButton();
        btnPerlenketten.setBounds(830, 125, 30, 30);
        btnPerlenketten.setIcon(new ImageIcon("images/Perlenketten.png"));
        btnPerlenketten.setToolTipText("Perlenketten");
        lblAnzahlDuftwasser = new JLabel("0 (0)");
        lblAnzahlDuftwasser.setBounds(865, 170, 100, 20);
        lblDuftwasserproduktion = new JLabel();
        lblDuftwasserproduktion.setBounds(424, 20, 352, 167);
        btnDuftwasser = new JButton();
        btnDuftwasser.setBounds(830, 165, 30, 30);
        btnDuftwasser.setIcon(new ImageIcon("images/Duftwasser.png"));
        btnDuftwasser.setToolTipText("Duftwasser");
        lblAnzahlMarzipan = new JLabel("0 (0)");
        lblAnzahlMarzipan.setBounds(865, 210, 100, 20);
        lblMarzipanproduktion = new JLabel();
        lblMarzipanproduktion.setBounds(267, 20, 616, 167);
        btnMarzipan = new JButton();
        btnMarzipan.setBounds(830, 205, 30, 30);
        btnMarzipan.setIcon(new ImageIcon("images/Marzipan.png"));
        btnMarzipan.setToolTipText("Marzipan");
        lblSeileproduktion = new JLabel();
        lblSeileproduktion.setBounds(425, 20, 300, 167);
        lblMessingproduktion = new JLabel();
        lblMessingproduktion.setBounds(323, 20, 504, 167);
        lblSteinproduktion = new JLabel();
        lblSteinproduktion.setBounds(464, 20, 222, 167);
        lblKanonenproduktionen = new JLabel();
        lblKanonenproduktionen.setBounds(171, 20, 808, 167);
        lblWerkzeugproduktion = new JLabel();
        lblWerkzeugproduktion.setBounds(222, 20, 706, 167);
        lblMosaikproduktion = new JLabel();
        lblMosaikproduktion.setBounds(335, 20, 480, 167);
        lblWaffenproduktion = new JLabel();
        lblWaffenproduktion.setBounds(250, 20, 650, 167);
        lblGlasproduktion = new JLabel();
        lblGlasproduktion.setBounds(359, 20, 433, 167);
        lblSalzproduktion = new JLabel();
        lblSalzproduktion.setBounds(383, 20, 385, 167);
        lblEisenproduktion = new JLabel();
        lblEisenproduktion.setBounds(341, 20, 467, 167);
        lblHolzproduktion = new JLabel();
        lblHolzproduktion.setBounds(467, 20, 216, 167);
        lblKriegsmaschinenproduktion = new JLabel();
        lblKriegsmaschinenproduktion.setBounds(180, 20, 790, 167);
        btnSeile = new JButton();
        btnSeile.setBounds(975, 120, 30, 30);
        btnSeile.setIcon(new ImageIcon("images/Seile.png"));
        btnSeile.setToolTipText("Seile");
        btnMessing = new JButton();
        btnMessing.setBounds(1015, 120, 30, 30);
        btnMessing.setIcon(new ImageIcon("images/Messing.png"));
        btnMessing.setToolTipText("Messing");
        btnStein = new JButton();
        btnStein.setBounds(1055, 120, 30, 30);
        btnStein.setIcon(new ImageIcon("images/Stein.png"));
        btnStein.setToolTipText("Stein");
        btnKanonen = new JButton();
        btnKanonen.setBounds(975, 160, 30, 30);
        btnKanonen.setIcon(new ImageIcon("images/Kanonen.png"));
        btnKanonen.setToolTipText("Kanonen");
        btnWerkzeuge = new JButton();
        btnWerkzeuge.setBounds(1015, 160, 30, 30);
        btnWerkzeuge.setIcon(new ImageIcon("images/Werkzeuge.png"));
        btnWerkzeuge.setToolTipText("Werkzeuge");
        btnMosaik = new JButton();
        btnMosaik.setBounds(1055, 160, 30, 30);
        btnMosaik.setIcon(new ImageIcon("images/Mosaik.png"));
        btnMosaik.setToolTipText("Mosaik");
        btnWaffen = new JButton();
        btnWaffen.setBounds(975, 200, 30, 30);
        btnWaffen.setIcon(new ImageIcon("images/Waffen.png"));
        btnWaffen.setToolTipText("Waffen");
        btnGlas = new JButton();
        btnGlas.setBounds(1015, 200, 30, 30);
        btnGlas.setIcon(new ImageIcon("images/Glas.png"));
        btnGlas.setToolTipText("Glas");
        btnSalz = new JButton();
        btnSalz.setBounds(1055, 200, 30, 30);
        btnSalz.setIcon(new ImageIcon("images/Salz.png"));
        btnSalz.setToolTipText("Salz");
        btnEisen = new JButton();
        btnEisen.setBounds(975, 240, 30, 30);
        btnEisen.setIcon(new ImageIcon("images/Eisen.png"));
        btnEisen.setToolTipText("Eisen");
        btnHolz = new JButton();
        btnHolz.setBounds(1015, 240, 30, 30);
        btnHolz.setIcon(new ImageIcon("images/Holz.png"));
        btnHolz.setToolTipText("Holz");
        btnKriegsmaschinen = new JButton();
        btnKriegsmaschinen.setBounds(1055, 240, 30, 30);
        btnKriegsmaschinen.setIcon(new ImageIcon("images/Kriegsmaschinen.png"));
        btnKriegsmaschinen.setToolTipText("Kriegsmaschinen");
        lblFehlerBettler = new JLabel("");
        lblFehlerBettler.setBounds(65, 55, 200, 20);
        lblFehlerBettler.setFont(font);
        lblFehlerBettler.setForeground(Color.red);
        lblFehlerBauern = new JLabel("");
        lblFehlerBauern.setBounds(160, 55, 200, 20);
        lblFehlerBauern.setFont(font);
        lblFehlerBauern.setForeground(Color.red);
        lblFehlerBuerger = new JLabel("");
        lblFehlerBuerger.setBounds(300, 55, 200, 20);
        lblFehlerBuerger.setFont(font);
        lblFehlerBuerger.setForeground(Color.red);
        lblFehlerPatrizier = new JLabel("");
        lblFehlerPatrizier.setBounds(440, 55, 200, 20);
        lblFehlerPatrizier.setFont(font);
        lblFehlerPatrizier.setForeground(Color.red);
        lblFehlerAdelige = new JLabel("");
        lblFehlerAdelige.setBounds(580, 55, 200, 20);
        lblFehlerAdelige.setFont(font);
        lblFehlerAdelige.setForeground(Color.red);
        lblFehlerNomaden = new JLabel("");
        lblFehlerNomaden.setBounds(740, 55, 200, 20);
        lblFehlerNomaden.setFont(font);
        lblFehlerNomaden.setForeground(Color.red);
        lblFehlerGesandte = new JLabel("");
        lblFehlerGesandte.setBounds(880, 55, 200, 20);
        lblFehlerGesandte.setFont(font);
        lblFehlerGesandte.setForeground(Color.red);
        lblEingabeOKBettler = new JLabel("");
        lblEingabeOKBettler.setBounds(65, 55, 200, 20);
        lblEingabeOKBettler.setFont(font);
        lblEingabeOKBettler.setForeground(new Color(0, 140, 0));
        lblEingabeOKBauern = new JLabel("");
        lblEingabeOKBauern.setBounds(160, 55, 200, 20);
        lblEingabeOKBauern.setFont(font);
        lblEingabeOKBauern.setForeground(new Color(0, 140, 0));
        lblEingabeOKBuerger = new JLabel("");
        lblEingabeOKBuerger.setBounds(300, 55, 200, 20);
        lblEingabeOKBuerger.setFont(font);
        lblEingabeOKBuerger.setForeground(new Color(0, 140, 0));
        lblEingabeOKPatrizier = new JLabel("");
        lblEingabeOKPatrizier.setBounds(440, 55, 200, 20);
        lblEingabeOKPatrizier.setFont(font);
        lblEingabeOKPatrizier.setForeground(new Color(0, 140, 0));
        lblEingabeOKAdelige = new JLabel("");
        lblEingabeOKAdelige.setBounds(580, 55, 200, 20);
        lblEingabeOKAdelige.setFont(font);
        lblEingabeOKAdelige.setForeground(new Color(0, 140, 0));
        lblEingabeOKNomaden = new JLabel("");
        lblEingabeOKNomaden.setBounds(740, 55, 200, 20);
        lblEingabeOKNomaden.setFont(font);
        lblEingabeOKNomaden.setForeground(new Color(0, 140, 0));
        lblEingabeOKGesandte = new JLabel("");
        lblEingabeOKGesandte.setBounds(880, 55, 200, 20);
        lblEingabeOKGesandte.setFont(font);
        lblEingabeOKGesandte.setForeground(new Color(0, 140, 0));
        lblSonstige = new JLabel("Sonstige");
        lblSonstige.setBounds(975, 90, 150, 20);
        txtBettler = new JTextField();
        txtBettler.setBounds(12, 55, 50, 22);
        txtBettler.setToolTipText("Anzahl Bettler");
        txtBauern = new JTextField();
        txtBauern.setBounds(107, 55, 50, 22);
        txtBauern.setToolTipText("Anzahl Bauern");
        txtBuerger = new JTextField();
        txtBuerger.setBounds(247, 55, 50, 22);
        txtBuerger.setToolTipText("Anzahl B\u00fcrger");
        txtPatrizier = new JTextField();
        txtPatrizier.setBounds(387, 55, 50, 22);
        txtPatrizier.setToolTipText("Anzahl Patrizier");
        txtAdelige = new JTextField();
        txtAdelige.setBounds(527, 55, 50, 22);
        txtAdelige.setToolTipText("Anzahl Adelige");
        txtNomaden = new JTextField();
        txtNomaden.setBounds(687, 55, 50, 22);
        txtNomaden.setToolTipText("Anzahl Nomaden");
        txtGesandte = new JTextField();
        txtGesandte.setBounds(827, 55, 50, 22);
        txtGesandte.setToolTipText("Anzahl Gesandte");
        btnZuruecksetzen = new JButton();
        btnZuruecksetzen.setBounds(950, 25, 150, 50);
        btnZuruecksetzen.setText("Felder zur\u00fccksetzen");
        btnZuruecksetzen.setToolTipText("Alle Felder zur\u00fccksetzen");
        pnlBedarf.add(lblBettler);
        pnlBedarf.add(lblBauern);
        pnlBedarf.add(lblBuerger);
        pnlBedarf.add(lblPatrizier);
        pnlBedarf.add(lblAdelige);
        pnlBedarf.add(lblNomaden);
        pnlBedarf.add(lblGesandte);
        pnlBedarf.add(btnFisch);
        pnlBedarf.add(lblAnzahlFisch);
        pnlBedarf.add(btnMost);
        pnlBedarf.add(lblAnzahlMost);
        pnlBedarf.add(btnGewuerze);
        pnlBedarf.add(lblAnzahlGewuerze);
        pnlBedarf.add(btnLeinenkutten);
        pnlBedarf.add(lblAnzahlLeinenkutten);
        pnlBedarf.add(btnBrot);
        pnlBedarf.add(lblAnzahlBrot);
        pnlBedarf.add(lblBrotproduktion);
        pnlBedarf.add(btnBier);
        pnlBedarf.add(lblAnzahlBier);
        pnlBedarf.add(lblBierproduktion);
        pnlBedarf.add(btnLederwaemser);
        pnlBedarf.add(lblAnzahlLederwaemser);
        pnlBedarf.add(btnBuecher);
        pnlBedarf.add(lblAnzahlBuecher);
        pnlBedarf.add(btnFleisch);
        pnlBedarf.add(lblAnzahlFleisch);
        pnlBedarf.add(btnWein);
        pnlBedarf.add(lblAnzahlWein);
        pnlBedarf.add(btnPelzmaentel);
        pnlBedarf.add(lblAnzahlPelzmaentel);
        pnlBedarf.add(btnBrokatgewaender);
        pnlBedarf.add(lblAnzahlBrokatgewaender);
        pnlBedarf.add(btnKerzenleuchter);
        pnlBedarf.add(lblAnzahlKerzenleuchter);
        pnlBedarf.add(btnBrillen);
        pnlBedarf.add(lblAnzahlBrillen);
        pnlBedarf.add(btnDatteln);
        pnlBedarf.add(lblAnzahlDatteln);
        pnlBedarf.add(btnMilch);
        pnlBedarf.add(lblAnzahlMilch);
        pnlBedarf.add(btnTeppiche);
        pnlBedarf.add(lblAnzahlTeppiche);
        pnlBedarf.add(btnKaffee);
        pnlBedarf.add(lblAnzahlKaffee);
        pnlBedarf.add(btnPerlenketten);
        pnlBedarf.add(lblAnzahlPerlenketten);
        pnlBedarf.add(btnDuftwasser);
        pnlBedarf.add(lblAnzahlDuftwasser);
        pnlBedarf.add(btnMarzipan);
        pnlBedarf.add(lblAnzahlMarzipan);
        pnlBedarf.add(btnSeile);
        pnlBedarf.add(btnMessing);
        pnlBedarf.add(btnStein);
        pnlBedarf.add(btnKanonen);
        pnlBedarf.add(btnWerkzeuge);
        pnlBedarf.add(btnMosaik);
        pnlBedarf.add(btnWaffen);
        pnlBedarf.add(btnGlas);
        pnlBedarf.add(btnSalz);
        pnlBedarf.add(btnEisen);
        pnlBedarf.add(btnHolz);
        pnlBedarf.add(btnKriegsmaschinen);
        pnlBedarf.add(lblFehlerBettler);
        pnlBedarf.add(lblFehlerBauern);
        pnlBedarf.add(lblFehlerBuerger);
        pnlBedarf.add(lblFehlerPatrizier);
        pnlBedarf.add(lblFehlerAdelige);
        pnlBedarf.add(lblFehlerNomaden);
        pnlBedarf.add(lblFehlerGesandte);
        pnlBedarf.add(lblEingabeOKBettler);
        pnlBedarf.add(lblEingabeOKBauern);
        pnlBedarf.add(lblEingabeOKBuerger);
        pnlBedarf.add(lblEingabeOKPatrizier);
        pnlBedarf.add(lblEingabeOKAdelige);
        pnlBedarf.add(lblEingabeOKNomaden);
        pnlBedarf.add(lblEingabeOKGesandte);
        pnlBedarf.add(lblSonstige);
        pnlBedarf.add(txtBettler);
        pnlBedarf.add(txtBauern);
        pnlBedarf.add(txtBuerger);
        pnlBedarf.add(txtPatrizier);
        pnlBedarf.add(txtAdelige);
        pnlBedarf.add(txtNomaden);
        pnlBedarf.add(txtGesandte);
        pnlBedarf.add(btnZuruecksetzen);
        frame.add(pnlBedarf);
        frame.add(pnlProduktionsketten);
    }

    public String getAnzahl(String name) {
        if (name.equals("Bettler")) {
            return this.getAnzahlBettler();
        }
        if (name.equals("Bauern")) {
            return this.getAnzahlBauern();
        }
        if (name.equals("Buerger")) {
            return this.getAnzahlBuerger();
        }
        if (name.equals("Patrizier")) {
            return this.getAnzahlPatrizier();
        }
        if (name.equals("Adelige")) {
            return this.getAnzahlAdelige();
        }
        if (name.equals("Nomaden")) {
            return this.getAnzahlNomaden();
        }
        if (name.equals("Gesandte")) {
            return this.getAnzahlGesandte();
        }
        return "-1";
    }

    public String getAnzahlBettler() {
        if (txtBettler.getText().toString().length() == 0) {
            return "0";
        }
        return txtBettler.getText().toString();
    }

    public String getAnzahlBauern() {
        if (txtBauern.getText().toString().length() == 0) {
            return "0";
        }
        return txtBauern.getText().toString();
    }

    public String getAnzahlBuerger() {
        if (txtBuerger.getText().toString().length() == 0) {
            return "0";
        }
        return txtBuerger.getText().toString();
    }

    public String getAnzahlPatrizier() {
        if (txtPatrizier.getText().toString().length() == 0) {
            return "0";
        }
        return txtPatrizier.getText().toString();
    }

    public String getAnzahlAdelige() {
        if (txtAdelige.getText().toString().length() == 0) {
            return "0";
        }
        return txtAdelige.getText().toString();
    }

    public String getAnzahlNomaden() {
        if (txtNomaden.getText().toString().length() == 0) {
            return "0";
        }
        return txtNomaden.getText().toString();
    }

    public String getAnzahlGesandte() {
        if (txtGesandte.getText().toString().length() == 0) {
            return "0";
        }
        return txtGesandte.getText().toString();
    }

    public void setProduktion(String name) {
        if (name.equals("Fisch")) {
            this.setFischproduktion();
        } else if (name.equals("Most")) {
            this.setMostproduktion();
        } else if (name.equals("Gew\u00fcrze")) {
            this.setGewuerzproduktion();
        } else if (name.equals("Leinenkutten")) {
            this.setLeinenkuttenproduktion();
        } else if (name.equals("Brot")) {
            this.setBrotproduktion();
        } else if (name.equals("Bier")) {
            this.setBierproduktion();
        } else if (name.equals("Lederw\u00e4mser")) {
            this.setLederwaemserproduktion();
        } else if (name.equals("B\u00fccher")) {
            this.setBuecherproduktion();
        } else if (name.equals("Fleisch")) {
            this.setFleischproduktion();
        } else if (name.equals("Wein")) {
            this.setWeinproduktion();
        } else if (name.equals("Pelzm\u00e4ntel")) {
            this.setPelzmaentelproduktion();
        } else if (name.equals("Brokatgew\u00e4nder")) {
            this.setBrokatgewaenderproduktion();
        } else if (name.equals("Kerzenleuchter")) {
            this.setKerzenleuchterproduktion();
        } else if (name.equals("Brillen")) {
            this.setBrillenproduktion();
        } else if (name.equals("Datteln")) {
            this.setDattelproduktion();
        } else if (name.equals("Milch")) {
            this.setMilchproduktion();
        } else if (name.equals("Teppiche")) {
            this.setTeppichproduktion();
        } else if (name.equals("Kaffee")) {
            this.setKaffeeproduktion();
        } else if (name.equals("Perlenketten")) {
            this.setPerlenkettenproduktion();
        } else if (name.equals("Duftwasser")) {
            this.setDuftwasserproduktion();
        } else if (name.equals("Marzipan")) {
            this.setMarzipanproduktion();
        } else if (name.equals("Seile")) {
            this.setSeileproduktion();
        } else if (name.equals("Messing")) {
            this.setMessingproduktion();
        } else if (name.equals("Stein")) {
            this.setSteinproduktion();
        } else if (name.equals("Kanonen")) {
            this.setKanonenproduktion();
        } else if (name.equals("Mosaik")) {
            this.setMosaikproduktion();
        } else if (name.equals("Werkzeuge")) {
            this.setWerkzeugproduktion();
        } else if (name.equals("Waffen")) {
            this.setWaffenproduktion();
        } else if (name.equals("Glas")) {
            this.setGlasproduktion();
        } else if (name.equals("Salz")) {
            this.setSalzproduktion();
        } else if (name.equals("Eisen")) {
            this.setEisenproduktion();
        } else if (name.equals("Holz")) {
            this.setHolzproduktion();
        } else if (name.equals("Kriegsmaschinen")) {
            this.setKriegsmaschinenproduktion();
        }
    }

    public void setFischproduktion() {
        lblFischproduktion.setIcon(new ImageIcon("images/Fischproduktion.png"));
        pnlProduktionsketten.add(lblFischproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setMostproduktion() {
        lblMostproduktion.setIcon(new ImageIcon("images/Mostproduktion.png"));
        pnlProduktionsketten.add(lblMostproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setGewuerzproduktion() {
        lblGewuerzproduktion.setIcon(new ImageIcon("images/Gewuerzproduktion.png"));
        pnlProduktionsketten.add(lblGewuerzproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setLeinenkuttenproduktion() {
        lblLeinenkuttenproduktion.setIcon(new ImageIcon("images/Leinenkuttenproduktion.png"));
        pnlProduktionsketten.add(lblLeinenkuttenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setBierproduktion() {
        lblBierproduktion.setIcon(new ImageIcon("images/Bierproduktion.png"));
        pnlProduktionsketten.add(lblBierproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setBrotproduktion() {
        lblBrotproduktion.setIcon(new ImageIcon("images/Brotproduktion.png"));
        pnlProduktionsketten.add(lblBrotproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setLederwaemserproduktion() {
        lblLederwaemserproduktion.setIcon(new ImageIcon("images/Lederwaemseproduktion.png"));
        pnlProduktionsketten.add(lblLederwaemserproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setBuecherproduktion() {
        lblBuecherproduktion.setIcon(new ImageIcon("images/Buecherproduktion.png"));
        pnlProduktionsketten.add(lblBuecherproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setFleischproduktion() {
        lblFleischproduktion.setIcon(new ImageIcon("images/Fleischproduktion.png"));
        pnlProduktionsketten.add(lblFleischproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setWeinproduktion() {
        lblWeinproduktion.setIcon(new ImageIcon("images/Weinproduktion.png"));
        pnlProduktionsketten.add(lblWeinproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setPelzmaentelproduktion() {
        lblPelzmaentelproduktion.setIcon(new ImageIcon("images/Pelzmaentelproduktion.png"));
        pnlProduktionsketten.add(lblPelzmaentelproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setBrokatgewaenderproduktion() {
        lblBrokatgewaenderproduktion.setIcon(new ImageIcon("images/Brokatgewaenderproduktion.png"));
        pnlProduktionsketten.add(lblBrokatgewaenderproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setKerzenleuchterproduktion() {
        lblKerzenleuchterproduktion.setIcon(new ImageIcon("images/Kerzenleuchterproduktion.png"));
        pnlProduktionsketten.add(lblKerzenleuchterproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setBrillenproduktion() {
        lblBrillenproduktion.setIcon(new ImageIcon("images/Brillenproduktion.png"));
        pnlProduktionsketten.add(lblBrillenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setDattelproduktion() {
        lblDattelproduktion.setIcon(new ImageIcon("images/Dattelproduktion.png"));
        pnlProduktionsketten.add(lblDattelproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setMilchproduktion() {
        lblMilchproduktion.setIcon(new ImageIcon("images/Milchproduktion.png"));
        pnlProduktionsketten.add(lblMilchproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setTeppichproduktion() {
        lblTeppichproduktion.setIcon(new ImageIcon("images/Teppichproduktion.png"));
        pnlProduktionsketten.add(lblTeppichproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setPerlenkettenproduktion() {
        lblPerlenkettenproduktion.setIcon(new ImageIcon("images/Perlenkettenproduktion.png"));
        pnlProduktionsketten.add(lblPerlenkettenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setDuftwasserproduktion() {
        lblDuftwasserproduktion.setIcon(new ImageIcon("images/Duftwasserproduktion.png"));
        pnlProduktionsketten.add(lblDuftwasserproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setKaffeeproduktion() {
        lblKaffeeproduktion.setIcon(new ImageIcon("images/Kaffeeproduktion.png"));
        pnlProduktionsketten.add(lblKaffeeproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setMarzipanproduktion() {
        lblMarzipanproduktion.setIcon(new ImageIcon("images/Marzipanproduktion.png"));
        pnlProduktionsketten.add(lblMarzipanproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setSeileproduktion() {
        lblSeileproduktion.setIcon(new ImageIcon("images/Seileproduktion.png"));
        pnlProduktionsketten.add(lblSeileproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setMessingproduktion() {
        lblMessingproduktion.setIcon(new ImageIcon("images/Messingproduktion.png"));
        pnlProduktionsketten.add(lblMessingproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setSteinproduktion() {
        lblSteinproduktion.setIcon(new ImageIcon("images/Steinproduktion.png"));
        pnlProduktionsketten.add(lblSteinproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setKanonenproduktion() {
        lblKanonenproduktionen.setIcon(new ImageIcon("images/Kanonenproduktion.png"));
        pnlProduktionsketten.add(lblKanonenproduktionen);
        pnlProduktionsketten.repaint();
    }

    public void setWerkzeugproduktion() {
        lblWerkzeugproduktion.setIcon(new ImageIcon("images/Werkzeugeproduktion.png"));
        pnlProduktionsketten.add(lblWerkzeugproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setMosaikproduktion() {
        lblMosaikproduktion.setIcon(new ImageIcon("images/Mosaikproduktion.png"));
        pnlProduktionsketten.add(lblMosaikproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setWaffenproduktion() {
        lblWaffenproduktion.setIcon(new ImageIcon("images/Waffenproduktion.png"));
        pnlProduktionsketten.add(lblWaffenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setGlasproduktion() {
        lblGlasproduktion.setIcon(new ImageIcon("images/Glasproduktion.png"));
        pnlProduktionsketten.add(lblGlasproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setSalzproduktion() {
        lblSalzproduktion.setIcon(new ImageIcon("images/Salzproduktion.png"));
        pnlProduktionsketten.add(lblSalzproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setEisenproduktion() {
        lblEisenproduktion.setIcon(new ImageIcon("images/Eisenproduktion.png"));
        pnlProduktionsketten.add(lblEisenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setHolzproduktion() {
        lblHolzproduktion.setIcon(new ImageIcon("images/Holzproduktion.png"));
        pnlProduktionsketten.add(lblHolzproduktion);
        pnlProduktionsketten.repaint();
    }

    public void setKriegsmaschinenproduktion() {
        lblKriegsmaschinenproduktion.setIcon(new ImageIcon("images/Kriegsmaschinenproduktion.png"));
        pnlProduktionsketten.add(lblKriegsmaschinenproduktion);
        pnlProduktionsketten.repaint();
    }

    public void resetTxt(String name) {
        if (name.equals("Bettler")) {
            this.settxtAnzahlBettler("");
        } else if (name.equals("Bauern")) {
            this.settxtAnzahlBauern("");
        } else if (name.equals("Buerger")) {
            this.settxtAnzahlBuerger("");
        } else if (name.equals("Patrizier")) {
            this.settxtAnzahlPatrizier("");
        } else if (name.equals("Adelige")) {
            this.settxtAnzahlAdelige("");
        } else if (name.equals("Nomaden")) {
            this.settxtAnzahlNomaden("");
        } else if (name.equals("Gesandte")) {
            this.settxtAnzahlGesandte("");
        }
    }

    public void setlblFehler(String name, int art) {
        if (art == 1) {
            if (name.equals("Bettler")) {
                this.setlblFehlerBettler("Fehler!");
            } else if (name.equals("Bauern")) {
                this.setlblFehlerBauern("Fehler!");
            } else if (name.equals("Buerger")) {
                this.setlblFehlerBuerger("Fehler!");
            } else if (name.equals("Patrizier")) {
                this.setlblFehlerPatrizier("Fehler!");
            } else if (name.equals("Adelige")) {
                this.setlblFehlerAdelige("Fehler!");
            } else if (name.equals("Nomaden")) {
                this.setlblFehlerNomaden("Fehler!");
            } else if (name.equals("Gesandte")) {
                this.setlblFehlerGesandte("Fehler!");
            }
        } else if (art == 2) {
            if (name.equals("Bettler")) {
                this.setlblFehlerBettler("");
            } else if (name.equals("Bauern")) {
                this.setlblFehlerBauern("");
            } else if (name.equals("Buerger")) {
                this.setlblFehlerBuerger("");
            } else if (name.equals("Patrizier")) {
                this.setlblFehlerPatrizier("");
            } else if (name.equals("Adelige")) {
                this.setlblFehlerAdelige("");
            } else if (name.equals("Nomaden")) {
                this.setlblFehlerNomaden("");
            } else if (name.equals("Gesandte")) {
                this.setlblFehlerGesandte("");
            }
        }
    }

    public void setlblEingabeOK(String name, int art) {
        if (art == 1) {
            if (name.equals("Bettler")) {
                this.setlblEingabeOKBettler("OK");
            } else if (name.equals("Bauern")) {
                this.setlblEingabeOKBauern("OK");
            } else if (name.equals("Buerger")) {
                this.setlblEingabeOKBuerger("OK");
            } else if (name.equals("Patrizier")) {
                this.setlblEingabeOKPatrizier("OK");
            } else if (name.equals("Adelige")) {
                this.setlblEingabeOKAdelige("OK");
            } else if (name.equals("Nomaden")) {
                this.setlblEingabeOKNomaden("OK");
            } else if (name.equals("Gesandte")) {
                this.setlblEingabeOKGesandte("OK");
            }
        } else if (art == 2) {
            if (name.equals("Bettler")) {
                this.setlblEingabeOKBettler("");
            } else if (name.equals("Bauern")) {
                this.setlblEingabeOKBauern("");
            } else if (name.equals("Buerger")) {
                this.setlblEingabeOKBuerger("");
            } else if (name.equals("Patrizier")) {
                this.setlblEingabeOKPatrizier("");
            } else if (name.equals("Adelige")) {
                this.setlblEingabeOKAdelige("");
            } else if (name.equals("Nomaden")) {
                this.setlblEingabeOKNomaden("");
            } else if (name.equals("Gesandte")) {
                this.setlblEingabeOKGesandte("");
            }
        }
    }

    public void resetlblEingabeFehler() {
        this.setlblEingabeOKBettler("");
        this.setlblEingabeOKBauern("");
        this.setlblEingabeOKBuerger("");
        this.setlblEingabeOKPatrizier("");
        this.setlblEingabeOKAdelige("");
        this.setlblEingabeOKNomaden("");
        this.setlblEingabeOKGesandte("");
        this.setlblFehlerBettler("");
        this.setlblFehlerBauern("");
        this.setlblFehlerBuerger("");
        this.setlblFehlerPatrizier("");
        this.setlblFehlerAdelige("");
        this.setlblFehlerNomaden("");
        this.setlblFehlerGesandte("");
    }

    public void setlblFehlerBettler(String fehler) {
        lblFehlerBettler.setText(fehler);
    }

    public void setlblFehlerBauern(String fehler) {
        lblFehlerBauern.setText(fehler);
    }

    public void setlblFehlerBuerger(String fehler) {
        lblFehlerBuerger.setText(fehler);
    }

    public void setlblFehlerPatrizier(String fehler) {
        lblFehlerPatrizier.setText(fehler);
    }

    public void setlblFehlerAdelige(String fehler) {
        lblFehlerAdelige.setText(fehler);
    }

    public void setlblFehlerNomaden(String fehler) {
        lblFehlerNomaden.setText(fehler);
    }

    public void setlblFehlerGesandte(String fehler) {
        lblFehlerGesandte.setText(fehler);
    }

    public void setlblEingabeOKBettler(String string) {
        lblEingabeOKBettler.setText(string);
    }

    public void setlblEingabeOKBauern(String string) {
        lblEingabeOKBauern.setText(string);
    }

    public void setlblEingabeOKBuerger(String string) {
        lblEingabeOKBuerger.setText(string);
    }

    public void setlblEingabeOKPatrizier(String string) {
        lblEingabeOKPatrizier.setText(string);
    }

    public void setlblEingabeOKAdelige(String string) {
        lblEingabeOKAdelige.setText(string);
    }

    public void setlblEingabeOKNomaden(String string) {
        lblEingabeOKNomaden.setText(string);
    }

    public void setlblEingabeOKGesandte(String string) {
        lblEingabeOKGesandte.setText(string);
    }

    public void settxtAnzahlBettler(String anzahl) {
        txtBettler.setText(anzahl);
    }

    public void settxtAnzahlBauern(String anzahl) {
        txtBauern.setText(anzahl);
    }

    public void settxtAnzahlBuerger(String anzahl) {
        txtBuerger.setText(anzahl);
    }

    public void settxtAnzahlPatrizier(String anzahl) {
        txtPatrizier.setText(anzahl);
    }

    public void settxtAnzahlAdelige(String anzahl) {
        txtAdelige.setText(anzahl);
    }

    public void settxtAnzahlNomaden(String anzahl) {
        txtNomaden.setText(anzahl);
    }

    public void settxtAnzahlGesandte(String anzahl) {
        txtGesandte.setText(anzahl);
    }

    public void setlblAnzahlFisch(String anzahl) {
        lblAnzahlFisch.setText(anzahl);
    }

    public void setlblAnzahlMost(String anzahl) {
        lblAnzahlMost.setText(anzahl);
    }

    public void setlblAnzahlGewuerze(String anzahl) {
        lblAnzahlGewuerze.setText(anzahl);
    }

    public void setlblAnzahlLeinenkutten(String anzahl) {
        lblAnzahlLeinenkutten.setText(anzahl);
    }

    public void setlblAnzahlBier(String anzahl) {
        lblAnzahlBier.setText(anzahl);
    }

    public void setlblAnzahlBrot(String anzahl) {
        lblAnzahlBrot.setText(anzahl);
    }

    public void setlblAnzahlLederwaemser(String anzahl) {
        lblAnzahlLederwaemser.setText(anzahl);
    }

    public void setlblAnzahlBuecher(String anzahl) {
        lblAnzahlBuecher.setText(anzahl);
    }

    public void setlblAnzahlFleisch(String anzahl) {
        lblAnzahlFleisch.setText(anzahl);
    }

    public void setlblAnzahlWein(String anzahl) {
        lblAnzahlWein.setText(anzahl);
    }

    public void setlblAnzahlPelzmaentel(String anzahl) {
        lblAnzahlPelzmaentel.setText(anzahl);
    }

    public void setlblAnzahlBrokatgewaender(String anzahl) {
        lblAnzahlBrokatgewaender.setText(anzahl);
    }

    public void setlblAnzahlKerzenleuchter(String anzahl) {
        lblAnzahlKerzenleuchter.setText(anzahl);
    }

    public void setlblAnzahlBrillen(String anzahl) {
        lblAnzahlBrillen.setText(anzahl);
    }

    public void setlblAnzahlDatteln(String anzahl) {
        lblAnzahlDatteln.setText(anzahl);
    }

    public void setlblAnzahlMilch(String anzahl) {
        lblAnzahlMilch.setText(anzahl);
    }

    public void setlblAnzahlTeppiche(String anzahl) {
        lblAnzahlTeppiche.setText(anzahl);
    }

    public void setlblAnzahlKaffee(String anzahl) {
        lblAnzahlKaffee.setText(anzahl);
    }

    public void setlblAnzahlPerlenketten(String anzahl) {
        lblAnzahlPerlenketten.setText(anzahl);
    }

    public void setlblAnzahlDuftwasser(String anzahl) {
        lblAnzahlDuftwasser.setText(anzahl);
    }

    public void setlblAnzahlMarzipan(String anzahl) {
        lblAnzahlMarzipan.setText(anzahl);
    }

    public void setBtnFischListener(MouseAdapter ma) {
        btnFisch.addMouseListener(ma);
    }

    public void setBtnMostListener(MouseAdapter ma) {
        btnMost.addMouseListener(ma);
    }

    public void setBtnGewuerzeListener(MouseAdapter ma) {
        btnGewuerze.addMouseListener(ma);
    }

    public void setBtnLeinenkuttenListener(MouseAdapter ma) {
        btnLeinenkutten.addMouseListener(ma);
    }

    public void setBtnBierListener(MouseAdapter ma) {
        btnBier.addMouseListener(ma);
    }

    public void setBtnBrotListener(MouseAdapter ma) {
        btnBrot.addMouseListener(ma);
    }

    public void setBtnLederwaemserListener(MouseAdapter ma) {
        btnLederwaemser.addMouseListener(ma);
    }

    public void setBtnBuecherListener(MouseAdapter ma) {
        btnBuecher.addMouseListener(ma);
    }

    public void setBtnFleischListener(MouseAdapter ma) {
        btnFleisch.addMouseListener(ma);
    }

    public void setBtnWeinListener(MouseAdapter ma) {
        btnWein.addMouseListener(ma);
    }

    public void setBtnPelzmaentelListener(MouseAdapter ma) {
        btnPelzmaentel.addMouseListener(ma);
    }

    public void setBtnBrokatgewaenderListener(MouseAdapter ma) {
        btnBrokatgewaender.addMouseListener(ma);
    }

    public void setBtnKerzenleuchterListener(MouseAdapter ma) {
        btnKerzenleuchter.addMouseListener(ma);
    }

    public void setBtnBrillenListener(MouseAdapter ma) {
        btnBrillen.addMouseListener(ma);
    }

    public void setBtnDattelnListener(MouseAdapter ma) {
        btnDatteln.addMouseListener(ma);
    }

    public void setBtnMilchListener(MouseAdapter ma) {
        btnMilch.addMouseListener(ma);
    }

    public void setBtnTeppicheListener(MouseAdapter ma) {
        btnTeppiche.addMouseListener(ma);
    }

    public void setBtnKaffeeListener(MouseAdapter ma) {
        btnKaffee.addMouseListener(ma);
    }

    public void setBtnPerlenkettenListener(MouseAdapter ma) {
        btnPerlenketten.addMouseListener(ma);
    }

    public void setBtnDuftwasserListener(MouseAdapter ma) {
        btnDuftwasser.addMouseListener(ma);
    }

    public void setBtnMarzipanListener(MouseAdapter ma) {
        btnMarzipan.addMouseListener(ma);
    }

    public void setBtnSeileListener(MouseAdapter ma) {
        btnSeile.addMouseListener(ma);
    }

    public void setBtnMessingListener(MouseAdapter ma) {
        btnMessing.addMouseListener(ma);
    }

    public void setBtnSteinListener(MouseAdapter ma) {
        btnStein.addMouseListener(ma);
    }

    public void setBtnKanonenListener(MouseAdapter ma) {
        btnKanonen.addMouseListener(ma);
    }

    public void setBtnWerkzeugeListener(MouseAdapter ma) {
        btnWerkzeuge.addMouseListener(ma);
    }

    public void setBtnMosaikListener(MouseAdapter ma) {
        btnMosaik.addMouseListener(ma);
    }

    public void setBtnWaffenListener(MouseAdapter ma) {
        btnWaffen.addMouseListener(ma);
    }

    public void setBtnGlasListener(MouseAdapter ma) {
        btnGlas.addMouseListener(ma);
    }

    public void setBtnSalzListener(MouseAdapter ma) {
        btnSalz.addMouseListener(ma);
    }

    public void setBtnEisenListener(MouseAdapter ma) {
        btnEisen.addMouseListener(ma);
    }

    public void setBtnHolzListener(MouseAdapter ma) {
        btnHolz.addMouseListener(ma);
    }

    public void setBtnKriegsmaschinenListener(MouseAdapter ma) {
        btnKriegsmaschinen.addMouseListener(ma);
    }

    public void setZuruecksetzenListener(MouseAdapter ma) {
        btnZuruecksetzen.addMouseListener(ma);
    }

    public void setTxtBettlerListener(KeyListener kl) {
        txtBettler.addKeyListener(kl);
    }

    public void setTxtBauernListener(KeyListener kl) {
        txtBauern.addKeyListener(kl);
    }

    public void setTxtBuergerListener(KeyListener kl) {
        txtBuerger.addKeyListener(kl);
    }

    public void setTxtPatrizierListener(KeyListener kl) {
        txtPatrizier.addKeyListener(kl);
    }

    public void setTxtAdeligeListener(KeyListener kl) {
        txtAdelige.addKeyListener(kl);
    }

    public void setTxtNomadenListener(KeyListener kl) {
        txtNomaden.addKeyListener(kl);
    }

    public void setTxtGesandteListener(KeyListener kl) {
        txtGesandte.addKeyListener(kl);
    }
}

