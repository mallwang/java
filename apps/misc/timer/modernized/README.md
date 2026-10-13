# Timer

A small Swing countdown timer. Enter a number of minutes, press **Start**, and the
remaining time is shown as `hh : mm : ss`. A dialog appears when time is up.

Modernized from the legacy `Timer.jar` (see `../legacy/`) (v1.0, Java 7), which was reverse engineered
from bytecode with CFR. The raw decompiler output is kept in `../legacy/decompiled/`.

## How to run the application

Requirements: Java 21 or newer (a desktop environment is needed, it is a GUI app).

```bash
java -jar target/timer-1.1.jar
```

If the JAR does not exist yet, build it first (see below).

Usage:

1. Type a whole number of minutes (at least 1) into the text field.
2. Click **Start**. The field shows the countdown.
3. Click **Stop** to abort and reset, or wait for the "Time over!" dialog.

## Development Guide

### Requirements

- JDK 21
- Maven 3.9+

### Build, test, package

```bash
mvn test       # run unit tests
mvn package    # run tests and build target/timer-1.1.jar (executable)
```

### Run from source

```bash
mvn -q package && java -jar target/timer-1.1.jar
```

### Project layout

```
pom.xml                                   Maven build (Java 21, executable JAR)
src/main/java/timer/TimerApp.java         GUI and countdown logic
src/test/java/timer/TimerAppTest.java     JUnit 5 test for the duration formatter
```

### Design notes

- UI updates run on the Swing event dispatch thread; the countdown uses `javax.swing.Timer`.
- `TimerApp.formatDuration` is package-private so it can be tested without a display.
- The GUI keeps the original absolute layout (`null` layout manager) and texts of v1.0.

### Changes compared to v1.0

- Single class instead of two classes with static Swing fields.
- Invalid input shows a warning instead of throwing in the timer thread; values below 1 are ignored.
- Start/Stop state is taken from the timer instead of comparing button text with `==`.
- Closing the window exits the application.
