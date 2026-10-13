package spacewar;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

/** Rules and state of "Space Invasion" (no Swing). One {@link #tick()} moves everything by one step. */
public final class SpaceGame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    static final int CRAFT_W = 43, CRAFT_H = 24;
    static final int ALIEN_SIZE = 20;
    static final int MISSILE_W = 14, MISSILE_H = 5;
    private static final int CRAFT_SPEED = 2;
    private static final int MISSILE_SPEED = 5;
    private static final int MISSILE_LIMIT_X = 390;
    private static final int INITIAL_ALIENS = 20;

    /** A rectangle that can move and be "killed". */
    public static final class Sprite {
        public int x, y;
        final int width, height;
        boolean alive = true;

        Sprite(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        Rectangle bounds() {
            return new Rectangle(x, y, width, height);
        }
    }

    private final RandomGenerator random;
    private final Sprite craft = new Sprite(40, 60, CRAFT_W, CRAFT_H);
    private final List<Sprite> aliens = new ArrayList<>();
    private final List<Sprite> missiles = new ArrayList<>();
    private int dx, dy;
    private int killed;
    private boolean over;

    public SpaceGame(RandomGenerator random) {
        this.random = random;
        for (int i = 0; i < INITIAL_ALIENS; i++) {
            spawnAlien(800, 1000);
        }
    }

    /** Sets the craft direction; each argument is -1, 0 or 1. */
    public void steer(int sx, int sy) {
        dx = sx * CRAFT_SPEED;
        dy = sy * CRAFT_SPEED;
    }

    public void fire() {
        if (!over) {
            missiles.add(new Sprite(craft.x + CRAFT_W, craft.y + CRAFT_H / 2, MISSILE_W, MISSILE_H));
        }
    }

    public void tick() {
        for (var m : missiles) {
            m.x += MISSILE_SPEED;
            m.alive &= m.x <= MISSILE_LIMIT_X;
        }
        missiles.removeIf(m -> !m.alive);

        for (var a : aliens) {
            if (a.x < 0) {
                a.x = WIDTH;
            }
            a.x--;
        }
        var before = aliens.size();
        aliens.removeIf(a -> !a.alive);
        var destroyed = before - aliens.size();
        killed += destroyed;
        for (int i = 0; i < destroyed; i++) {
            spawnAlien(500, 500);
        }

        craft.x = Math.clamp(craft.x + dx, 1, WIDTH - CRAFT_W);
        craft.y = Math.clamp(craft.y + dy, 1, HEIGHT - CRAFT_H);
        checkCollisions();
    }

    private void checkCollisions() {
        var c = craft.bounds();
        for (var a : aliens) {
            if (c.intersects(a.bounds())) {
                craft.alive = false;
                a.alive = false;
                over = true;
            }
        }
        for (var m : missiles) {
            for (var a : aliens) {
                if (m.bounds().intersects(a.bounds())) {
                    m.alive = false;
                    a.alive = false;
                }
            }
        }
    }

    /** New alien right of the board: x in [offset, offset + spread), y in [30, 260). */
    private void spawnAlien(int offset, int spread) {
        aliens.add(new Sprite(random.nextInt(spread) + offset, random.nextInt(230) + 30, ALIEN_SIZE, ALIEN_SIZE));
    }

    public boolean isOver() {
        return over;
    }

    public int killed() {
        return killed;
    }

    public Sprite craft() {
        return craft;
    }

    public List<Sprite> aliens() {
        return aliens;
    }

    public List<Sprite> missiles() {
        return missiles;
    }
}
