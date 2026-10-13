/*
 * Decompiled with CFR 0.152.
 */
package sekundenzerlegung;

import javax.swing.JOptionPane;

public class Sekundenzerleger {
    int j;
    int t;
    int s;
    int m;
    int sk = 0;

    public void teileAuf(int sek) {
        this.j = sek / 31536000;
        this.t = sek / 86400 - 365 * (sek / 31536000);
        this.s = sek / 3600 - 24 * (sek / 86400);
        this.m = sek / 60 - 60 * (sek / 3600);
        this.sk = sek - 60 * (sek / 60);
        System.out.println(String.valueOf(sek) + " Sekunden entsprechen: " + this.j + " Jahre(n), " + this.t + " Tage(n), " + this.s + " Stunde(n), " + this.m + " Minute(n), " + this.sk + " Sekunde(n).");
        JOptionPane.showMessageDialog(null, String.valueOf(sek) + " Sekunden entsprechen: " + this.j + " Jahre(n), " + this.t + " Tage(n), " + this.s + " Stunde(n), " + this.m + " Minute(n), " + this.sk + " Sekunde(n).");
    }
}

