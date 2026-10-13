# Plan: Reverse engineer and modernize the legacy Java apps

## Task

The JARs in this folder are small historical Java (Swing) applications that exist
only as compiled artifacts. Goal:

1. Decompile each JAR to Java source.
2. Place each app's source in its own sub-directory (`<app>/legacy/decompiled/` during
   step 1, later renamed/migrated to a clean project).
3. Modernize each app (Maven project, Java 21, modern idioms, small tests).

Source files from other projects (e.g. `../timer/src`) are used as ground truth to
validate decompiler quality where they exist.

## Findings (feasibility)

- No obfuscation; class/package names are readable. All JARs contain `LocalVariableTable`
  and `SourceFile`, so variable names are recovered.
- Bytecode versions: Java 1.1 (major 45) up to Java 8 (52).
- Lost for good: comments, JavaDoc, original formatting, build/IDE files.
- Decompiled Swing code inlines constants (`JTextField.CENTER` -> `0`) and
  turns instance calls on static members into static calls. Behavior is unchanged.
- `1337_Converter_v1.2.jar` is an Eclipse "runnable JAR" (`JarRsrcLoader`); the real
  classes/libs are nested and must be unpacked first.
- The seven "Kopfrechnen" apps (Hunderter, ZehnerAddi/Multi/Subtra, Quersummenrechner,
  Zahlzerlegung, Sekundenzerleger, VielfacheBerechnen, Taschenrechner) share ~25 classes
  and nearly equal JAR sizes: likely one template -> candidate for a shared module.

## Tooling

- JDK: OpenJDK 21 (`java`, `javac`), Maven 3.9.
- Decompiler: CFR 0.152 in `tools/cfr.jar` (from Maven Central).
- **Gotcha:** CFR crashes (NPE) on relative JAR paths without a parent dir. Pass absolute paths:

```bash
java -jar tools/cfr.jar "$PWD/Timer.jar" --outputdir "$PWD/timer/legacy/decompiled/src/main/java"
```

## Folder layout (per app)

```
<app>/
  legacy/        original material: the JAR, original sources if they exist (source/),
                 raw decompiler output (decompiled/)
  modernized/    the modernized Maven project incl. README.md (README only here)
```

Base for modernization: original sources if they exist and match the JAR (verify with
`javap -c` on the classes), otherwise the CFR output. Original sources give comments and
formatting; decompiled output is the cross-check for what actually shipped.

## Workflow per app

1. Decompile into `<app>/legacy/decompiled/src/main/java`.
2. Compile with `javac` (sanity check), run, compare with original JAR behavior.
3. Move resources from the JAR (images, etc.) to `src/main/resources`.
4. Modernize: `pom.xml` (Java 21, executable JAR), `var`/lambdas/diamond/try-with-resources,
   `SwingUtilities.invokeLater`, rename obscure identifiers, drop dead code, add a smoke test.
5. Write `README.md` (English) in the modernized app folder with a "How to run the application"
   guide and a Development Guide (requirements, build, test, project layout, notes on origin/changes).
6. Verify the new JAR runs; record status below.

## Order

1. Timer (pilot, has original source for comparison)
2. Kopfrechnen family (shared module)
3. Snake, Bingo
4. Notendurchschnittsrechner, Herzenswunsch, Herztest
5. Arbeitszeitrechner, Gleitzeit (resources + `config_gzr_3_5.txt`)
6. 1337_Converter

## Status

| App | Decompiled | Compiles | Modernized | Notes |
|-----|:---:|:---:|:---:|-------|
| Timer | yes | yes | yes | Modernized in `timer/modernized/`; legacy material in `timer/legacy/`. Diff vs `../timer/src`: only comments, formatting, inlined constants. Original sources are ISO-8859-1 (umlauts garbled when read as UTF-8). |
| Kopfrechnen family (9 JARs) | yes | yes | yes | One project `kopfrechnen/modernized/` (chooser, 3 classes); each JAR is the whole workspace with another Main-Class, see its README. Behavior of the GUI not visually verified. |
| Snake | yes | yes | yes | `snake/modernized/`; logic split from UI, EDT timer instead of thread. Starts without errors, gameplay not played through. |
| Bingo | yes | yes | yes | `bingo/modernized/`; single class. Starts without errors, not clicked through. |
| Notendurchschnittsrechner | yes | yes | yes | |
| Herzenswunsch | yes | yes | yes | |
| Herztest | yes | yes | yes | |
| Arbeitszeitrechner | yes | yes | yes | v2.0, `arbeitszeit/` |
| Gleitzeit | yes | yes | yes | v3.6, `gleitzeit/`; mini mode and help dialog dropped |
| 1337_Converter | yes | yes | yes | v2.0, `1337_converter/` |

