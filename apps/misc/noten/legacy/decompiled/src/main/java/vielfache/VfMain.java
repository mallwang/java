/*
 * Decompiled with CFR 0.152.
 */
package vielfache;

import javax.swing.JOptionPane;
import vielfache.Multiplizierer;

public class VfMain {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Mit diesem Rechner k\u00f6nnen Sie die Vielfachen Ihrer eingegebenen Zahl berechnen. Bitte geben Sie diese nun ein:");
        int n = Integer.parseInt(n2);
        Multiplizierer m = new Multiplizierer();
        m.multiplizieren(n);
    }
}

