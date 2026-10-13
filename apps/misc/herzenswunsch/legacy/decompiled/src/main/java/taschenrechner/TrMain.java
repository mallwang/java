/*
 * Decompiled with CFR 0.152.
 */
package taschenrechner;

import javax.swing.JOptionPane;
import taschenrechner.Addition;
import taschenrechner.Division;
import taschenrechner.Multiplikation;
import taschenrechner.Subtraktion;

public class TrMain {
    public static void main(String[] args) {
        String eingabe = JOptionPane.showInputDialog("Mit diesem kleinen Taschenrechner k\u00f6nnen Sie einfache Rechenaufgaben l\u00f6sen. Geben Sie bitte nun eine komplette Rechenaufgabe ein:");
        int i = 0;
        while (i < eingabe.length()) {
            switch (eingabe.charAt(i)) {
                case '+': {
                    Addition.addition(eingabe);
                    break;
                }
                case '-': {
                    Subtraktion.subtraktion(eingabe);
                    break;
                }
                case '*': {
                    Multiplikation.multiplikation(eingabe);
                    break;
                }
                case '/': {
                    Division.division(eingabe);
                }
            }
            ++i;
        }
    }
}