## Log

- 2026-10-12: Feasibility check of all 18 JARs. Installed/verified JDK 21, fetched CFR.
  Timer decompiled to `timer-decompiled/` and compiled cleanly. Next: modernize Timer
  (pom.xml, Java 21, run/verify).
- 2026-10-12: Timer modernized in `timer-app/`: Maven, Java 21, single `TimerApp` class,
  `javax.swing.Timer` (EDT-safe, replaces `java.util.Timer` + cross-thread Swing calls),
  `ActionListener` instead of `MouseAdapter`, input validation, `==` string compare removed,
  JUnit 5 test for the duration formatter. `mvn package` builds `target/timer-1.1.jar`;
  JAR starts without errors (GUI not visually verified). Behavior changes vs v1.0: invalid or
  <1 minute input no longer crashes the timer thread; window closes the app.
- 2026-10-12: Workflow extended with a README.md step per modernized app. Added `timer-app/README.md`.
- 2026-10-12: Reviewed original sources in `../timer` (Eclipse project, Java 1.7, ISO-8859-1).
  `Oberflaeche.class` and `Start.class` are byte-identical to the JAR; `Start$1/$2.class` differ
  (source may be a slightly different build). Original source confirms the logic of `timer-app`;
  only extra information: German comments, and `setDefaultCloseOperation(EXIT_ON_CLOSE)` was
  commented out in v1.0 (so closing the window left the JVM running because of the non-daemon
  `java.util.Timer` thread). `timer-app` exits on close deliberately.
- 2026-10-12: Original `../timer` sources verified against the JAR (`javap -c` identical for all 4
  classes) -> usable as modernization base. Restructured Timer to `timer/legacy/{Timer.jar,source,decompiled}`
  and `timer/modernized`. Problem: WSL shows a ghost entry `timer/modernized` (stat fails, mkdir says
  "Already exists") although Windows has no such dir; the project currently lives in `timer/mtmp`.
  Resolved 2026-10-13: the project now lives in `timer/modernized` (`mtmp` is gone).
  Root `Timer.jar` is still in place (copied, not moved).
- 2026-10-13: Kopfrechnen: all 9 JARs (counted as 9, not 8) decompiled to `kopfrechnen/legacy/<jar>/decompiled/`.
  Finding: every JAR holds the whole workspace (25 classes) with a different Main-Class, in two
  snapshots (Taschenrechner == VielfacheBerechnen = newest). Modernized into one Maven project
  `kopfrechnen/modernized/` (Java 21, chooser, JUnit test); `mvn package` passes, GUI not clicked through.
  Root JARs moved into `kopfrechnen/legacy/`. Leftover classes (herztest, sternenbaum, zinseszinsen,
  alleSchleifen) not migrated.
- 2026-10-13: Snake and Bingo decompiled (`<app>/legacy/{<jar>,decompiled}`) and modernized (`snake/modernized`,
  `bingo/modernized`), `mvn package` passes, both JARs start under WSLg without errors. Snake: fixed thread-unsafe
  Swing access and the quick-double-key self-collision. Root JARs moved into the `legacy/` folders.
- 2026-10-13: Notendurchschnittsrechner, Herzenswunsch and Herztest decompiled and modernized (`noten/`, `herzenswunsch/`,
  `herztest/`). Again workspace snapshots; only the manifest Main-Class of each was migrated (NdMain, HerzMain, herztest.Test),
  the rest duplicates Kopfrechnen. `mvn package` passes, all three JARs start under WSLg; GUI not clicked through.
  Root JARs moved into the `legacy/` folders.
- 2026-10-13: Arbeitszeitrechner (`arbeitszeit/`, v2.0) and Gleitzeit (`gleitzeit/`, v3.6) decompiled and modernized into pure
  logic + Swing UI with tests. Fixed the unreachable 45 min break and string-identity comparison; replaced the busy loop and
  off-EDT timer with `javax.swing.Timer`. Gleitzeit drops mini mode/help dialog (see its README). `config_gzr_3_5.txt` moved to
  `gleitzeit/legacy/`. `mvn package` passes, both JARs start under WSLg; GUI not clicked through. Root JARs moved to `legacy/`.
- 2026-10-13: 1337_Converter decompiled and modernized (`1337_converter/`, v2.0). Not a nested JAR after all: plain classes plus the
  Eclipse loader. Conversion split into `Leet` (tested), Swing UI on a `javax.swing.Timer`. `mvn package` passes, JAR starts under
  WSLg; GUI not clicked through. Root JAR moved to `legacy/`. All apps in the table are now done.
