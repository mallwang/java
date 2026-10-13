package kopfrechnen;

import java.util.random.RandomGenerator;

/** One mental-arithmetic exercise type; operand ranges are those of the legacy apps. */
enum Quiz {
    HUNDERTER_ADDIEREN('+', 1, 100, 1, 100),
    ZEHNER_ADDIEREN('+', 1, 10, 1, 10),
    ZEHNER_MULTIPLIZIEREN('x', 1, 10, 1, 10),
    ZEHNER_SUBTRAHIEREN('-', 11, 20, 1, 10);

    /** A concrete task such as "7 + 12". */
    record Task(int a, char op, int b) {
        int answer() {
            return switch (op) {
                case '+' -> a + b;
                case '-' -> a - b;
                default -> a * b;
            };
        }

        @Override
        public String toString() {
            return a + " " + op + " " + b;
        }
    }

    private final char op;
    private final int minA, maxA, minB, maxB;

    Quiz(char op, int minA, int maxA, int minB, int maxB) {
        this.op = op;
        this.minA = minA;
        this.maxA = maxA;
        this.minB = minB;
        this.maxB = maxB;
    }

    Task next(RandomGenerator rnd) {
        return new Task(rnd.nextInt(minA, maxA + 1), op, rnd.nextInt(minB, maxB + 1));
    }
}
