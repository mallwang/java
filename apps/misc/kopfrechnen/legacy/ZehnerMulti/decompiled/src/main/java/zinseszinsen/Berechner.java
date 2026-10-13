/*
 * Decompiled with CFR 0.152.
 */
package zinseszinsen;

public class Berechner {
    public void berechne(double b, double z, double l) {
        String z1 = String.valueOf(z);
        double wert1 = 0.0;
        double zinsen = b * (z / 100.0);
        wert1 = b + zinsen;
        System.out.println("Anlagebetrag: " + b + " Euro");
        System.out.println("Zinssatz: " + z + " %");
        System.out.println("Laufzeit: " + l + " Jahre");
        System.out.println("Ersparte Zinsen im Jahr 1: " + zinsen);
        System.out.println("Neuer Betrag: " + wert1);
        System.out.println(z1);
    }
}

