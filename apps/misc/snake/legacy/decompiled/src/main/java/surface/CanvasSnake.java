/*
 * Decompiled with CFR 0.152.
 */
package surface;

import control.helper.Position;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import surface.MovementSnake;

public class CanvasSnake
extends JPanel {
    private static final long serialVersionUID = 1L;
    private MovementSnake movement;
    private boolean gameOver;
    private Queue<Position> queue;
    private Position nextTile;

    public CanvasSnake() {
        this.setBorder(new LineBorder(Color.BLACK));
        this.init();
    }

    public void init() {
        this.queue = new LinkedList<Position>(){
            private static final long serialVersionUID = 1L;

            @Override
            public boolean contains(Object o) {
                Position p = (Position)o;
                boolean found = false;
                Iterator i = this.iterator();
                while (i.hasNext() && !found) {
                    if (!((Position)i.next()).equals(p)) continue;
                    found = true;
                }
                return found;
            }
        };
        this.movement = new MovementSnake(this, this.queue);
        this.movement.start();
        this.gameOver = false;
    }

    public Queue<Position> getQueue() {
        return this.queue;
    }

    public MovementSnake getMovement() {
        return this.movement;
    }

    public Position getNextTile() {
        return this.nextTile;
    }

    public void setNextTile(Position nextTile) {
        this.nextTile = nextTile;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.gameOver) {
            g.setColor(new Color(150, 200, 120));
        } else {
            g.setColor(new Color(0, 128, 0));
        }
        this.paintFields(g);
        if (this.gameOver) {
            g.setColor(Color.ORANGE);
            Font font = new Font("Serif", 1, 42);
            g.setFont(font);
            g.drawString("Game Over", 20, 70);
            font = new Font("Serif", 1, 14);
            g.setFont(font);
            g.drawString("Click to Continue / Esc to Close", 25, 95);
        }
    }

    private void paintFields(Graphics g) {
        for (Position pos : this.queue) {
            g.fillRect(pos.getX() * 8 + 1, pos.getY() * 8 + 1, 7, 7);
        }
        g.setColor(Color.RED);
        g.fillOval(this.nextTile.getX() * 8 + 1, this.nextTile.getY() * 8 + 1, 7, 7);
    }

    public void gameOver() {
        this.gameOver = true;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }
}

