/*
 * Decompiled with CFR 0.152.
 */
package sekundenzerlegung;

import javax.swing.JOptionPane;
import sekundenzerlegung.Sekundenzerleger;

public class SZerlMain {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Mit diesem Rechner k\u00f6nnen Sie Sekunden in Jahre, Tage usw. zerlegen. Geben Sie bitte die Sekunden ein:");
        int sek = Integer.parseInt(n2);
        Sekundenzerleger sz = new Sekundenzerleger();
        sz.teileAuf(sek);
    }
}

