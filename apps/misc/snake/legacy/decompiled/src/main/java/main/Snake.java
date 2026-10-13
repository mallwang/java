/*
 * Decompiled with CFR 0.152.
 */
package main;

import surface.SurfaceSnake;

public class Snake {
    public static final int X_MAX = 30;
    public static final int Y_MAX = 20;
    public static final int X_LENGTH = 8;
    public static final int Y_LENGTH = 8;
    public static final int WINDOW_X_SIZE = 240;
    public static final int WINDOW_Y_SIZE = 160;
    public static final long VELOCITY = 90L;
    private static SurfaceSnake surf;

    public static SurfaceSnake getSurf() {
        return surf;
    }

    public static void main(String[] args) {
        surf = new SurfaceSnake();
        surf.setVisible(true);
    }
}

