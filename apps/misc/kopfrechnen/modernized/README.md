# Kopfrechnen

Nine small mental-arithmetic apps (Swing `JOptionPane` dialogs, German UI) in one JAR.
Starting the JAR shows a chooser:

| App | What it does |
|-----|--------------|
| Hunderter addieren | Quiz: add two numbers 1-100 |
| Zehner addieren / multiplizieren | Quiz: two numbers 1-10 |
| Zehner subtrahieren | Quiz: 11-20 minus 1-10 |
| Quersummenrechner | Digit sum of a number |
| Zahlzerlegung | Spells a number digit by digit in German, forwards or backwards |
| Sekundenzerleger | Splits seconds into years, days, hours, minutes, seconds |
| Vielfache berechnen | Multiples 1-10 of a number |
| Taschenrechner | One term `a op b` with `+ - * /` |

Modernized from the nine legacy JARs in `../legacy/` (Java 1.x-8), reverse engineered with CFR.
The raw decompiler output of each JAR is in `../legacy/<jar>/decompiled/`.

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/kopfrechnen-2.0.jar                    # chooser
java -jar target/kopfrechnen-2.0.jar zehner_addieren    # start one app directly
```

Direct names (case-insensitive): `hunderter_addieren`, `zehner_addieren`, `zehner_multiplizieren`,
`zehner_subtrahieren`, `quersumme`, `zahlzerlegung`, `sekundenzerleger`, `vielfache`, `taschenrechner`.

The quizzes ask until you answer wrong; then they ask "Weiter? (j/n)". Cancel ends any app.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # unit tests of the logic (no display needed)
mvn package    # tests + target/kopfrechnen-2.0.jar (executable)
```

### Project layout

```
pom.xml
src/main/java/kopfrechnen/Apps.java        chooser, main(), dialog flow of every app
src/main/java/kopfrechnen/Arithmetic.java  UI-free logic (calculator, digit sum, spelling, ...)
src/main/java/kopfrechnen/Quiz.java        quiz types and random task generation
src/test/java/kopfrechnen/ArithmeticTest.java
```

### Origin

Each of the nine JARs contained the *whole* workspace (25 classes: all apps plus the
unrelated `herztest`, `sternenbaum`, `zinseszinsen`, `alleSchleifen`), only the `Main-Class`
differs. Two snapshots exist: `Hunderter`, `ZehnerAddi/Multi/Subtra`, `Quersummenrechner`,
`Zahlzerlegung`, `Sekundenzerleger` are older (separate `*Main` class names; the four quizzes
there show an intro dialog); `Taschenrechner` and `VielfacheBerechnen` are the newest and identical.
The newest snapshot was used as base, the intro dialog of the older quizzes was kept. The extra
leftover classes were not migrated (`herztest` is covered by `Herztest.jar`).

### Changes compared to the legacy JARs

- One JAR with chooser instead of nine; 25 classes with copy-pasted logic reduced to 3.
- Taschenrechner: the old code ran one dialog per operator character found in the input and
  crashed on negative numbers or bad input; now one `a op b` term is evaluated (negative first
  operand allowed). Bad input shows a warning instead of a stack trace.
- Quizzes: Cancel/invalid input no longer crashes; a wrong answer also shows the correct one;
  only an answer starting with `j` continues (before: anything but `j` ended, same).
- Vielfache: no leading comma in the list. Zahlzerlegung/Quersumme: non-digits are rejected
  (before: garbage or repeated words).
- Console `println` output of the old apps was dropped.
