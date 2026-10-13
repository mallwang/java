/*
 * Decompiled with CFR 0.152.
 */
package sonstiges;

public class SMain2 {
    public static void main(String[] args) {
        int j;
        int i = 0;
        while (i < 100) {
            if (i == 74) break;
            if (i % 9 == 0) {
                System.out.println(i);
            }
            ++i;
        }
        i = 0;
        while ((j = ++i * 30) != 1260) {
            if (i % 10 != 0) continue;
            System.out.println(i);
        }
    }
}

