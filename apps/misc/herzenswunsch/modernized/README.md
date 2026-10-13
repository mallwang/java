# Herzenswunsch

Enter a name and how many ♥ you want (1-20); you get them handed out.

Modernized from the legacy `Herzenswunsch.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`). The legacy JAR is a snapshot of a whole school workspace;
only the class named in its manifest (`sternenbaum.HerzMain`) is migrated, the other classes are covered by
the Kopfrechnen project or were unused exercises.

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/herzenswunsch-2.0.jar
```

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test
mvn package    # target/herzenswunsch-2.0.jar (executable)
```

### Project layout

```
src/main/java/herzenswunsch/HerzenswunschApp.java      dialogs + pure logic (static methods)
src/test/java/herzenswunsch/HerzenswunschAppTest.java
```

### Changes compared to the legacy JAR

- Package `sternenbaum` -> `herzenswunsch`; logic in `HerzenswunschApp.hearts`.
- Non-numbers and 0 or negative counts give a message instead of a crash / empty reward; more than 20 still says too many.
- Cancel ends the app quietly.
