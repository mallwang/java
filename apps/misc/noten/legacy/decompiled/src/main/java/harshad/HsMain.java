/*
 * Decompiled with CFR 0.152.
 */
package harshad;

import harshad.Quersumme;
import javax.swing.JOptionPane;

public class HsMain {
    public static void main(String[] args) {
        String a = JOptionPane.showInputDialog("Zahl eingeben (e=Ende)");
        do {
            if (a != "e") {
                Quersumme qs;
                int c;
                int b = Integer.parseInt(a);
                if (b % (c = (qs = new Quersumme()).berechne(a)) == 0) {
                    JOptionPane.showMessageDialog(null, "Zahl ist Hashad Zahl");
                    continue;
                }
                JOptionPane.showMessageDialog(null, "Zahl ist keine Hashad Zahl");
                continue;
            }
            JOptionPane.showMessageDialog(null, "Ende");
        } while (a != "e");
    }
}

