# Arbeitszeitrechner

Shows when to leave work and how much time is left, based on your arrival time. Optionally enter a planned
leave time to see the remaining time to it. The remaining time is red while work is left and green afterwards.
The weekly working time is spread over 5 days; the window title and the tray tooltip show the remaining time.

Modernized from the legacy `Arbeitszeitrechner v. 1.5.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/arbeitszeit-2.0.jar
```

Select the arrival time ("kommen"). Menu *Einstellungen*: refresh interval, weekly hours (20-60) and
"In Tray verstecken". Settings are stored in `~/.Arbeitszeitrechner/config.properties`.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # work-time rules and settings, no display needed
mvn package    # tests + target/arbeitszeit-2.0.jar (executable)
```

### Project layout

```
src/main/java/arbeitszeit/WorkTime.java         daily minutes, break, leave time, remaining time (no Swing)
src/main/java/arbeitszeit/Settings.java         refresh interval and weekly hours, .properties persistence
src/main/java/arbeitszeit/ArbeitszeitApp.java   window, tray icon, update timer
src/main/resources/                             window and tray icons
src/test/java/arbeitszeit/WorkTimeTest.java
```

### Changes compared to v1.5

- Break rule fixed: 30 min from 6 h of daily work, **45 min from 9 h**. The legacy 45 min branch was unreachable.
- `"--"` was compared by reference (`!=`); now compared by value.
- The update loop was a `java.util.Timer` calling Swing off the EDT; now a `javax.swing.Timer`. Deprecated
  `Date` constructors are replaced by `java.time`.
- Static controller state replaced by `WorkTime` (pure) and `Settings` (record).
- Config moved from `config.xml` (StAX) to `config.properties`; a missing or corrupt file falls back to the
  defaults (1 s, 40 h) instead of crashing. An existing `config.xml` is not migrated.
- Layout managers instead of absolute positions.
