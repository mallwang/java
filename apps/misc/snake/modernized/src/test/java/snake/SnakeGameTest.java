package snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;
import snake.SnakeGame.Direction;
import snake.SnakeGame.Position;

class SnakeGameTest {
    private final SnakeGame game = new SnakeGame(RandomGenerator.getDefault());

    @Test
    void eatingFoodGrowsAndScores() {
        game.placeFood(new Position(4, 10));
        game.tick();
        assertEquals(10, game.score());
        assertEquals(4, game.length());
        assertEquals(1, game.fruits());
    }

    @Test
    void movingKeepsLengthAndWallEndsGame() {
        game.tick();
        assertEquals(3, game.length());
        for (int i = 0; i < SnakeGame.WIDTH; i++) {
            game.tick();
        }
        assertTrue(game.isOver());
    }

    @Test
    void reversingIntoTheNeckIsIgnored() {
        game.turn(Direction.UP);
        game.turn(Direction.LEFT); // opposite of the last move (RIGHT): ignored, snake keeps going UP
        game.tick();
        assertFalse(game.isOver());
    }
}
