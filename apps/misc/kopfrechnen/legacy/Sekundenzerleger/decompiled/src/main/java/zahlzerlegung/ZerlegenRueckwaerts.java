/*
 * Decompiled with CFR 0.152.
 */
package zahlzerlegung;

import javax.swing.JOptionPane;

public class ZerlegenRueckwaerts {
    public void zerlegen(String n2) {
        String k = "";
        int stellenzahl = n2.length();
        String w = null;
        int i = n2.length() - 1;
        while (stellenzahl > 0) {
            switch (n2.charAt(i)) {
                case '1': {
                    w = "eins";
                    break;
                }
                case '2': {
                    w = "zwei";
                    break;
                }
                case '3': {
                    w = "drei";
                    break;
                }
                case '4': {
                    w = "vier";
                    break;
                }
                case '5': {
                    w = "f\u00fcnf";
                    break;
                }
                case '6': {
                    w = "sechs";
                    break;
                }
                case '7': {
                    w = "sieben";
                    break;
                }
                case '8': {
                    w = "acht";
                    break;
                }
                case '9': {
                    w = "neun";
                    break;
                }
                case '0': {
                    w = "null";
                }
            }
            --stellenzahl;
            k = String.valueOf(k) + " " + w;
            --i;
        }
        JOptionPane.showMessageDialog(null, "R\u00fcckwaerts zerlegt:" + k);
        JOptionPane.showMessageDialog(null, "Sinnlos, aber trotzdem genial :)");
        System.out.println("Rueckwaerts zerlegt:" + k);
    }
}

