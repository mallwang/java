# ANNO 1404 Produktionsrechner

Calculator for the ANNO 1404 city builder. Enter the number of inhabitants per group (Bettler, Bauern, Bürger,
Patrizier, Adelige, Nomaden, Gesandte) and see for every good how many production buildings you need and how much is
consumed per minute, shown as `buildings (consumption)`. Click a good to see its production chain.

Modernized from the legacy JAR `Anno1404 Produktionsrechner v2.jar` (see `../legacy/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/anno1404-produktionsrechner-2.0.jar
```

Type the inhabitants into the fields (digits only, up to 5). Goods that are not yet available at that population show
`0 (0)`. **Felder zurücksetzen** clears all fields.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # consumption, rounding, unlock rules, input parsing, image names; no display needed
mvn package    # tests + target/anno1404-produktionsrechner-2.0.jar (executable)
```

### Project layout

```
src/main/java/annorechner/Bevoelkerung.java   population groups
src/main/java/annorechner/Ware.java           one good and its calculation
src/main/java/annorechner/Waren.java          table of all goods, input parsing
src/main/java/annorechner/AnnoApp.java        window
src/main/resources/img/                       icons and production chain images
src/test/java/annorechner/RechnerTest.java
```

### Changes compared to the legacy version

- Two classes (`Hauptseite` with 1400 lines, copy-pasted block per good) -> one data table of goods plus one generic
  calculation, covered by tests. Fixed `null` layout with absolute bounds replaced by layout managers.
- Text fields are validated on every change (not only on key release), so pasting works too.
- Images are loaded from the JAR; the Windows `:sec.endpointdlp` files were not copied.
- Unchanged: consumption rates, production per building, per-group truncation, unlock thresholds. Kerzenleuchter is
  unlocked only by Adelige (>= 3000), not by Patrizier, as in the legacy version.
