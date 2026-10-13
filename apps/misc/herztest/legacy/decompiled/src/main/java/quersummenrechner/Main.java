/*
 * Decompiled with CFR 0.152.
 */
package quersummenrechner;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Mit diesem Rechner k\u00f6nnen sie die Quersumme einer beliebigen Zahl ausrechnen. Bitte geben Sie diese nun ein:");
        char ch = '\u0000';
        int ergebnis = 0;
        int i = 0;
        while (i < n2.length()) {
            ch = n2.charAt(i);
            int c = ch - 48;
            ergebnis += c;
            ++i;
        }
        JOptionPane.showMessageDialog(null, "Die Quersumme lautet: " + ergebnis);
        System.out.println(ergebnis);
    }
}

