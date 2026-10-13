package snake;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;

/** Rules and state of the game, independent of Swing so it can be tested. */
final class SnakeGame {
    static final int WIDTH = 30;
    static final int HEIGHT = 20;
    private static final int START_LENGTH = 3;

    enum Direction {
        LEFT(-1, 0), UP(0, -1), RIGHT(1, 0), DOWN(0, 1);

        final int dx, dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        Direction opposite() {
            return values()[(ordinal() + 2) % 4];
        }
    }

    record Position(int x, int y) {}

    private final RandomGenerator rnd;
    private final Deque<Position> body = new ArrayDeque<>(); // tail first, head last
    private final Set<Position> occupied = new HashSet<>();
    private Direction direction = Direction.RIGHT;
    private Direction lastMoved = Direction.RIGHT;
    private Position food;
    private int score;
    private boolean over;

    SnakeGame(RandomGenerator rnd) {
        this.rnd = rnd;
        for (int x = 1; x <= START_LENGTH; x++) {
            grow(new Position(x, 10));
        }
        food = randomFreeCell();
    }

    /** Ignores a turn back onto the snake's own neck (compared to the last move, not the last key press). */
    void turn(Direction d) {
        if (d != lastMoved.opposite()) {
            direction = d;
        }
    }

    /** Advances one step; hitting a wall or the snake ends the game. */
    void tick() {
        if (over) {
            return;
        }
        var head = new Position(body.getLast().x() + direction.dx, body.getLast().y() + direction.dy);
        if (head.x() < 0 || head.x() >= WIDTH || head.y() < 0 || head.y() >= HEIGHT || occupied.contains(head)) {
            over = true;
            return;
        }
        lastMoved = direction;
        grow(head);
        if (head.equals(food)) {
            score += 10;
            if (body.size() == WIDTH * HEIGHT) {
                over = true; // board full: nothing left to eat
            } else {
                food = randomFreeCell();
            }
        } else {
            occupied.remove(body.removeFirst());
        }
    }

    private void grow(Position head) {
        body.addLast(head);
        occupied.add(head);
    }

    private Position randomFreeCell() {
        Position p;
        do {
            p = new Position(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
        } while (occupied.contains(p));
        return p;
    }

    Iterable<Position> body() { return body; }
    Position food() { return food; }
    int score() { return score; }
    int length() { return body.size(); }
    int fruits() { return body.size() - START_LENGTH; }
    boolean isOver() { return over; }

    /** Test hook: puts the food on a given cell. */
    void placeFood(Position p) { food = p; }
}
