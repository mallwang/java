/*
 * Decompiled with CFR 0.152.
 */
package alleSchleifen;

public class DoSchleife {
    public void doSchleife(double n) {
        double i = 3.0;
        do {
            System.out.println("do Schleife: " + 1.0 / (2.0 * i + 1.0));
        } while ((i += 1.0) < 2.0 * n);
    }
}

