package spacewar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

class SpaceGameTest {

    private static SpaceGame emptyGame() {
        var g = new SpaceGame(new Random(1));
        g.aliens().clear();
        return g;
    }

    private static SpaceGame.Sprite alienAt(SpaceGame g, int x, int y) {
        g.aliens().add(new SpaceGame.Sprite(x, y, SpaceGame.ALIEN_SIZE, SpaceGame.ALIEN_SIZE));
        return g.aliens().getLast();
    }

    @Test
    void startsWithTwentyAliensOffscreen() {
        var g = new SpaceGame(new Random(1));
        assertEquals(20, g.aliens().size());
        assertTrue(g.aliens().stream().allMatch(a -> a.x >= 800 && a.y >= 30 && a.y < 260));
    }

    @Test
    void missileKillsAlienAndAlienRespawns() {
        var g = emptyGame();
        alienAt(g, 150, 70);
        g.fire(); // missile at (83, 72)
        for (int i = 0; i < 20 && g.killed() == 0; i++) {
            g.tick();
        }
        assertEquals(1, g.killed());
        assertEquals(1, g.aliens().size()); // replacement spawned
        assertFalse(g.isOver());
    }

    @Test
    void touchingAlienEndsGame() {
        var g = emptyGame();
        alienAt(g, 50, 60);
        g.tick();
        assertTrue(g.isOver());
    }

    @Test
    void craftStaysOnBoard() {
        var g = emptyGame();
        g.steer(1, 1);
        for (int i = 0; i < 400; i++) {
            g.tick();
        }
        assertEquals(SpaceGame.WIDTH - SpaceGame.CRAFT_W, g.craft().x);
        assertEquals(SpaceGame.HEIGHT - SpaceGame.CRAFT_H, g.craft().y);
    }

    @Test
    void alienWrapsAroundAtLeftEdge() {
        var g = emptyGame();
        var a = alienAt(g, -1, 200);
        g.tick();
        assertEquals(399, a.x);
    }

    @Test
    void highscoreFormatMatchesLegacyFile() {
        assertEquals(43, HighScore.decode("17613"));
        assertEquals("17613", HighScore.encode(43));
        assertEquals(0, HighScore.decode("0"));
    }
}
