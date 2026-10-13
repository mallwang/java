/*
 * Decompiled with CFR 0.152.
 */
package vokabeln;

import javax.swing.JOptionPane;

public class Worttester {
    public void teste(String wort) {
        String b = null;
        String a = null;
        if (wort.contains("hallo")) {
            b = "Gria\u00df Di, Servus, Habe die Ehre, Gria\u00df Euch";
            a = "[gri\u00e0sdi, se\u00e0wus, hawede\u00e0re, gri\u00e0ssaich]";
        } else if (wort.contains("guten tag")) {
            b = "Gr\u00fc\u00df Gott";
            a = "[gri\u00e0sgo:d]";
        } else if (wort.contains("tsch\u00fcss")) {
            b = "Servus, Pfia Gott, Pfiat Di";
            a = "[serwus, bfi\u00e0god, bfi\u00e0ddi]";
        } else if (wort.contains("vorsicht")) {
            b = "Obacht";
            a = "[owachd]";
        } else {
            b = "Wort nicht im W\u00f6rterbuch enthalten";
            a = "Wort iis need im Buach drinna";
        }
        JOptionPane.showMessageDialog(null, "Bayerisch:  " + b);
        JOptionPane.showMessageDialog(null, "Aussprache:  " + a);
    }
}

