# Volle Lotte

Dice game for 2-5 players with six dice and a card per round. Throw, secure scoring dice by clicking them, throw the
rest again and write the points once you have at least 350 in the round. A throw without a scoring die loses the
round. When all six dice are secured, the bonus of the drawn card (200-600) is added.

Modernized from the legacy Eclipse project `volle-lotte` (see `../legacy/`).

## How to run the application

Requirements: Java 21 or newer and a desktop environment.

```bash
java -jar target/volle-lotte-2.0.jar
```

Enter the player names in the dialog, press **Start**, then **Würfeln**. Click dice to secure them (white), click
again to give them back. **Datei > Neues Spiel** restarts, **Hilfe > Regelwerk** shows the rules.

## Development Guide

### Requirements

- JDK 21, Maven 3.9+

### Build, test, package

```bash
mvn test       # scoring, round and deck rules, no display needed
mvn package    # tests + target/volle-lotte-2.0.jar (executable)
```

### Project layout

```
src/main/java/vollelotte/Punkte.java          scoring of dice, bust check
src/main/java/vollelotte/Runde.java           one turn of one player
src/main/java/vollelotte/Spiel.java           players, scores, turn order
src/main/java/vollelotte/Kartenstapel.java    shuffled deck, reshuffles when empty
src/main/java/vollelotte/Karte.java           card types
src/main/java/vollelotte/VolleLotteApp.java   window and menu
src/main/java/vollelotte/Spielbrett.java      board panel
src/main/java/vollelotte/NeuesSpielDialog.java
src/main/resources/img/                       card images
src/test/java/vollelotte/                     tests for scoring, round, game and deck
```

### Changes compared to the legacy version

- 14 classes with static global state (`Hauptseite` with 1100 lines) -> 8. Rules are separated from the UI and
  covered by tests; the copy-pasted per-die code is gone. Unused classes (`Status`, `StatusModel`, `Rangliste`,
  `VolleLotte`) and the no-op Speichern/Laden menu items were dropped.
- The bust check treated three 4s like no score (`vierer < 4`); now any triple scores.
- Securing a non-scoring die (e.g. a lone 2) is no longer allowed, nor is throwing with nothing secured or with no
  free dice left.
- Drawing from an empty deck crashed; the deck is now reshuffled.
- Images are loaded from the JAR instead of an absolute Windows path.
- Unchanged: scoring table, 350 minimum, card bonus when all six dice are secured, the legacy deck (10 of each bonus
  card), rules text content. The other cards (Stern, Aussetzen, Doppelte Lotte, Plus-Minus 1000, Straße, Kleeblatt)
  are still drawn without effect and there is still no winning score, as in the unfinished legacy game.
- New: the dice of a lost round stay visible for 1.5 s before the next player is up.
