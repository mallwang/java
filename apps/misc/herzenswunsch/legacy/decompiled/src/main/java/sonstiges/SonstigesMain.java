/*
 * Decompiled with CFR 0.152.
 */
package sonstiges;

public class SonstigesMain {
    public static void main(String[] args) {
        block0: while (true) {
            float i = 100001.0f;
            while (true) {
                if (!(i > 0.0f)) continue block0;
                System.out.println(i);
                System.out.println(i / 2.0f);
                System.out.println(i / 3.0f);
                System.out.println(i / 4.0f);
                int j = 1;
                while (j < 100001) {
                    System.out.println(j);
                    ++j;
                }
                i -= 1.0f;
            }
            break;
        }
    }
}

