/*
 * Decompiled with CFR 0.152.
 */
package eurostueckelung;

import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class EsMain {
    public static void main(String[] args) {
        String euroBetrag = JOptionPane.showInputDialog("Wert eingeben");
        String komma = ",";
        StringTokenizer sT = new StringTokenizer(euroBetrag, komma);
        String vKomma1 = sT.nextToken();
        String nKomma1 = sT.nextToken();
        int vKomma = Integer.parseInt(vKomma1);
        int nKomma = Integer.parseInt(nKomma1);
        System.out.println(vKomma);
        System.out.println(nKomma);
    }
}

