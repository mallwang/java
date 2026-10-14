-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 08. Nov 2012 um 22:52
-- Server Version: 5.5.16
-- PHP-Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `gruppe1`
--
CREATE DATABASE `gruppe1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `gruppe1`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `strasse` varchar(40) NOT NULL,
  `hausnummer` varchar(6) NOT NULL,
  `wohnort` varchar(40) NOT NULL,
  `plz` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Daten für Tabelle `adresse`
--

INSERT INTO `adresse` (`id`, `strasse`, `hausnummer`, `wohnort`, `plz`) VALUES
(1, 'Hütberg', '3', 'Wolfsbach', 92266),
(2, 'Schwabstraße', '167', 'Stuttgart', 70193),
(3, 'Königstraße', '46', 'Stuttgart', 70173),
(4, 'hauptstrasse', '23', 'stuttgart', 70123),
(5, 'Bismarckstraße', '175', 'Hannover', 30173),
(6, 'Vogelsangstraße', '78', 'Stuttgart', 70197),
(7, 'Porschestraße', '1', 'Stuttgart', 70435),
(8, 'Mercedesstraße', '5', 'Stuttgart', 70372),
(9, 'Wilhelm-Epstein-Straße', '10', 'Frankfurt', 60431),
(10, 'Peter-Böhler-Straße', '19', 'Frankfurt', 60431),
(11, 'Georgstraße', '96', 'Hannover', 30159),
(12, 'Robert-Enke-Straße', '1', 'Hannover', 30169),
(13, 'Auf dem Bruche', '3', 'Springe', 31832),
(14, 'ulica', '42', 'prag', 23456),
(33, 'Pfarrhof', '10', 'Arnstadt', 99310);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `darsteller`
--

CREATE TABLE IF NOT EXISTS `darsteller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_vorname` varchar(30) NOT NULL,
  `d_nachname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=109 ;

--
-- Daten für Tabelle `darsteller`
--

INSERT INTO `darsteller` (`id`, `d_vorname`, `d_nachname`) VALUES
(1, 'Robert', 'De Niro'),
(2, 'Ben', 'Stiller'),
(3, 'Blythe', 'Danner'),
(4, 'Teri', 'Polo'),
(5, 'James', 'Rebhorn'),
(6, 'Francois', 'Cluzet'),
(7, 'Omar', 'Sy'),
(8, 'Anne ', 'Le Ny'),
(9, 'Audrey', 'Fleurot'),
(10, 'Clotilde', 'Mollet'),
(11, 'Cyril', 'Mendy'),
(12, 'Chris', 'Evans'),
(13, 'Mark', 'Ruffalo'),
(14, 'Scarlett', 'Johansson'),
(15, 'Robert', 'Downey'),
(16, 'Chris', 'Hemsworth'),
(17, 'Jeremy', 'Renner'),
(18, 'Don', 'Cheadle'),
(19, 'Tom', 'Hiddleston'),
(20, 'Sam', 'Worthington'),
(21, 'Zoe', 'Saldana'),
(22, 'Sigourney', 'Weaver'),
(23, 'Stephen', 'Lang'),
(24, 'Lola', 'Herrera'),
(25, 'Michelle', 'Rodriguez'),
(26, 'Laz', 'Alonso'),
(27, 'Ken', 'Watanabe'),
(28, 'Tsuyoshi', 'Ihara'),
(29, 'Ryo', 'Kase'),
(30, 'Hiroshi', 'Watanabe'),
(31, 'Yuki', 'Matsuzaki'),
(32, 'Toshiya', 'Agata'),
(33, 'Toshi', 'Toda'),
(34, 'Ken', 'Kensei'),
(35, 'Mark', 'Moses'),
(36, 'Roxanne', 'Hart'),
(37, 'Yoshio', 'Iizuka'),
(38, 'Koji', 'Wada'),
(39, 'Mitsuyuki', 'Oishi'),
(40, 'Mathew', 'Botuchis'),
(41, 'Ryan', 'Kelley'),
(42, 'Mel', 'Gibson'),
(43, 'Jodie', 'Foster'),
(44, 'James', 'Garner'),
(45, 'Graham', 'Greene'),
(46, 'James', 'Coburn'),
(47, 'Samuel L.', 'Jackson'),
(48, 'John', 'Heard'),
(49, 'Kelly', 'Rowan'),
(50, 'Clifton', 'Collins Jr.'),
(51, 'Tony', 'Plana'),
(52, 'Jonah', 'Rooney'),
(53, 'Ebony Monique', 'Solomon'),
(54, 'Ioannis', 'Bogris'),
(55, 'Dominic', 'Hoffman'),
(56, 'Martha', 'Velez'),
(57, 'Heath', 'Ledger'),
(58, 'Christian', 'Bale'),
(59, 'Aaron', 'Eckhart'),
(60, 'Michael', 'Caine'),
(61, 'Maggie', 'Gyllenhaal'),
(62, 'Gary', 'Oldman'),
(63, 'Amanda', 'Seyfried'),
(64, 'Justin', 'Timberlake'),
(65, 'Cillian', 'Murphy'),
(66, 'Shyloh', 'Oostwald'),
(67, 'Olivia', 'Wilde'),
(68, 'Johnny', 'Galecki'),
(69, 'Colin', 'McGurk'),
(70, 'Zac', 'Efron'),
(71, 'Taylor', 'Schilling'),
(72, 'Blythe', 'Danner'),
(73, 'Riley', 'Stewart'),
(74, 'Jared', 'Harris'),
(75, 'Stephen', 'Fry'),
(76, 'Rachel', 'McAdams'),
(77, 'Geraldine', 'James'),
(78, 'Gabrielle', 'Scharnitzky'),
(79, 'Jude', 'Law'),
(80, 'Noomi', 'Rapace'),
(81, 'Eddie', 'Marsan'),
(82, 'Kelly', 'Reilly'),
(83, 'Russell', 'Peters'),
(84, 'Jake', 'Gyllenhaal'),
(85, 'Michelle', 'Monaghan'),
(86, 'Vera', 'Farmiga'),
(87, 'Jeffrey', 'Wright'),
(88, 'Michael', 'Arden'),
(89, 'Bradley', 'Cooper'),
(90, 'Abbie', 'Cornish'),
(92, 'Anna', 'Friel'),
(93, 'Andrew', 'Howard'),
(94, 'Johnny', 'Whitworth'),
(95, 'Bryan', 'Greenberg'),
(96, 'Mila', 'Kunis'),
(97, 'Emma', 'Stone'),
(98, 'Rashida', 'Jones'),
(99, 'Woody', 'Harrelson'),
(100, 'Richard', 'Jenkins'),
(101, 'Patricia', 'Clarkson'),
(102, 'Jenny', 'Elfman'),
(106, 'Markus', 'Allwang'),
(107, 'Sophia', 'Harland'),
(108, 'Lena', 'Pinkerneil');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `film_regie`
--

