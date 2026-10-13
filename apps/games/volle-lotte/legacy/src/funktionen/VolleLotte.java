package funktionen;

public class VolleLotte {

	private static Kartenspiel kartenspiel;
	private Spieler spieler;
	private boolean spiellaeuft;
	
	// Konstruktor
	public VolleLotte(){
		kartenspiel = new Kartenspiel();
		spiellaeuft = false;
	}
	
	public boolean getSpielStatus(){
		return spiellaeuft;
	}
	
	public void createNewPlayer(String name){
		spieler = new Spieler(name, 0);
	}
	
	public void neuesSpiel(){
		spiellaeuft = true;
	}
	
	public static void main(String[] args) {
		System.out.println("Neues Spiel wird gestartet!");
		// Nun starten wir eine Runde BlackJack
	    VolleLotte volleLotte = new VolleLotte();
	    System.out.println("Kartenanzahl: " + kartenspiel.kartenAnzahl());
	    int kartenImSpiel = kartenspiel.kartenAnzahl();
	    for (int i=0; i<kartenImSpiel; i++){
	    	System.out.println("Karte" + (i*1+1) + ": " + kartenspiel.naechsteKarte().karteToString());
	    }
		
	}
}
