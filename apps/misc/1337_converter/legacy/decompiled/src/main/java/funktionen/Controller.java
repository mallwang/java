/*
 * Decompiled with CFR 0.152.
 */
package funktionen;

import funktionen.Aktionen;
import gui.Hauptseite;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private static Hauptseite hauptseite;
    private static Aktionen aktionen;
    static Date zeit;
    static SimpleDateFormat sdfAktuell;

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
        Controller.Wecker();
    }

    private static void Wecker() {
        zeit = new Date();
        sdfAktuell = new SimpleDateFormat("hh:mm:ss");
        while (true) {
            zeit.setTime(System.currentTimeMillis());
            hauptseite.setLblUhrzeitText(sdfAktuell.format(zeit));
            try {
                Thread.currentThread();
                Thread.sleep(1000L);
                continue;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            break;
        }
    }

    public static boolean erzeugeHauptseite() {
        try {
            hauptseite = new Hauptseite();
            aktionen = new Aktionen();
            Hauptseite.frame.setVisible(true);
            class Normal
            extends MouseAdapter {
                Normal() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Controller.setzeAllesAufNormal();
                }
            }
            Normal normal = new Normal();
            class Style
            extends MouseAdapter {
                Style() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Controller.setzeAllesAufStyle();
                }
            }
            Style style = new Style();
            class Schliessen
            extends MouseAdapter {
                Schliessen() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    System.exit(0);
                }
            }
            Schliessen schliessen = new Schliessen();
            class InAblageKopieren
            extends MouseAdapter {
                InAblageKopieren() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Controller.inZwischenablageKopieren();
                }
            }
            InAblageKopieren inAblageKopieren = new InAblageKopieren();
            class EinfachesL337
            extends MouseAdapter {
                EinfachesL337() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    hauptseite.setInfoText("");
                    if (Hauptseite.L337Style) {
                        if (Hauptseite.TAeingabe.getText().length() != 0) {
                            hauptseite.setInfoText("d3r 73x7 83f1nd37 51ch n1ch7 1n d3r 2w15ch3n48l493");
                        }
                    } else if (Hauptseite.TAeingabe.getText().length() != 0) {
                        hauptseite.setInfoText("Der Text befindet sich nicht in der Zwischenablage");
                    }
                    Hauptseite.info.setForeground(new Color(180, 0, 0));
                    String eingabe = Hauptseite.TAeingabe.getText();
                    String ausgabe = "";
                    ausgabe = aktionen.umwandeln(eingabe, 1);
                    Hauptseite.TAausgabe.setText(ausgabe);
                }
            }
            EinfachesL337 einfachesL337 = new EinfachesL337();
            class UltimativesL337
            extends MouseAdapter {
                UltimativesL337() {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    hauptseite.setInfoText("");
                    if (Hauptseite.L337Style) {
                        if (Hauptseite.TAeingabe.getText().length() != 0) {
                            hauptseite.setInfoText("d3r 73x7 83f1nd37 51ch n1ch7 1n d3r 2w15ch3n48l493");
                        }
                    } else if (Hauptseite.TAeingabe.getText().length() != 0) {
                        hauptseite.setInfoText("Der Text befindet sich nicht in der Zwischenablage");
                    }
                    Hauptseite.info.setForeground(new Color(180, 0, 0));
                    String eingabe = Hauptseite.TAeingabe.getText();
                    String ausgabe = "";
                    ausgabe = aktionen.umwandeln(eingabe, 2);
                    Hauptseite.TAausgabe.setText(ausgabe);
                }
            }
            UltimativesL337 ultimativesL337 = new UltimativesL337();
            class Texteingabe
            extends KeyAdapter {
                Texteingabe() {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    hauptseite.setInfoText("");
                    if (Hauptseite.L337Style) {
                        if (Hauptseite.TAeingabe.getText().length() != 0) {
                            hauptseite.setInfoText("d3r 73x7 83f1nd37 51ch n1ch7 1n d3r 2w15ch3n48l493");
                            Hauptseite.info.setBounds(325, 180, 330, 15);
                        }
                    } else if (Hauptseite.TAeingabe.getText().length() != 0) {
                        hauptseite.setInfoText("Der Text befindet sich nicht in der Zwischenablage");
                        Hauptseite.info.setBounds(340, 180, 330, 15);
                    }
                    Hauptseite.info.setForeground(new Color(180, 0, 0));
                    String eingabe = Hauptseite.TAeingabe.getText();
                    String ausgabe = "";
                    if (Hauptseite.rb1.isSelected()) {
                        ausgabe = aktionen.umwandeln(eingabe, 1);
                    } else if (Hauptseite.rb2.isSelected()) {
                        ausgabe = aktionen.umwandeln(eingabe, 2);
                    }
                    Hauptseite.TAausgabe.setText(ausgabe);
                }
            }
            Texteingabe texteingabe = new Texteingabe();
            hauptseite.setMnItmNormalListener(normal);
            hauptseite.setMnItmL337Listener(style);
            hauptseite.setMnItmSchliessenListener(schliessen);
            hauptseite.setBtnListener(inAblageKopieren);
            hauptseite.setBnt1Listener(einfachesL337);
            hauptseite.setBnt2Listener(ultimativesL337);
            hauptseite.setTAeingabeListener(texteingabe);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void setzeAllesAufStyle() {
        hauptseite.setStyle(true);
        hauptseite.setInfoText("");
        if (Hauptseite.TAeingabe.getText().length() != 0) {
            hauptseite.setInfoText("d3r 73x7 83f1nd37 51ch n1ch7 1n d3r 2w15ch3n48l493");
        }
        Hauptseite.info.setForeground(new Color(180, 0, 0));
        Hauptseite.info.setBounds(325, 180, 330, 15);
        hauptseite.setBeendenText("833nd3n");
        hauptseite.setBtnText("73x7 1n d13 zw15ch3n48l493 k0p13r3n");
        hauptseite.setVersionText("v3r510n: 1.2");
        hauptseite.setNormalText("n0rm4l");
        hauptseite.setSchliessenText("5chl13553n");
        hauptseite.setStyleText("57yl3");
        Hauptseite.frame.setTitle("l337 c0nv3r73r  v.1.2");
    }

    public static void setzeAllesAufNormal() {
        hauptseite.setStyle(false);
        hauptseite.setInfoText("");
        if (Hauptseite.TAeingabe.getText().length() != 0) {
            hauptseite.setInfoText("Der Text befindet sich nicht in der Zwischenablage");
        }
        Hauptseite.info.setForeground(new Color(180, 0, 0));
        Hauptseite.info.setBounds(340, 180, 330, 15);
        hauptseite.setBeendenText("Beenden");
        hauptseite.setBtnText("Text in die Zwischenablage kopieren");
        hauptseite.setVersionText("Version: 1.2");
        hauptseite.setNormalText("Normal");
        hauptseite.setSchliessenText("Schliessen");
        hauptseite.setStyleText("Style");
        Hauptseite.frame.setTitle("L337 Converter  v.1.2");
    }

    public static void inZwischenablageKopieren() {
        if (Hauptseite.TAeingabe.getText().length() >= 1) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(Hauptseite.TAausgabe.getText()), null);
            if (Hauptseite.L337Style) {
                hauptseite.setInfoText("d3r 73x7 83f1nd37 51ch nun 1n d3r zw15ch3n48l493!");
            } else {
                Hauptseite.info.setText("Der Text befindet sich nun in der Zwischenablage!");
            }
            Hauptseite.info.setForeground(new Color(0, 140, 0));
        } else {
            Hauptseite.info.setBounds(370, 180, 180, 15);
            if (Hauptseite.L337Style) {
                hauptseite.setInfoText("81773 9383n 513 31n3n 73x7 31n!");
                Hauptseite.info.setBounds(380, 180, 330, 15);
            } else {
                Hauptseite.info.setText("Bitte geben Sie einen Text ein!");
                Hauptseite.info.setBounds(380, 180, 330, 15);
            }
            Hauptseite.info.setForeground(new Color(180, 0, 0));
        }
    }
}

