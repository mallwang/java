/*
 * Decompiled with CFR 0.152.
 */
package sternenbaum;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Wieviele Zeilen m\u00f6chten Sie?");
        int n = Integer.parseInt(n2);
        System.out.println("Anzahl der Stellen: " + n);
        String a = "";
        int i = n;
        while (i > 0) {
            a = String.valueOf(a) + "* ";
            System.out.println(a);
            --i;
        }
    }
}

