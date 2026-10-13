/*
 * Decompiled with CFR 0.152.
 */
package restberechner;

import javax.swing.JOptionPane;

public class rbMain {
    public static void main(String[] args) {
        String z3 = JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein");
        int z1 = Integer.parseInt(z3);
        String z4 = JOptionPane.showInputDialog("Bitte geben Sie eine weitere Zahl ein");
        int z2 = Integer.parseInt(z4);
        int erg = z1 / z2;
        int rest = z1 % z2;
        JOptionPane.showMessageDialog(null, "Ergebnis: " + erg + " Rest: " + rest);
    }
}

