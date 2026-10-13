# KDET (Kalibrierungsdaten-Export-Tool)

Converts calibration data to CSV. Pick a ZIP with XML files, a ZIP with XSLT stylesheets and an output folder.
`main.xslt` splits the XML files by section; every other `<section>.xslt` turns its section into `<section>.csv`
(for example `accounts`, `companies`, `ratings`, `additionals`, `defaults`, `externalRatings`).

Modernized from the legacy Eclipse project (see `../legacy/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/kdet-2.0.jar
```

Choose the XML archive, the XSLT archive and the output folder, then click "Verarbeiten". The timings are shown in a dialog
and written to `Kalibrierungtool.log` in the working directory. The stylesheets for the 7 known sections are in
`../legacy/data/`; zip them (flat) to get an XSLT archive.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+ (downloads Saxon-HE from Maven Central)

### Build, test, package

```bash
mvn test       # runs the real stylesheets against a small XML fixture, no display needed
mvn package    # tests + target/kdet-2.0.jar (executable, Saxon bundled)
```

### Project layout

```
src/main/java/kdet/KdetTransformer.java   unzip, split by section, XSLT to CSV (no Swing)
src/main/java/kdet/KdetApp.java           window and dialogs
src/test/java/kdet/KdetTransformerTest.java
src/test/resources/xslt/                  copy of the legacy stylesheets, used as test input
```

### Changes compared to the legacy version

- 10 classes -> 2. Logic and UI are separated; the five listeners, `UnZip`, `Log` and `ErrorWindow` are gone.
- The stylesheets are XSLT 2.0 (`for-each-group`, `for ... return`), so the JDK's built-in XSLT 1.0 engine cannot run them.
  The Saxon 8 jars in `JAR/` were replaced by the Maven dependency Saxon-HE 12.5 (found through the JAXP `TransformerFactory`).
- Temporary files go to a temp directory that is deleted afterwards, not into the output folder. Before, `deleteFiles()` removed
  every `.xml` / `.xslt` in the output folder, including the user's own files.
- Paths are built with `Path`; the legacy code used `"\\main.xslt"` and so only worked on Windows.
- ZIP entries that would leave the target folder (zip slip) are rejected.
- Section stylesheets are matched to their section by name; before, the section list and the stylesheet list were indexed in parallel
  and could get out of step.
- The hard-coded network share in the XML file chooser is gone; the file chooser opens in the last chosen folder.
- Layout uses `GridBagLayout` instead of absolute positions. Texts (German) and the output encoding set by the stylesheets are unchanged.
