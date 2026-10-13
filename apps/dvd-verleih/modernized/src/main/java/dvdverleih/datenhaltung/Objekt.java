package dvdverleih.datenhaltung;

public class Objekt {
	private int id = -1;
	private String art = null;
	private String titel = null;
	private String genre = null;
	private int anzahl = -1;
	private int fsk = -1;
	private int jahr = -1;
	private String hersteller;
	private String tags;
	private String originaltitel;
	private String beschreibung;
	private String preis;
	private int verfuegbar;
	private String regie;

	public int getAnzahl() {
		return anzahl;
	}

	public String getArt() {
		return art;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public int getFsk() {
		return fsk;
	}

	public String getGenre() {
		return genre;
	}

	public String getHersteller() {
		return hersteller;
	}

	public int getId() {
		return id;
	}

	public int getJahr() {
		return jahr;
	}

	public String getOriginaltitel() {
		return originaltitel;
	}

	public String getPreis() {
		return preis;
	}

	public String getRegie() {
		return regie;
	}

	public String getTags() {
		return tags;
	}

	public String getTitel() {
		return titel;
	}

	public int getVerfuegbar() {
		return verfuegbar;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void setFsk(int fsk) {
		this.fsk = fsk;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	public void setOriginaltitel(String originaltitel) {
		this.originaltitel = originaltitel;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

	public void setRegie(String regie) {
		this.regie = regie;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setVerfuegbar(int verfuegbar) {
		this.verfuegbar = verfuegbar;
	}

}
