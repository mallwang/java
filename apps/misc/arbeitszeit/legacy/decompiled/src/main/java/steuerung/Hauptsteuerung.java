/*
 * Decompiled with CFR 0.152.
 */
package steuerung;

import gui.Hauptseite;
import gui.Tray;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import steuerung.Setting;
import steuerung.StaXParser;
import steuerung.StaXWriter;
import steuerung.TimerRestzeit;

public class Hauptsteuerung {
    public static Hauptseite hauptseite;
    public static Tray tray;
    public static TimerRestzeit timer;
    public static long REFRESH;
    public static int WORKTIME;
    public static String RESTZEITSTRING;
    public static StaXWriter staXWriter;
    public static String PATH;

    static {
        PATH = String.valueOf(System.getProperty("user.home")) + File.separator + ".Arbeitszeitrechner" + File.separator + "config.xml";
    }

    public static void main(String[] args) {
        Hauptsteuerung.checkFileExistance();
        Hauptsteuerung.readConfiguration();
        Hauptsteuerung.createHauptseite();
    }

    private static void checkFileExistance() {
        Boolean fileExistance = new File(PATH).isFile();
        if (!fileExistance.booleanValue()) {
            staXWriter = new StaXWriter();
            staXWriter.setFile(PATH);
            try {
                staXWriter.createConfig("1", "40");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void readConfiguration() {
        StaXParser staXParser = new StaXParser();
        List<Setting> readConfig = staXParser.readConfig(PATH);
        Setting setting = readConfig.get(0);
        Hauptsteuerung.setRefresh(Long.valueOf(setting.getRefresh()) * 1000L);
        Hauptsteuerung.setWorktime(Integer.valueOf(setting.getWorktime()));
    }

    private static void createHauptseite() {
        hauptseite = new Hauptseite();
        timer = new TimerRestzeit();
        ItemListener ilUhrzeitKommenStd = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (hauptseite.getUhrzeitKommenStd() != "--" && hauptseite.getUhrzeitKommenMin() != "--") {
                    Hauptsteuerung.createFeierabendKernzeit();
                    timer = new TimerRestzeit();
                } else {
                    Hauptsteuerung.setDefaultTimes();
                }
            }
        };
        hauptseite.setListenerCboUhrzeitKommenStd(ilUhrzeitKommenStd);
        ItemListener ilUhrzeitKommenMin = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (hauptseite.getUhrzeitKommenStd() != "--" && hauptseite.getUhrzeitKommenMin() != "--") {
                    Hauptsteuerung.createFeierabendKernzeit();
                    timer = new TimerRestzeit();
                } else {
                    Hauptsteuerung.setDefaultTimes();
                }
            }
        };
        hauptseite.setListenerCboUhrzeitKommenMin(ilUhrzeitKommenMin);
        ItemListener ilUhrzeitGehenStd = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (hauptseite.getUhrzeitGehenStd() != "--" && hauptseite.getUhrzeitGehenMin() != "--" && hauptseite.getUhrzeitKommenStd() != "--" && hauptseite.getUhrzeitKommenMin() != "--") {
                    Hauptsteuerung.createRestzeitGeplant();
                } else {
                    hauptseite.setLblUhrzeitFeierabendGeplant("--:-- Std", Color.BLACK);
                }
            }
        };
        hauptseite.setListenerCboUhrzeitGehenStd(ilUhrzeitGehenStd);
        ItemListener ilUhrzeitGehenMin = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (hauptseite.getUhrzeitGehenStd() != "--" && hauptseite.getUhrzeitGehenMin() != "--" && hauptseite.getUhrzeitKommenStd() != "--" && hauptseite.getUhrzeitKommenMin() != "--") {
                    Hauptsteuerung.createRestzeitGeplant();
                } else {
                    hauptseite.setLblUhrzeitFeierabendGeplant("--:-- Std", Color.BLACK);
                }
            }
        };
        hauptseite.setListenerCboUhrzeitGehenMin(ilUhrzeitGehenMin);
        MouseAdapter maAktualisierung = new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                Object[] possibilities = new Object[]{"60", "30", "20", "15", "10", "5", "2", "1"};
                String interval = (String)JOptionPane.showInputDialog(Hauptseite.frame, "Bitte w\u00e4hlen Sie das\nAktualisierungsintervall:\n", "Aktualisierungszeit w\u00e4hlen.", 1, null, possibilities, String.valueOf(Hauptsteuerung.getRefresh() / 1000L));
                if (interval != null && interval.length() > 0) {
                    Hauptsteuerung.setRefresh(Long.valueOf(interval) * 1000L);
                    timer = new TimerRestzeit();
                    Hauptsteuerung.createConfig(interval, String.valueOf(Hauptsteuerung.getWorktime()));
                }
            }
        };
        hauptseite.setListenerMiAktualisierung(maAktualisierung);
        MouseAdapter maArbeitszeit = new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                Object[] possibilities = new Object[]{"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"};
                String interval = (String)JOptionPane.showInputDialog(Hauptseite.frame, "Bitte w\u00e4hlen Sie Ihre Wochen-\narbeitszeit (5 Arbeitstage):\n", "Wochenarbeitszeit w\u00e4hlen.", 1, null, possibilities, String.valueOf(Hauptsteuerung.getWorktime()));
                if (interval != null && interval.length() > 0) {
                    Hauptsteuerung.setWorktime(Integer.valueOf(interval));
                    timer = new TimerRestzeit();
                    Hauptsteuerung.createConfig(String.valueOf(Hauptsteuerung.getRefresh() / 1000L), interval);
                }
            }
        };
        hauptseite.setListenerMiArbeitszeit(maArbeitszeit);
        MouseAdapter maTray = new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                tray = new Tray();
            }
        };
        hauptseite.setListenerMiTray(maTray);
    }

    private static void createConfig(String refresh, String worktime) {
        staXWriter = new StaXWriter();
        staXWriter.setFile(PATH);
        try {
            staXWriter.createConfig(refresh, worktime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createRestzeitGeplant() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date dateUhrzeitGehen = null;
        Date dateNow = null;
        Calendar calNow = Calendar.getInstance();
        int stdNow = calNow.get(11);
        int minNow = calNow.get(12);
        dateNow = new Date(70, 0, 1, stdNow, minNow);
        dateUhrzeitGehen = new Date(70, 0, 1, Integer.valueOf(hauptseite.getUhrzeitGehenStd()), Integer.valueOf(hauptseite.getUhrzeitGehenMin()));
        long diff = dateUhrzeitGehen.getTime() - dateNow.getTime();
        String hours = String.valueOf(diff / 3600000L);
        String min = String.valueOf(diff / 60000L % 60L);
        hours = hours.replace("-", "");
        min = min.replace("-", "");
        if (hours.length() == 1) {
            hours = "0" + hours;
        }
        if (min.length() == 1) {
            min = "0" + min;
        }
        String restzeit = String.valueOf(hours) + ":" + min + " Std";
        if (diff <= 0L) {
            hauptseite.setLblUhrzeitFeierabendGeplant(restzeit, new Color(0, 204, 0));
        } else {
            hauptseite.setLblUhrzeitFeierabendGeplant(restzeit, Color.RED);
        }
    }

    private static void setDefaultTimes() {
        hauptseite.setLblUhrzeitFeierabendZeit("--:-- Uhr");
        hauptseite.setLblUhrzeitFeierabendRestzeitZeit("--:-- Std", Color.BLACK);
        hauptseite.setFrameTitle("Arbeitszeitrechner v. ");
    }

    private static void createFeierabendKernzeit() {
        int arbeitszeit = Hauptsteuerung.createArbeitszeit();
        int pausenzeit = Hauptsteuerung.createPausenzeit();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date dateUhrzeitKommen = sdf.parse(String.valueOf(hauptseite.getUhrzeitKommenStd()) + ":" + hauptseite.getUhrzeitKommenMin());
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateUhrzeitKommen);
            cal.add(12, arbeitszeit + pausenzeit);
            Date dateUhrzeitGehen = cal.getTime();
            String time = sdf.format(dateUhrzeitGehen);
            hauptseite.setLblUhrzeitFeierabendZeit(String.valueOf(time) + " Uhr");
        }
        catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    public static void createRestzeit() {
        if (hauptseite.getUhrzeitKommenStd() != "--" && hauptseite.getUhrzeitKommenMin() != "--") {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date dateUhrzeitKommen = null;
            Date dateUhrzeitGehen = null;
            Date dateNow = null;
            try {
                int arbeitszeit = Hauptsteuerung.createArbeitszeit();
                int pausenzeit = Hauptsteuerung.createPausenzeit();
                Calendar calNow = Calendar.getInstance();
                int stdNow = calNow.get(11);
                int minNow = calNow.get(12);
                dateNow = new Date(70, 0, 1, stdNow, minNow);
                dateUhrzeitKommen = sdf.parse(String.valueOf(hauptseite.getUhrzeitKommenStd()) + ":" + hauptseite.getUhrzeitKommenMin());
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateUhrzeitKommen);
                cal.add(12, arbeitszeit + pausenzeit);
                dateUhrzeitGehen = cal.getTime();
                String time = sdf.format(dateUhrzeitGehen);
                hauptseite.setLblUhrzeitFeierabendZeit(String.valueOf(time) + " Uhr");
            }
            catch (ParseException e1) {
                e1.printStackTrace();
            }
            long diff = dateUhrzeitGehen.getTime() - dateNow.getTime();
            String hours = String.valueOf(diff / 3600000L);
            String min = String.valueOf(diff / 60000L % 60L);
            hours = hours.replace("-", "");
            min = min.replace("-", "");
            if (hours.length() == 1) {
                hours = "0" + hours;
            }
            if (min.length() == 1) {
                min = "0" + min;
            }
            String restzeit = String.valueOf(hours) + ":" + min + " Std";
            if (diff <= 0L) {
                hauptseite.setLblUhrzeitFeierabendRestzeitZeit(restzeit, new Color(0, 204, 0));
                Hauptsteuerung.setRestzeitString("Feierabend seit " + restzeit);
            } else {
                hauptseite.setLblUhrzeitFeierabendRestzeitZeit(restzeit, Color.RED);
                Hauptsteuerung.setRestzeitString("Feierabend in " + restzeit);
            }
            try {
                Tray.setTooltip(Hauptsteuerung.getRestzeitString());
            }
            catch (Exception exception) {
                // empty catch block
            }
            hauptseite.setFrameTitle(String.valueOf(restzeit) + " - Arbeitszeitrechner v. ");
            if (hauptseite.getUhrzeitGehenStd() != "--" && hauptseite.getUhrzeitGehenMin() != "--") {
                Hauptsteuerung.createRestzeitGeplant();
            }
        } else {
            Hauptsteuerung.setRestzeitString("Bitte zuerst Kommenzeit angeben!");
        }
        Calendar calNow = Calendar.getInstance();
        String stdNow = String.valueOf(calNow.get(11));
        String minNow = String.valueOf(calNow.get(12));
        String secNow = String.valueOf(calNow.get(13));
        if (stdNow.length() == 1) {
            stdNow = "0" + stdNow;
        }
        if (minNow.length() == 1) {
            minNow = "0" + minNow;
        }
        if (secNow.length() == 1) {
            secNow = "0" + secNow;
        }
        Hauptseite.setLblAktUhrzeit(String.valueOf(stdNow) + ":" + minNow + ":" + secNow);
    }

    private static int createArbeitszeit() {
        return Hauptsteuerung.getWorktime() * 60 / 5;
    }

    private static int createPausenzeit() {
        int arbeitszeit = Hauptsteuerung.createArbeitszeit();
        int pausenzeit = 0;
        if (arbeitszeit >= 360) {
            pausenzeit = 30;
        } else if (arbeitszeit >= 540) {
            pausenzeit = 45;
        }
        return pausenzeit;
    }

    public static void setRefresh(long refresh) {
        REFRESH = refresh;
    }

    public static void setWorktime(int worktime) {
        WORKTIME = worktime;
    }

    public static void setRestzeitString(String restzeit) {
        RESTZEITSTRING = restzeit;
    }

    public static long getRefresh() {
        return REFRESH;
    }

    public static int getWorktime() {
        return WORKTIME;
    }

    public static String getRestzeitString() {
        return RESTZEITSTRING;
    }
}

