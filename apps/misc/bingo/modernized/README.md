# Bingo ("Grünes Fenster")

A tiny click game: press **Klick Mich!** ten times. The progress bar fills in 10 % steps; at 100 %
the window turns red and "BINGO!!" asks whether to quit (Yes quits, No keeps the window open).

Modernized from the legacy `Bingo.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`). The original used the package name `aufgabe1` (an exercise).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/bingo-1.1.jar
```

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test
mvn package    # target/bingo-1.1.jar (executable)
```

### Project layout

```
src/main/java/bingo/BingoApp.java
src/test/java/bingo/BingoAppTest.java   formatting only; the GUI is not unit-tested
```

### Changes compared to the legacy JAR

- Package/class `aufgabe1.Fenster` -> `bingo.BingoApp`; `ActionListener` lambda instead of a
  `MouseListener` with four empty methods (so keyboard activation of the button works too).
- Window is created on the EDT; the duplicated "BINGO!!" branch is merged.
- Same size, texts, colors and absolute layout as before.
