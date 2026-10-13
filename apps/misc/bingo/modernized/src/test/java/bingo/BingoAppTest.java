package bingo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BingoAppTest {
    @Test
    void formatsPercent() {
        assertEquals("0%", BingoApp.percent(0));
        assertEquals("100%", BingoApp.percent(10));
    }
}