CREATE TABLE IF NOT EXISTS `film_regie` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `objekt_id` int(9) NOT NULL,
  `regie_id` int(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Daten für Tabelle `film_regie`
--

INSERT INTO `film_regie` (`id`, `objekt_id`, `regie_id`) VALUES
(1, 3, 1),
(2, 11, 5),
(3, 12, 6),
(4, 1, 2),
(5, 2, 3),
(6, 4, 4),
(7, 13, 7),
(8, 14, 8),
(9, 15, 9),
(10, 16, 10),
(11, 17, 11),
(12, 18, 12),
(13, 19, 13),
(14, 20, 14),
(15, 28, 2),
(16, 30, 2),
(17, 31, 2),
(18, 32, 2),
(19, 34, 16),
(20, 35, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kunde`
--

CREATE TABLE IF NOT EXISTS `kunde` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vorname` varchar(30) NOT NULL,
  `nachname` varchar(30) NOT NULL,
  `geburtsdatum` date NOT NULL,
  `adresse` int(11) NOT NULL,
  `telefon` varchar(15) NOT NULL,
  `personalausweisnummer` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personalausweisnummer` (`personalausweisnummer`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Daten für Tabelle `kunde`
--

INSERT INTO `kunde` (`id`, `vorname`, `nachname`, `geburtsdatum`, `adresse`, `telefon`, `personalausweisnummer`) VALUES
(1, 'Markus', 'Allwang', '1990-04-24', 1, '015141449291', '1234567890'),
(2, 'Anna', 'Meier', '1987-12-16', 3, '07113458', '95746395'),
(3, 'Günter', 'Weidemann', '1972-04-14', 4, '051146375', '64527495'),
(4, 'Liliana', 'Fehmann', '1984-01-26', 5, '073465477', '2435367213'),
(5, 'Georg', 'Gras', '1972-06-19', 6, '0854737243', '126321546372'),
(6, 'Ingeborg', 'Müllermeier', '1962-03-13', 7, '0504567354', '876599834'),
(7, 'Isabel', 'Reichenbach', '1977-11-22', 8, '0711345345', '998756243'),
(8, 'Andreas', 'Cordel', '1979-08-30', 9, '0711346758', '98234512'),
(9, 'Christian', 'Waldmann', '1975-10-10', 10, '061172384', '9327432647'),
(10, 'Maria', 'Maulwurf', '1980-02-29', 11, '081137234', '324783245'),
(11, 'Anastasia', 'Dommberg', '1992-12-24', 12, '0523349823', '543267845'),
(12, 'Nikolaus', 'Mann', '1979-07-06', 2, '0577345346', '893457345'),
(13, 'Lucas', 'Smit', '1993-08-05', 13, '07356667', '987678777'),
(14, 'Jan', 'Schlaudraff', '1983-01-04', 14, '051134278', '8345783645'),
(23, 'Sophia', 'Harland', '1992-01-17', 2, '01903434343', '9704631642920117614010280');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `objekt`
--

CREATE TABLE IF NOT EXISTS `objekt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `art` enum('Spiel','Film','Zubehoer') NOT NULL,
  `genre` enum('Film_Kriminalfilm','Film_Komödie','Film_Western','Film_Melodram','Film_Musical','Film_Kriegsfilm','Film_Erotikfilm','Film_Roadmovie','Film_Science-Fiction','Film_Mystery','Film_Fantasy','Film_Horrorfilm','Film_Actionfilm','Film_Thriller','Film_Dokumentarfilm','Film_Drama','Film_Zeichentrickfilm','Spiel_Action','Spiel_Adventure','Spiel_Ego-Shooter','Spiel_Erotik','Spiel_Geschicklichkeit','Spiel_JumpAndRun','Spiel_Lernen','Spiel_MusikUndTanz','Spiel_Puzzle','Spiel_Quiz','Spiel_BeatEmUps','Spiel_ShotEmUps','Spiel_Sport','Spiel_Strategie','Spiel_Simulation','Spiel_Rollenspiel','Zubehoer_Player','Zubehoer_Beamer','Film_Liebesfilm') NOT NULL,
  `format` enum('PC','XBox','PS3','BlueRay','DVD','Beamer') NOT NULL,
  `titel` varchar(50) NOT NULL,
  `hersteller` varchar(30) NOT NULL,
  `produktionsjahr` int(4) NOT NULL,
  `fsk` int(2) NOT NULL,
  `originaltitel` varchar(50) NOT NULL,
  `tags` varchar(50) NOT NULL,
  `beschreibung` text NOT NULL,
  `preis` decimal(9,2) NOT NULL COMMENT 'Preis pro Ausleihtag',
  `gesamtAnzahl` int(2) NOT NULL,
  `verfuegbareAnzahl` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Daten für Tabelle `objekt`
--

INSERT INTO `objekt` (`id`, `art`, `genre`, `format`, `titel`, `hersteller`, `produktionsjahr`, `fsk`, `originaltitel`, `tags`, `beschreibung`, `preis`, `gesamtAnzahl`, `verfuegbareAnzahl`) VALUES
(1, 'Film', 'Film_Komödie', 'BlueRay', 'Meine Braut, ihr Vater und ich', 'Paramount Home Entertainment', 2000, 6, 'Meet the Parents', 'Braut, Vater, Ich, Hochzeit', 'Greg Focker (Ben Stiller) schwebt im Siebten Himmel: als Krankenpfleger hat er seinen Traumjob gefunden, mit der zauberhaften Kindergärtnerin Pam (Teri Polo) seine Traumfrau. Nur der Trauschein fehlt noch zum gemeinsamen Glück. Noch...\r\nWährend eines Wochenendbesuchs bei Pams Eltern will er endlich um ihre Hand anhalten. So hat er sich das zumindest vorgestellt... Doch frei nach einer alten Binsenwahrheit kommt es erstens anders, und zweitens als es sich Greg in seinen schlimmsten Albträumen ausmalen kann. Denn so sehr er auch versucht, bei den künftigen Schwiegereltern, Jack (Robert De Niro) und Dina (Blythe Danner), den besten Eindruck zu hinterlassen - die einzige Zielsicherheit beweist er beim direkten Marsch ins nächste Fettnäpfchen. Schon der Start ins alles entscheidende Wochenende geht gründlich daneben: Gregs Koffer geht auf dem Flug verloren, sein einziger Pullover wird Opfer eines Baby-Bäuerchens und Pam verrät ihren tierliebenden Eltern von Gregs Abneigung gegen Katzen. Als er dann auch noch ausgerechnet beim ersten gemeinsamen Familienabendessen die Urne seiner Schwiegeroma in Spe vom Kaminsims schießt - mit einem Champagnerkorken - ist die Stimmung endgültig auf dem Nullpunkt. Die Liste der Missgeschicke wird bald so lang wie das Gesicht von Schwiegervater Jack. Der ist als exzentrischer Ex-CIA-Agent natürlich besonders misstrauisch und schließt den Freund seiner Tochter kurzerhand erst einmal an den persönlichen Lügendetektor an. Und es kommt noch schlimmer: Greg verwandelt das Grundstück in eine Jauchegrube und fackelt das Garten-Mobiliar ab. Als durch seine Tollpatschigkeit dann auch noch der Lieblingskater der Familie, Mr. Jinx, verschwindet, ist das Maß endgültig voll. Wenn sich Greg nicht schnell etwas einfallen lässt, kann er den bereits gekauften Verlobungsring zum Pfandleiher bringen. Die Zeit läuft...', 0.50, 5, 5),
(2, 'Film', 'Film_Kriegsfilm', 'BlueRay', 'Letters from Iwo Jima', 'Warner Home Video', 2006, 16, 'Letters from Iwo Jima', 'Weltkrieg, 1945, Japan, Krieg', '1945: Während der amerikanischen Invasion auf der Pazifikinsel Iwo Jima bahnt sich ein Gemetzel ungeahnten Ausmaßes an. Doch die japanischen Truppen, die der US-Übermacht in allen Belangen unterlegen sind, leisten erbitterten Widerstand. Angeführt von General Kuribayashi und dessen Freund Baron Nishi, die beide vor dem Zweiten Weltkrieg in den USA ausgebildet wurden, führen die Japaner einen heldenhaften Kampf. Doch da wissen die meisten bereits, dass sie den kargen schwarzen Felsen nicht lebend verlassen werden.', 1.00, 5, 5),
(3, 'Film', 'Film_Thriller', 'DVD', '187 - Eine tödliche Zahl ', 'Concorde', 1997, 16, '', 'Schule, USA', 'Der idealistische Lehrer Trevor Garfield versucht an einer New Yorker Public School, seine Schüler auf das Arbeitsleben vorzubereiten. Doch die Kids haben für Ausbildung nichts übrig und ziehen Waffen ihren Füllfederhaltern vor. Als Garfield einem seiner Schüler die Versetzung verweigert, wird er von diesem niedergestochen. 15 Monate später versucht Trevor in L.A. einen Neubeginn. Doch auch hier ist die Situation im Klassenzimmer keinen Deut besser als im "big apple". Aber der wackere Pauker hat sich verändert - er ist zu einer Zeitbombe geworden...', 0.50, 5, 5),
(4, 'Film', 'Film_Western', 'DVD', 'Maverick', 'Warner Home Video', 1994, 12, 'Maverick', 'Western, Poker', 'Brett Maverick gilt als gewieftester Spieler und schnellster Schütze des Wilden Westen - auch wenn er keiner Fliege etwas zuleide tun kann. Er befindet sich auf dem Weg nach St. Louis, wo auf einem Schaufelraddampfer ein großes Pokerturnier stattfinden soll. Bei der ereignisreichen Reise trifft Maverick in der vifen Annabelle und dem schlitzohrigen Sheriff Cooper auf Gleichgesinnte. Nach einer Reihe von aufregenden Abenteuern müssen sie am Pokertisch beweisen, wer der Gerissenste von allen ist.', 0.50, 5, 5),
(5, 'Spiel', 'Spiel_Ego-Shooter', 'XBox', 'Call of Duty 4 - Modern Warfare', 'Activision', 2007, 18, 'Call of Duty 4 - Modern Warfare', 'Krieg', 'Der neueste Actionthriller des preisgekrönten Entwicklerteams Infinity Ward, Schöpfer der Call of Duty®-Reihe, bietet das wohl intensivste und dabei filmreifste Actionerlebnis aller Zeiten. Call of Duty 4: Modern Warfare bewaffnet die Spieler mit einem ganzen Arsenal moderner, schussgewaltiger Feuerkraft und führt sie im Kampf gegen eine Rebellengruppe in Krisenregionen rings um den Globus. Als US-Marine sowie als britischer SAS-Soldat erlebt der Spieler hautnah die dramatische Geschichte voller plötzlicher Wendungen. Er setzt fortschrittlicheTechnologien ein, modernste Feuerwaffen und koordinierte Luft- und Bodenschläge. Auf den modernen Schlachtfeldern sind Schnelligkeit, Präzision und Kommunikation entscheidend. Der epische Titel bietet zusätzlich einen noch komplexeren Mehrspieler-Modus, der unzählige Spieler mit faszinierendem Gameplay und vielfachen Konfigurationsmöglichkeiten in seinen Bann ziehen wird.\r\n\r\nFEATURES:\r\n- Das Produkt spielt in der Gegenwart: Verschiedene Schauplätze (Russland, Naher Osten, Barentsee) bieten viel Abwechslung, das Spiel enthält eine durchgängige, packende Story\r\n- Neue, realistische Waffen und Fahrzeuge\r\n- Neue, realistische Animationen und Bewegungsabläufe\r\n- Mehrspieler-Vergnügen: Online spielbar per PC, Xbox360 und PS3, inklusive Lobby und Matchmaking-Features. PC mit Punkbuster-Anti-Cheat\r\n- Äußerst intensive Action: Die beliebte CoD-Atmosphäre wird auf einen völlig neuen Level gebracht\r\n- Noch realistischerer Look, stark verbesserte Optik: Inklusive Transparenz- und Raucheffekten, bombastisch-intensive Soundkulisse.', 0.50, 5, 5),
(6, 'Spiel', 'Spiel_Sport', 'PS3', 'Asterix Maximum Gaudium', 'Namco', 2001, 0, 'Mega Madness', 'Römer, Gallien, Obelix, Wettkampf', 'Wieder einmal sind die gallischen Dorfbewohner ganz aus dem Häuschen:\r\nMajestix veranstaltet das verrückteste Turnier aller Zeiten! Wer wird Sieger? Asterix? Obelix? Troubadix? Gutemiene?\r\n> Tritt in 14 verrückten, typisch gallischen Wettkämpfen an:\r\nWie wärs mit einem haarsträubenden Ruderbootrennen, einer abgefahrenen Katapultjagd oder dem spannenden Skeetschießen...?\r\n> Mehrspieler-Modus: \r\n2-Spieler Teams, 2-Spieler-Duell Modus\r\n> 3 Fantastische Spielmodi:\r\n- "Maximum Gaudium"; Was für ein Turnier!\r\n- "Übung"; Action ohne Umwege!\r\n- "Teamspiel"; für dicke Freunde.', 0.50, 5, 5),
(7, 'Spiel', 'Spiel_Sport', 'PS3', 'FIFA 13', 'Electronic Arts', 2012, 0, 'FIFA 13', 'Fussball, Sport, Wettkampf, Online', 'JOIN THE CLUB! FIFA 13 fängt die Dramatik des echten Fußballs ein.\r\n\r\nDie Verbesserungen für PlayStation 3 in Bereichen wie künstliche Intelligenz, Dribbling, Ballkontrolle und Kollisionen machen den Kampf um den Ball so spannend wie nie, bieten im Angriff ungeahnte Freiheiten, ermöglichen kreative Spielzüge und fangen die Dramatik des echten Fußballs ein.\r\n\r\nDas neue Feature Attacking Intelligence versieht Spieler mit der bislang ausgereiftesten künstlichen Intelligenz. Die Akteure auf dem Platz analysieren den Raum besser, setzen die Defensive stärker und intelligenter unter Druck und antizipieren Spielzüge akkurater. Jetzt zählt jede einzelne Ballberührung – dank dem vollständigsten und intuitivsten Dribblingsystem, das die Serie je gesehen hat. Das Feature Complete Dribbling wurde vom besten Spieler der Welt, Lionel Messi, inspiriert. In der Offensive können Spieler sich dem Gegner stellen und mittels einer Kombination aus Dribbling und echter 360°-Bewegungsfreiheit kreativer und gefährlicher in Eins-zu-eins-Situationen gehen. Die FIFA 13 1st Touch Ball Control weist neue Wege bei der Ballbehandlung: die nahezu perfekte Kontrolle bei jedem Spieler in jeder Situation gibt es nicht mehr. So haben Verteidiger mehr Möglichkeiten, an den Ball zu kommen. Die zweite Generation der preisgekrönten Physik-Engine erweitert das körperliche Element auf die Zweikämpfe ohne Ball. Verteidiger können sich mit Schieben und Zerren Positionsvorteile schaffen, mit ihrem Gewicht und ihrem Einsatz den Gegner zu Fehlern zwingen und Entscheidungen schon treffen, bevor der Ball kommt. Mit FIFA 13 Tactical Free Kicks bieten sich neue Möglichkeiten, gefährliche und unberechenbare Freistöße einzustudieren. Bis zu drei Angreifer kann man um den Ball gruppieren und den Gegner verwirren – mit Täuschungsmanövern und erweiterten Passmöglichkeiten.\r\n\r\nMit FIFA 13 kommt das umfangreichste Spiel in der Geschichte der Serie.\r\n\r\n\r\n\r\nFEATURES:\r\n--> Attacking Intelligence - Dank neuer Positionsintelligenz können Spielzüge von den Mitspielern besser analysiert werden. Sie positionieren sich so, dass neue Angriffsoptionen entstehen. Die Spieler setzen die Defensive stärker und intelligenter unter Druck, ziehen Verteidiger auf sich und öffnen Passwege für ihre Mitspieler. Hinzu kommt, dass sie nicht nur ballfixiert agieren, sondern auch Spielzüge ohne Ball genau antizipieren. Entsprechend gestalten sie ihre Laufwege, um sich bietende Räume zu nutzen.\r\n--> Complete Dribbling - Jetzt zählt jede einzelne Ball-berührung. Eins-zu-eins-Situationen lassen sich dank mehr Kreativität im Angriff besser nutzen. Die Spieler haben mit Ball einen explosiveren Antritt und nutzen die präzise Ballbehandlung mittels echter 360°-Bewegungsfreiheit, wenn sie von einem Verteidiger gestellt werden. Im Angriff sucht man die Konfrontation mit dem Gegner und gibt dem Ball eine beliebige Richtung, um Grätschen auszuweichen. Oder man dreht sich und schirmt den Ball ab, hält seine Position und den Gegner auf Distanz.\r\n--> FIFA 13 1st Touch Control - Das neue Ballannahme-System ist noch realistischer: die nahezu perfekte Kontrolle bei jedem Spieler in jeder Situation gibt es nicht mehr, insbesondere bei schwierigen Bällen. Schlechte Pässe sind schwerer unter Kontrolle zu bringen, wovon Verteidiger profitieren können. Defensivdruck, Ballweg und Geschwindigkeit, das sind die Faktoren, die eine erfolgreiche Ballannahme bestimmen. Dabei sind technisch stärkere Spieler mit guter Ballkontrolle natürlich im Vorteil. Es gibt also weniger perfekte Ballbehandlung, dazu die Möglichkeit, Pässe zu überdosieren und insgesamt eine bessere Balance zwischen Angriff und Verteidigung.\r\n-->Player Impact Engine - Die zweite Generation der preisgekrönten Physik-Engine erweitert das körperliche Element auf die Zweikämpfe ohne Ball. So haben Verteidiger mehr Möglichkeiten, an den Ball zu kommen. Verteidiger setzen ihren Körper besser ein, um in Ballbesitz zu kommen. Sie können sich mit Schieben und Zerren Positionsvorteile schaffen, mit ihrem Gewicht und ihrem Einsatz den Gegner zu Fehlern zwingen und Entscheidungen schon treffen, bevor der Ball kommt.\r\n--> FIFA 13 Tactical Free Kicks - Gefährliche und unberechenbare Freistöße einstudieren. Bis zu drei Angreifer kann man um den Ball gruppieren und den Gegner verwirren – mit Täuschungsmanövern, erweiterten Passmöglichkeiten und raffinierteren Freistoßvarianten. Dafür kann der Gegner bestimmen, wie viele seiner Spieler die Mauer bilden sollen, diese sich nach vorn bewegen lassen oder einen Spieler aus der Mauer laufen lassen, der versucht, den Freistoß zu blocken.\r\n--> EA SPORTS Football Club — Alles, was in FIFA 13 und während der Spiele gegen Freunde passiert, ist von Bedeutung und geht in die Statistik des Spielers ein. So erhält er Football Club Credits, um Objekte aus dem Katalog freizuschalten: etwa Trikots, spezielle Torjubel, Virtual Pro Attribute-Boosts, Super Scout Credits für den Karrieremodus, FIFA 13 Ultimate Team Münzen-Boosts und Extra-Spiele im „Mann-gegen-Mann: Saisons“-Modus. Innerhalb der FIFA-Community kann er seinen Status verbessern und bis zu Level 100 aufsteigen. Die Saison in FIFA 13 wird durch Ereignisse in der realen Fußballwelt beeinflusst. „Support Your Club“ ermöglicht es den Fans, ihren Verein zu repräsentieren und in jedem Bereich von FIFA 13 gegen rivalisierende Vereine anzutreten. Die Spieler erhalten Punkte und sorgen so für einen besseren Platz ihres Vereins in den „Support Your Club“-Ligatabellen oder helfen ihm im Kampf gegen den Abstieg.\r\n--> EA SPORTS Football Club App für iPhone — Greife auf dein Football Club Profil und die Aktivitäten deiner Freunde zu, verwalte dein FIFA 13 Ultimate Team, durchsuche Live-Auktionen und biete mit, um neue Akteure für dein ultimatives Team zu erhalten.\r\n--> Absolute Authentizität - Über 500 lizenzierte Vereine und mehr als 15.000 Spieler.\r\n--> MoveMotion-Controller Unterstützung\r\n\r\nSpiel komplett in Deutsch (Handbuch online verfügbar)\r\n\r\nOnline-Multiplayer mit Registrierung spielbar, kostenloser Zugangscode bei Neukauf enthalten (einmalig verwendbar)', 1.50, 5, 5),
(8, 'Spiel', 'Spiel_MusikUndTanz', 'XBox', 'Just Dance 4 (Move)', 'Ubisoft', 2012, 0, 'Just Dance 4 (Move)', 'Tanzen, Sport, Musik', 'Das Nummer 1 Tanzspiel ist mit Just Dance 4 zurück! Noch mehr Songs, Moves und stundenlanger Spielspass für euch, eure Freunde und die ganze Familie! Just Dance 4 ist dein Fahrschein, für beste und brandneue Musik mit über 40 Tracks von Top-Künstlern, bekannten Genres und den besten Songs. Die Trackliste beinhaltet außerdem legendäre Künstler wie Barry White und The B-52, um die gesamte Familie in Tanzlaune zu versetzen.\r\n\r\nFeatures:\r\n- Top Hits der Charts: Just Dance 4 beinhaltet über 40 komplett neue Songs, angefangen von aktuellen Top-Ten-Hits bis hin zu legendären Evergreens und klassischen Dance-Party Tracks! Mit Nicki Minaj "Super Bass", "Good Feeling" von Flo Rida, "Moves Like Jagger" von Maroon 5 ft. Christina Aguilera, "So What" von P!Nk oder "You¡¦re The First, The Last, My Everything" von Barry White. Hier findet jeder seinen persönlichen Lieblingssong.\r\n- Just Dance-Off: Fordere deine Freunde in 6 verschiedenen Tanzrunden im All-New-Battle Mode heraus. Kämpfe Kopf an Kopf zu einzigartigen Choreographien und Musik, um den Kampf beim Dance-Battle zu gewinnen!\r\n- Deine Crew: Glänze bei Songs, die dir und deinen Freunden vier gleichzeitige Tanzeinlagen bieten. Jeder Spieler kann seinen eigenen Moment des Ruhms mit einzigartigen Choreographien und Moves ergattern.\r\n- Bekomme nie genug: Just Dance 4 beinhaltet immer noch die favorisierten Modi wie Duett-Modus und Tanz-Mash-up-Modus mit mehr als 45 Mash-up-Choreographien und natürlich vielem mehr!\r\n- Playstation Move zum Spielen erforderlich!\r\n\r\n\r\nTracklist:\r\nAlexandra Stan - Mr. Saxobeat\r\nArmy of Lovers - Crucified\r\nBarry White - You''re The First, The Last, My Everything\r\nBill Medley & Jennifer Warnes - The Thime Of My Life\r\nBoys Town Gang - Can''t Take My Eyes Of You\r\nCarly Rae Jepsen - Call Me Maybe\r\nEmma - Cercavo Amore\r\nElvis Presley - Jailhouse Rock\r\nEurope - The Final Countdown\r\nFlo Rida - Good Feeling\r\nJennifer Lopez ft. Pitbull - On The Floor\r\nJustin Biber ft. Nicki Minaj - Beauty And A Beat\r\nKat Deluna ft. Busta Rhymes - Run The Show\r\nLas Ketchup - Asereje (The Ketchup Song)\r\nMaroon 5 ft. Christina Aguilera - Moves Like Jagger\r\nNelly Furtado - Maneater\r\nNicki Minaj - Super Bass\r\nOne Direction - What Makes You Beautiful\r\nP!nk - So What\r\nRick Astley - Never Gonna Give You Up\r\nRicky Martin - Livin'' La Vida Loca\r\nRihanna - Dusturbia\r\nRihanna ft. Jay-Z - Umbrella\r\nSelena Gomez and The Scene - Love You Like A Love Song\r\nSergio Mendes ft. The Black Eyed Peas - Mas Que Nada\r\nStefanie Heinzmann - Diggin'' In The Dirt\r\nStevie Wonder - Superstition\r\nThe B52''s - Rock Lobster\r\nWil Smith - Wild Wild West\r\n...und viele weitere wie Everybody Needs Somebody To Love, Oops!...I Did It Again, Time Warp, We No Speak Americano, Hot For Me, Oh No!, Beware Of The Boys (Mundian To Bach Ke), Istanbul, Hit''Em Up Style (Opps!), Like It, Crazy Little Thing, Make The Party (Don''t Stop), Some Catchin'' Up To Do, Rock N''Roll (Will Take You To The Mountain), Tribal Dance', 1.00, 5, 5),
(9, 'Spiel', 'Spiel_Rollenspiel', 'PC', 'Borderlands 2', '2K Games', 2012, 18, 'Borderlands 2', 'Zombies, Fantasy, Online', 'Ein neues Zeitalter des Ballerns und Plünderns!\r\n\r\nMach dich bereit für einen Höllenritt als einer von vier neuen Kammer-Jägern, die den ungezähmten Planeten Pandora auf der Suche nach Schätzen und Chaos durchforsten. Tritt gegen wilde Kreaturen, abgedrehte Psychos und den Superschurken Handsome Jack an.\r\n\r\nFeatures:\r\n- Fesselndes Gameplay: Wilde Ego-Shooter-Action gepaart mit den Rollenspielelementen Beutesammeln und Charakterweiterentwicklung\r\n- Spiel mit Freunden: Zieh allein ins Gefecht oder online mit bis zu 4 Spielern im übergangslosen Koop-Modus.\r\n- Aus unzähligen Waffen mach noch unzähligere: Borderlands 2 enthält Millionen prozessual generierter Waffen. Oh ja, das geht!\r\n- 100% Uncut\r\n- Spiel komplett in Deutsch!', 1.50, 5, 5),
(10, 'Spiel', 'Spiel_Sport', 'PC', 'NBA 2K 13 ', '2K Games', 2012, 0, 'NBA 2K 13 ', 'Sport, Basketball, Online', 'Der Beginn einer neuen Dynastie!\r\n\r\nEine Produktion von Jay Z!\r\nProduziert von dem mehrfachen mit Platin ausgezeichneten Musiker Jay Z, der für den Soundtrack, die Präsentationselemente und den übergreifenden Stil des Spiels verantwortlich zeichnete.\r\n\r\nVerbessertes Gameplay!\r\nDie Stick-Steuerung sorgt für eine bessere Ballkontrolle, der dynamische Wurfgenerator für realistischere Wurfvarianten, neue Signature Skills für mehr Abwechslung...\r\n\r\nMeine Karriere-Modus!\r\nErstelle deinen eigenen Mein Spieler, statte ihn mit Signature Skills aus und kaufe mit Virtueller Währung (VC) individuelle Animationen für ihn. Mit neuen Sponsoring-Optionen, brandneuer Freizeitkleidung und Ausrüstung und vielem mehr.\r\n\r\nUSA Basketball!\r\nEndlich kannst du die Frage beantworten, wer aus einem Spiel zwischen Michael Jordans Dream Team von 1992 und Kevin Durants Team von 2012 als Sieger hervorgehen würde.\r\n\r\nMein Team-Modus!\r\nSammle Spieler, Trikots, Arenen und Boosts und spiele dann online gegen Spieler und ihre Mein Teams, in unserem neuen, rangbasierten Spielersuche-System.\r\n\r\n\r\nSpielsprache in Englisch, Handbuch und Bildschirmtext in Deutsch!\r\n\r\nKinect Sensorleiste wird unterstützt!\r\n\r\n\r\nFür Online-Spiele ist eine Registrierung erforderlich. Zugangscode bei Neukauf mit enthalten (einmalig verwendbar)', 1.50, 5, 5),
(11, 'Film', 'Film_Actionfilm', 'DVD', 'The Darkt Knight', 'Warner Home Video', 2008, 16, 'The Dark Knight - Why so serious?', 'DC Comics, Oscar-prämiert, Batman, Clowns', 'Die Fortsetzung von ''Batman Begins'' vereint Regisseur Christopher Nolan und Hauptdarsteller Christian Bale, der erneut die Rolle des Batman aka Bruce Wayne in seinem Kampf gegen das Böse verkörpert. Unterstützt von Lieutenant Jim Gordon (Gary Oldman) und Staatsanwalt Harvey Dent (Aaron Eckhart) setzt Batman sein Vorhaben fort, das organisierte Verbrechen in Gotham endgültig zu zerschlagen. Das Dreiergespann erweist sich als effektiv. Doch bald sehen sie sich einem genialen, immer mächtiger werdenden Kriminellen gegenübergestellt, der als Joker (Heath Ledger) bekannt ist. Er stürzt Gotham in ein anarchisches Chaos und zwingt Batman immer näher an die Grenze zwischen Gerechtigkeit und Rache.', 0.50, 5, 5),
(12, 'Film', 'Film_Science-Fiction', 'BlueRay', 'In Time - Deine Zeit läuft ab', 'Twentieth Century Fox', 2011, 12, 'In Time - Live forever or die trying', 'Überwachung, Verschwörung, Flucht', 'In der Zukunft leben die Menschen in einer Welt, in der die offizielle Währung Geld durch Zeit ersetzt wird. Mit 25 hören die Menschen auf zu altern. Sie sind genetisch so ausgestattet, dass sie nur noch ein Jahr lang überleben können, es sei denn sie schaffen es, sich einen Ausweg zu erkaufen. Die Reichen können sich Tausende von Jahren und somit ewige Jugend und Unsterblichkeit erkaufen, während die Armen betteln, leihen und Minute für Minute stehlen, nur um den Tag zu überstehen. In dieser Welt ist Will Salas (Justin Timberlake) einer der weniger Glücklichen. Jeden Tag erwacht er mit nicht mehr als 23 Stunden auf seiner tickenden Lebensuhr und seine Zeit läuft ab. Jeden Tag muss er genug Zeit verdienen, um den nächsten Morgen zu erleben. Als jedoch ein reicher Fremder in sein Leben tritt und kurz darauf stirbt, wird Will fälschlicherweise des Mordes bezichtigt. Plötzlich ist er auf der Flucht - zusammen mit der attraktiven Geisel Sylvia (Amanda Seyfried). Von jetzt an leben die beiden von Minute zu Minute, und ihre Liebe wird zum wichtigsten Instrument im Kampf gegen das korrupte System.', 1.00, 5, 5),
(13, 'Film', 'Film_Drama', 'BlueRay', 'The Lucky One - Für immer der Deine', 'Warner Home Video Germany', 2012, 12, 'The Lucky One', 'Krieg, Liebe, Intrige', 'Beim Einsatz im Irak war das Foto einer Frau, das ihm das Schicksal in die Hände spielte, für Logan ein Glücksbringer. In die Heimat zurückgekehrt, ermittelt Logan den Namen der Unbekannten und sucht sie im ländlichen North Carolina auf. Weil er nicht die passenden Worte findet, verschweigt er Beth, die im Krieg ihren Bruder verlor, die Wahrheit, beginnt für sie zu arbeiten. Als sich die beiden langsam näherkommen, sieht Beths Ex seine letzte Chance auf Sanierung seiner gescheiterten Ehe gefährdet und reagiert.', 1.00, 5, 5),
(14, 'Film', 'Film_Komödie', 'DVD', 'Ziemlich beste Freunde', 'Universum Film', 2011, 6, 'Intouchables', 'Freundschaft', 'Der arbeitslose und frisch aus dem Knast entlassene Driss bewirbt sich pro forma als Pfleger beim querschnittsgelähmten reichen Erbe Philippe, um den Stempel für die Arbeitslosenunterstützung zu bekommen. Gegen jede Vernunft engagiert ihn der reiche Aristokrat, weil er spürt, dass dieser farbige Junge aus der Banlieue ihm nicht mit Mitleid begegnet. Statt mit dem Behindertenauto düsen die beiden bald mit dem Maserati durch Paris, rauchen Joints, laden schon mal zu einer Orgie ein. Beide respektieren sich, und Philippe gewinnt neue Kraft für das, was ihm vom Leben bleibt. ', 1.50, 5, 5),
(15, 'Film', 'Film_Actionfilm', 'BlueRay', 'Marvel''s The Avengers', 'Walt Disney Studios', 2012, 12, 'Marvel''s The Avengers', 'Comic-Verfilmung, Marvel', 'Mit einer Bedrohung von solchem Ausmaß für die ganze Welt hat es Nick Fury von S.H.I.E.L.D. noch nie zu tun gehabt. Wenn überhaupt, dann hat nur eine ganze Riege von Superhelden eine Chance, ihr zu begegnen. Also versucht er Iron Man, Thor, Captain America, Black Widow und Hulk für eine konzertierte Rettungsaktion zusammen zu trommeln: keine einfache Aufgabe. Doch noch schwieriger ist es, aus den eigenwilligen Individuen ein Team zu formen. ', 1.50, 5, 5),
(17, 'Film', 'Film_Kriminalfilm', 'BlueRay', 'Sherlock Holmes - Spiel im Schatten', 'Warner Home Video', 2011, 12, 'Sherlock Holmes: A Game of Shadows', 'Detektiv, Action', 'Das unvermeidliche Sequel zu der sensationell erfolgreichen Neuerfindung des Meisterdetektivs als unerschrockener Actionheld aus dem Jahr 2009 setzt auf dieselbe Rezeptur wie das Original: Robert Downey Jr. und Jude Law erweisen sich als gut eingespieltes Team, das sich von einem Setpiece zum nächsten schwingt und erstmals auf den bösen Professor Moriarty, gespielt von Jared Harris, trifft. ', 1.50, 5, 5),
(18, 'Film', 'Film_Thriller', 'DVD', 'Source Code', 'Kinowelt Home Entertainment', 2011, 16, 'Source Code', 'Krieg, Soldat, Terror', 'Gerade war US-Soldat Colter Stevens noch im Kriegseinsatz, plötzlich findet er sich in einem Zug nach Chicago wieder - an der Seite einer jungen Frau, die ihn kennt, er sie aber nicht. Kurz darauf explodiert der Zug, und eine Stimme instruiert Stevens, dass er wieder zurück muss. Dank einer revolutionären Technologie kann er im Körper eines Passagiers die letzten acht Minuten vor der Explosion wiederholt miterleben, damit den Bomber ermitteln und dessen nächsten Terroranschlag noch am gleichen Tag verhindern', 1.00, 5, 5),
(19, 'Film', 'Film_Thriller', 'DVD', 'Ohne Limit', 'Concorde Home Entertainment', 2011, 16, 'Limitless', 'Drogen', 'Eddie Morras Leben verläuft nicht gerade auf der Erfolgsspur. Viel zu lange schreibt er schon an seinem ersten Roman und treibt sich in den Straßen und Bars von New York herum. Als ihn auch noch seine Freundin Lindy verlässt, fehlt ihm jede Motivation. Zufällig begegnet er eines Tages einem alten Freund, der ihm eine neue Designerdroge namens NZT anbietet. Und das Mittel wirkt tatsächlich Wunder: Innerhalb kürzester Zeit ist Eddie in der Lage, sämtliche Kapazitäten seines Gehirns zu nutzen. Seinen Roman bringt er in nur wenigen Stunden zu Ende. Plötzlich ist alles ganz leicht! Doch schon bald merkt Eddie, auf welch gefährliches Spiel er sich eingelassen hat.', 1.00, 5, 5),
(20, 'Film', 'Film_Komödie', 'BlueRay', 'Freunde mit gewissen Vorzügen', 'Sony Pictures', 2011, 12, 'Friends with Benefits', 'Liebe', 'Dylan ist neu in New York, doch Jamie, die ihn aus Kalifornien für einen Traumjob nach Manhattan und aus seiner Reserve gelockt hat, sorgt für schnelle Akklimatisation. Der aufstrebende Art Director und die quirlige Headhunterin landen schnell zusammen im Bett, wollen aber trotz gelegentlichem Spaßsex nur beste Freunde bleiben. Beider Bindungsphobien machen sie blind dafür, wie perfekt sie tatsächlich zusammenpassen, und bewirken schließlich, dass auseinanderbricht, was eigentlich zusammengehört. ', 1.00, 5, 5),
(21, 'Zubehoer', 'Zubehoer_Player', 'BlueRay', 'Sony BDP-S490', 'Sony', 2012, 0, '', 'Sony, BDP, Player', 'Wer sich das Kinoerlebnis ins eigene Wohnzimmer holen möchte, ist mit dem Blu-Ray-Player Sony BDP-S490 richtig beraten - brillante Blu-Ray-Discs oder DVD-Filme anwählen und genießen. Mit dreidimensionaler Technik erlebt man Fernsehen mit dem Blu-Ray-Player in moderner 3D-Qualität hautnah. Per DLNA lässt sich der Blu-Ray-Player Sony BDP-S490 mit anderen Geräten verbinden. Gut vernetzt ist das Gerät mit Anschlüssen für USB und HDMI. Wer bei einem Blu-Ray-Player Wert auf eine 3D-Fähigkeit, ein modernes DLNA-Feature, einen USB-Anschluss und eine detailreiche Full-HD-Bildqualität legt, sollte sich den Sony BDP-S490 genauer ansehen.\r\n\r\nGerätetyp\r\nBlu-ray-Player\r\n3D-fähig\r\nJa\r\nNetzwerkfähigkeit\r\nDLNA\r\nHDMI\r\nJa\r\nUSB\r\nJa\r\nBildqualität\r\n1080p (Full HD)\r\nWiedergabeformate (Audio/Video/Bild)\r\nBD-ROM, CD, BD-R, BD-RE, DVD\r\nBreite\r\n43 Zentimeter\r\nHöhe\r\n4,2 Zentimeter\r\nTiefe\r\n19,9 Zentimeter\r\nGewicht\r\n1.5 Kilogramm', 3.00, 5, 5),
(23, 'Zubehoer', 'Zubehoer_Beamer', 'Beamer', 'BenQ MS513', 'BenQ', 2012, 0, '', 'Beamer, BenQ, Projektor', 'Der neue Einstiegs-Projektor mit SVGA (800x600 Pixel) Auflösung, Smart Eco Funktion, PC 3D Ready, Long Life Lampe (bis zu 6.500 Std.) und Lautsprecher\r\n2.700 Ansi-Lumen und 10.000:1 Kontrast\r\nHDMI, 2x D-Sub, Component, Composite, S-Video, Audio, USB (Typ B), RS-232, D-Sub Out, Audio Out', 6.00, 5, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `objekt_darsteller`
--

CREATE TABLE IF NOT EXISTS `objekt_darsteller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objekt_id` int(11) NOT NULL,
  `darsteller_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=120 ;

--
-- Daten für Tabelle `objekt_darsteller`
--

INSERT INTO `objekt_darsteller` (`id`, `objekt_id`, `darsteller_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 2, 27),
(7, 2, 28),
(8, 2, 29),
(9, 2, 30),
(10, 2, 31),
(11, 2, 32),
(12, 2, 33),
(13, 2, 34),
(14, 2, 35),
(15, 2, 36),
(16, 2, 37),
(17, 2, 38),
(18, 2, 39),
(19, 2, 40),
(20, 2, 41),
(21, 4, 42),
(22, 4, 43),
(23, 4, 44),
(24, 4, 45),
(25, 4, 46),
(26, 3, 47),
(27, 3, 48),
(28, 3, 49),
(29, 3, 50),
(30, 3, 51),
(31, 3, 52),
(32, 3, 53),
(33, 3, 54),
(34, 3, 55),
(35, 3, 56),
(36, 11, 57),
(37, 11, 58),
(38, 11, 59),
(39, 11, 60),
(40, 11, 61),
(41, 11, 62),
(42, 12, 63),
(43, 12, 64),
(44, 12, 65),
(45, 12, 66),
(46, 12, 67),
(47, 12, 68),
(48, 12, 69),
(49, 13, 70),
(50, 13, 71),
(51, 13, 72),
(52, 13, 73),
(53, 14, 6),
(54, 14, 7),
(55, 14, 8),
(56, 14, 9),
(57, 14, 10),
(58, 14, 11),
(59, 15, 12),
(60, 15, 13),
(61, 15, 14),
(62, 15, 15),
(63, 15, 16),
(64, 15, 17),
(65, 15, 18),
(66, 15, 19),
(67, 15, 47),
(68, 16, 20),
(69, 16, 21),
(70, 16, 22),
(71, 16, 23),
(72, 16, 24),
(73, 16, 25),
(74, 16, 26),
(75, 17, 74),
(76, 17, 75),
(77, 17, 76),
(78, 17, 77),
(79, 17, 78),
(80, 17, 79),
(81, 17, 80),
(82, 17, 81),
(83, 17, 82),
(84, 17, 15),
(85, 18, 83),
(86, 18, 84),
(87, 18, 85),
(88, 18, 86),
(89, 18, 87),
(90, 18, 88),
(91, 19, 89),
(92, 19, 90),
(93, 19, 1),
(94, 19, 92),
(95, 19, 93),
(96, 19, 94),
(97, 20, 95),
(98, 20, 96),
(99, 20, 97),
(100, 20, 98),
(101, 20, 99),
(102, 20, 100),
(103, 20, 101),
(104, 20, 102),
(105, 20, 64),
(106, 27, 1),
(107, 27, 2),
(108, 28, 1),
(109, 30, 1),
(110, 30, 15),
(111, 31, 1),
(112, 31, 15),
(113, 32, 1),
(114, 32, 15),
(115, 34, 106),
(116, 34, 107),
(117, 34, 108),
(118, 35, 1),
(119, 35, 15);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `regie`
--

CREATE TABLE IF NOT EXISTS `regie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_vorname` varchar(30) NOT NULL,
  `r_nachname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Daten für Tabelle `regie`
--

INSERT INTO `regie` (`id`, `r_vorname`, `r_nachname`) VALUES
(1, 'Kevin', 'Reynolds'),
(2, 'Jay', 'Roach'),
(3, 'Clint', 'Eastwood'),
(4, 'Richard', 'Donner'),
(5, 'Christopher', 'Nolan'),
(6, 'Andrew', 'Niccol'),
(7, 'Scott', 'Hicks'),
(8, 'Eric', 'Toledano'),
(9, 'Joss', 'Whedon'),
(10, 'James', 'Cameron'),
(11, 'Guy', 'Ritchie'),
(12, 'Duncan', 'Jones'),
(13, 'Neil', 'Burger'),
(14, 'Will', 'Gluck'),
(16, 'Andy', 'Bosch');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `verleih`
--

CREATE TABLE IF NOT EXISTS `verleih` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `kunde_id` int(9) NOT NULL,
  `objekt_id` int(9) NOT NULL,
  `ausleihdatum` date NOT NULL,
  `anzahl` int(1) NOT NULL,
  `verliehen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Daten für Tabelle `verleih`
--

INSERT INTO `verleih` (`id`, `kunde_id`, `objekt_id`, `ausleihdatum`, `anzahl`, `verliehen`) VALUES
(1, 9, 20, '2012-11-01', 1, 0),
(2, 7, 15, '2012-11-08', 5, 0),
(3, 7, 17, '2012-11-08', 3, 0),
(4, 7, 1, '2012-11-08', 24, 0),
(5, 7, 15, '2012-11-08', 1, 0),
(6, 7, 1, '2012-11-08', 24, 0),
(7, 7, 1, '2012-11-08', 2, 0),
(8, 7, 2, '2012-11-08', 3, 0),
(9, 7, 4, '2012-11-08', 2, 0),
(10, 7, 3, '2012-11-08', 5, 0),
(11, 7, 11, '2012-11-08', 5, 1),
(12, 9, 1, '2012-11-08', 5, 0),
(13, 9, 4, '2012-11-08', 4, 0),
(14, 23, 28, '2012-11-05', 1, 0),
(15, 23, 34, '2012-11-08', 1, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
