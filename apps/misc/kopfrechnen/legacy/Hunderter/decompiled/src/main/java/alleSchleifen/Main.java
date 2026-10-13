/*
 * Decompiled with CFR 0.152.
 */
package alleSchleifen;

import alleSchleifen.DoSchleife;
import alleSchleifen.ForSchleife;
import alleSchleifen.WhileSchleife;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String n2 = JOptionPane.showInputDialog("Bitte geben Sie eine Zahl ein:");
        double n = Integer.parseInt(n2);
        ForSchleife fS = new ForSchleife();
        WhileSchleife wS = new WhileSchleife();
        DoSchleife dS = new DoSchleife();
        fS.forSchleife(n);
        wS.whileSchleife(n);
        dS.doSchleife(n);
    }
}

