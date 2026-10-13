# Java projects

Collection of Java applications, partly modernized from legacy code.

## Prerequisites

- JDK 21 or newer
- Maven 3.9+
- A desktop environment for the Swing apps (on WSL2: WSLg)

Each modernized app is a Maven project:

```bash
cd apps/misc/<app>/modernized
mvn package                          # tests + target/<app>-<version>.jar
java -jar target/<app>-<version>.jar
```

The exact JAR name and controls are in each app's README.

## Repository layout

| Path | Content |
|------|---------|
| [apps/](apps/README.md) | All applications, one directory each with `legacy/` and `modernized/` |
| [apps/misc/](apps/misc/README.md) | Small Swing apps (listed below) |
| [apps/games/](apps/games/README.md) | Games |
| `legacy/` | Old applications not yet modernized (git-ignored, local only) |
| [docs/](docs/legacy-modernization.md) | Migration plans and status logs ([misc](docs/misc-modernization.md), [legacy](docs/legacy-modernization.md)) |
| `tools/` | Decompiler (CFR) |
| [AGENTS.md](AGENTS.md) | Context for coding agents |

## Available applications

See [apps/misc](apps/misc/README.md): [timer](apps/misc/timer/modernized/README.md),
[kopfrechnen](apps/misc/kopfrechnen/modernized/README.md), [snake](apps/misc/snake/modernized/README.md),
[bingo](apps/misc/bingo/modernized/README.md), [noten](apps/misc/noten/modernized/README.md),
[herzenswunsch](apps/misc/herzenswunsch/modernized/README.md), [herztest](apps/misc/herztest/modernized/README.md),
[arbeitszeit](apps/misc/arbeitszeit/modernized/README.md), [gleitzeit](apps/misc/gleitzeit/modernized/README.md),
[1337_converter](apps/misc/1337_converter/modernized/README.md),
[verbrauchsrechner](apps/misc/verbrauchsrechner/modernized/README.md),
[KDET](apps/misc/KDET/modernized/README.md).

Games: [spacewar](apps/games/spacewar/modernized/README.md), [volle-lotte](apps/games/volle-lotte/modernized/README.md), [anno1404-produktionsrechner](apps/games/anno1404-produktionsrechner/modernized/README.md) (see [apps/games](apps/games/README.md)).
