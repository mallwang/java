/*
 * Decompiled with CFR 0.152.
 */
package surface;

import control.Direction;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import surface.CanvasSnake;

public class SurfaceSnake
extends JFrame
implements KeyListener,
MouseListener {
    private static final long serialVersionUID = 1L;
    private CanvasSnake canvas;
    private JLabel scoreLabel;

    public SurfaceSnake() {
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Hungry Snake");
        this.scoreLabel = new JLabel(this.prepareInfoString(0, 3, 0));
        this.canvas = new CanvasSnake();
        this.addKeyListener(this);
        this.canvas.addMouseListener(this);
        this.canvas.setSize(240, 160);
        this.setSize(this.canvas.getWidth() + 100, this.canvas.getHeight() + 26);
        this.setResizable(false);
        this.canvas.setBounds(0, 0, 240, 160);
        this.scoreLabel.setBounds(250, -18, 160, 100);
        Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((winDim.width - this.getWidth()) / 2, (winDim.height - this.getHeight()) / 3);
        this.add(this.canvas);
        this.add(this.scoreLabel);
    }

    public JLabel getScoreLabel() {
        return this.scoreLabel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction dir = this.canvas.getMovement().getDirection();
        if (e.getKeyCode() == 37 && dir != Direction.RIGHT) {
            this.canvas.getMovement().setDirection(Direction.LEFT);
        }
        if (e.getKeyCode() == 38 && dir != Direction.DOWN) {
            this.canvas.getMovement().setDirection(Direction.UP);
        }
        if (e.getKeyCode() == 39 && dir != Direction.LEFT) {
            this.canvas.getMovement().setDirection(Direction.RIGHT);
        }
        if (e.getKeyCode() == 40 && dir != Direction.UP) {
            this.canvas.getMovement().setDirection(Direction.DOWN);
        }
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.canvas.isGameOver()) {
            this.scoreLabel.setText(this.prepareInfoString(0, 3, 0));
            this.canvas.init();
        }
    }

    public String prepareInfoString(int score, int length, int fruits) {
        return "<html>Punkte: <font color=blue>" + score + "</font><br>" + "L\u00e4nge: <font color=#7B8C80>" + length + "</font><br>" + "Fr\u00fcchte: <font color=red>" + fruits + "</font></html>";
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int n;
        if (this.canvas.isGameOver() && e.getKeyCode() == 27 && (n = JOptionPane.showConfirmDialog(this, "M\u00f6chtest du wirklich beenden?", "Hungry Snake beenden", 0)) == 0) {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

