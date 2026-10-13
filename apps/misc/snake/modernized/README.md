# Hungry Snake

Classic snake on a 30 x 20 board. Eat the red fruits (+10 points each); the snake grows with every fruit.
The game ends when you hit a wall or yourself.

Modernized from the legacy `Snake_1.0.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/snake-1.1.jar
```

Controls: arrow keys steer. After "Game Over": click the board to play again, **Esc** asks to quit.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # game rules, no display needed
mvn package    # tests + target/snake-1.1.jar (executable)
```

### Project layout

```
src/main/java/snake/SnakeGame.java   rules and state (no Swing)
src/main/java/snake/SnakeApp.java    window, rendering, key/mouse handling, game timer
src/test/java/snake/SnakeGameTest.java
```

### Changes compared to v1.0

- 8 classes -> 2. The game logic is separated from the UI (`SnakeGame`), `Position` is a record
  (the old one had `equals` without `hashCode`), the body is a `Deque` plus a `HashSet` for collision checks.
- The game loop was a background thread that called Swing directly; now a `javax.swing.Timer` (90 ms) on the EDT.
- A turn is only rejected if it reverses the *last move*; before, two quick key presses (e.g. UP, LEFT while
  moving right) could steer the snake into its own neck. The `Thread.sleep` in the key handler is gone.
- Food placement no longer loops forever on a full board (the game ends instead).
- Layout uses `BorderLayout` instead of absolute positions; the window is centered.
- Unchanged: board size, speed, scoring, colors, texts, "tail cell counts as collision" rule.
