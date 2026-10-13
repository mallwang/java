/*
 * Decompiled with CFR 0.152.
 */
package herztest;

import javax.swing.JOptionPane;

public class Test {
    public static void main(String[] args) {
        String Interesse2 = JOptionPane.showInputDialog("Findest du Markus toll? ( j / n )");
        char Interesse = Interesse2.charAt(0);
        if (Interesse == 'j') {
            JOptionPane.showMessageDialog(null, "Super, du erh\u00e4ltst ein \u2665");
            String GemZeit2 = JOptionPane.showInputDialog("Genie\u00dft du die gemeinsame Zeit? ( j / n )");
            char GemZeit = GemZeit2.charAt(0);
            if (GemZeit == 'j') {
                JOptionPane.showMessageDialog(null, "Super, du erh\u00e4ltst ein weiteres \u2665");
                String Schmett2 = JOptionPane.showInputDialog("Hast du Schmetterlinge im Bauch? ( j / n )");
                char Schmett = Schmett2.charAt(0);
                if (Schmett == 'j') {
                    JOptionPane.showMessageDialog(null, "Super, du erh\u00e4ltst das letzte \u2665");
                    JOptionPane.showMessageDialog(null, "Du hast gewonnen und alle \u2665 \u2665 \u2665 gesammelt");
                } else {
                    JOptionPane.showMessageDialog(null, "Schade, aber du hast immerhin \u2665 \u2665 gesammelt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Schade, du hast nur \u2665 gesammelt");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Schade, du hast kein \u2665 gesammelt");
        }
    }
}

