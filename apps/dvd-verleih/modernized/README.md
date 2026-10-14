# DVD-Verleih

Rental management for a small video store: register customers, manage films, games and accessories (actors, directors,
genres, formats, FSK, prices), search the stock, lend and return items. Data is kept in an embedded SQLite database.

Modernized from the legacy Eclipse project `dvd-verleih` (see `../legacy/`), which needed a MySQL server.

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/dvd-verleih-2.0.jar
```

The database is created on first start in `~/.dvd-verleih/dvd-verleih.db`. Use another file with
`java -Ddvdverleih.db=/path/to/file.db -jar target/dvd-verleih-2.0.jar`.

To start with the sample data of the original project (customers, films, games, rentals), add
`-Ddvdverleih.beispieldaten=true`. It is only loaded while the database has no customers.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+ (the first build downloads `sqlite-jdbc`)

### Build, test, package

```bash
mvn test       # database layer and customer lend/return flow, no display needed
mvn package    # tests + target/dvd-verleih-2.0.jar (executable, SQLite driver bundled)
```

### Project layout

```
src/main/java/dvdverleih/db/DB_Verbindung.java   SQLite access, schema creation, search queries
src/main/java/dvdverleih/funktionen/             Controller (wiring, main), KKonto (customer account), Pruefung (input checks)
src/main/java/dvdverleih/datenhaltung/           Kunde, Objekt
src/main/java/dvdverleih/gui/                    Swing windows, dialogs and panels
src/main/java/dvdverleih/slider/                 two-thumb range slider
src/main/resources/cd.png                        window icon
src/main/resources/beispieldaten.sql             sample data (from the legacy dump)
docs/                                            original project documents (see below)
src/test/java/dvdverleih/                        DbTest, KKontoTest
```

### Changes compared to the legacy version

- MySQL (JDBC 5.1.22, bundled connector sources) replaced by embedded SQLite. The original MySQL dump is kept as
  `docs/legacy-mysql-dump.sql`. The 8 tables (`adresse`, `kunde`, `objekt`, `verleih`, `darsteller`, `regie`,
  `objekt_darsteller`, `film_regie`) are created on first start without the dump's constraints (`NOT NULL`, enums,
  unique ID card number); its sample data is available as `beispieldaten.sql`, loaded on request.
- `docs/` holds the original project documents: `Dokumentation.doc`, `Usecases_DVD-Verleih.odt`, the presentation, the
  GUI prototypes (`PrototypenGUISeite*.pdf`) and the project plan (`Projektablauf.gan`).
- All SQL uses prepared statements; table and column names are checked against a whitelist pattern. The legacy built
  queries by string concatenation (SQL injection).
- The search for films, games and accessories is one shared query. Films without actors or directors are now found
  (LEFT JOINs).
- Date checks are strict (`2001-02-29` is rejected); the day count of a loan uses `java.time`.
- The window icon is loaded from the classpath instead of a relative file path; the `RangeSliderDemo` and the
  `main` of `Pruefung` were dropped.
- Fixed string comparisons with `==` for the categories "Film" and "Spiel".
- Unchanged: German names and texts, the window flow and the reflection calls in `Controller`. Known legacy quirks kept:
  the input validation in the customer and item dialogs is very permissive, and the available count of an item is not
  updated on lend/return.
