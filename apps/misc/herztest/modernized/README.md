# Herztest

Three yes/no questions; every yes earns a ♥, the first no ends the quiz.

Modernized from the legacy `Herztest.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`). The legacy JAR is a snapshot of a whole school workspace;
only the class named in its manifest (`herztest.Test`) is migrated, the other classes are covered by
the Kopfrechnen project or were unused exercises.

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/herztest-2.0.jar
```

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test
mvn package    # target/herztest-2.0.jar (executable)
```

### Project layout

```
src/main/java/herztest/HerztestApp.java      dialogs + pure logic (static methods)
src/test/java/herztest/HerztestAppTest.java
```

### Changes compared to the legacy JAR

- Class `herztest.Test` -> `herztest.HerztestApp`; questions in a list, one loop instead of nested ifs.
- Answers `j`, `J`, `ja` count as yes; empty input or Cancel counts as no (legacy crashed).
- Texts as in the newest legacy snapshot (proper umlauts; the old Herzenswunsch snapshot had a mojibake heart).
