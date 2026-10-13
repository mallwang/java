/*
 * Decompiled with CFR 0.152.
 */
package zinseszinsen;

import javax.swing.JOptionPane;
import zinseszinsen.Berechner;

public class Main {
    public static void main(String[] args) {
        String n1 = JOptionPane.showInputDialog("Anzulegender Betrag in Euro?");
        double anlageBetrag = Integer.parseInt(n1);
        String n2 = JOptionPane.showInputDialog("Wie hoch ist der Jahreszins?");
        double zinsen = Integer.parseInt(n2);
        String n3 = JOptionPane.showInputDialog("Wie lange betr\u00e4gt die Laufzeit?");
        double laufzeit = Integer.parseInt(n3);
        Berechner b = new Berechner();
        b.berechne(anlageBetrag, zinsen, laufzeit);
    }
}

