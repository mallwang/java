# Plan: Modernize the remaining apps in `legacy/`

Follow-up to [misc-modernization.md](misc-modernization.md). The root `legacy/` folder (git-ignored)
holds the apps that were not yet modernized. Unlike the misc apps, these are **Eclipse/Gradle source
projects, not bare JARs** (one exception), so no decompiling is needed for most of them.

## Feasibility

Yes, for 6 of 9 entries. `my-vocabularies` (Android), `anno-online` (data) and `tankaufstellung` (empty) are not migrated.

| Entry | Type | Java LOC | Verdict |
|-------|------|---------:|---------|
| `tankaufstellung` | Eclipse, Swing | 13 | Empty `main` stub. **Skip** (nothing to modernize). |
| `verbrauchsrechner` | Eclipse, console | 334 | Easy. Pure logic + a `Test` class. |
| `spacewar` | Eclipse, Swing game (5 classes, PNGs, `highscore.txt`) | 574 | Easy. |
| `KDET` | Eclipse, Swing + XSLT, Saxon 8 jars | 727 | Medium. Saxon 8 jars -> Maven Saxon-HE (stylesheets are XSLT 2.0, the JDK engine is 1.0 only). |
| `volle-lotte` | Eclipse, Swing board game (14 classes, ~70 images) | 2,469 | Medium. Logic (`funktionen`) and UI (`gui`) are already split. |
| `anno1404-produktionsrechner` | **JAR only** + images (Swing) | ? | Medium. Needs CFR decompile (same as misc). |
| `dvd-verleih` | Eclipse, Swing + MySQL (JDBC 5.1.22), 3 packages, `slider` | ~8,600 (own code) | Large. No schema available; migrate to SQLite (see Decisions). |
| `my-vocabularies` | **Android** (Gradle, 2 modules, fragments, SQLite) | ~2,900 | Not a desktop app. **Stays legacy** (see Decisions). |
| `anno-online` | **No code**: 2 `.xlsx` files, ~520 images | 0 | Not an app. **Stays in `legacy/`** as reference data. |

Notes found while looking:
- Sources are ISO-8859-1 (umlauts), German identifiers (`Hauptseite`, `Karte`, ...). Convert to UTF-8
  when copying; keep German domain names where they carry meaning, rename obscure ones.
- `*:sec.endpointdlp` files are Windows/WSL metadata noise. Do not copy them.
- `dvd-verleih/lib/mysql-connector-java-5.1.22` contains the full connector source (150k lines). Not
  app code; use the Maven dependency `com.mysql:mysql-connector-j` instead.
- Existing `bin/` class files can be used to verify sources match (`javap -c`), as done for Timer.

## Decisions

1. **my-vocabularies (Android)**: stays in `legacy/` (root, git-ignored), not modernized. The same applies
   to any other Android project found later: Java 21 + Maven + JUnit does not fit them.
2. **dvd-verleih**: replace MySQL with embedded **SQLite** (`org.xerial:sqlite-jdbc`). Derive the schema
   from the SQL found in the code and create it on first start.
3. **Games**: new group `apps/games/` (`spacewar`, `volle-lotte`, `anno1404-produktionsrechner`).
   `anno-online` (data only) stays in root `legacy/` as reference, like `my-vocabularies`.

## Target layout

Same as before (see [misc-modernization.md](misc-modernization.md)): `apps/<group>/<app>/{legacy,modernized}`.
Proposed groups:
- `apps/misc/`: `verbrauchsrechner` (small tool), `KDET` (utility)
- `apps/games/`: `spacewar`, `volle-lotte`, `anno1404-produktionsrechner` (new group)
- `apps/dvd-verleih/` (own group, large)

Legacy material (sources, `bin/`, original JAR, `.project`/`.classpath`) is moved with `git mv`-style
move into `<app>/legacy/`, never edited. New group READMEs/indexes follow `apps/misc/README.md`.

## Workflow per app

Same as the misc workflow, adapted:
1. Move the app from `legacy/` to `apps/<group>/<app>/legacy/` (source apps: keep `src/`, `bin/`, `.project`).
   JAR-only apps: decompile with CFR into `legacy/decompiled/`.
