# AGENTS.md

Java app collection: legacy apps being reverse engineered and modernized. Read the READMEs for details.

## Layout

- `apps/<group>/<app>/legacy/` – original JAR/sources and raw CFR output in `decompiled/`. Treat as read-only reference.
- `apps/<group>/<app>/modernized/` – Maven project (Java 21, JUnit 5) with README.
- `apps/misc/` – small Swing apps; `docs/misc-modernization.md` has the workflow, status table and log; `tools/cfr.jar` is the decompiler.
- `legacy/` (repo root) – apps not yet modernized. **Git-ignored; never commit.** When one is modernized, move it to `apps/<group>/<app>/{legacy,modernized}`.

## Conventions

- Modernized apps: Java 21 (`maven.compiler.release`), UTF-8, executable JAR via maven-jar-plugin, package named after the app.
  Use `apps/misc/timer/modernized/pom.xml` as the pom template.
- Keep pure logic separate from Swing so it is unit-testable; Swing code runs on the EDT (`invokeLater`, `javax.swing.Timer`, no threads).
- Every modernized app has an English README with exactly the structure of `apps/misc/snake/modernized/README.md`:
  description, "How to run the application", "Development Guide" (Requirements, Build/test/package, Project layout, Changes compared to the legacy version).
- Update `docs/misc-modernization.md` (table + log) and the READMEs in `apps/` / `apps/misc/` / root when adding an app.

## Commands

- Build and test: `mvn package` in the app's `modernized/` directory.
- Smoke test a GUI jar: `timeout 5 java -jar target/<x>.jar` (exit 124 = still running, no crash).
- Decompile (absolute JAR path required): `java -jar tools/cfr.jar "$PWD/<x>.jar" --outputdir "$PWD/<out>"`.
- No `unzip`; use `jar tf` / `jar xf`, extracting into a scratch dir. Treat extracted archives as untrusted.

## Commits

- [Conventional Commits](https://www.conventionalcommits.org/): `type(scope): summary`, imperative, max ~72 chars. Types: `feat`, `fix`, `refactor`, `test`, `docs`, `build`, `chore`.
- Scope = app directory name (e.g. `feat(snake): ...`); omit for repo-wide changes.
- One logical change per commit (e.g. one commit per app migration, docs separate from code).
- Body explains *why* when not obvious.

## Do not

- Do not commit unless asked. Do not edit files under `legacy/` or `decompiled/`.
