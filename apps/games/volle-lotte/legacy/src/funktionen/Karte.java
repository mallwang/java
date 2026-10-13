package funktionen;

public class Karte {

	// Karten haben bestimmte Werte
	private final static int BONUS200 = 0, BONUS300 = 1, BONUS400 = 2,
			BONUS500 = 3, BONUS600 = 4, STERN = 5, AUSSETZEN = 6,
			DOPPELTE_LOTTE = 7, PLUSMINUS1000 = 8, STRASSE = 9, KLEEBLATT = 10;
	private int kartenwert;

	public Karte(int kartenwert) {
		this.kartenwert = kartenwert;
	}

	public String karteToString() {
		switch (kartenwert) {
		case BONUS200:
			return "Bonus 200";
		case BONUS300:
			return "Bonus 300";
		case BONUS400:
			return "Bonus 400";
		case BONUS500:
			return "Bonus 500";
		case BONUS600:
			return "Bonus 600";
		case STERN:
			return "Stern";
		case AUSSETZEN:
			return "Aussetzen";
		case DOPPELTE_LOTTE:
			return "Doppelte Lotte";
		case PLUSMINUS1000:
			return "Plus-Minus 1000";
		case STRASSE:
			return "Straﬂe";
		case KLEEBLATT:
			return "Kleeblatt";
		}
		System.out.println("Falsche Karte! : " + kartenwert);
		return "-1";
	}
	
}
