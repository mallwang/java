/*
 * Decompiled with CFR 0.152.
 */
package notendurchschnitt;

import javax.swing.JOptionPane;

public class NdMain {
    public static void main(String[] args) {
        boolean pruefung = true;
        do {
            String alleNoten = JOptionPane.showInputDialog("Hallo Manu! Bitte gib alle deine Noten hintereinander ohne Leerzeichen ein (z.B. 233) e=ende");
            pruefung = alleNoten.contains("e");
            if (alleNoten.contains("0")) {
                JOptionPane.showMessageDialog(null, "0 ist keine Note");
                continue;
            }
            if (alleNoten.contains("7")) {
                JOptionPane.showMessageDialog(null, "7 ist keine Note");
                continue;
            }
            if (alleNoten.contains("8")) {
                JOptionPane.showMessageDialog(null, "8 ist keine Note");
                continue;
            }
            if (alleNoten.contains("9")) {
                JOptionPane.showMessageDialog(null, "9 ist keine Note");
                continue;
            }
            if (alleNoten.contains("e")) {
                JOptionPane.showMessageDialog(null, "Ora et labora!");
                continue;
            }
            int quersummeNote = 0;
            int i = 0;
            while (i < alleNoten.length()) {
                quersummeNote += Integer.parseInt(alleNoten.substring(i, i + 1));
                ++i;
            }
            float dNote = (float)((double)quersummeNote * 1.0 / (double)alleNoten.length());
            JOptionPane.showMessageDialog(null, "Durchschnittsnote: " + dNote);
        } while (!pruefung);
    }
}

