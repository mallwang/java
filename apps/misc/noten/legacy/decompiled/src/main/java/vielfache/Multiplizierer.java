/*
 * Decompiled with CFR 0.152.
 */
package vielfache;

import javax.swing.JOptionPane;

public class Multiplizierer {
    public void multiplizieren(int n) {
        String alle = "";
        int w = n;
        System.out.println("Die Vielfachen von 1 bis 10:");
        int i = 1;
        while (i <= 10) {
            w = n * i;
            alle = String.valueOf(alle) + ", " + w;
            ++i;
        }
        System.out.println(alle);
        JOptionPane.showMessageDialog(null, "Die Vielfachen von " + n + " lauten:" + alle);
    }
}

