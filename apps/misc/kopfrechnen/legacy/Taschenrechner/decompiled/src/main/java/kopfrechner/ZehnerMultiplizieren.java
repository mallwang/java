/*
 * Decompiled with CFR 0.152.
 */
package kopfrechner;

import javax.swing.JOptionPane;

public class ZehnerMultiplizieren {
    public static void main(String[] args) {
        int Nochmal = 106;
        while (Nochmal == 106) {
            int zahl1 = (int)(10.0 * Math.random() + 1.0);
            int zahl2 = (int)(10.0 * Math.random() + 1.0);
            int erg = zahl1 * zahl2;
            String Ausgabe = JOptionPane.showInputDialog("Wie lautet folgende Rechnung?: " + zahl1 + " x " + zahl2);
            int A = Integer.parseInt(Ausgabe);
            if (A == erg) {
                JOptionPane.showMessageDialog(null, "Richtig!");
                continue;
            }
            JOptionPane.showMessageDialog(null, "Falsch!");
            String Nochmal3 = JOptionPane.showInputDialog("Weiter? ( j / n ) ");
            Nochmal = Nochmal3.charAt(0);
        }
        JOptionPane.showMessageDialog(null, "Gut gemacht!");
    }
}

