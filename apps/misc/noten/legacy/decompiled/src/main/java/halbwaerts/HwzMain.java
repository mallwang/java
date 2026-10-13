/*
 * Decompiled with CFR 0.152.
 */
package halbwaerts;

import javax.swing.JOptionPane;

public class HwzMain {
    public static void main(String[] args) {
        String a1 = JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein:");
        int a = Integer.parseInt(a1);
        float b = a;
        while (b > 1.0f) {
            JOptionPane.showMessageDialog(null, "Der neue Wert lautet: " + (b /= 2.0f));
        }
    }
}

