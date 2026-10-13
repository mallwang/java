package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.random.RandomGenerator;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import snake.SnakeGame.Direction;

/** "Hungry Snake": arrow keys steer; after game over click to restart or press Esc to quit. */
public final class SnakeApp extends JFrame {
    private static final int CELL = 8;
    private static final int TICK_MS = 90;
    private static final Map<Integer, Direction> KEYS = Map.of(
            KeyEvent.VK_LEFT, Direction.LEFT, KeyEvent.VK_UP, Direction.UP,
            KeyEvent.VK_RIGHT, Direction.RIGHT, KeyEvent.VK_DOWN, Direction.DOWN);

    private final JLabel scoreLabel = new JLabel();
    private final Timer timer = new Timer(TICK_MS, e -> tick());
    private final Board board = new Board();
    private SnakeGame game;

    private SnakeApp() {
        super("Hungry Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        scoreLabel.setPreferredSize(new Dimension(90, 0));
        add(board);
        add(scoreLabel, BorderLayout.EAST);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                var d = KEYS.get(e.getKeyCode());
                if (d != null) {
                    game.turn(d);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (game.isOver() && e.getKeyCode() == KeyEvent.VK_ESCAPE
                        && JOptionPane.showConfirmDialog(SnakeApp.this, "Möchtest du wirklich beenden?",
                                "Hungry Snake beenden", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (game.isOver()) {
                    start();
                }
            }
        });
        start();
    }

    private void start() {
        game = new SnakeGame(RandomGenerator.getDefault());
        updateScore();
        timer.start();
        board.repaint();
    }

    private void tick() {
        int before = game.score();
        game.tick();
        if (game.score() != before) {
            updateScore();
        }
        if (game.isOver()) {
            timer.stop();
        }
        board.repaint();
    }

    private void updateScore() {
        scoreLabel.setText("<html>Punkte: <font color=blue>%d</font><br>Länge: <font color=#7B8C80>%d</font><br>Früchte: <font color=red>%d</font></html>"
                .formatted(game.score(), game.length(), game.fruits()));
    }

    private final class Board extends JPanel {
        Board() {
            setPreferredSize(new Dimension(SnakeGame.WIDTH * CELL, SnakeGame.HEIGHT * CELL));
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(game.isOver() ? new Color(150, 200, 120) : new Color(0, 128, 0));
            for (var p : game.body()) {
                g.fillRect(p.x() * CELL + 1, p.y() * CELL + 1, CELL - 1, CELL - 1);
            }
            g.setColor(Color.RED);
            g.fillOval(game.food().x() * CELL + 1, game.food().y() * CELL + 1, CELL - 1, CELL - 1);
            if (game.isOver()) {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Serif", Font.BOLD, 42));
                g.drawString("Game Over", 20, 70);
                g.setFont(new Font("Serif", Font.BOLD, 14));
                g.drawString("Click to Continue / Esc to Close", 25, 95);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeApp().setVisible(true));
    }
}
