/*
 * Decompiled with CFR 0.152.
 */
package taschenrechner;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Addition {
    public static void addition(String eingabe) {
        String operand = "+";
        StringTokenizer st = new StringTokenizer(eingabe, operand);
        String str1 = st.nextToken();
        String str2 = st.nextToken();
        int wert1 = Integer.parseInt(str1);
        int wert2 = Integer.parseInt(str2);
        double ergebnis = (double)wert1 + (double)wert2;
        JOptionPane.showMessageDialog(null, "Ergebnis ist: " + ergebnis);
        System.out.println("Ergebnis ist: " + ergebnis);
    }
}

