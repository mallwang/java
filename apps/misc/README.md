# Small applications

Small historical Swing apps that existed only as JARs. Each was decompiled with CFR and rebuilt as a Java 21
Maven project. Every app folder has `legacy/` (original JAR, sources if any, raw decompiler output) and
`modernized/` (project with README: how to run, development guide, changes).

| App | Description | Modernized version |
|-----|-------------|--------------------|
| [timer](timer/modernized/README.md) | Countdown timer | 1.1 |
| [kopfrechnen](kopfrechnen/modernized/README.md) | Nine mental-arithmetic quiz apps in one JAR | see README |
| [snake](snake/modernized/README.md) | Classic snake game | 1.1 |
| [bingo](bingo/modernized/README.md) | Click game ("Grünes Fenster") | see README |
| [noten](noten/modernized/README.md) | Grade average calculator | 2.0 |
| [herzenswunsch](herzenswunsch/modernized/README.md) | Prints hearts for a chosen number | 2.0 |
| [herztest](herztest/modernized/README.md) | Yes/no love quiz | 2.0 |
| [arbeitszeit](arbeitszeit/modernized/README.md) | Working time / leave time calculator | 2.0 |
| [gleitzeit](gleitzeit/modernized/README.md) | Flextime calculator (GZR) | 3.6 |
| [1337_converter](1337_converter/modernized/README.md) | Text to leetspeak converter | 2.0 |
| [verbrauchsrechner](verbrauchsrechner/modernized/README.md) | Anno 1404 consumption calculator (console) | 2.0 |

Migration plans: [misc-modernization.md](../../docs/misc-modernization.md), [legacy-modernization.md](../../docs/legacy-modernization.md).

Back to [all applications](../README.md).
