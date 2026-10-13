package funktionen;

import java.util.ArrayList;

public class Wurf {

	private int anzahlWuerfel;
	private static Wuerfel wuerfel;
	private static int[] results;
	
	public Wurf(int anzahlWuerfel){
		
		// Würfelanzahl festlegen
		this.anzahlWuerfel = anzahlWuerfel;
		
		results = new int[anzahlWuerfel];
		
		// Würfel werfen
		wuerfeln();
	}
	
	private void wuerfeln(){
		
		for (int i=0; i<anzahlWuerfel; i++){
			int augenzahl = 0;
			augenzahl = (int) (Math.random()*6+1);
			wuerfel = new Wuerfel(augenzahl);
			results[i] = wuerfel.getAugenzahl();
		}
	}
	
	public int[] getResults(){
		
		return this.results;
	}
}
