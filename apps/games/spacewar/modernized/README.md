# Space Invasion

Side-scrolling shooter on a 400 x 300 board. Aliens fly in from the right; shoot them with missiles. Every destroyed
alien counts and is replaced by a new one. The game ends when an alien touches your craft; the best score is kept in
`highscore.txt`.

Modernized from the legacy Eclipse project `spacewar` (see `../legacy/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/spacewar-2.0.jar
```

Controls: arrow keys move, **Space** or **F** fires, **Esc** asks to quit. The highscore file is read from and written
to the directory you start the game in.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # game rules and highscore format, no display needed
mvn package    # tests + target/spacewar-2.0.jar (executable)
```

### Project layout

```
src/main/java/spacewar/SpaceGame.java   rules and state (no Swing)
src/main/java/spacewar/HighScore.java   highscore file format
src/main/java/spacewar/SpaceApp.java    window, rendering, keys, game timer
src/main/resources/img/                 craft, alien and missile images
src/test/java/spacewar/SpaceGameTest.java
```

### Changes compared to the legacy version

- 5 classes -> 3. Rules are separated from the UI (`SpaceGame`); raw `ArrayList`s and casts are gone, as are the
  per-object image loading with `System.exit` (done once in the app) and the unused code (`getIngame`, `Collision` frame).
- Images were named `*.PNG` but loaded as `*.png`, which only worked on case-insensitive file systems (Windows).
  They are now `img/*.png` inside the JAR.
- The highscore was rewritten on every repaint of the game-over screen; now it is saved once. The file format is
  unchanged (the old `highscore.txt` still works). A missing file is no longer created at start.
- Missiles removed while iterating skipped the next missile; fixed.
- The craft can no longer leave the board to the right or bottom; holding two opposite arrows and releasing one no
  longer stops the craft.
- The game timer stops at game over and while the quit dialog is open.
- Unchanged: 5 ms tick, speeds, 20 initial aliens, scoring, wrap-around of aliens at the left edge, texts.
