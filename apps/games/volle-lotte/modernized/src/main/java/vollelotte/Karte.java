package vollelotte;

/** Cards of the deck. Only the bonus cards have an effect, the others are drawn but score nothing extra (as in the legacy game). */
public enum Karte {
    BONUS_200("Bonus 200", 200, "Bonus_200_Card_1.png"),
    BONUS_300("Bonus 300", 300, "Bonus_300_Card.png"),
    BONUS_400("Bonus 400", 400, "Bonus_400_Card.png"),
    BONUS_500("Bonus 500", 500, "Bonus_500_Card.png"),
    BONUS_600("Bonus 600", 600, "Bonus_600_Card.png"),
    STERN("Stern", 0, "Star_Card.png"),
    AUSSETZEN("Aussetzen", 0, "Forbidden_Card.png"),
    DOPPELTE_LOTTE("Doppelte Lotte", 0, "Doppelte_Lotte_Card.png"),
    PLUSMINUS_1000("Plus-Minus 1000", 0, "plusminus_1000_Card.png"),
    STRASSE("Straße", 0, "Street_2000_Card.png"),
    KLEEBLATT("Kleeblatt", 0, "Lucky_Card02.png");

    private final String anzeigename;
    private final int bonus;
    private final String bild;

    Karte(String anzeigename, int bonus, String bild) {
        this.anzeigename = anzeigename;
        this.bonus = bonus;
        this.bild = bild;
    }

    /** Points added when all six dice were secured in the round. */
    public int bonus() {
        return bonus;
    }

    /** File name below {@code img/} in the resources. */
    public String bild() {
        return bild;
    }

    @Override
    public String toString() {
        return anzeigename;
    }
}
