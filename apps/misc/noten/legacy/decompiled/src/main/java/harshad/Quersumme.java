/*
 * Decompiled with CFR 0.152.
 */
package harshad;

public class Quersumme {
    public int berechne(String a) {
        char ch = '\u0000';
        int ergebnis = 0;
        int i = 0;
        while (i < a.length()) {
            ch = a.charAt(i);
            int c = ch - 48;
            ergebnis += c;
            ++i;
        }
        return ergebnis;
    }
}

