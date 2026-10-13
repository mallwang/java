/*
 * Decompiled with CFR 0.152.
 */
package harshad;

import harshad.Quersumme;
import javax.swing.JOptionPane;

public class HsMain {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Dies ist ein Hashad-Zahl Berechner. Eine Harshad Zahl ist eine Zahl, die durch ihre Quersumme teilbar ist. Bsp. ist 777 durch 21 teilbar. ");
        String a = JOptionPane.showInputDialog("Zahl eingeben (e=Ende)");
        int b = Integer.parseInt(a);
        Quersumme qs = new Quersumme();
        int c = qs.berechne(a);
        if (b % c == 0) {
            JOptionPane.showMessageDialog(null, "Zahl ist Harshad Zahl");
        } else {
            JOptionPane.showMessageDialog(null, "Zahl ist keine Harshad Zahl");
        }
    }
}

