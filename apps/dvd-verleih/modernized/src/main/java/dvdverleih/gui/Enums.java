package dvdverleih.gui;

public class Enums {

	public enum Formate_Film {
		alle_Formate, DVD, BlueRay
	}

	public enum Formate_Film_aendern {
		DVD, BlueRay
	}

	public enum Formate_Spiel {
		alle_Formate, XBox, PS3, PC
	}

	public enum Formate_Spiel_aendern {
		XBox, PS3, PC
	}

	public enum Formate_Zubehoer {
		alle, Player, Beamer
	}

	public enum Formate_Zubehoer_aendern {
		Player, Beamer
	}

	public enum Genre_Film {
		Kriminalfilm, Komödie, Western, Liebesfilm, Melodram, Musical, Kriegsfilm, Erotikfilm, Roadmovie, Science_Fiction, Mystery, Fantasy, Horror, Action, Thriller, Dokumentarfilm, Drama, Zeichentrickfilm
	}

	public enum Genre_Spiel {
		Action, Adventure, Egoshooter, Erotik, Geschicklichkeit, Jump_and_Run, Lernen, Musik_und_Tanz, Puzzle, Quiz, Beat_Em_Ups, Shot_Em_Ups, Sport, Strategie, Simulation, Rollenspiel
	}

	public enum Genre_Zubehoer {
		BlueRay, DVD
	}

	public enum Kategorie {
		Film, Spiel, Zubehör
	}
}
