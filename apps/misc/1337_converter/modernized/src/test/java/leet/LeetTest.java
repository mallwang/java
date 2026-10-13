package leet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import leet.Leet.Mode;
import org.junit.jupiter.api.Test;

class LeetTest {
    @Test
    void simple() {
        assertEquals("73x7 83f1nd37 51ch", Leet.convert("Text befindet sich", Mode.SIMPLE));
    }

    @Test
    void ultimate() {
        assertEquals("/-/4|_|_0 \\/\\/0®|_l)!", Leet.convert("Hallo World!", Mode.ULTIMATE));
    }

    @Test
    void empty() {
        assertEquals("", Leet.convert("", Mode.SIMPLE));
    }
}