2. Verify sources against `bin/*.class` (`javap -c`) where `bin/` exists; compile the legacy code once.
3. Create `modernized/` from `apps/misc/timer/modernized/pom.xml` (Java 21, UTF-8, executable JAR, package = app name).
4. Modernize: pure logic separate from Swing, EDT-only Swing (`javax.swing.Timer`, no threads), `var`/records/
   switch expressions/try-with-resources where they help, resources to `src/main/resources`, JUnit 5 tests for the logic.
5. English README with the exact structure of `apps/misc/snake/modernized/README.md`.
6. `mvn package`, smoke test `timeout 5 java -jar target/<x>.jar` (exit 124 = OK).
7. Update docs table + log, indexes (`apps/README.md`, group README, root README).
8. One commit per app (`feat(<app>): ...`), docs in a separate commit.

## Order (smallest and most self-contained first)

1. `verbrauchsrechner` (pilot; console logic, tests are easy)
2. `spacewar` (resources + highscore file handling; replace relative-path file I/O)
3. `KDET` (XSLT pipeline; check Saxon vs JDK transformer on the 7 stylesheets, no sample input in repo, so
   write a tiny fixture)
4. `volle-lotte` (largest Swing game that is still manageable)
5. `anno1404-produktionsrechner` (decompile first)
6. `dvd-verleih` (with SQLite, see Decisions)
7. `my-vocabularies`, `anno-online` stay in `legacy/`; `tankaufstellung` skipped.

## Status

| App | Moved | Compiles (legacy) | Modernized | Notes |
|-----|:---:|:---:|:---:|-------|
| verbrauchsrechner | yes | yes | yes | `apps/misc/verbrauchsrechner/`, v2.0 |
| spacewar | yes | yes | yes | `apps/games/spacewar/`, v2.0 |
| KDET | yes | untested | yes | `apps/misc/KDET/`, v2.0, Saxon-HE 12.5 |
| volle-lotte | no | no | no | |
| anno1404-produktionsrechner | no | no | no | JAR only |
| dvd-verleih | no | no | no | MySQL -> SQLite |
| my-vocabularies | no | n/a | n/a | Android, stays legacy |
| anno-online | no | n/a | n/a | data only, stays legacy |
| tankaufstellung | no | n/a | n/a | empty stub, skipped |

## Log

- 2026-10-13: Feasibility check of the 9 entries in `legacy/`; this plan written. Nothing moved or modified.
- 2026-10-13: Decisions recorded (Android stays legacy, SQLite for dvd-verleih, `apps/games/`). verbrauchsrechner moved to
  `apps/misc/verbrauchsrechner/{legacy,modernized}` and modernized: unfinished legacy (only fish calculated) became one generic
  calculation over 15 goods, console main with arguments, JUnit tests. `mvn package` passes, JAR prints the table. Legacy output for
  the example population (9 fishers) reproduced.
- 2026-10-13: spacewar moved to `apps/games/spacewar/{legacy,modernized}` and modernized (new group `apps/games/`). Logic split from
  Swing, 6 JUnit tests. Fixed: highscore rewritten on every repaint, case-sensitive image names (`.PNG` vs `.png`), missile removal
  while iterating, craft leaving the board. Highscore file format kept. `mvn package` passes, JAR starts under WSLg; gameplay not played through.
- 2026-10-13: KDET moved to `apps/misc/KDET/{legacy,modernized}` and modernized. The 7 stylesheets use XSLT 2.0 (`for-each-group`, `for ... return`), so the JDK engine is not an option; Saxon 8 jars replaced by Saxon-HE 12.5, shaded into the executable JAR. Logic split from Swing (`KdetTransformer`), 3 JUnit tests run the real stylesheets on a small fixture. Fixed: temp files written to / deleted from the output folder, Windows-only `\\` paths, parallel-list indexing of sections, zip slip. `mvn package` passes, JAR starts under WSLg; GUI not clicked through, no real calibration data available.
