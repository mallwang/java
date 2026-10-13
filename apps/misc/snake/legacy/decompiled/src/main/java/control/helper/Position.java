/*
 * Decompiled with CFR 0.152.
 */
package control.helper;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int[] pos) {
        this(pos[0], pos[1]);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Position adjacentLeft() {
        return new Position(this.x - 1, this.y);
    }

    public Position adjacentUp() {
        return new Position(this.x, this.y - 1);
    }

    public Position adjacentRight() {
        return new Position(this.x + 1, this.y);
    }

    public Position adjacentDown() {
        return new Position(this.x, this.y + 1);
    }

    public boolean equals(Object obj) {
        Position p = (Position)obj;
        return this.x == p.x && this.y == p.y;
    }
}

