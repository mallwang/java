package annorechner;

/** Population groups of the two worlds (Orient: beggars to nobles, Occident: nomads and envoys). */
public enum Bevoelkerung {
    BETTLER("Bettler", "Bettler.png"),
    BAUERN("Bauern", "Bauer.png"),
    BUERGER("Bürger", "Buerger.png"),
    PATRIZIER("Patrizier", "Patrizier.png"),
    ADELIGE("Adelige", "Adeliger.png"),
    NOMADEN("Nomaden", "Nomade.png"),
    GESANDTE("Gesandte", "Gesandte.png");

    private final String anzeigename;
    private final String bild;

    Bevoelkerung(String anzeigename, String bild) {
        this.anzeigename = anzeigename;
        this.bild = bild;
    }

    public String bild() {
        return bild;
    }

    @Override
    public String toString() {
        return anzeigename;
    }
}
