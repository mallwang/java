/*
 * Decompiled with CFR 0.152.
 */
package zahlzerlegung;

import javax.swing.JOptionPane;
import zahlzerlegung.ZerlegenRueckwaerts;
import zahlzerlegung.ZerlegenVorwaerts;

public class Main {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Mit diesem Rechner k\u00f6nnen Sie Ihre Zahl in Worten vorw\u00e4rts oder r\u00fcckw\u00e4rts ausgeben lassen. Geben Sie diese nun ein:");
        System.out.println("Zahl: " + n2);
        ZerlegenVorwaerts vorwaerts = new ZerlegenVorwaerts();
        ZerlegenRueckwaerts rueckwaerts = new ZerlegenRueckwaerts();
        String n3 = JOptionPane.showInputDialog("In welche Richtung? Vorw\u00e4rts (v) oder R\u00fcckw\u00e4rts (r)?");
        char n = n3.charAt(0);
        switch (n) {
            case 'v': {
                vorwaerts.zerlegen(n2);
                break;
            }
            case 'r': {
                rueckwaerts.zerlegen(n2);
            }
        }
    }
}

