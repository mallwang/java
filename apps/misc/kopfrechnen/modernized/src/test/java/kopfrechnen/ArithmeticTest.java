package kopfrechnen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;

class ArithmeticTest {
    @Test
    void calculates() {
        assertEquals(5.0, Arithmetic.calculate("2+3"));
        assertEquals(3.5, Arithmetic.calculate(" 7 / 2 "));
        assertEquals(-4.0, Arithmetic.calculate("-2*2"));
        assertThrows(IllegalArgumentException.class, () -> Arithmetic.calculate("2+"));
    }

    @Test
    void multiplesSplitAndDigits() {
        assertEquals("3, 6, 9, 12, 15, 18, 21, 24, 27, 30", Arithmetic.multiples(3));
        assertTrue(Arithmetic.splitSeconds(31_536_000 + 86_400 + 3600 + 60 + 1)
                .endsWith("1 Jahre(n), 1 Tage(n), 1 Stunde(n), 1 Minute(n), 1 Sekunde(n)."));
        assertEquals(6, Arithmetic.digitSum("123"));
        assertEquals("eins null zwei", Arithmetic.spellDigits("102", false));
        assertEquals("zwei null eins", Arithmetic.spellDigits("102", true));
        assertThrows(IllegalArgumentException.class, () -> Arithmetic.digitSum("1a"));
    }

    @Test
    void quizTasksStayInRange() {
        var rnd = RandomGenerator.getDefault();
        for (int i = 0; i < 1000; i++) {
            var t = Quiz.ZEHNER_SUBTRAHIEREN.next(rnd);
            assertTrue(t.a() >= 11 && t.a() <= 20 && t.b() >= 1 && t.b() <= 10 && t.answer() > 0);
        }
        assertEquals(12, new Quiz.Task(3, 'x', 4).answer());
    }
}
