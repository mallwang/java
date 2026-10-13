/*
 * Decompiled with CFR 0.152.
 */
package vokabeln;

import javax.swing.JOptionPane;
import vokabeln.Worttester;

public class VkMain {
    public static void main(String[] args) {
        String wort = JOptionPane.showInputDialog("Dies ist ein Deutsch-Bayerisch W\u00f6rterbuch. Bitte geben Sie nun ein Wort ein:");
        wort = wort.toLowerCase();
        Worttester wT = new Worttester();
        wT.teste(wort);
    }
}

