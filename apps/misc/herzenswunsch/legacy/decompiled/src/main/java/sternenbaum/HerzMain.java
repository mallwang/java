/*
 * Decompiled with CFR 0.152.
 */
package sternenbaum;

import javax.swing.JOptionPane;

public class HerzMain {
    public static void main(String[] args) {
        String n1 = JOptionPane.showInputDialog("Bitte nenne mir deinen Namen:");
        String n2 = JOptionPane.showInputDialog("Hallo " + n1 + ", wieviele \u2665 m\u00f6chtest du?");
        int n = Integer.parseInt(n2);
        System.out.println("Anzahl der Herzen: " + n);
        String a = "";
        if (n < 21) {
            int i = n;
            while (i < 21 && i > 0) {
                a = String.valueOf(a) + "\u2665 ";
                --i;
            }
            JOptionPane.showMessageDialog(null, String.valueOf(n1) + ", du erh\u00e4ltst: " + a);
            System.out.println(a);
        } else {
            JOptionPane.showMessageDialog(null, "Zu viele Herzen!");
        }
    }
}

