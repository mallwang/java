# Notendurchschnittsrechner

Type your grades (1-6) as one digit string, e.g. `233`, and get the average. `e` or Cancel quits.

Modernized from the legacy `Notendurchschnittsrechner.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`). The legacy JAR is a snapshot of a whole school workspace;
only the class named in its manifest (`notendurchschnitt.NdMain`) is migrated, the other classes are covered by
the Kopfrechnen project or were unused exercises.

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/noten-2.0.jar
```

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test
mvn package    # target/noten-2.0.jar (executable)
```

### Project layout

```
src/main/java/noten/NotenApp.java      dialogs + pure logic (static methods)
src/test/java/noten/NotenAppTest.java
```

### Changes compared to the legacy JAR

- Logic extracted to `NotenApp.average`; invalid digits / empty input give a message instead of a crash or NaN.
- Cancel no longer throws a NullPointerException; `e` or Cancel ends the loop ("Ora et labora!").
- Any non-digit character is rejected (legacy only checked 0, 7, 8, 9 and `e`).
- Result is shown with two decimals (German format) instead of a raw float.
- Greeting "Hallo Manu!" dropped from the prompt.
