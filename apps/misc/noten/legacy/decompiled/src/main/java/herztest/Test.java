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
            JOptionPane.showMessageDialog(null, "Super, du erh\u00c3\u00a4ltst ein \u00e2\u2122\u00a5");
            String GemZeit2 = JOptionPane.showInputDialog("Genie\u00c3\u0178t du die gemeinsame Zeit? ( j / n )");
            char GemZeit = GemZeit2.charAt(0);
            if (GemZeit == 'j') {
                JOptionPane.showMessageDialog(null, "Super, du erh\u00c3\u00a4ltst ein weiteres \u00e2\u2122\u00a5");
                String Schmett2 = JOptionPane.showInputDialog("Hast du Schmetterlinge im Bauch? ( j / n )");
                char Schmett = Schmett2.charAt(0);
                if (Schmett == 'j') {
                    JOptionPane.showMessageDialog(null, "Super, du erh\u00c3\u00a4ltst das letzte \u00e2\u2122\u00a5");
                    JOptionPane.showMessageDialog(null, "Du hast gewonnen und alle \u00e2\u2122\u00a5 \u00e2\u2122\u00a5 \u00e2\u2122\u00a5 gesammelt");
                } else {
                    JOptionPane.showMessageDialog(null, "Schade, aber du hast immerhin \u00e2\u2122\u00a5 \u00e2\u2122\u00a5 gesammelt");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Schade, du hast nur \u00e2\u2122\u00a5 gesammelt");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Schade, du hast kein \u00e2\u2122\u00a5 gesammelt");
        }
    }
}

