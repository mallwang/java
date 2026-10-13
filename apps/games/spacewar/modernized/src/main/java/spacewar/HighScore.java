package spacewar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Highscore file in the legacy format: the score is stored lightly obfuscated as {@code (score * 24 + 4) * 17 + 1}. */
final class HighScore {

    static String encode(int score) {
        return String.valueOf((score * 24 + 4) * 17 + 1);
    }

    static int decode(String text) {
        return (((Integer.parseInt(text.strip()) - 1) / 17) + 4) / 24;
    }

    /** Returns 0 if the file is missing or unreadable. */
    static int load(Path file) {
        try {
            return decode(Files.readString(file));
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    static void save(Path file, int score) throws IOException {
        Files.writeString(file, encode(score));
    }

    private HighScore() {}
}
