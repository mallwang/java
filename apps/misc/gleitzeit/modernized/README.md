# GZR (Gleitzeitrechner)

Flextime calculator: saldo, leave time, break times, progress bars and pay estimate from your arrival time.
The window refreshes every second and the title shows saldo and progress.

Rules: a start before 06:30 counts from 06:30; target 7:48 net (leave = arrival + 8:18); pause 1 at
arrival + 6:00 (30 min), pause 2 at arrival + 9:30 (15 min, only if it ends by 19:30); maximum work time =
arrival + 10:45, capped at 19:30; pay = net salary / 168.48 h x time worked.

Modernized from the legacy `Gleitzeit3.5.jar` (see `../legacy/`), reverse engineered with CFR
(raw output in `../legacy/decompiled/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/gleitzeit-3.6.jar
```

Select arrival time, meal time and net salary (400-10000 EUR in 10 EUR steps). Your choices are saved to
`~/.gleitzeit/config_gzr_3_5.txt` in the legacy format `<netto>x<kommenH>x<kommenM>x<essenH>x<essenM>`
(example: `../legacy/config_gzr_3_5.txt`).

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # rules and config, no display needed
mvn package    # tests + target/gleitzeit-3.6.jar (executable)
```

### Project layout

```
src/main/java/gleitzeit/GleitzeitModel.java   calculation (no Swing)
src/main/java/gleitzeit/Config.java           config record, 5-field file format
src/main/java/gleitzeit/GleitzeitApp.java     window and update timer
src/main/resources/img/                       icons
src/test/java/gleitzeit/GleitzeitModelTest.java
```

### Changes compared to v3.5

- The endless `while(true)` loop with `Thread.sleep` in the constructor is a `javax.swing.Timer` on the EDT;
  deprecated `Date` mutation replaced by `java.time`.
- The config is stored in the home directory instead of the working directory and parsed safely (invalid file
  falls back to defaults); it is no longer rewritten byte-wise with console output.
- Dropped: mini mode, warnings on/off radios, help dialog, look-ahead warnings for running pauses, and the
  check icons next to finished progress bars. Text warnings for the rules above remain.
- Layout managers instead of absolute positions.
