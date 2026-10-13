# Verbrauchsrechner

Consumption calculator for the game Anno 1404. Enter the number of farmers (Bauern), artisans (Handwerker),
merchants (Kaufleute) and nobles (Adlige); it prints, for each of 15 goods, how many production buildings are
needed and their average load, e.g. `FISCH  9x (88,9%)`.

Modernized from the unfinished legacy Eclipse project `verbrauchsrechner` (see `../legacy/`).

## How to run the application

Requirements: Java 21 or newer.

```bash
java -jar target/verbrauchsrechner-2.0.jar                    # example population 1568 2325 1258 2324
java -jar target/verbrauchsrechner-2.0.jar 1000 500 200 100   # bauern handwerker kaufleute adlige
```

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # calculation, no display needed
mvn package    # tests + target/verbrauchsrechner-2.0.jar (executable)
```

### Project layout

```
src/main/java/verbrauchsrechner/Verbrauchsrechner.java   good table, calculation, console main
src/test/java/verbrauchsrechner/VerbrauchsrechnerTest.java
```

### Changes compared to the legacy version

- The legacy code was a work in progress: ~200 mutable fields, only fish was calculated (the other 14 methods
  returned 0) and `main` printed a debug value. The constants are now an enum `Good` (production time plus
  consumption per resident type) and one generic formula covers all 15 goods.
- Rounding: legacy truncated and printed `+1`; now the number of buildings is rounded up and the average
  load per building is reported (the format comes from the scratch class `Test.java`, which was dropped).
  For the example population fish gives 9 buildings, as the legacy output did.
- Dropped as unused: the tax values (`steu_*`) and the production times of intermediate goods (wheat, flour, ...).
- `main` takes the population as arguments instead of hard-coded numbers.
