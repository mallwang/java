package spacewar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/** "Space Invasion": steer with the arrow keys, fire with space or F, Esc quits. Highscore in ./highscore.txt. */
public final class SpaceApp extends JFrame {
    private static final int TICK_MS = 5;
    private static final Path HIGHSCORE_FILE = Path.of("highscore.txt");

    private final Image craftImage = load("raumschiff");
    private final Image alienImage = load("alien");
    private final Image missileImage = load("rakete");
    private final Set<Integer> held = new HashSet<>();
    private final Timer timer = new Timer(TICK_MS, e -> tick());
    private final int highscore = HighScore.load(HIGHSCORE_FILE);
    private final SpaceGame game = new SpaceGame(RandomGenerator.getDefault());
    private final Board board = new Board();
    private boolean newHighscore;

    private SpaceApp() {
        super("Space Invasion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(board);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                held.add(e.getKeyCode());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE, KeyEvent.VK_F -> game.fire();
                    case KeyEvent.VK_ESCAPE -> confirmQuit();
                    default -> { }
                }
                steer();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                held.remove(e.getKeyCode());
                steer();
            }
        });
        timer.start();
    }

    private static Image load(String name) {
        URL url = SpaceApp.class.getResource("/img/" + name + ".png");
        if (url == null) {
            JOptionPane.showMessageDialog(null, "Es wurden keine Bilder gefunden!\nDas Programm wird beendet.",
                    "Bilder nicht gefunden", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return new ImageIcon(url).getImage();
    }

    private void steer() {
        game.steer((held.contains(KeyEvent.VK_RIGHT) ? 1 : 0) - (held.contains(KeyEvent.VK_LEFT) ? 1 : 0),
                (held.contains(KeyEvent.VK_DOWN) ? 1 : 0) - (held.contains(KeyEvent.VK_UP) ? 1 : 0));
    }

    private void confirmQuit() {
        timer.stop();
        if (JOptionPane.showConfirmDialog(this, "Möchtest du wirklich beenden?", "Space Invasion beenden",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        if (!game.isOver()) {
            timer.start();
        }
    }

    private void tick() {
        game.tick();
        if (game.isOver()) {
            timer.stop();
            if (game.killed() > highscore) {
                newHighscore = true;
                try {
                    HighScore.save(HIGHSCORE_FILE, game.killed());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        board.repaint();
    }

    private final class Board extends JPanel {
        Board() {
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(SpaceGame.WIDTH, SpaceGame.HEIGHT));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            if (game.isOver()) {
                g.setFont(new Font("Helvetica", Font.BOLD, 14));
                center(g, "Game Over", getHeight() / 3);
                center(g, (newHighscore ? "Neuer Highscore: " : "Zerstörte Aliens: ") + game.killed(), getHeight() / 2);
                return;
            }
            var c = game.craft();
            if (c.alive) {
                g.drawImage(craftImage, c.x, c.y, null);
            }
            game.missiles().forEach(m -> g.drawImage(missileImage, m.x, m.y, null));
            game.aliens().stream().filter(a -> a.alive).forEach(a -> g.drawImage(alienImage, a.x, a.y, null));
            g.drawString("Aliens zerstört: " + game.killed(), 5, 15);
            g.drawString("Leer/F = Feuern", 300, 15);
            g.drawString("Highscore: " + highscore, 5, getHeight() - 5);
            g.drawString("Esc = Beenden", 300, getHeight() - 5);
        }

        private void center(Graphics g, String text, int y) {
            g.drawString(text, (getWidth() - g.getFontMetrics().stringWidth(text)) / 2, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpaceApp().setVisible(true));
    }
}
