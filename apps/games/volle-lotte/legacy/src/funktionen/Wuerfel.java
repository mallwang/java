package funktionen;

public class Wuerfel {

	private static final int EINS=1, ZWEI=2, DREI=3, VIER=4, FUENF=5, SECHS=6;
	private int augenzahl;
	
	public Wuerfel(int augenzahl){
		this.augenzahl = augenzahl;
	}
	
	public String WuerfelToString(){
		switch (augenzahl){
		case EINS:
			return "Eins";
		case ZWEI:
			return "Zwei";
		case DREI:
			return "Drei";
		case VIER:
			return "Vier";
		case FUENF:
			return "Fünf";
		case SECHS:
			return "Sechs";
		}
		System.out.println("Falsche Augenzahl! : " + augenzahl);
		return "-1";
	}
	
	public int getAugenzahl(){
		return augenzahl;
	}
}
